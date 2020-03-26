package com.turnos.app.ESPECIALIDAD;
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
@RequestMapping("/Especialidades")
public class EspecialidadesREST {	
	
	@Autowired
	private EspecialidadesDAO especialidadesDAO;
	
	// GET: http://localhost:1317/Especialidades
	@GetMapping
	public ResponseEntity<List<Especialidad>> getEspecialidades(){		
		List<Especialidad> especialidades = especialidadesDAO.findAll();
		return ResponseEntity.ok(especialidades);
	}
	
	// GET: http://localhost:1317/Especialidades/1
	@RequestMapping(value="/{especialidadID}")
	public ResponseEntity<Especialidad> getEspecialidadByID(@PathVariable("especialidadID") Long id){		
		Optional<Especialidad> optionalEspecialidad = especialidadesDAO.findById(id);
			if(optionalEspecialidad.isPresent()) {
				return ResponseEntity.ok(optionalEspecialidad.get());
			}
			else {
				return ResponseEntity.noContent().build();
			}
	}
	
	// POST: http://localhost:1317/Especialidades/1
	@PostMapping
	public ResponseEntity<Especialidad> createMedico(@RequestBody Especialidad especialidad){
		Especialidad nuevaEspecialidad = especialidadesDAO.save(especialidad);
		return ResponseEntity.ok(nuevaEspecialidad);
	}
	
	// DELETE: http://localhost:1317/Especialidad/1
		@DeleteMapping(value="/{especialidadID}")
		//http://localhost:1317/Medicos/2
		public ResponseEntity<Void> deleteMedico(@PathVariable("especialidadID") Long id){
			especialidadesDAO.deleteById(id);
			return ResponseEntity.ok(null);
		}
	
}
