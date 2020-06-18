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
import com.turnos.app.HELPERS.TurnoHelper;
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
	public ResponseEntity<AgendaPaciente> crearAgendaPaciente(@RequestBody AgendaPaciente agendaPaciente) throws Exception{
		
		if (agendaPaciente.getPaciente().getIdUsuario().equals(agendaPaciente.getMedico().getIdUsuario())) {
			throw new Exception("Paciente y médico no pueden ser la misma persona");
		}
		//TODO:validar que no tenga un turno el mismo dia y horario
		AgendaPaciente nuevaAgendaPaciente = agendaPacienteService.guardarAgenda_ReservarTurno(agendaPaciente);
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
  	
  	//TODO: se debe probar la llamada o ver si hacer un control de errores mas sofisticado.
    //PUT: http://localhost:1317/AgendaPacientes/1/ConfirmarTurno
 	@RequestMapping(value = "/{idAgendaPaciente}/ConfirmarTurno", method = RequestMethod.PUT)
    public ResponseEntity<AgendaPaciente> confirmarTurnoPaciente(@PathVariable("idAgendaPaciente") long idAgendaPaciente) throws Exception {
 		
 		 Optional<AgendaPaciente> agendaPaciente = agendaPacienteService.findById(idAgendaPaciente);
 		 if(agendaPaciente.isPresent()) {
 			if (agendaPaciente.get().getTurno().getEstado().equals(EstadoTurno.CANCELADO)){
 				 throw new Exception("El turno ha sido cancelado por el sistema debido a un problema con el profesional. "
 				 		+ "Será contactado para su reprogramación");
 			}
 			if (agendaPaciente.get().getTurno().getEstado().equals(EstadoTurno.CONFIRMADO)){
				 throw new Exception("El turno ya ha sido confirmado");
			}
 			if (agendaPaciente.get().getTurno().getEstado().equals(EstadoTurno.CANCELADO)){
				 throw new Exception("El turno no puede confirmarse porque está cancelado");
			}
 			if (agendaPaciente.get().getTurno().getEstado().equals(EstadoTurno.ANULADO)){
				 throw new Exception("El turno no puede confirmarse porque está anulado");
			}
 			
 			if (agendaPaciente.get().getTurno().getEstado().equals(EstadoTurno.RESERVADO)){
 				ResponseEntity.ok(agendaPacienteService.confirmarTurnoAgenda(agendaPaciente.get()));	
 	 		}
 		 }
 		 else {
			return ResponseEntity.noContent().build();
 		 }
		return null;
    }
 	
    //PUT: http://localhost:1317/AgendaPacientes/1/AnularTurno
 	@RequestMapping(value = "/{idAgendaPaciente}/AnularTurno", method = RequestMethod.PUT)
    public ResponseEntity<AgendaPaciente> anularTurnoPaciente(@PathVariable("idAgendaPaciente") long idUsuario) throws Exception {
 		
 		 Optional<AgendaPaciente> agendaPaciente = agendaPacienteService.findById(idUsuario);
 		 if(agendaPaciente.isPresent()) {
 			if (agendaPaciente.get().getTurno().getEstado().equals(EstadoTurno.ANULADO)){
 				throw new Exception("El turno ya ha sido anulado anteriormente");
 			}
 			
 			if (agendaPaciente.get().getTurno().getEstado().equals(EstadoTurno.CANCELADO)){
 				throw new Exception("No se puede anular un turno cancelado");
 			}
 			agendaPaciente.get().getTurno().setEstado(EstadoTurno.ANULADO);
 			
 			agendaPaciente = Optional.of(agendaPacienteService.guardarAgenda(agendaPaciente.get()));
 			
 			if(!TurnoHelper.sePuedeAnularElTurnoSinGenerarCargos(agendaPaciente.get().getFechaTurno(), agendaPaciente.get().getTurnoDesde())) {
 				throw new Exception("Se generarán cargos en su cte. cte. por no anular el turno 12hs antes");
 			}else {
 				return ResponseEntity.ok(agendaPaciente.get());
 			}
 		 }
 		 else {
			return ResponseEntity.noContent().build();
 		 }	
    }
}
