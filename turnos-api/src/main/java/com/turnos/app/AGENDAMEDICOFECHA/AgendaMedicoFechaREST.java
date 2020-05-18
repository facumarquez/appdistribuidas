package com.turnos.app.AGENDAMEDICOFECHA;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.AGENDAMEDICO.AgendaMedicoServiceImpl;
import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.ESPECIALIDAD.EspecialidadServiceImpl;

@RestController
@RequestMapping("/AgendaMedicoFechas")
public class AgendaMedicoFechaREST {	
	
    @Autowired
	private AgendaMedicoFechaServiceImpl agendaMedicoFechaService;
    
    @Autowired
	private AgendaMedicoServiceImpl agendaMedicoService;
    
    @Autowired
   	private EspecialidadServiceImpl especialidadService;
    
	
//    // GET: http://localhost:1317/AgendaMedicoFechas
//    @GetMapping
//	public ResponseEntity<List<AgendaMedicoFecha>> getFechasAgendasMedico(){		
//		List<AgendaMedicoFecha> agendaMedicoFechas = agendaMedicoFechaService.findAll();
//		return ResponseEntity.ok(agendaMedicoFechas);
//	}
    
 	// GET: http://localhost:1317/AgendaMedicoFechas/1
 	@RequestMapping(value="/{idAgendaMedicoFecha}")
	public ResponseEntity<AgendaMedicoFecha> getAgendaMedicoFechasByID(@PathVariable("idAgendaMedicoFecha") Long id){		
		Optional<AgendaMedicoFecha> optionalFechaAgenda = agendaMedicoFechaService.findById(id);
		if(optionalFechaAgenda.isPresent()) {
			return ResponseEntity.ok(optionalFechaAgenda.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}

 	//TODO: se debe recibir listado.......
 	// POST: http://localhost:1317/AgendaMedicoFechas
	@PostMapping
	public ResponseEntity<AgendaMedicoFecha> crearFechaAgendaMedico(@RequestBody AgendaMedicoFecha fechaAgenda){
		AgendaMedicoFecha nuevaFechaAgendaMedico = agendaMedicoFechaService.crearFechasDeAgenda(fechaAgenda);
		return ResponseEntity.ok(nuevaFechaAgendaMedico);
	}
	
 	// GET: http://localhost:1317/AgendaMedicoFechas/aaaammdd/1/1
 	@RequestMapping(value="/{fecha}/{idAgendaMedico}/{idEspecialidad}")
 	public ResponseEntity<AgendaMedicoFecha> getAgendaMedicoFechaByMedicoYEspecialidad(@PathVariable("fecha") String fecha, 
 																					@PathVariable("idAgendaMedico") Long idAgendaMedico,
 																					@PathVariable("idEspecialidad") Long idEspecialidad){	

 		Optional<AgendaMedico> agendaMedico = agendaMedicoService.findById(idAgendaMedico);
 		Optional<Especialidad> especialidad = especialidadService.findById(idEspecialidad);
 		
		Optional<AgendaMedicoFecha> agendaMedicoFecha= agendaMedicoFechaService.buscarPorFechaYMedicoYEspecialidad(fecha, agendaMedico, especialidad);
		if(agendaMedicoFecha.isPresent()) {
			return ResponseEntity.ok(agendaMedicoFecha.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
}
