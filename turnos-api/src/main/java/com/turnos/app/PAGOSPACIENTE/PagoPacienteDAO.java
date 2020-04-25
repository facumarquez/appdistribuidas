package com.turnos.app.PAGOSPACIENTE;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.PACIENTE.Paciente;


public interface PagoPacienteDAO extends JpaRepository<PagoPaciente, Long>  {

	Optional<PagoPaciente> findByPacienteAndMesAndAnio(Paciente paciente,int mes, int anio);
}