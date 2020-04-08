package com.turnos.app.AGENDAMEDICO;
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

import com.turnos.app.MEDICO.Medico;
import com.turnos.app.MEDICO.MedicosServiceImpl;

@RestController
@RequestMapping("/AgendaMedicos")
public class AgendaMedicoREST {	
	
    @Autowired
	private AgendaMedicoServiceImpl agendaMedicoService;
    
    @Autowired
	private MedicosServiceImpl medicoService;
    
	
    // GET: http://localhost:1317/AgendaMedicos
    @GetMapping
	public ResponseEntity<List<AgendaMedico>> getAgendasMedico(){		
		List<AgendaMedico> agendaMedico = agendaMedicoService.findAll();
		return ResponseEntity.ok(agendaMedico);
	}
    
 	// GET: http://localhost:1317/AgendaMedicos/1
 	@RequestMapping(value="/{idAgendaMedico}")
	public ResponseEntity<AgendaMedico> getAgendaMedicoByID(@PathVariable("idAgendaMedico") Long id){		
		Optional<AgendaMedico> optionalAgendaMedico = agendaMedicoService.findById(id);
		if(optionalAgendaMedico.isPresent()) {
			return ResponseEntity.ok(optionalAgendaMedico.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}

 	// POST: http://localhost:1317/AgendaMedicos
	@PostMapping
	public ResponseEntity<AgendaMedico> createAgendaMedico(@RequestBody AgendaMedico medico){
		AgendaMedico nuevaAgendaMedico = agendaMedicoService.crearAgenda(medico);
		return ResponseEntity.ok(nuevaAgendaMedico);
	}
	
	@DeleteMapping(value="/{idAgendaMedico}")
	//http://localhost:1317/AgendaMedicos/1
	public ResponseEntity<Void> deleteAgendaMedico(@PathVariable("idAgendaMedico") Long id){
		agendaMedicoService.borrarAgendaPorID(id);
		return ResponseEntity.ok(null);
	}
	
 	// GET: http://localhost:1317/AgendaMedicos/1/1/2020
 	@RequestMapping(value="/{idMedico}/{mes}/{anio}")
 	public ResponseEntity<AgendaMedico> getAgendaMedicoByMedicoYPeriodo(@PathVariable("idMedico") Long idMedico,
																		@PathVariable("mes") int mes,@PathVariable("anio") int anio){	

 		Optional<Medico> medico = medicoService.findById(idMedico);
 		
		Optional<AgendaMedico> agendaMedico= agendaMedicoService.findByMedicoYPeriodo(medico, mes, anio);
		if(agendaMedico.isPresent()) {
			return ResponseEntity.ok(agendaMedico.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
}
