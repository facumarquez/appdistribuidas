package com.turnos.app.AGENDAMEDICOFECHA;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.AGENDAMEDICOTURNO.EstadoTurno;
import com.turnos.app.ESPECIALIDAD.Especialidad;

@Service
@Transactional(readOnly = false)
public class AgendaMedicoFechaServiceImpl implements AgendaMedicoFechaService{
	
	@Autowired
	AgendaMedicoFechaDAO agendaMedicoFechaDAO;
	
	
	@Transactional(readOnly = true)
	public Optional<AgendaMedicoFecha> findById(Long id) {
		return agendaMedicoFechaDAO.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<AgendaMedicoFecha> findByFecha(String fecha) {
		return agendaMedicoFechaDAO.findByFecha(fecha);
	}
	
	
	@Override
	public AgendaMedicoFecha crearFechasDeAgenda(AgendaMedicoFecha fechasAgenda) {
		return agendaMedicoFechaDAO.save(fechasAgenda);
	}

	@Override
	public List<AgendaMedicoFecha> buscarFechasPorEspecialidadYAgendaMedico(Optional<Especialidad> especialidad, Optional<AgendaMedico> agendaMedico) {
		return agendaMedicoFechaDAO.findByEspecialidadAndAgendaMedico(especialidad,agendaMedico);
	}
	
	
	public HashSet <AgendaMedicoFecha>  buscarFechasConTurnosDisponibles(List <AgendaMedicoFecha> fechas, String horario) {
		
		String horaHasta = "12:00";
		HashSet <AgendaMedicoFecha>  fechasConTurnosDisponibles = new HashSet<AgendaMedicoFecha>();
		
		List <AgendaMedicoTurno> turnosDisponibles = fechas.stream()
 				.flatMap(f -> f.getHorarios().stream())
 				.flatMap(h -> h.getTurnos().stream())
				.filter(t -> t.getEstado().equals(EstadoTurno.DISPONIBLE))
				.collect(Collectors.toList());
		
		if (horario.equals("M")) {
			turnosDisponibles = turnosDisponibles.stream().filter
											(t -> LocalTime.parse(t.getTurnoDesde()).compareTo(LocalTime.parse(horaHasta))< 0)
											.collect(Collectors.toList());
		}
		
		if (horario.equals("T")) {
			turnosDisponibles = turnosDisponibles.stream().filter
											(t -> LocalTime.parse(t.getTurnoDesde()).compareTo(LocalTime.parse(horaHasta))>= 0)
											.collect(Collectors.toList());
			}
 					
 		for (AgendaMedicoTurno turno : turnosDisponibles) {
 			fechasConTurnosDisponibles.add(turno.getAgendaMedicoHorario().getAgendaMedicoFecha());
		}
 		
 		
 		return fechasConTurnosDisponibles;
	}
}
