package com.turnos.app.PACIENTE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientesDAO extends JpaRepository<Paciente, Long>  {

		List<Paciente> findByLegajo(String legajo);

		Paciente findOneByLegajo(String legajo);
		
}