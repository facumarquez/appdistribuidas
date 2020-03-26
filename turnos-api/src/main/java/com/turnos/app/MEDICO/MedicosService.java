package com.turnos.app.MEDICO;

import java.util.List;

public interface MedicosService {
	
	
	public Medico save(Medico medico);
	
	public List<Medico> findByLegajo(String legajo);
	
	public Medico findOneByLegajo(String legajo);
	
}
