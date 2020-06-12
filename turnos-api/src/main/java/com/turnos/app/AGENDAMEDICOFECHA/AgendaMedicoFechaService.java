package com.turnos.app.AGENDAMEDICOFECHA;

import java.util.List;
import java.util.Optional;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.ESPECIALIDAD.Especialidad;

public interface AgendaMedicoFechaService {
	
	
	public AgendaMedicoFecha crearFechasDeAgenda(AgendaMedicoFecha fechasAgenda);
	
	public List<AgendaMedicoFecha> buscarFechasPorEspecialidadYAgendaMedico(Optional<Especialidad> especialidad,
																						Optional<AgendaMedico> agendaMedico);
}
