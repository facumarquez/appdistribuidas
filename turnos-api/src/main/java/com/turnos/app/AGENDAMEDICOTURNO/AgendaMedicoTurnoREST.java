package com.turnos.app.AGENDAMEDICOTURNO;
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

import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;
import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorarioServiceImpl;

@RestController
@RequestMapping("/AgendaMedicoTurnos")
public class AgendaMedicoTurnoREST {	
	
    @Autowired
	private AgendaMedicoTurnoServiceImpl agendaMedicoTurnoService;
    
    @Autowired
	private AgendaMedicoHorarioServiceImpl agendaMedicoHorarioService;
    
   
	
    // GET: http://localhost:1317/AgendaMedicoTurnos
    @GetMapping
	public ResponseEntity<List<AgendaMedicoTurno>> getTurnosAgendasMedico(){		
		List<AgendaMedicoTurno> agendaMedicoTurnos = agendaMedicoTurnoService.findAll();
		return ResponseEntity.ok(agendaMedicoTurnos);
	}
    
 	// GET: http://localhost:1317/AgendaMedicoTurnos/1
 	@RequestMapping(value="/{idAgendaMedicoTurno}")
	public ResponseEntity<AgendaMedicoTurno> getAgendaMedicoTurnoByID(@PathVariable("idAgendaMedicoTurno") Long id){		
		Optional<AgendaMedicoTurno> turnoAgenda = agendaMedicoTurnoService.findById(id);
		if(turnoAgenda.isPresent()) {
			return ResponseEntity.ok(turnoAgenda.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}

 	// POST: http://localhost:1317/AgendaMedicoTurnos
	@PostMapping
	public ResponseEntity<AgendaMedicoTurno> createTurnoAgendaMedico(@RequestBody AgendaMedicoTurno turnoAgenda){
		AgendaMedicoTurno nuevoTurnoAgendaMedico = agendaMedicoTurnoService.crearTurnosDeAgenda(turnoAgenda);
		return ResponseEntity.ok(nuevoTurnoAgendaMedico);
	}
	
	
	//TODO: no se si va esta llamada
 	// GET: http://localhost:1317/AgendaMedicoTurnos/2/12/15
 	@RequestMapping(value="/{idAgendaMedicoHorario}/{turnoDesde}/{turnoHasta}")
 	public ResponseEntity<AgendaMedicoTurno> getAgendaMedicoTurnoByRangoTurnoYHorarioAgenda(@PathVariable("idAgendaMedicoHorario") Long idAgendaMedicoHorario,
 																							@PathVariable("turnoDesde") String turnoDesde, 
 																							@PathVariable("turnoHasta") String turnoHasta){	

 		Optional<AgendaMedicoHorario> agendaMedicoHorario = agendaMedicoHorarioService.findById(idAgendaMedicoHorario);
 		 		
		Optional<AgendaMedicoTurno> agendaMedicoTurno= agendaMedicoTurnoService.buscarPorRangoDeTurnoYHorario(turnoDesde, turnoHasta, agendaMedicoHorario);
		if(agendaMedicoTurno.isPresent()) {
			return ResponseEntity.ok(agendaMedicoTurno.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
 	
	// DELETE: http://localhost:1317/AgendaMedicoTurnos/1
	@DeleteMapping(value="/{idAgendaMedicoTurno}")
	public ResponseEntity<Void> deleteTurno(@PathVariable("idAgendaMedicoTurno") Long id) throws Exception{
		
		agendaMedicoTurnoService.deleteByID(id);
		return ResponseEntity.ok(null);
	}
}
