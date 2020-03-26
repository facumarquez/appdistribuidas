package com.turnos.app.TURNO;
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
@RequestMapping("/Turnos")
public class TurnosREST {
	
	@Autowired
	private TurnosServiceImpl turnosService;
	
	// GET: http://localhost:1317/Turnos
	@GetMapping
	public ResponseEntity<List<Turno>> getTurnos(){		
		List<Turno> turnos = turnosService.findAll();
		return ResponseEntity.ok(turnos);
	}
	
	// GET: http://localhost:1317/Turnos/1
	@RequestMapping(value="{turnoID}")
	public ResponseEntity<Turno> getTurnoByID(@PathVariable("turnoID") Long id){		
		Optional<Turno> optionalTurno = turnosService.findById(id);
			if(optionalTurno.isPresent()) {
				return ResponseEntity.ok(optionalTurno.get());
			}
			else {
				return ResponseEntity.noContent().build();
			}	
	}
	
	// POST: http://localhost:1317/Turnos
	@PostMapping
	public ResponseEntity<Turno> createTurno(@RequestBody Turno turno){
		Turno nuevoTurno = turnosService.save(turno);
		return ResponseEntity.ok(nuevoTurno);
	}	
	
	// DELETE: http://localhost:1317/Turnos/1
	@DeleteMapping(value="/{turnoID}")
	//http://localhost:1317/Turnos/2
	public ResponseEntity<Void> deleteTurno(@PathVariable("turnoID") Long id){
		turnosService.deleteByID(id);
		return ResponseEntity.ok(null);
	}
}
