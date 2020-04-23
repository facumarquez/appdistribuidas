package com.turnos.app.AGENDAMEDICOTURNO;

import java.util.Optional;

import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;

public interface AgendaMedicoTurnoService {
	
	
	public AgendaMedicoTurno crearTurnosDeAgenda(AgendaMedicoTurno fechasAgenda);
	
	public Optional<AgendaMedicoTurno> buscarPorRangoDeTurnoYHorario(String turnoDesde,String turnoHasta,
																			Optional<AgendaMedicoHorario> agendaMedicoHorario);
															
}

