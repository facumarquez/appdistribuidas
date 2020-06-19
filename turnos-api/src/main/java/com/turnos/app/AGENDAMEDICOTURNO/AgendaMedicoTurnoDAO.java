package com.turnos.app.AGENDAMEDICOTURNO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;


public interface AgendaMedicoTurnoDAO extends JpaRepository<AgendaMedicoTurno, Long>  {

	Optional<AgendaMedicoTurno> findByTurnoDesdeAndTurnoHastaAndAgendaMedicoHorario(String turnoDesde,String turnoHasta, 
																				  Optional<AgendaMedicoHorario> agendaMedicoHorario);
}