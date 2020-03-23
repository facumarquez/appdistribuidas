package com.turnos.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turnos.app.ENTITIES.Turno;

public interface TurnosDAO extends JpaRepository<Turno, Long> {

}