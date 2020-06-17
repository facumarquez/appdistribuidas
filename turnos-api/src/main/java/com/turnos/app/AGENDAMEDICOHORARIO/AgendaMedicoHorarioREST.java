package com.turnos.app.AGENDAMEDICOHORARIO;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	//TODO: sacar en el futuro
 	// POST: http://localhost:1317/AgendaMedicoHorarios
	@PostMapping
	public ResponseEntity<List<AgendaMedicoHorario>> crearHorarioAgendaMedico(@RequestBody List<AgendaMedicoHorario> horariosAgenda){
		
		return ResponseEntity.ok(agendaMedicoHorarioService.crearHorariosDeAgenda(horariosAgenda));
		
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
 	
	//DELETE: http://localhost:1317/AgendaMedicoHorarios/1
	@DeleteMapping(value="/{idAgendaMedicoHorario}")
	public ResponseEntity<Void> deleteAgendaMedicoHorario(@PathVariable("idAgendaMedicoHorario") Long id){
		agendaMedicoHorarioService.deleteByID(id);
		return ResponseEntity.ok(null);
	}
	
    //PUT: http://localhost:1317/AgendaMedicoHorarios/1/
 	@RequestMapping(value = "/{idAgendaMedicoHorario}/", method = RequestMethod.PUT)
    public ResponseEntity<AgendaMedicoHorario> modificarHorarioAgenda(@PathVariable("idAgendaMedicoHorario") long idHorario,
    																								@RequestBody AgendaMedicoHorario nuevoHorario) {
 		
 		 Optional<AgendaMedicoHorario> horario = agendaMedicoHorarioService.findById(idHorario);
 		 if(horario.isPresent()) {
 			horario.get().setHoraDesde(nuevoHorario.getHoraDesde().trim());
 			horario.get().setHoraHasta(nuevoHorario.getHoraHasta().trim());
   			//return ResponseEntity.ok(agendaMedicoHorarioService.guardarHorarioDeAgenda(horario.get()));
   			//TODO: ver como arrelgar esto....
 			return ResponseEntity.ok(null);
   		 }
   		 else {
  			return ResponseEntity.noContent().build();
   		 }	
    }
 	
 	//TODO:Documentar y probar
	//DELETE: http://localhost:1317/AgendaMedicoHorarios
	@DeleteMapping(value="/")
	public ResponseEntity<Void> deleteHorarios(@RequestBody List<AgendaMedicoHorario> horariosAgenda){
		
		agendaMedicoHorarioService.deleteHorarios(horariosAgenda);
		return ResponseEntity.ok(null);
	}
}
