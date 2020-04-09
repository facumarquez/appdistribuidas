package com.turnos.app.ESPECIALIDAD;

import java.util.Optional;

public interface EspecialidadService {
	
	public Optional<Especialidad> findById(Long id);
	
}
