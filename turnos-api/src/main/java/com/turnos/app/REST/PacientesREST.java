package com.turnos.app.REST;
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
import com.turnos.app.DAO.PacientesDAO;
import com.turnos.app.ENTITIES.Paciente;

@RestController
@RequestMapping("/Pacientes")
public class PacientesREST {	
	
    @Autowired
	private PacientesDAO pacientesDAO;
	    	
 	@GetMapping
	public ResponseEntity<List<Paciente>> getPacientes(){		
		List<Paciente> pacientes = pacientesDAO.findAll();
		return ResponseEntity.ok(pacientes);
	}

	@RequestMapping(value="{legajo}")
	public ResponseEntity<Paciente> getPacienteByID(@PathVariable("pacienteID") Long id){		
		Optional<Paciente> optionalPaciente = pacientesDAO.findById(id);
		if(optionalPaciente.isPresent()) {
			return ResponseEntity.ok(optionalPaciente.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}
	
	 @RequestMapping("/getPaciente")
	    public List<Paciente> getFiltered() {
	        return pacientesDAO.findByLegajo("1");
	    }
	
	@PostMapping
	public ResponseEntity<Paciente> createPaciente(@Valid @RequestBody Paciente paciente){
		Paciente nuevoPaciente = pacientesDAO.save(paciente);
		return ResponseEntity.ok(nuevoPaciente);
	}	
}




