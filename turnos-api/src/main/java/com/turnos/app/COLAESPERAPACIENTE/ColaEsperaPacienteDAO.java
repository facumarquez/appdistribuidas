package com.turnos.app.COLAESPERAPACIENTE;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.PACIENTE.Paciente;


public interface ColaEsperaPacienteDAO extends JpaRepository<ColaEsperaPaciente, Long>  {

	Optional<ColaEsperaPaciente> findByPacienteAndEspecialidad(Paciente paciente, Especialidad especialidad);
}