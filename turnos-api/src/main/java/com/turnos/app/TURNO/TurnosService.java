package com.turnos.app.TURNO;

import java.util.List;

public interface TurnosService {
	
	public Turno save(Turno turno);
	public List<Turno> findAll();
	
}
