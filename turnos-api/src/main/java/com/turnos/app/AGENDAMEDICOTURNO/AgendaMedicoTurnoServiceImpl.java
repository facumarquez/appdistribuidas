package com.turnos.app.AGENDAMEDICOTURNO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;

@Service
@Transactional(readOnly = false)
public class AgendaMedicoTurnoServiceImpl implements AgendaMedicoTurnoService{
	
	@Autowired
	AgendaMedicoTurnoDAO agendaMedicoTurnoDAO;
	
	//TODO: ver mas adelante si hace falta....
	@Transactional(readOnly = false)
	public AgendaMedicoTurno guardarTurno(AgendaMedicoTurno turno) {

		return agendaMedicoTurnoDAO.save(turno);
	}
	
	@Transactional(readOnly = true)
	public List<AgendaMedicoTurno> findAll() {
		return agendaMedicoTurnoDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<AgendaMedicoTurno> findById(Long id) {
		return agendaMedicoTurnoDAO.findById(id);
	}
	
	@Override
	public AgendaMedicoTurno crearTurnosDeAgenda(AgendaMedicoTurno turnosAgenda) {
		return agendaMedicoTurnoDAO.save(turnosAgenda);
	}

	@Override
	public Optional<AgendaMedicoTurno> buscarPorRangoDeTurnoYHorario(String turnoDesde, String turnoHasta,
			Optional<AgendaMedicoHorario> agendaMedicoHorario) {
		return agendaMedicoTurnoDAO.findByTurnoDesdeAndTurnoHastaAndAgendaMedicoHorario(turnoDesde, turnoHasta, agendaMedicoHorario);
	}
	
	public ResponseEntity<Void> deleteByID(Long idAgendaMedicoTurno) throws Exception {
		
		Optional<AgendaMedicoTurno> turno = agendaMedicoTurnoDAO.findById(idAgendaMedicoTurno);
		
		if (turno.isPresent()) {
			if (turno.get().getEstado().equals(EstadoTurno.RESERVADO)) {
				throw new Exception("No se puede eliminar un turno reservado por un paciente");
			}else {
				agendaMedicoTurnoDAO.deleteById(idAgendaMedicoTurno);
			}
		}else {
			throw new Exception("Error al obtener el turno");
		}

		return ResponseEntity.ok(null);
	}
	
 	public List<AgendaMedicoTurno> obtenerTurnosPorFechaYRangoHorario(AgendaMedicoFecha agendaMedicoFecha, String rangoHorario) {
 		
 		String horaHasta = "12:00";
 		List <AgendaMedicoTurno> turnosDisponibles = new ArrayList<AgendaMedicoTurno>();
 		 		
 		for (AgendaMedicoHorario  horario : agendaMedicoFecha.getHorarios()) {

 			//traigo los turnos libres de mañana solamente....
			if (rangoHorario.equals("M")) {
				turnosDisponibles.addAll(horario.getTurnos().stream().filter
												(t -> LocalTime.parse(t.getTurnoDesde()).compareTo(LocalTime.parse(horaHasta))< 0)
												.filter(t->t.getEstado().equals(EstadoTurno.DISPONIBLE))
												.collect(Collectors.toList()));
			}
			
			//traigo los turnos libres de tarde solamente....
			if (rangoHorario.equals("T")) {
				turnosDisponibles.addAll(horario.getTurnos().stream().filter
												(t -> LocalTime.parse(t.getTurnoDesde()).compareTo(LocalTime.parse(horaHasta))>= 0)
												.filter(t->t.getEstado().equals(EstadoTurno.DISPONIBLE))
												.collect(Collectors.toList()));
			}
 		}
 			
 		//ordeno los turnos por campo desde
 		Comparator<AgendaMedicoTurno> comparadorTurnos = (AgendaMedicoTurno t1, AgendaMedicoTurno t2) -> {
 			return (t1.getTurnoDesde().compareTo(t2.getTurnoDesde()));
 		};
 		
 		Collections.sort(turnosDisponibles, comparadorTurnos);
 		
 		return turnosDisponibles;
 	}
 	
 	public List<AgendaMedicoTurno> obtenerTurnosPorFecha_Horario_Medico(List<AgendaMedicoFecha> fechasAgenda, String rangoHorario, 
 																				long idMedico,long idEspecialidad) throws Exception {
 		
 		
 		AgendaMedicoFecha fechaDelMedico;
 		List <AgendaMedicoTurno> turnosDisponibles = new ArrayList<AgendaMedicoTurno>();
 		
 		fechasAgenda = fechasAgenda.stream().filter(f->f.getAgendaMedico().getMedico().getIdUsuario().equals(idMedico))
 												.filter(f->f.getEspecialidad().getId().equals(idEspecialidad))
 															.collect(Collectors.toList());
 		
 		if (fechasAgenda.size() != 1) {
 			throw new Exception("Ocurrió un problema al obtener los turnos del médico especificado");
 		}else {
 			fechaDelMedico = fechasAgenda.get(0);
 		}
 		
 		turnosDisponibles = this.obtenerTurnosPorFechaYRangoHorario(fechaDelMedico, rangoHorario);
 		
 		//ordeno los turnos por campo desde
 		Comparator<AgendaMedicoTurno> comparadorTurnos = (AgendaMedicoTurno t1, AgendaMedicoTurno t2) -> {
 			return (t1.getTurnoDesde().compareTo(t2.getTurnoDesde()));
 		};
 		
 		Collections.sort(turnosDisponibles, comparadorTurnos);
 		
 		return turnosDisponibles;
 	}
}
