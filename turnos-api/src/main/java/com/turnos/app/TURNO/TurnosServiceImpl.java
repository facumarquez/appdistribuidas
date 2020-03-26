package com.turnos.app.TURNO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class TurnosServiceImpl implements TurnosService {
	
	@Autowired
	TurnosDAO turnosDAO;	
	
	@Transactional(readOnly = true)
	public Optional<Turno> findById(Long id) {
		return turnosDAO.findById(id);
	}
	
	public ResponseEntity<Void> deleteByID(Long turnoID) {
		turnosDAO.deleteById(turnoID);
		return ResponseEntity.ok(null);
	}

	public Turno save(Turno turno) {
		return turnosDAO.save(turno);
	}

	public List<Turno> findAll() {
		return turnosDAO.findAll();
	}



}
