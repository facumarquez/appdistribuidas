package com.turnos.app.HELPERS;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
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
}
