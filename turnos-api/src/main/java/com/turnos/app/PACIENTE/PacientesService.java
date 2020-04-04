package com.turnos.app.PACIENTE;

import java.util.Optional;

public interface PacientesService {
	
	
	public Paciente save(Paciente paciente);
	
	public Optional<Paciente> findByDocumento(String dni);
	
}
