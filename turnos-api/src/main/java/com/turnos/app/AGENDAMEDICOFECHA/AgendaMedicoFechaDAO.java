package com.turnos.app.AGENDAMEDICOFECHA;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.ESPECIALIDAD.Especialidad;


public interface AgendaMedicoFechaDAO extends JpaRepository<AgendaMedicoFecha, Long>  {

	List<AgendaMedicoFecha> findByEspecialidadAndAgendaMedico(Optional<Especialidad> especialidad ,Optional<AgendaMedico> agendaMedico);
	
	List <AgendaMedicoFecha> findByFecha(String fecha);
	
	List <AgendaMedicoFecha> findByFechaAndEspecialidad(String fecha, Especialidad especialidad);
	
	Optional <AgendaMedicoFecha> findByFechaAndAgendaMedico(String fecha, AgendaMedico agendaMedico);
	
}