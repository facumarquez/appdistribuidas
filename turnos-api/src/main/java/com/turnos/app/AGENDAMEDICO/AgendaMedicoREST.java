package com.turnos.app.AGENDAMEDICO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFechaServiceImpl;
import com.turnos.app.MEDICO.Medico;
import com.turnos.app.MEDICO.MedicosServiceImpl;

@RestController
@RequestMapping("/AgendaMedicos")
public class AgendaMedicoREST {	
	
    @Autowired
	private AgendaMedicoServiceImpl agendaMedicoService;
    
    @Autowired
	private MedicosServiceImpl medicoService;
    
    @Autowired
	private AgendaMedicoFechaServiceImpl agendaMedicoFechaService;
    
	
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
	@PostMapping(value="/{idAgendaMedico}/GenerarTurnos")
	public boolean generarTurnos(@PathVariable("idAgendaMedico") Long idAgendaMedico) throws Exception{
		//TODO: validar que el periodo sea mayor o igual al mes del sistema

		agendaMedicoService.generarTurnos(idAgendaMedico);
			
		return true;

	}
	
	// GET: http://localhost:1317/AgendaMedico/1/20200101
	 @RequestMapping(value="/{idAgendaMedico}/{fecha}")
	 public ResponseEntity<AgendaMedicoFecha> obtenerFechaEspecificaDeAgenda(@PathVariable("idAgendaMedico") long idAgendaMedico,
			 																		@PathVariable("fecha") String fecha){	

		Optional <AgendaMedico> agendaMedico = agendaMedicoService.findById(idAgendaMedico);
		
		if (agendaMedico.isPresent()){
			Optional <AgendaMedicoFecha> agendaMedicofecha = agendaMedicoFechaService.findByFechaAndAgendaMedico(fecha, agendaMedico.get());
			if(agendaMedicofecha.isPresent()) {
				return ResponseEntity.ok(agendaMedicofecha.get());
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.noContent().build();
		}
	 }
	 
	//DELETE: http://localhost:1317/AgendaMedico/1/EliminarFechasHuerfanas
	@DeleteMapping(value="/{idAgendaMedico}/EliminarFechasHuerfanas")
	public ResponseEntity<Void> eliminarFechasHuerfanas(@PathVariable("idAgendaMedico") Long idAgendaMedico){
		
		agendaMedicoService.eliminarFechasHuerfanas(idAgendaMedico);
		
		return ResponseEntity.ok(null);
	}
}
