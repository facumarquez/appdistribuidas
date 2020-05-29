package com.turnos.app.MEDICO;
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

import com.turnos.app.ESPECIALIDAD.Especialidad;

@RestController
@RequestMapping("/Medicos")
public class MedicosREST {	
	
    @Autowired
	private MedicosServiceImpl medicosService;
    
    // GET: http://localhost:1317/Medicos
    @GetMapping
	public ResponseEntity<List<Medico>> getMedicos(){		
		List<Medico> medicos = medicosService.findAll();
		return ResponseEntity.ok(medicos);
	}
 	// GET: http://localhost:1317/Medicos/1
 	@RequestMapping(value="/{idMedico}")
	public ResponseEntity<Medico> getMedicoByID(@PathVariable("idMedico") Long id){		
		Optional<Medico> optionalMedico = medicosService.findById(id);
		if(optionalMedico.isPresent()) {
			return ResponseEntity.ok(optionalMedico.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}
 	
 	// GET: http://localhost:1317/Medicos/1/especialidades/
 	@RequestMapping(value="/{idMedico}/especialidades")
	public ResponseEntity<List<Especialidad>> obtenerEspecialidadesPorIdMedico(@PathVariable("idMedico") Long id){		
		Optional<Medico> optionalMedico = medicosService.findById(id);
		if(optionalMedico.isPresent()) {
			if (!optionalMedico.get().getEspecialidades().isEmpty()) {
				return ResponseEntity.ok(optionalMedico.get().getEspecialidades());
			}
			else {
				return ResponseEntity.noContent().build();	
			}
		}else {
			return ResponseEntity.noContent().build();
		}
	}
 	
 	// POST: http://localhost:1317/Medicos
	@PostMapping
	public ResponseEntity<Medico> createMedico(@RequestBody Medico medico){
		Medico nuevoMedico = medicosService.save(medico);
		return ResponseEntity.ok(nuevoMedico);
	}
	
	// DELETE: http://localhost:1317/Medicos/1
	@DeleteMapping(value="/{medicoID}")
	public ResponseEntity<Void> deleteMedico(@PathVariable("medicoID") Long id){
		medicosService.deleteByID(id);
		return ResponseEntity.ok(null);
	}
}
