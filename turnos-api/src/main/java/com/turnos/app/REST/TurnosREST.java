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

import com.turnos.app.DAO.TurnosDAO;
import com.turnos.app.ENTITIES.Turno;

@RestController
@RequestMapping("/Turnos")
public class TurnosREST {
	
	@Autowired
	private TurnosDAO turnoesDAO;
	
	@GetMapping
	public ResponseEntity<List<Turno>> getTurnos(){		
		List<Turno> turnoes = turnoesDAO.findAll();
		return ResponseEntity.ok(turnoes);
	}
	
	@RequestMapping(value="{turnoID}")
	public ResponseEntity<Turno> getTurnoByID(@PathVariable("turnoID") Long id){		
		Optional<Turno> optionalTurno = turnoesDAO.findById(id);
			if(optionalTurno.isPresent()) {
				return ResponseEntity.ok(optionalTurno.get());
			}
			else {
				return ResponseEntity.noContent().build();
			}	
	}
	
	@PostMapping
	public ResponseEntity<Turno> createTurno(@RequestBody Turno turno){
		Turno nuevoTurno = turnoesDAO.save(turno);
		return ResponseEntity.ok(nuevoTurno);
	}	
}
