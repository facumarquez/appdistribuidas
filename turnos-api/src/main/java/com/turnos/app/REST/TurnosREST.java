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

import com.turnos.app.DAO.MedicosDAO;
import com.turnos.app.ENTITIES.Medico;

@RestController
@RequestMapping("/Medicos")
public class TurnosREST {
	
	@Autowired
	private MedicosDAO medicosDAO;
	
	@GetMapping
	public ResponseEntity<List<Medico>> getMedicos(){		
		List<Medico> medicos = medicosDAO.findAll();
		return ResponseEntity.ok(medicos);
	}
	
	@RequestMapping(value="{medicoID}")
	public ResponseEntity<Medico> getMedicoByID(@PathVariable("medicoID") Long id){		
		Optional<Medico> optionalMedico = medicosDAO.findById(id);
			if(optionalMedico.isPresent()) {
				return ResponseEntity.ok(optionalMedico.get());
			}
			else {
				return ResponseEntity.noContent().build();
			}	
	}
	
	@PostMapping
	public ResponseEntity<Medico> createMedico(@RequestBody Medico medico){
		Medico nuevoMedico = medicosDAO.save(medico);
		return ResponseEntity.ok(nuevoMedico);
	}
	
}