package com.turnos.app.PACIENTE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class PacientesServiceImpl implements PacientesService {
	
	@Autowired
	PacientesDAO pacientesDAO;

	@Override
	public Paciente save(Paciente paciente) {
		return pacientesDAO.save(paciente);
	}

	@Override
	public Optional<Paciente> findByDocumento(String dni) {
		return pacientesDAO.findByDocumento(dni);
	}
	
	@Transactional(readOnly = true)
	public Optional<Paciente> findById(Long id) {
		return pacientesDAO.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		return pacientesDAO.findAll();
	}
	
	public ResponseEntity<Void> deleteByID(Long pacienteID) {
		pacientesDAO.deleteById(pacienteID);
		return ResponseEntity.ok(null);
	}
}
