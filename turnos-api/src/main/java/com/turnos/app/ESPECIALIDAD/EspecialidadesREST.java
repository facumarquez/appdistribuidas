package com.turnos.app.ESPECIALIDAD;
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

@RestController
@RequestMapping("/Especialidades")
public class EspecialidadesREST {
	
	
	@Autowired
	private EspecialidadesDAO especialidadesDAO;
	
	@GetMapping
	public ResponseEntity<List<Especialidad>> getEspecialidades(){		
		List<Especialidad> especialidades = especialidadesDAO.findAll();
		return ResponseEntity.ok(especialidades);
	}
	
	@RequestMapping(value="{especialidadID}")
	public ResponseEntity<Especialidad> getEspecialidadByID(@PathVariable("especialidadID") Long id){		
		Optional<Especialidad> optionalEspecialidad = especialidadesDAO.findById(id);
			if(optionalEspecialidad.isPresent()) {
				return ResponseEntity.ok(optionalEspecialidad.get());
			}
			else {
				return ResponseEntity.noContent().build();
			}	
	}
	
	@PostMapping
	public ResponseEntity<Especialidad> createMedico(@RequestBody Especialidad especialidad){
		Especialidad nuevaEspecialidad = especialidadesDAO.save(especialidad);
		return ResponseEntity.ok(nuevaEspecialidad);
	}
	
}
