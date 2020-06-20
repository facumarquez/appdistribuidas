package com.turnos.app.AGENDAMEDICOFECHA;
import java.util.ArrayList;
import java.util.HashSet;
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
import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurnoServiceImpl;
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
    
    @Autowired
	private AgendaMedicoTurnoServiceImpl agendaMedicoTurnoService;
    
	
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
		
		return ResponseEntity.ok(agendaMedicoFechaService.crearFechasDeAgenda(fechasAgenda));
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
 	//DOCUMENTAR: idMedico optional
 	// GET: http://localhost:1317/AgendaMedicoFechas/1/1/1/2020/T
 	//TODO: ver que devuelva  un "nocontent" para que no rompa retrofit
 	@RequestMapping(value= "/{idEspecialidad}/{idMedico}/{mes}/{anio}/{horario}")
 	public ResponseEntity<List<AgendaMedicoFecha>> getAgendaMedicoFechasByEspecialidad_Medico_Periodo_Horario(
 																					@PathVariable("idEspecialidad") Long idEspecialidad,
 																					@PathVariable("idMedico") Long idMedico,
 																					@PathVariable("mes") int mes,
 																					@PathVariable("anio") int anio,
 																					@PathVariable("horario") String horario){	
 	 		
 		HashSet <AgendaMedicoFecha> fechasConTurnosDisponibles = new HashSet<AgendaMedicoFecha>();
 		
 		Optional<Especialidad> especialidad = especialidadService.findById(idEspecialidad);
 		
 		Optional<Medico> medico = medicoService.findById(idMedico);
 	 		
 		Optional<AgendaMedico> agendaMedico = agendaMedicoService.findByMedicoYPeriodo(medico, mes, anio);
 	 		
 		List <AgendaMedicoFecha> fechas = agendaMedicoFechaService.buscarFechasPorEspecialidadYAgendaMedico(especialidad, agendaMedico);
 	 	
 		fechasConTurnosDisponibles = agendaMedicoFechaService.buscarFechasConTurnosDisponibles(fechas, horario);
 		
 		if(!fechasConTurnosDisponibles.isEmpty()) {
 			return ResponseEntity.ok(new ArrayList<AgendaMedicoFecha>(fechasConTurnosDisponibles));
 		}
 		else {
 			return ResponseEntity.ok(new ArrayList<AgendaMedicoFecha>());
 		}
 	}
 	
 	//TODO: DOCUMENTAR: 
 	// GET: http://localhost:1317/AgendaMedicoFechas/1/1/2020/T
 	//TODO: ver que devuelva  un "nocontent" para que no rompa retrofit
 	@RequestMapping(value= "/{idEspecialidad}/{mes}/{anio}/{horario}")
 	public ResponseEntity<List<AgendaMedicoFecha>> getAgendaMedicoFechasByEspecialidad_Periodo_Horario(
 																					@PathVariable("idEspecialidad") Long idEspecialidad,
 																					@PathVariable("mes") int mes,
 																					@PathVariable("anio") int anio,
 																					@PathVariable("horario") String horario){	
 	 		
 		HashSet <AgendaMedicoFecha> fechasConTurnosDisponibles = new HashSet<AgendaMedicoFecha>();
 		
 		Optional<Especialidad> especialidad = especialidadService.findById(idEspecialidad);
 		List<AgendaMedico> agendasDeMedicos = agendaMedicoService.findByPeriodo(mes, anio);
 		List <AgendaMedicoFecha> fechas = new ArrayList<AgendaMedicoFecha>();
 		
 	 	for (AgendaMedico agenda : agendasDeMedicos) {
 	 		 fechas = agendaMedicoFechaService.buscarFechasPorEspecialidadYAgendaMedico(especialidad, Optional.of(agenda));
 	 	 	 fechasConTurnosDisponibles.addAll(agendaMedicoFechaService.buscarFechasConTurnosDisponibles(fechas, horario));
		}

 	 	if(!fechasConTurnosDisponibles.isEmpty()) {
 			return ResponseEntity.ok(new ArrayList<AgendaMedicoFecha>(fechasConTurnosDisponibles));
 		}
 		else {
 			return ResponseEntity.ok(new ArrayList<AgendaMedicoFecha>());
 		}
 	}
 	
 	// GET: http://localhost:1317/AgendaMedicoFechas/20200101/M/Especialidad/1/MedicosDisponibles
 	@RequestMapping(value="/{fecha}/{horario}/Especialidad/{idEspecialidad}/MedicosDisponibles")
 	public ResponseEntity<List<Medico>> getMedicosPorFechaDeAtencion_Especialidad_Horario(@PathVariable("fecha") String fecha,
 																				@PathVariable("horario") String horario,
 																				@PathVariable("idEspecialidad") long idEspecialidad){	

 		Optional<Especialidad> especialidad = especialidadService.findById(idEspecialidad);
 		
 		List<AgendaMedicoFecha> fechasAgenda = agendaMedicoFechaService.findByFechaAndEspecialidad(fecha,especialidad.get());
 		
 		HashSet<AgendaMedicoFecha> fechasFiltradasPorHorario = agendaMedicoFechaService.buscarFechasConTurnosDisponibles(fechasAgenda, horario);
 		
 		List<AgendaMedicoFecha> fechasFiltradas = new ArrayList<AgendaMedicoFecha>();
 		fechasFiltradas.addAll(fechasFiltradasPorHorario);
 		
 		List<Medico> medicos = medicoService.obtenerMedicosPorFecha(fechasFiltradas);
 		
 		if(medicos != null & medicos.size() > 0) {
			return ResponseEntity.ok(medicos);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
 	
 	// GET: http://localhost:1317/AgendaMedicoFechas/1/M/TurnosDisponibles
 	@RequestMapping(value="/{idAgendaMedicoFecha}/{horario}/TurnosDisponibles")
 	public ResponseEntity<List<AgendaMedicoTurno>> getTurnosDeUnaFechaYHorarioEspecifico(@PathVariable("idAgendaMedicoFecha") Long idAgendaMedicoFecha,
 																								@PathVariable("horario") String horario){	

 		Optional<AgendaMedicoFecha> agendaMedicoFecha = agendaMedicoFechaService.findById(idAgendaMedicoFecha);
 		
 		List<AgendaMedicoTurno> turnos = agendaMedicoTurnoService.obtenerTurnosPorFechaYRangoHorario(agendaMedicoFecha.get(),horario);
 		
 		if(turnos != null & turnos.size() > 0) {
			return ResponseEntity.ok(turnos);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
 	
 	// GET: http://localhost:1317/AgendaMedicoFechas/20200101/Medicos/1/TurnosDisponibles
 	@RequestMapping(value="/{fecha}/Medicos/{idMedico}/TurnosDisponibles")
 	public ResponseEntity<List<AgendaMedicoTurno>> getTurnosDeUnMedicoEspecifico(@PathVariable("fecha") String fecha,
 																					@PathVariable("idMedico") long idMedico) throws Exception{	

 		Optional<Medico> medico = medicoService.findById(idMedico);
 		
 		List<AgendaMedicoFecha> fechasAgenda = agendaMedicoFechaService.findByFecha(fecha);
 		
 		List<AgendaMedicoTurno> turnos = agendaMedicoTurnoService.obtenerTurnosPorFechaYMedico(fechasAgenda,medico.get());

 		if(turnos != null & turnos.size() > 0) {
			return ResponseEntity.ok(turnos);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
 	
	@PostMapping(path = "/Horarios")
	public ResponseEntity<List<AgendaMedicoHorario>> obtenerHorariosDeFechas(@RequestBody List<AgendaMedicoFecha> fechasAgenda){
		
		return ResponseEntity.ok(agendaMedicoFechaService.buscarHorariosPorFechas(fechasAgenda));
		
	}
}
