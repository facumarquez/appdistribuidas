package com.turnos.app.HELPERS;

import java.util.Date;
import java.text.SimpleDateFormat;

public class FechaHelper {

	public static String convertirFechaAFormatoJapones(Date fecha) {
		
		return new SimpleDateFormat("yyyyMMdd").format(fecha);
	}
}
