package com.turnos.app.USUARIO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioREST {	
	
    @Autowired
	private UsuarioServiceImpl usuarioService;
	
   	// GET: http://localhost:1317/Usuarios/user/pass
 	@RequestMapping(value="/{usuario}/{pass}")
	public ResponseEntity<Usuario> getUsuarioByUserAndPass(@PathVariable("usuario") String user,@PathVariable("pass") String pass){		
		Optional<Usuario> optionalUsuario = usuarioService.findByUserAndPass(user, pass);
		if(optionalUsuario.isPresent()) {
			return ResponseEntity.ok(optionalUsuario.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}
}
