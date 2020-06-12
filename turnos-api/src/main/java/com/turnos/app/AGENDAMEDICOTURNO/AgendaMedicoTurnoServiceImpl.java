package com.turnos.app.AGENDAMEDICOTURNO;

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
import com.turnos.app.MEDICO.Medico;

@Service
@Transactional(readOnly = false)
public class AgendaMedicoTurnoServiceImpl implements AgendaMedicoTurnoService{
	
	@Autowired
	AgendaMedicoTurnoDAO agendaMedicoTurnoDAO;
	
	
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
	
	public ResponseEntity<Void> deleteByID(Long idAgendaMedicoTurno) {
		agendaMedicoTurnoDAO.deleteById(idAgendaMedicoTurno);
		return ResponseEntity.ok(null);
	}
	
 	public List<AgendaMedicoTurno> obtenerTurnosPorFecha(AgendaMedicoFecha agendaMedicoFecha) {
 			
 		List <AgendaMedicoTurno> turnosDisponibles = new ArrayList<AgendaMedicoTurno>();
 		
 		for (AgendaMedicoHorario  horario : agendaMedicoFecha.getHorarios()) {
 			//traigo los turnos libres solamente....
			turnosDisponibles.addAll(horario.getTurnos().stream().filter
						(t->t.getEstado().equals(EstadoTurno.DISPONIBLE)).collect(Collectors.toList())); 
 		}
 			
 		//ordeno los turnos por campo desde
 		Comparator<AgendaMedicoTurno> comparadorTurnos = (AgendaMedicoTurno t1, AgendaMedicoTurno t2) -> {
 			return (t1.getTurnoDesde().compareTo(t2.getTurnoDesde()));
 		};
 		
 		Collections.sort(turnosDisponibles, comparadorTurnos);
 		
 		return turnosDisponibles;
 	}
 	
 	public List<AgendaMedicoTurno> obtenerTurnosPorFechaYMedico(List<AgendaMedicoFecha> fechasAgenda, Medico medico) throws Exception {
			
 		
 		AgendaMedicoFecha fechaDelMedico;
 		List <AgendaMedicoTurno> turnosDisponibles = new ArrayList<AgendaMedicoTurno>();
 		
 		fechasAgenda.stream().filter(f->f.getAgendaMedico().getMedico().equals(medico)).collect(Collectors.toList());
 		
 		if (fechasAgenda.size() != 1) {
 			throw new Exception("Ocurrió un problema al obtener los turnos del médico especificado");
 		}else {
 			fechaDelMedico = fechasAgenda.get(0);
 		}
 		
 		for (AgendaMedicoHorario  horario : fechaDelMedico.getHorarios()) {
 			//traigo los turnos libres solamente....
			turnosDisponibles.addAll(horario.getTurnos().stream().filter
						(t->t.getEstado().equals(EstadoTurno.DISPONIBLE)).collect(Collectors.toList())); 
 		}
 			
 		//ordeno los turnos por campo desde
 		Comparator<AgendaMedicoTurno> comparadorTurnos = (AgendaMedicoTurno t1, AgendaMedicoTurno t2) -> {
 			return (t1.getTurnoDesde().compareTo(t2.getTurnoDesde()));
 		};
 		
 		Collections.sort(turnosDisponibles, comparadorTurnos);
 		
 		return turnosDisponibles;
 	}
}
