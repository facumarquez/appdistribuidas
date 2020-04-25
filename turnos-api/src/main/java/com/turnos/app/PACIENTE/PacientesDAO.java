package com.turnos.app.PACIENTE;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.USUARIO.Usuario;

public interface PacientesDAO extends JpaRepository<Paciente, Long>  {

	Optional<Paciente> findByDocumento(String documento);
	
	Optional<Usuario> findByUsuarioAndPassword(String usuario,String password);
}