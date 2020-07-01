package com.turnos.app.ESPECIALIDAD;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnos.app.MEDICO.Medico;

@RestController
@RequestMapping("/Especialidades")
public class EspecialidadesREST {	
	
	@Autowired
	private EspecialidadesDAO especialidadesDAO;
	
	// GET: http://localhost:1317/Especialidades
	@GetMapping
	public ResponseEntity<List<Especialidad>> getEspecialidades(){		
		List<Especialidad> especialidades = especialidadesDAO.findAll();
		
		List<Especialidad> especialidadesConMedicosAsignados = new ArrayList<Especialidad>();
				
		for (Especialidad especialidad : especialidades) {
			if (especialidad.getMedicos() != null && especialidad.getMedicos().size() > 0) {
				especialidadesConMedicosAsignados.add(especialidad);
			}
		}
		
		return ResponseEntity.ok(especialidadesConMedicosAsignados);
	}
	
	// GET: http://localhost:1317/Especialidades/1
	@RequestMapping(value="/{especialidadID}")
	public ResponseEntity<Especialidad> getEspecialidadByID(@PathVariable("especialidadID") Long id){		
		Optional<Especialidad> optionalEspecialidad = especialidadesDAO.findById(id);
			if(optionalEspecialidad.isPresent()) {
				return ResponseEntity.ok(optionalEspecialidad.get());
			}
			else {
				return ResponseEntity.noContent().build();
			}
	}
	
	 // GET: http://localhost:1317/Especialidades/1/Medicos
  	@RequestMapping(value="/{idEspecialidad}/Medicos")
 	public ResponseEntity<List<Medico>> obtenerMedicosPorIdEspecialidad(@PathVariable("idEspecialidad") Long id){		
 		Optional<Especialidad> especialidad = especialidadesDAO.findById(id);
 		
 		if(especialidad.isPresent()) {
 			List <Medico> medicos = especialidad.get().getMedicos();
 			 					
 			if (!medicos.isEmpty()) {
 				return ResponseEntity.ok(medicos);
 			}
 			else {
 				return ResponseEntity.noContent().build();	
 			}
 		}else {
 			return ResponseEntity.noContent().build();
 		}
 	}
}
