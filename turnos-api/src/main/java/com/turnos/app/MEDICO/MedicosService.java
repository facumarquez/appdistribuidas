package com.turnos.app.MEDICO;

import java.util.List;
import java.util.Optional;

import com.turnos.app.USUARIO.Usuario;

public interface MedicosService {
	
	
	public Medico save(Medico medico);
	
	public List<Medico> findByLegajo(String legajo);
	
	public Medico findOneByLegajo(String legajo);
	
	public Optional<Usuario> findByUserAndPass(String user, String pass);
}
