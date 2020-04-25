package com.turnos.app.AGENDAPACIENTE;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.MEDICO.Medico;
import com.turnos.app.PACIENTE.Paciente;


public interface AgendaPacienteDAO extends JpaRepository<AgendaPaciente, Long>  {

	Optional<AgendaPaciente> findByPacienteAndMedicoAndEspecialidad(Optional<Paciente> paciente, 
														Optional<Medico> medico, Optional<Especialidad> especialidad);
	
}