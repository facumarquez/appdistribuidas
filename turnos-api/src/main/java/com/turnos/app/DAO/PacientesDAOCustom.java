package com.turnos.app.DAO;
import java.util.List;

import com.turnos.app.ENTITIES.*;

public interface PacientesDAOCustom {
	
	List<Paciente> findByLegajo(String legajo);

}
