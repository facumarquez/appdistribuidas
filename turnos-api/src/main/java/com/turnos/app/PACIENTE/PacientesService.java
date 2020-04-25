package com.turnos.app.PACIENTE;

import java.util.Optional;

import com.turnos.app.USUARIO.Usuario;

public interface PacientesService {
	
	
	public Paciente save(Paciente paciente);
	
	public Optional<Paciente> findByDocumento(String dni);
	
	public Optional<Usuario> findByUserAndPass(String user, String pass);
	
}
