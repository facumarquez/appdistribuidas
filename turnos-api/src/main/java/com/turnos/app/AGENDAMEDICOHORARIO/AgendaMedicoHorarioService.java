package com.turnos.app.AGENDAMEDICOHORARIO;

import java.util.Optional;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;

public interface AgendaMedicoHorarioService {
	
	
	public AgendaMedicoHorario crearHorariosDeAgenda(AgendaMedicoHorario horariosAgenda);
	
	public Optional<AgendaMedicoHorario> buscarPorRangoHorarioYFecha(String horaDesde,String horaHasta,
																			Optional<AgendaMedicoFecha> agendaMedicoFecha);
															
}

