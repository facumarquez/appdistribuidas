package com.turnos.app.AGENDAMEDICO;

import java.util.Optional;

import com.turnos.app.MEDICO.Medico;

public interface AgendaMedicoService {
	
	
	public AgendaMedico crearAgenda(AgendaMedico agenda) throws Exception;
	
	public Optional<AgendaMedico> findByMedicoYPeriodo(Optional<Medico> medico, int mes,int anio);
	
}
