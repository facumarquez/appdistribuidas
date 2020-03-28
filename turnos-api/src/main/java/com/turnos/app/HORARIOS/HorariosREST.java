package com.turnos.app.HORARIOS;
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
@RequestMapping("/Horarios")
public class HorariosREST {	
	
	@Autowired
	private HorariosDAO horariosDAO;
	
	@GetMapping
	public ResponseEntity<List<Horario>> getEspecialidades(){		
		List<Horario> horarios = horariosDAO.findAll();
		return ResponseEntity.ok(horarios);
	}
	
	@RequestMapping(value="/{horarioID}")
	public ResponseEntity<Horario> getHorariosByID(@PathVariable("horarioID") Long id){		
		Optional<Horario> optionalHorario = horariosDAO.findById(id);
			if(optionalHorario.isPresent()) {
				return ResponseEntity.ok(optionalHorario.get());
			}
			else {
				return ResponseEntity.noContent().build();
			}
	}
	
	@PostMapping
	public ResponseEntity<Horario> createHorario(@RequestBody Horario horario){
		Horario nuevoHorario = horariosDAO.save(horario);
		return ResponseEntity.ok(nuevoHorario);
	}
	
		@DeleteMapping(value="/{horarioID}")
		public ResponseEntity<Void> deleteHorario(@PathVariable("horarioID") Long id){
			horariosDAO.deleteById(id);
			return ResponseEntity.ok(null);
		}
	
}
