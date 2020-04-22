package com.turnos.app.ESPECIALIDAD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
