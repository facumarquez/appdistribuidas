package com.turnos.app.PACIENTE;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientesDAO extends JpaRepository<Paciente, Long>  {

	Optional<Paciente> findByDocumento(String documento);
}