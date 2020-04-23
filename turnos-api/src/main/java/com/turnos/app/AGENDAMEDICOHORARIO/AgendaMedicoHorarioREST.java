package com.turnos.app.AGENDAMEDICOHORARIO;
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

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFechaServiceImpl;

@RestController
@RequestMapping("/AgendaMedicoHorarios")
public class AgendaMedicoHorarioREST {	
	
    @Autowired
	private AgendaMedicoHorarioServiceImpl agendaMedicoHorarioService;
    
    @Autowired
	private AgendaMedicoFechaServiceImpl agendaMedicoFechaService;
    
   
	
    // GET: http://localhost:1317/AgendaMedicoHorarios
    @GetMapping
	public ResponseEntity<List<AgendaMedicoHorario>> getHorariosAgendasMedico(){		
		List<AgendaMedicoHorario> agendaMedicoHorarios = agendaMedicoHorarioService.findAll();
		return ResponseEntity.ok(agendaMedicoHorarios);
	}
    
 	// GET: http://localhost:1317/AgendaMedicoHorarios/1
 	@RequestMapping(value="/{idAgendaMedicoHorario}")
	public ResponseEntity<AgendaMedicoHorario> getAgendaMedicoHorarioByID(@PathVariable("idAgendaMedicoHorario") Long id){		
		Optional<AgendaMedicoHorario> horarioAgenda = agendaMedicoHorarioService.findById(id);
		if(horarioAgenda.isPresent()) {
			return ResponseEntity.ok(horarioAgenda.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}

 	// POST: http://localhost:1317/AgendaMedicoHorarios
	@PostMapping
	public ResponseEntity<AgendaMedicoHorario> createHorarioAgendaMedico(@RequestBody AgendaMedicoHorario horarioAgenda){
		AgendaMedicoHorario nuevoHorarioAgendaMedico = agendaMedicoHorarioService.crearHorariosDeAgenda(horarioAgenda);
		return ResponseEntity.ok(nuevoHorarioAgendaMedico);
	}
	
 	// GET: http://localhost:1317/AgendaMedicoHorarios/2/12/15
 	@RequestMapping(value="/{idAgendaMedicoFecha}/{horaDesde}/{horaHasta}")
 	public ResponseEntity<AgendaMedicoHorario> getAgendaMedicoHorarioByRangoHorarioYFechaAgenda(@PathVariable("idAgendaMedicoFecha") Long idAgendaMedicoFecha,
 																								@PathVariable("horaDesde") String horaDesde, 
 																					            @PathVariable("horaHasta") String horaHasta){	

 		Optional<AgendaMedicoFecha> agendaMedicoFecha = agendaMedicoFechaService.findById(idAgendaMedicoFecha);
 		 		
		Optional<AgendaMedicoHorario> agendaMedicoHorario= agendaMedicoHorarioService.buscarPorRangoHorarioYFecha(horaDesde, horaHasta, agendaMedicoFecha);
		if(agendaMedicoHorario.isPresent()) {
			return ResponseEntity.ok(agendaMedicoHorario.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
}
