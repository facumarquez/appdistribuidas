package com.turnos.app.AGENDAPACIENTE;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turnos.app.AGENDAMEDICOTURNO.EstadoTurno;
import com.turnos.app.HELPERS.FechaHelper;
import com.turnos.app.PACIENTE.Paciente;
import com.turnos.app.PACIENTE.PacientesServiceImpl;

@RestController
@RequestMapping("/AgendaPacientes")
public class AgendaPacienteREST {	
	
    @Autowired
	private AgendaPacienteServiceImpl agendaPacienteService;
    
    @Autowired
	private PacientesServiceImpl pacienteService;
    
	
 	// POST: http://localhost:1317/AgendaPacientes
	@PostMapping
	public ResponseEntity<AgendaPaciente> crearAgendaPaciente(@RequestBody AgendaPaciente agendaPaciente){
		
		AgendaPaciente nuevaAgendaPaciente = agendaPacienteService.guardarAgenda(agendaPaciente);
		return ResponseEntity.ok(nuevaAgendaPaciente);
	}
	
    // GET: http://localhost:1317/AgendaPacientes/Pacientes/1/TurnosPendientes/
  	@RequestMapping(value="/Pacientes/{idPaciente}/TurnosPendientes")
 	public ResponseEntity<List<AgendaPaciente>> obtenerTurnosPendientesPorPaciente(@PathVariable("idPaciente") Long id){		
 		
  		Date fechaActual = new Date();
		String fechaJapones = FechaHelper.convertirFechaAFormatoJapones(fechaActual);
			
  		Optional<Paciente> paciente = pacienteService.findById(id);
 		if(paciente.isPresent() && !paciente.get().getAgendas().isEmpty()) {
 			
 			 
 			//filtros los turnos pendientes de atencion cuya fecha es mayor o igual a la del día...
 			List<AgendaPaciente> agendaTurnosPendientes = paciente.get().getAgendas().stream().filter
 										(t->t.getFechaTurno().compareTo(fechaJapones) >= 0)  //fecha del dia o mayor a hoy...
 										.collect(Collectors.toList()); 
 			
 				return ResponseEntity.ok(agendaTurnosPendientes);
 		}
 		else {
 			return ResponseEntity.noContent().build();	
 		}
 	}
  	
    //PUT: http://localhost:1317/AgendaPacientes/1/ConfirmarTurno
 	@RequestMapping(value = "/{idAgendaPaciente}/ConfirmarTurno", method = RequestMethod.PUT)
    public ResponseEntity<AgendaPaciente> confirmarTurnoPaciente(@PathVariable("idAgendaPaciente") long idAgendaPaciente) {
 		
 		 Optional<AgendaPaciente> agendaPaciente = agendaPacienteService.findById(idAgendaPaciente);
 		 if(agendaPaciente.isPresent()) {
 			agendaPaciente.get().getTurno().setEstado(EstadoTurno.CONFIRMADO);
 			return ResponseEntity.ok(agendaPacienteService.guardarAgenda(agendaPaciente.get()));
 		 }
 		 else {
			return ResponseEntity.noContent().build();
 		 }	
    }
 	
    //PUT: http://localhost:1317/AgendaPacientes/1/AnularTurno
 	@RequestMapping(value = "/{idAgendaPaciente}/AnularTurno", method = RequestMethod.PUT)
    public ResponseEntity<AgendaPaciente> anularTurnoPaciente(@PathVariable("idAgendaPaciente") long idUsuario) {
 		
 		 Optional<AgendaPaciente> agendaPaciente = agendaPacienteService.findById(idUsuario);
 		 if(agendaPaciente.isPresent()) {
 			agendaPaciente.get().getTurno().setEstado(EstadoTurno.ANULADO);
 			return ResponseEntity.ok(agendaPacienteService.guardarAgenda(agendaPaciente.get()));
 		 }
 		 else {
			return ResponseEntity.noContent().build();
 		 }	
    }
}
