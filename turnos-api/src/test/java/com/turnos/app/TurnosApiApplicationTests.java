package com.turnos.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.HELPERS.TurnoHelper;

@SpringBootTest
class TurnosApiApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testGeneradorDeTurnos() {
		
		List<AgendaMedicoTurno> turnos = new ArrayList<AgendaMedicoTurno>();
		
		turnos = TurnoHelper.generarTurnos("09:00", "18:00", null);
		//Se deberian generar 36 turnos (4 turnos x hora) x 9 hs(9 a 18)...
		assertEquals(turnos.size(),36);
	}
}
