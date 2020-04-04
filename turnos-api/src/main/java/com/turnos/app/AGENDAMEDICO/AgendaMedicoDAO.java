package com.turnos.app.AGENDAMEDICO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.MEDICO.Medico;


public interface AgendaMedicoDAO extends JpaRepository<AgendaMedico, Long>  {

	Optional<AgendaMedico> findByMedicoAndMesAndAnio(Optional<Medico> medico, int mes, int anio);
	
}