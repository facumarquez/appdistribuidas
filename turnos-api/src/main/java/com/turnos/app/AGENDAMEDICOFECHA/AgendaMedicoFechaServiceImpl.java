package com.turnos.app.AGENDAMEDICOFECHA;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;
import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorarioDAO;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.AGENDAMEDICOTURNO.EstadoTurno;
import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.HELPERS.FechaHelper;

@Service
@Transactional(readOnly = false)
public class AgendaMedicoFechaServiceImpl implements AgendaMedicoFechaService{
	
	@Autowired
	AgendaMedicoFechaDAO agendaMedicoFechaDAO;
	
	@Autowired
	AgendaMedicoHorarioDAO agendaMedicoHorarioDAO;
	
	
	@Transactional(readOnly = true)
	public Optional<AgendaMedicoFecha> findById(Long id) {
		return agendaMedicoFechaDAO.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<AgendaMedicoFecha> findByFecha(String fecha) {
		return agendaMedicoFechaDAO.findByFecha(fecha);
	}
	
	@Transactional(readOnly = true)
	public List<AgendaMedicoFecha> findByFechaAndEspecialidad(String fecha,Especialidad especialidad) {
		return agendaMedicoFechaDAO.findByFechaAndEspecialidad(fecha, especialidad);
	}
	
	@Transactional(readOnly = true)
	public Optional<AgendaMedicoFecha> findByFechaAndAgendaMedico(String fecha,AgendaMedico agendaMedico) {
		return agendaMedicoFechaDAO.findByFechaAndAgendaMedico(fecha, agendaMedico);
	}
	
	
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public List<AgendaMedicoFecha> crearFechasDeAgenda(List <AgendaMedicoFecha> fechasAgenda) throws Exception {
		
		List<AgendaMedicoFecha> fechasCreadas = new ArrayList<AgendaMedicoFecha>();
		
		for (AgendaMedicoFecha fecha : fechasAgenda) {
			Optional <AgendaMedicoFecha> fechaEspecifica  = agendaMedicoFechaDAO.findByFechaAndAgendaMedico(fecha.getFecha(), fecha.getAgendaMedico());
			
			if (!fechaEspecifica.isPresent()) {
				fechasCreadas.add(agendaMedicoFechaDAO.save(fecha));
			}else {
				if (!fecha.getEspecialidad().getId().equals(fechaEspecifica.get().getEspecialidad().getId())){
					throw new Exception("No se crearon la/las fechas porque la fecha " + 
														FechaHelper.convertirFechaAFormatoddMMyyyy(fechaEspecifica.get().getFecha()) + 
															" ha sido cargada anteriormente con la especialidad " +
															fechaEspecifica.get().getEspecialidad().getNombre() + ". Seleccione un rango correcto.");
				}
				fechasCreadas.add(fechaEspecifica.get());
			}
		}
		return fechasCreadas;
	}

	@Override
	public List<AgendaMedicoFecha> buscarFechasPorEspecialidadYAgendaMedico(Optional<Especialidad> especialidad, Optional<AgendaMedico> agendaMedico) {
		return agendaMedicoFechaDAO.findByEspecialidadAndAgendaMedico(especialidad,agendaMedico);
	}
	
	
	public HashSet <AgendaMedicoFecha>  buscarFechasConTurnosDisponibles(List <AgendaMedicoFecha> fechas, String horario) {
		
		Date fechaActual = new Date();
		String fechaJapones = FechaHelper.convertirFechaAFormatoJapones(fechaActual);
		
		String horaHasta = "12:00";
		HashSet <AgendaMedicoFecha>  fechasConTurnosDisponibles = new HashSet<AgendaMedicoFecha>();
		
		List <AgendaMedicoTurno> turnosDisponibles = fechas.stream()
 				.flatMap(f -> f.getHorarios().stream())
 				.flatMap(h -> h.getTurnos().stream())
				.filter(t -> t.getEstado().equals(EstadoTurno.DISPONIBLE))
				.collect(Collectors.toList());
		
		if (horario.equals("M")) {
			turnosDisponibles = turnosDisponibles.stream().filter
											(t -> LocalTime.parse(t.getTurnoDesde()).compareTo(LocalTime.parse(horaHasta))< 0).filter
	 										(t->t.getAgendaMedicoHorario().getAgendaMedicoFecha().getFecha().compareTo(fechaJapones) > 0)
											.collect(Collectors.toList());
		}
		
		if (horario.equals("T")) {
			turnosDisponibles = turnosDisponibles.stream().filter
											(t -> LocalTime.parse(t.getTurnoDesde()).compareTo(LocalTime.parse(horaHasta))>= 0).filter
											(t->t.getAgendaMedicoHorario().getAgendaMedicoFecha().getFecha().compareTo(fechaJapones) > 0)
											.collect(Collectors.toList());
			}
 					
 		for (AgendaMedicoTurno turno : turnosDisponibles) {
 			fechasConTurnosDisponibles.add(turno.getAgendaMedicoHorario().getAgendaMedicoFecha());
		}
 		
 		
 		return fechasConTurnosDisponibles;
	}
	
	
	public List<AgendaMedicoHorario> buscarHorariosPorFechas(List<AgendaMedicoFecha> fechas) {
		
		List<AgendaMedicoHorario> horarios = new ArrayList<AgendaMedicoHorario>();
		
		for (AgendaMedicoFecha fecha : fechas) {
			AgendaMedicoFecha fechaBuscada = agendaMedicoFechaDAO.findById(fecha.getId()).get();
			if (fechaBuscada.getHorarios() != null) {
				horarios.addAll(fechaBuscada.getHorarios());
			}
		}
		return horarios;
	}
	
	public List<AgendaMedicoTurno> buscarTurnosPorFecha(AgendaMedicoFecha fecha) {
		
		List<AgendaMedicoHorario> horarios = fecha.getHorarios();
		List<AgendaMedicoTurno> turnos = new ArrayList<AgendaMedicoTurno>();
		
		for (AgendaMedicoHorario horario : horarios) {
			turnos.addAll(horario.getTurnos());
		}
		
		//ordeno los turnos por campo desde
 		Comparator<AgendaMedicoTurno> comparadorTurnos = (AgendaMedicoTurno t1, AgendaMedicoTurno t2) -> {
 			return (t1.getTurnoDesde().compareTo(t2.getTurnoDesde()));
 		};
 		
 		Collections.sort(turnos, comparadorTurnos);
 		
		return turnos;
	}
}
