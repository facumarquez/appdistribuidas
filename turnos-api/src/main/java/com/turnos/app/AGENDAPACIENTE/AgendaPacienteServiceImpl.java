package com.turnos.app.AGENDAPACIENTE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurnoDAO;
import com.turnos.app.AGENDAMEDICOTURNO.EstadoTurno;
import com.turnos.app.COLAESPERAPACIENTE.ColaEsperaPaciente;
import com.turnos.app.COLAESPERAPACIENTE.ColaEsperaPacienteDAO;
import com.turnos.app.HELPERS.TurnoHelper;
import com.turnos.app.PACIENTE.Paciente;
import com.turnos.app.PACIENTE.PacientesDAO;

@Service
@Transactional(readOnly = false)
public class AgendaPacienteServiceImpl implements AgendaPacienteService {
	
	@Autowired
	AgendaPacienteDAO agendaPacienteDAO;
	
	@Autowired
	AgendaMedicoTurnoDAO agendaMedicoTurnoDAO;
	
	@Autowired
	PacientesDAO pacienteDAO;
	
	@Autowired
	ColaEsperaPacienteDAO colaEsperaPacienteDAO;
	
	@Transactional(readOnly = false)
	public Void anularTurnoAgenda(AgendaPaciente agenda) {

		agenda.getTurno().setEstado(EstadoTurno.DISPONIBLE);
		
		agendaPacienteDAO.save(agenda);
		
		agendaPacienteDAO.deleteById(agenda.getId());
		
		return null;
	}
	
	public AgendaPaciente confirmarTurnoAgenda(AgendaPaciente agenda) throws Exception {
		
		if(!TurnoHelper.sePuedeConfirmarElTurno(agenda.getFechaTurno(), agenda.getTurnoDesde())) {
			throw new Exception("El turno puede ser confirmado desde 12 hasta 1 hora antes");
		}
		agenda.getTurno().setEstado(EstadoTurno.CONFIRMADO);
		return agendaPacienteDAO.save(agenda);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public AgendaPaciente guardarAgenda_ReservarTurno(AgendaPaciente agenda) throws Exception {
		
		List<AgendaPaciente> agendasDelPaciente = new ArrayList<AgendaPaciente>();
		
		Optional<Paciente> paciente = pacienteDAO.findById(agenda.getPaciente().getIdUsuario());
		
		if (paciente.isPresent()) {
			
			agendasDelPaciente = paciente.get().getAgendas();
			
			//valido que no tenga otro turno en el mismo horario...
			List<AgendaPaciente> turnosSuperpuestos = agendasDelPaciente.stream().filter
						(t->t.getFechaTurno().equals(agenda.getFechaTurno())).filter
							(t-> t.getTurnoDesde().equals(agenda.getTurnoDesde())).filter
							(t-> t.getEstadoTurno().equals(EstadoTurno.RESERVADO) || t.getEstadoTurno().equals(EstadoTurno.CONFIRMADO))
							.collect(Collectors.toList()); 
			
 			
 			if(turnosSuperpuestos.size() >= 1) {
 				throw new Exception("Ya posee un turno en el mismo horario");
 			}
 			
 			agendasDelPaciente = paciente.get().getAgendas();
 			
 			List<AgendaPaciente> turnosMismaEspecialidad = agendasDelPaciente.stream().filter
					(t->t.getEspecialidad().getId().equals(agenda.getEspecialidad().getId())).filter
						(t-> t.getFechaTurno().equals(agenda.getFechaTurno())).filter
						(t-> t.getEstadoTurno().equals(EstadoTurno.RESERVADO) || t.getEstadoTurno().equals(EstadoTurno.CONFIRMADO))
						.collect(Collectors.toList()); 
 			
 			if(turnosMismaEspecialidad.size() >= 1) {
 				throw new Exception("Ya posee un turno de la especialidad seleccionada en el día");
 			}
 			
 			AgendaPaciente agendaPacienteNuevo = agendaPacienteDAO.save(agenda);
 			AgendaMedicoTurno turno = agendaPacienteNuevo.getTurno();
 			turno.setEstado(EstadoTurno.RESERVADO);
 			agendaMedicoTurnoDAO.save(turno);
 			agendaPacienteNuevo.setTurno(turno);
 			return agendaPacienteNuevo;
 
		}else {
			throw new Exception("Error al obtener la agenda del paciente");
		}
	}

	@Transactional(readOnly = true)
	public List<AgendaPaciente> findAll() {
		return agendaPacienteDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<AgendaPaciente> findById(Long id) {
		return agendaPacienteDAO.findById(id);
	}
	
	@Transactional(readOnly = false)
	public ColaEsperaPaciente agregarAColaDeEspera(ColaEsperaPaciente colaEspera) throws Exception {
		
		Optional<ColaEsperaPaciente> colaExistente = colaEsperaPacienteDAO.findByPacienteAndEspecialidadAndFecha(colaEspera.getPaciente(), colaEspera.getEspecialidad(),
																										colaEspera.getFecha());
		if (!colaExistente.isPresent())
			return colaEsperaPacienteDAO.save(colaEspera);
		else
			throw new Exception("Ya se encuentra en cola de espera");
	}
}
