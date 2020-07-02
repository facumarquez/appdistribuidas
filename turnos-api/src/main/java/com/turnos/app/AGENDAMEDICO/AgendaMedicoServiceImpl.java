package com.turnos.app.AGENDAMEDICO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFechaDAO;
import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurnoDAO;
import com.turnos.app.HELPERS.TurnoHelper;
import com.turnos.app.MEDICO.Medico;

@Service
@Transactional(readOnly = false)
public class AgendaMedicoServiceImpl implements AgendaMedicoService {
	
	@Autowired
	AgendaMedicoDAO agendaMedicoDAO;
	
	@Autowired
	AgendaMedicoTurnoDAO agendaMedicoTurnoDAO;
	
	@Autowired
	AgendaMedicoFechaDAO agendaMedicoFechaDAO;
	
	public AgendaMedico crearAgenda(AgendaMedico agenda) throws Exception {
		
		if(TurnoHelper.rangoDeAgendaMedicoPermitida(agenda.getAnio(), agenda.getMes())) {
			return agendaMedicoDAO.save(agenda);
		}else {
			throw new Exception("Se puede generar la agenda sólo de los 2 meses siguientes al actual");
		}
	}

	@Override
	public Optional<AgendaMedico> findByMedicoYPeriodo(Optional<Medico> medico, int mes, int anio) {
		return agendaMedicoDAO.findByMedicoAndMesAndAnio(medico,mes,anio);
	}
	
	@Transactional(readOnly = true)
	public Optional<AgendaMedico> findById(Long id) {
		return agendaMedicoDAO.findById(id);
	}
	
	public ResponseEntity<Void> borrarAgendaPorID(Long idAgendaMedico) {
		agendaMedicoDAO.deleteById(idAgendaMedico);
		return ResponseEntity.ok(null);
	}
	
	@Transactional(rollbackFor = Exception.class,readOnly = true)
	public ResponseEntity<Void> generarTurnos(Long idAgendaMedico) throws Exception {
		
		List <AgendaMedicoFecha> fechas = new ArrayList<AgendaMedicoFecha>();
		List <AgendaMedicoHorario> horarios = new ArrayList<AgendaMedicoHorario>();
		List <AgendaMedicoTurno> turnos = new ArrayList<AgendaMedicoTurno>();
		
		Optional<AgendaMedico> agendaMedico = agendaMedicoDAO.findById(idAgendaMedico);
		
		if (agendaMedico.isPresent()) {
			fechas = agendaMedico.get().getFechas();
			for (AgendaMedicoFecha fecha : fechas) {
				horarios = fecha.getHorarios();
				for (AgendaMedicoHorario horario : horarios) {
					if (horario.getTurnos() == null || horario.getTurnos().size() == 0) {
						turnos = TurnoHelper.generarTurnos(horario.getHoraDesde(),horario.getHoraHasta(),horario); 
						for (AgendaMedicoTurno turno : turnos) {
							Optional<AgendaMedicoTurno> turnoExistente = agendaMedicoTurnoDAO.findByTurnoDesdeAndTurnoHastaAndAgendaMedicoHorario
																	(turno.getTurnoDesde(), turno.getTurnoHasta(), Optional.of(turno.getAgendaMedicoHorario()));
							//TODO: ver si ahace falta
							if (!turnoExistente.isPresent())
								agendaMedicoTurnoDAO.save(turno);
							else
								throw new Exception("Las franjas horarias se superponen. Operación cancelada");
						}	
					}
				}
			}
		}else {
			return ResponseEntity.ok(null);
		}
			
		return ResponseEntity.ok(null);
	}
	
	
	public List<AgendaMedico> findByPeriodo(int mes, int anio) {
		return agendaMedicoDAO.findByMesAndAnio(mes,anio);
	}
	
	@Transactional(readOnly = false)
	public Void eliminarFechasHuerfanas(Long idAgendaMedico) {
		
		Optional<AgendaMedico> agenda = agendaMedicoDAO.findById(idAgendaMedico);
		
		if (agenda.isPresent()) {
			for (AgendaMedicoFecha fecha : agenda.get().getFechas()) {
				if(fecha.getHorarios() == null || fecha.getHorarios().size() == 0) {
					agendaMedicoFechaDAO.deleteById(fecha.getId());
				}
			}
		}
		return null;
	}
}
