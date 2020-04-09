package com.turnos.app.AGENDAMEDICOFECHA;

import java.util.Optional;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.ESPECIALIDAD.Especialidad;

public interface AgendaMedicoFechaService {
	
	
	public AgendaMedicoFecha crearFechasDeAgenda(AgendaMedicoFecha fechasAgenda);
	
	public Optional<AgendaMedicoFecha> buscarPorFechaYMedicoYEspecialidad(String fecha,
															Optional<AgendaMedico> agendaMedico, Optional<Especialidad> especialidad);
	
}
