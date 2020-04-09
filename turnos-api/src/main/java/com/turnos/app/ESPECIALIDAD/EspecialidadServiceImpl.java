package com.turnos.app.ESPECIALIDAD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class EspecialidadServiceImpl implements EspecialidadService {
	
	@Autowired
	EspecialidadesDAO especialidadDAO;
	
	@Transactional(readOnly = true)
	public Optional<Especialidad> findById(Long id) {
		return especialidadDAO.findById(id);
	}
}
