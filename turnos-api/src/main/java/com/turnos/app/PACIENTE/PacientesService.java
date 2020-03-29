package com.turnos.app.PACIENTE;

import java.util.List;

public interface PacientesService {
	
	
	public Paciente save(Paciente paciente);
	
	public List<Paciente> findByDocumento(String dni);
	
	public Paciente findOneByDocumento(String dni);
	
}
