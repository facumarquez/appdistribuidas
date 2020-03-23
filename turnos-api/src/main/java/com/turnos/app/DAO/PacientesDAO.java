package com.turnos.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turnos.app.ENTITIES.Paciente;

public interface PacientesDAO extends JpaRepository<Paciente, Long>, PacientesDAOCustom  {
	
		
}