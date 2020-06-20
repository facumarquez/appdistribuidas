package com.turnos.app.PACIENTE;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Pacientes")
public class PacientesREST {	
	
    @Autowired
	private PacientesServiceImpl pacientesService;
	
    // GET: http://localhost:1317/Pacientes
    @GetMapping
	public ResponseEntity<List<Paciente>> getPacientes(){		
		List<Paciente> pacientes = pacientesService.findAll();
		return ResponseEntity.ok(pacientes);
	}

 	// GET: http://localhost:1317/Pacientes/1
 	@RequestMapping(value="/{pacienteID}")
	public ResponseEntity<Paciente> getPacienteByID(@PathVariable("pacienteID") Long id){		
		Optional<Paciente> optionalPaciente = pacientesService.findById(id);
		if(optionalPaciente.isPresent()) {
			return ResponseEntity.ok(optionalPaciente.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}
 	
	// GET: http://localhost:1317/Pacientes/doc/1
 	@RequestMapping(value="/doc/{documento}")
	public ResponseEntity<Paciente> getPacienteByDocumento(@PathVariable("documento") String dni){		
 		Optional<Paciente> paciente = pacientesService.findByDocumento(dni);
 		if(paciente.isPresent()) {
			return ResponseEntity.ok(paciente.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}
 	
 	// POST: http://localhost:1317/Pacientes
	@PostMapping
	public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente){
		Paciente nuevoPaciente = pacientesService.save(paciente);
		return ResponseEntity.ok(nuevoPaciente);
	}
	
	// DELETE: http://localhost:1317/Pacientes/1
	@DeleteMapping(value="/{pacienteID}")
	//http://localhost:1317/Pacientes/2
	public ResponseEntity<Void> deletePaciente(@PathVariable("pacienteID") Long id){
		pacientesService.deleteByID(id);
		return ResponseEntity.ok(null);
	}
	
 	// GET: http://localhost:1317/Pacientes/1/pagoAlDia/
 	@RequestMapping(value="/{idPaciente}/pagoAlDia/")
	public boolean pacienteAlDia(@PathVariable("idPaciente") Long id) throws Exception{		
		Optional<Paciente> paciente = pacientesService.findById(id);
		if(paciente.isPresent()) {
			return paciente.get().getCuotaAlDia();
		}
		else {
			throw new Exception("No se encontró el paciente ");
		}	
	}
}
