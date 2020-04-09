package com.turnos.app.AGENDAMEDICOFECHA;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.ESPECIALIDAD.Especialidad;


public interface AgendaMedicoFechaDAO extends JpaRepository<AgendaMedicoFecha, Long>  {

	Optional<AgendaMedicoFecha> findByFechaAndAgendaMedicoAndEspecialidad(String fecha,Optional<AgendaMedico> agendaMedico,
																				Optional<Especialidad> especialidad);
	
}