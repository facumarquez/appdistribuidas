package com.turnos.app.AGENDAMEDICO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
	
	
// 	// GET: http://localhost:1317/AgendaMedicos/1
// 	@RequestMapping(value="/{idAgendaMedico}")
//	public ResponseEntity<AgendaMedico> getAgendaMedicoByID(@PathVariable("idAgendaMedico") Long id){		
//		Optional<AgendaMedico> optionalAgendaMedico = agendaMedicoService.findById(id);
//		if(optionalAgendaMedico.isPresent()) {
//			return ResponseEntity.ok(optionalAgendaMedico.get());
//		}
//		else {
//			return ResponseEntity.noContent().build();
//		}	
//	}

 	// POST: http://localhost:1317/AgendaMedicos
	@PostMapping
	public ResponseEntity<AgendaMedico> crearAgendaMedico(@RequestBody AgendaMedico agendaMedico) throws Exception{
		
		AgendaMedico nuevaAgendaMedico = agendaMedicoService.crearAgenda(agendaMedico);
		return ResponseEntity.ok(nuevaAgendaMedico);
		
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
 	
 	// POST: http://localhost:1317/AgendaMedicos/1/ConfirmarAgenda
	@PostMapping(value="/{idAgendaMedico}/ConfirmarAgenda")
	public boolean confirmarAgendaMedico(@PathVariable("idAgendaMedico") Long idAgendaMedico){
		//TODO: validar que el periodo sea mayor o igual al mes del sistema
		try {
			agendaMedicoService.confirmarAgenda(idAgendaMedico);
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
}
