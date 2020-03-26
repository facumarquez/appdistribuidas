package com.turnos.app.MEDICO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicosDAO extends JpaRepository<Medico, Long>  {

		List<Medico> findByLegajo(String legajo);

		Medico findOneByLegajo(String legajo);
		
}