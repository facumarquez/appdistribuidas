package com.turnos.app.PACIENTE;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/Pacientes")
public class PacientesREST {	
	
    @Autowired
	private PacientesServiceImpl pacientesService;
	    	
   @GetMapping
	public ResponseEntity<List<Paciente>> getPacientes(){		
		List<Paciente> pacientes = pacientesService.findAll();
		return ResponseEntity.ok(pacientes);
	}
 	
 	@RequestMapping(value="{pacienteID}")
	public ResponseEntity<Paciente> getPacienteByID(@PathVariable("pacienteID") Long id){		
		Optional<Paciente> optionalPaciente = pacientesService.findById(id);
		if(optionalPaciente.isPresent()) {
			return ResponseEntity.ok(optionalPaciente.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}
 	
	@PostMapping
	public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente){
		Paciente nuevoPaciente = pacientesService.save(paciente);
		return ResponseEntity.ok(nuevoPaciente);
	}
}
