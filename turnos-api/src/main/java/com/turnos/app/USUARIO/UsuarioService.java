package com.turnos.app.USUARIO;

import java.util.Optional;

public interface UsuarioService {
	
	public Optional<Usuario> findByUserAndPass(String user, String pass);
}
