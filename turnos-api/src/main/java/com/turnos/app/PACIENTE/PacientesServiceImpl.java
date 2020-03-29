package com.turnos.app.PACIENTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class PacientesServiceImpl implements PacientesService {
	
	@Autowired
	PacientesDAO pacientesDAO;
	
    @Transactional(readOnly = true)
	public List<Paciente> findByDocumento(String dni) {
    	return pacientesDAO.findByDocumento(dni);
    }    
    
	public Paciente save(Paciente paciente) {
			return pacientesDAO.save(paciente);
	}
	
	@Transactional(readOnly = true)
	public Paciente findOneByDocumento(String value) {
		return pacientesDAO.findOneByDocumento(value);
	}
	
	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		return pacientesDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Paciente> findById(Long id) {
		return pacientesDAO.findById(id);
	}
	
	public ResponseEntity<Void> deleteByID(Long pacienteID) {
		pacientesDAO.deleteById(pacienteID);
		return ResponseEntity.ok(null);
	}
}
