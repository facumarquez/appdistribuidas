package com.turnos.app.AGENDAMEDICOFECHA;

import java.util.List;
import java.util.Optional;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.ESPECIALIDAD.Especialidad;

public interface AgendaMedicoFechaService {
	
	
	public AgendaMedicoFecha crearFechasDeAgenda(AgendaMedicoFecha fechasAgenda);
	
	public List<AgendaMedicoFecha> buscarPorEspecialidad_Medico_Fecha_Horario(String fecha,
															Optional<AgendaMedico> agendaMedico, Optional<Especialidad> especialidad);
	
}
