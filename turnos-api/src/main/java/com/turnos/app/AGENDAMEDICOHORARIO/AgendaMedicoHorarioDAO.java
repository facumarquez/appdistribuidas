package com.turnos.app.AGENDAMEDICOHORARIO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;


public interface AgendaMedicoHorarioDAO extends JpaRepository<AgendaMedicoHorario, Long>  {

	Optional<AgendaMedicoHorario> findByHoraDesdeAndHoraHastaAndAgendaMedicoFecha(String horaDesde,String horaHasta, 
																				  AgendaMedicoFecha agendaMedicoFecha);
	
	List<AgendaMedicoHorario> findByAgendaMedicoFecha(AgendaMedicoFecha agendaMedicoFecha);
}