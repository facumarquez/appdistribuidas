package com.turnos.app.USUARIO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnos.app.MEDICO.MedicosServiceImpl;
import com.turnos.app.PACIENTE.PacientesServiceImpl;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioREST {	
	
    @Autowired
	private MedicosServiceImpl medicoService;
    
    @Autowired
	private PacientesServiceImpl pacienteService;
	
   	// GET: http://localhost:1317/Usuarios/user/pass/tipo
 	@RequestMapping(value="/{usuario}/{pass}/{tipo}")
	public ResponseEntity<Usuario> getUsuarioByUserAndPassAndTipo(@PathVariable("usuario") String user,@PathVariable("pass") String pass,
																				@PathVariable("tipo") String tipo){		
 		Optional<Usuario> usuario = null;
 		
 		if (tipo.toLowerCase().equals("medico"))
 			usuario = medicoService.findByUserAndPass(user, pass);
 		else if (tipo.toLowerCase().equals("paciente"))
 			usuario = pacienteService.findByUserAndPass(user, pass);

 		if(usuario.isPresent())
 			return ResponseEntity.ok(usuario.get());
 		
 		else 
 			return ResponseEntity.noContent().build();
	}
}
