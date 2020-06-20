package com.turnos.app.MEDICO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.USUARIO.Usuario;

@Service
@Transactional(readOnly = false)
public class MedicosServiceImpl implements MedicosService {
	
	@Autowired
	MedicosDAO medicosDAO;
	
    @Transactional(readOnly = true)
	public List<Medico> findByLegajo(String legajo) {
    	return medicosDAO.findByLegajo(legajo);
    }    

	public Medico save(Medico medico) {
			return medicosDAO.save(medico);
	}
	
	@Transactional(readOnly = true)
	public Medico findOneByLegajo(String value) {
		return medicosDAO.findOneByLegajo(value);
	}
	
	@Transactional(readOnly = true)
	public List<Medico> findAll() {
		return medicosDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Medico> findById(Long id) {
		return medicosDAO.findById(id);
	}
	
	public ResponseEntity<Void> deleteByID(Long medicoID) {
		medicosDAO.deleteById(medicoID);
		return ResponseEntity.ok(null);
	}
	
	@Transactional(readOnly = true)
	public Optional<Usuario> findByUserAndPass(String user, String pass) {
		return medicosDAO.findByUsuarioAndPassword(user, pass);
	}
	
	public List<Medico> obtenerMedicosPorFecha(List<AgendaMedicoFecha> fechasAgenda) {
		
		HashSet<Medico> medicos = new HashSet<Medico>();
		List<Medico> medicosOrdenado = new ArrayList<Medico>();
	
		for (AgendaMedicoFecha fecha : fechasAgenda) {
			medicos.add(fecha.getAgendaMedico().getMedico());
		}
		medicosOrdenado.addAll(medicos);
 		
		Comparator<Medico> comparadorMedicos = (Medico m1, Medico m2) -> {
 			return (m1.getApellido().compareTo(m2.getApellido()));
 		};
 		
 		Collections.sort(medicosOrdenado,comparadorMedicos);

 		return medicosOrdenado;
 	}
}
