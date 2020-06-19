package com.turnos.app.HELPERS;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.AGENDAMEDICOTURNO.EstadoTurno;

public class TurnoHelper {
	
	public static final int INTERVALO_TURNO = 15;
	
	public static List<AgendaMedicoTurno> generarTurnos(String horaDesde,String horaHasta,AgendaMedicoHorario horario){
		
		List <AgendaMedicoTurno> turnos = new ArrayList<AgendaMedicoTurno>();
		
		String turnoDesde = horaDesde;
		
		AgendaMedicoTurno turno = generarTurno(turnoDesde,horario);
		
		String turnoHasta = turno.getTurnoHasta();
		
		turnos.add(turno);
		
		while (LocalTime.parse(turnoHasta).compareTo(LocalTime.parse(horaHasta)) < 0) {
        	turnoDesde = turnoHasta;
        	turno = generarTurno(turnoDesde, horario);
        	turnoHasta = turno.getTurnoHasta();
        	turnos.add(turno);
        }
       return turnos;
	}
	
	private static AgendaMedicoTurno generarTurno(String turnoDesde, AgendaMedicoHorario horario) {

		GregorianCalendar calendario = (GregorianCalendar) GregorianCalendar.getInstance();
		calendario.set(Calendar.HOUR_OF_DAY,LocalTime.parse(turnoDesde).getHour());
		calendario.set(Calendar.MINUTE,LocalTime.parse(turnoDesde).getMinute());
		calendario.add(GregorianCalendar.MINUTE, INTERVALO_TURNO);
		
		String turnoHasta = new SimpleDateFormat("HH:mm").format(calendario.getTime()).toString();
		
		return new AgendaMedicoTurno(turnoDesde, turnoHasta, EstadoTurno.DISPONIBLE, horario);
	}
	
	public static boolean sePuedeConfirmarElTurno(String fecha, String horario) {
		
		Date ahora = new Date();
		
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
		

		return fecha12HorasAntes.compareTo(ahora) * ahora.compareTo(fechaUnaHoraAntes) >= 0;
	}
	
	public static boolean sePuedeAnularElTurnoSinGenerarCargos(String fecha, String horario) {
		
		Date ahora = new Date();
		
		int anio = Integer.valueOf(fecha.substring(0,4));
		int mes = Integer.valueOf(fecha.substring(4,6))-1;
		int dia = Integer.valueOf(fecha.substring(6,8));
		int hora = Integer.valueOf(horario.substring(0,2));
		int minuto = Integer.valueOf(horario.substring(3,5));
		
		GregorianCalendar calendarioTurno = (GregorianCalendar) GregorianCalendar.getInstance();
		calendarioTurno.set(anio, mes, dia, hora,minuto);
		Date fechaHoraTurno = calendarioTurno.getTime();
		
		
		Calendar calendarDoceHorasAntes = Calendar.getInstance();
		calendarDoceHorasAntes.setTime(fechaHoraTurno);
	      
		calendarDoceHorasAntes.add(Calendar.HOUR, -12);

		Date fecha12HorasAntes = calendarDoceHorasAntes.getTime();
		
		return ahora.compareTo(fecha12HorasAntes) <= 0;
	}
	
	public static boolean rangoDeAgendaMedicoPermitida(int anio, int mes) {
		
		int anioAgenda = anio;
		int mesAgenda = mes;
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
				
		
		return periodoAgenda.compareTo(ahora) > 0 &&  periodoAgenda.compareTo(periodoAgendaHastaDosMeses) <= 0;
	}
}
