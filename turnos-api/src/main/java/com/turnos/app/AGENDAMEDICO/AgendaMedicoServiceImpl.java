package com.turnos.app.AGENDAMEDICO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
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
	
	
	public AgendaMedico crearAgenda(AgendaMedico agenda) {
		return agendaMedicoDAO.save(agenda);
	}

	@Override
	public Optional<AgendaMedico> findByMedicoYPeriodo(Optional<Medico> medico, int mes, int anio) {
		return agendaMedicoDAO.findByMedicoAndMesAndAnio(medico,mes,anio);
	}
	
	@Transactional(readOnly = true)
	public List<AgendaMedico> findAll() {
		return agendaMedicoDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<AgendaMedico> findById(Long id) {
		return agendaMedicoDAO.findById(id);
	}
	
	public ResponseEntity<Void> borrarAgendaPorID(Long idAgendaMedico) {
		agendaMedicoDAO.deleteById(idAgendaMedico);
		return ResponseEntity.ok(null);
	}
	
	@Transactional(readOnly = true)
	public ResponseEntity<Void> confirmarAgenda(Long idAgendaMedico) {
		
		List <AgendaMedicoFecha> fechas = new ArrayList<AgendaMedicoFecha>();
		List <AgendaMedicoHorario> horarios = new ArrayList<AgendaMedicoHorario>();
		List <AgendaMedicoTurno> turnos = new ArrayList<AgendaMedicoTurno>();
		
		Optional<AgendaMedico> agendaMedico = agendaMedicoDAO.findById(idAgendaMedico);
		
		if (agendaMedico.isPresent()) {
			fechas = agendaMedico.get().getFechas();
			for (AgendaMedicoFecha fecha : fechas) {
				horarios = fecha.getHorarios();
				for (AgendaMedicoHorario horario : horarios) {
					turnos = TurnoHelper.generarTurnos(horario.getHoraDesde(),horario.getHoraHasta(),horario); 
					for (AgendaMedicoTurno turno : turnos) {
						agendaMedicoTurnoDAO.save(turno);
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
}
