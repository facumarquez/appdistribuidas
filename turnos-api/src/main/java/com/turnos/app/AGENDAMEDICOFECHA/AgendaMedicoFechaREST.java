package com.turnos.app.AGENDAMEDICOFECHA;
import java.util.ArrayList;
import java.util.List;
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
import com.turnos.app.MEDICO.Medico;
import com.turnos.app.MEDICO.MedicosServiceImpl;

@RestController
@RequestMapping("/AgendaMedicoFechas")
public class AgendaMedicoFechaREST {	
	
    @Autowired
	private AgendaMedicoFechaServiceImpl agendaMedicoFechaService;
    
    @Autowired
	private AgendaMedicoServiceImpl agendaMedicoService;
    
    @Autowired
   	private EspecialidadServiceImpl especialidadService;
    
    @Autowired
   	private MedicosServiceImpl medicoService;
    
	
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

 	//TODO: documentar.....
 	// POST: http://localhost:1317/AgendaMedicoFechas
	@PostMapping
	public ResponseEntity<List<AgendaMedicoFecha>> crearFechasAgendaMedico(@RequestBody List<AgendaMedicoFecha> fechasAgenda){
		
		List<AgendaMedicoFecha> fechasCreadas= new ArrayList<AgendaMedicoFecha>();
		
		for (AgendaMedicoFecha fecha : fechasAgenda) {
			fechasCreadas.add(agendaMedicoFechaService.crearFechasDeAgenda(fecha));
		}
		
		return ResponseEntity.ok(fechasCreadas);
	}
	
	
	
 	// GET: http://localhost:1317/AgendaMedicoFechas/AgendaMedicos/{idAgendaMedico}/
 	@RequestMapping(value="/AgendaMedicos/{idAgendaMedico}")
 	public ResponseEntity<List<AgendaMedicoFecha>> getFechasPorAgendaMedico(@PathVariable("idAgendaMedico") Long idAgendaMedico){	
 		Optional<AgendaMedico> agendaMedico= agendaMedicoService.findById(idAgendaMedico);
		
 		if(agendaMedico.isPresent() && !agendaMedico.get().getFechas().isEmpty() ) {
			return ResponseEntity.ok(agendaMedico.get().getFechas());
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
 	
 	//TODO: ver si incluir el filtro de fecha especifica y horario o hacer otro metodo....
 	// GET: http://localhost:1317/AgendaMedicoFechas/1/1/6/2020
 	@RequestMapping(value="/{idEspecialidad}/{idMedico}/{mes}/{anio}")
 	public ResponseEntity<List<AgendaMedicoFecha>> getAgendaMedicoFechasByEspecialidad_Medico(
 																					@PathVariable("idEspecialidad") Long idEspecialidad,
 																					@PathVariable("idMedico") Long idMedico,
 																					@PathVariable("mes") int mes,
 																					@PathVariable("anio") int anio){	
 	 		
 	 		
 		Optional<Especialidad> especialidad = especialidadService.findById(idEspecialidad);
 		Optional<Medico> medico = medicoService.findById(idMedico);
 	 		
 		Optional<AgendaMedico> agendaMedico = agendaMedicoService.findByMedicoYPeriodo(medico, mes, anio);
 	 		
 		List <AgendaMedicoFecha> fechas = agendaMedicoFechaService.buscarFechasPorEspecialidadYAgendaMedico(especialidad, agendaMedico);
 	 	     
 		if(!fechas.isEmpty()) {
 			return ResponseEntity.ok(fechas);
 		}
 		else {
 			return ResponseEntity.noContent().build();
 		}
 	}
 	
 	/*
	//TODO: a medio terminar....
 	// GET: http://localhost:1317/AgendaMedicoFechas/1/1/aaaammdd/M
 	@RequestMapping(value="/{idEspecialidad}/{idMedico}/{fecha}/{horario}")
 	public ResponseEntity<List<AgendaMedicoFecha>> getAgendaMedicoFechaByEspecialidad_Medico_Fecha_Horario(
 																					@PathVariable("idEspecialidad") Long idEspecialidad,
 																					@PathVariable("idMedico") Long idMedico,
 																					@PathVariable("fecha") String fecha,
 																					@PathVariable("horario") String horario){	

 		Optional<Especialidad> especialidad = especialidadService.findById(idEspecialidad);
 		Optional<Medico> medico = medicoService.findById(idMedico);
 		
 		//List <AgendaMedicoFecha> fechas = agendaMedicoFechaService.buscarPorEspecialidad_Medico_Fecha_Horario
 		//																								(especialidad,medico);
 	    List <AgendaMedicoFecha> fechas = null; 
 		if(!fechas.isEmpty()) {
			return ResponseEntity.ok(fechas);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
	*/
}
