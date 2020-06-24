package com.turnos.app.HELPERS;

import java.util.Date;
import java.text.SimpleDateFormat;

public class FechaHelper {

	public static String convertirFechaAFormatoJapones(Date fecha) {
		
		return new SimpleDateFormat("yyyyMMdd").format(fecha);
	}
	
	public static String convertirFechaAFormatoddMMyyyy(String fecha) {
		
		return fecha.substring(6,8) + "/" + fecha.substring(4,6) + "/" + fecha.substring(0,4) ;
	}
}
