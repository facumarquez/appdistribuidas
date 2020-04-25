package com.turnos.app.AGENDAPACIENTE;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.ESPECIALIDAD.EspecialidadServiceImpl;
import com.turnos.app.MEDICO.Medico;
import com.turnos.app.MEDICO.MedicosServiceImpl;
import com.turnos.app.PACIENTE.Paciente;
import com.turnos.app.PACIENTE.PacientesServiceImpl;

@RestController
@RequestMapping("/AgendaPacientes")
public class AgendaPacienteREST {	
	
    @Autowired
	private AgendaPacienteServiceImpl agendaPacienteService;
    
    @Autowired
	private PacientesServiceImpl pacienteService;
    
    @Autowired
	private MedicosServiceImpl medicoService;
    
    @Autowired
	private EspecialidadServiceImpl especialidadService;
	
//	// GET: http://localhost:1317/AgendaMedicos
//	@GetMapping 
//    public ResponseEntity<List<AgendaMedico>> getAgendasMedico(){
//    	List<AgendaMedico> agendaMedico = agendaMedicoService.findAll(); 
//    	return ResponseEntity.ok(agendaMedico); 
//	}
	 
    
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

 	// POST: http://localhost:1317/AgendaPacientes
	@PostMapping
	public ResponseEntity<AgendaPaciente> crearAgendaPaciente(@RequestBody AgendaPaciente agendaPaciente){
		
		AgendaPaciente nuevaAgendaPaciente = agendaPacienteService.crearAgenda(agendaPaciente);
		return ResponseEntity.ok(nuevaAgendaPaciente);
	}
	
//	@DeleteMapping(value="/{idAgendaMedico}")
//	//http://localhost:1317/AgendaMedicos/1
//	public ResponseEntity<Void> deleteAgendaMedico(@PathVariable("idAgendaMedico") Long id){
//		agendaMedicoService.borrarAgendaPorID(id);
//		return ResponseEntity.ok(null);
//	}
	
 	// GET: http://localhost:1317/AgendaPacientes/1/1/1
 	@RequestMapping(value="/{idPaciente}/{idMedico}/{idEspecialidad}")
 	public ResponseEntity<AgendaPaciente> obtenerAgendaPacientePorPacienteYEspecialidadYMedico
 																(@PathVariable("idPaciente") Long idPaciente,
																		@PathVariable("idMedico") Long idMedico,
																				@PathVariable("idEspecialidad") Long idEspecialidad){	

 		Optional<Paciente> paciente = pacienteService.findById(idPaciente);
 		Optional<Medico> medico = medicoService.findById(idMedico);
 		Optional<Especialidad> especialidad = especialidadService.findById(idEspecialidad);
 		
		Optional<AgendaPaciente> agendaPaciente= agendaPacienteService.findByPacienteAndMedicoAndEspecialidad
																								(paciente, medico, especialidad);
		if(agendaPaciente.isPresent()) {
			return ResponseEntity.ok(agendaPaciente.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
}
