package com.turnos.app.USUARIO;

import java.util.Optional;

public interface UsuarioService {
	
	public Usuario save(Usuario usuario);
	
	public Optional<Usuario> findByUsuario(String user);
}
