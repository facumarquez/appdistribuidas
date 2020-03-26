package com.turnos.app.PACIENTE;

import java.util.List;

public interface PacientesService {
	
	
	public Paciente save(Paciente paciente);
	
	public List<Paciente> findByLegajo(String legajo);
	
	public Paciente findOneByLegajo(String legajo);
	
}
