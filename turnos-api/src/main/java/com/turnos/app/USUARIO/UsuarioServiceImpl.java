package com.turnos.app.USUARIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	//@Transactional(readOnly = true)
	//public Optional<Usuario> findByUserAndPass(String user, String pass) {
	//	return usuarioDAO.findByUsuarioAndPassword(user, pass);
	//}
}
