package com.turnos.app.MEDICO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.USUARIO.Usuario;

public interface MedicosDAO extends JpaRepository<Medico, Long>  {

		List<Medico> findByLegajo(String legajo);

		Medico findOneByLegajo(String legajo);
		
		Optional<Usuario> findByUsuarioAndPassword(String usuario,String password);
		
}