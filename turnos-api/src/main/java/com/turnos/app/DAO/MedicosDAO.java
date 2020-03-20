package com.turnos.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnos.app.ENTITIES.Medico;

public interface MedicosDAO extends JpaRepository<Medico, Long> {

}