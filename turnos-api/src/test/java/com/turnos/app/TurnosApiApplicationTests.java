package com.turnos.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFechaServiceImpl;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.HELPERS.FechaHelper;
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
	
	@Test
	public void sePuedeConfirmarTurno() {
		
		Date ahora = new Date();
		
		String fecha = "20200630";
		String horario = "17:00";
		
		int anio = Integer.valueOf(fecha.substring(0,4));
		int mes = Integer.valueOf(fecha.substring(4,6))-1;
		int dia = Integer.valueOf(fecha.substring(6,8));
		int hora = Integer.valueOf(horario.substring(0,2));
		int minuto = Integer.valueOf(horario.substring(3,5));
		
		GregorianCalendar calendarioTurno = (GregorianCalendar) GregorianCalendar.getInstance();
		calendarioTurno.set(anio, mes, dia, hora,minuto);
		Date fechaHoraTurno = calendarioTurno.getTime();
		
		
		Calendar calendarUnaHoraAntes = Calendar.getInstance();
		Calendar calendarDoceHorasAntes = Calendar.getInstance();
		calendarUnaHoraAntes.setTime(fechaHoraTurno);
		calendarDoceHorasAntes.setTime(fechaHoraTurno);
	      
		calendarUnaHoraAntes.add(Calendar.HOUR, -1);
		calendarDoceHorasAntes.add(Calendar.HOUR, -12);

		Date fechaUnaHoraAntes = calendarUnaHoraAntes.getTime();
		Date fecha12HorasAntes = calendarDoceHorasAntes.getTime();
		

		assertEquals(true,fecha12HorasAntes.compareTo(ahora) * ahora.compareTo(fechaUnaHoraAntes) >= 0);

	}
	
	@Test
	public void rangoDeAgendaMedicoPermitida() {
		
		int anioAgenda = 2020;
		int mesAgenda = 7;
		int diaAgenda = 1;
		
		GregorianCalendar calendarioAgenda = (GregorianCalendar) GregorianCalendar.getInstance();
		calendarioAgenda.set(anioAgenda, mesAgenda -1, diaAgenda);
		Date periodoAgenda = calendarioAgenda.getTime();
		
		Date ahora = new Date();
		GregorianCalendar calendarioAhora = (GregorianCalendar) GregorianCalendar.getInstance();
		calendarioAhora.setTime(ahora);
		calendarioAhora.set(Calendar.DAY_OF_MONTH,1);
		ahora = calendarioAhora.getTime();
		
		calendarioAhora.add(Calendar.MONTH, 2);
		Date periodoAgendaHastaDosMeses = calendarioAhora.getTime(); 

		assertEquals(true, periodoAgenda.compareTo(ahora) > 0 &&  periodoAgenda.compareTo(periodoAgendaHastaDosMeses) <= 0 );
		
	}
	
	@Test
	public void testFechaJaponesAddMMyyyy() {
		
		String  fechaFormateada = FechaHelper.convertirFechaAFormatoddMMyyyy("20200622");

		assertEquals(fechaFormateada, "22/06/2020");
		
	}
	
	@Test
	public void puedeModificarFechaAgenda() throws Exception {
		
		AgendaMedicoFechaServiceImpl agendaMedicoFechaService = new AgendaMedicoFechaServiceImpl();
		
		AgendaMedico agendaMedico = new AgendaMedico();
		
		agendaMedico.setAnio(2020);
		agendaMedico.setMes(7);
				
		boolean res = agendaMedicoFechaService.puedeModificarFechaAgenda(agendaMedico, "20200708");
		
		assertEquals(true, res);
		
	}
	
	@Test
	public void compararHorarios() throws ParseException {
		
		String hora1 = "09:00"; 
		String hora2 = "10:00";
		assertEquals(true, (hora1.compareTo(hora2) < 0));
		
	}
}
