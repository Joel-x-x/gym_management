package com.gym.utilidades;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.formdev.flatlaf.json.ParseException;
import java.time.Duration;

public class FechasUtilidades {
	
	public static Calendar stringToCalendar(String fecha) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		Calendar calendar = Calendar.getInstance();
        	
    	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
		try {
			date = dateFormat.parse(fecha);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        calendar.setTime(date);
            
        return calendar;
	}
	
	public static String calendarToString(Calendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
        // Establecer la hora, los minutos y los segundos en cero
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		
		String fecha = dateFormat.format(calendar.getTime());
		
		return fecha;
	}
	
    public static LocalDateTime stringToLocalDateTime(String fecha) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        
        LocalDateTime localDateTime = LocalDateTime.parse(fecha, formatter);
        
        return localDateTime;
    }
	
    public static String obtenerTiempoRestante(LocalDateTime fechaFin) {
        LocalDateTime fechaActual = LocalDateTime.now();

        if (fechaActual.isAfter(fechaFin)) {
            return "Finalizado";
        }

        Duration diferencia = Duration.between(fechaActual, fechaFin);

        long dias = diferencia.toDays();
        long horas = diferencia.toHoursPart();
        long minutos = diferencia.toMinutesPart();

        return dias + " días " + horas + " horas " + minutos + " minutos";
    }
	
    public static String cambiarFormatoFecha(String fecha) {
    
    	if(fecha == null) {
    		return null;
    	}
    	
        String formatoEntrada = "yyyy-MM-dd HH:mm:ss";
        String formatoSalida = "EEE dd MMM yyyy HH:mm";

        SimpleDateFormat sdfEntrada = new SimpleDateFormat(formatoEntrada);
        SimpleDateFormat sdfSalida = new SimpleDateFormat(formatoSalida);

        try {
            Date date = sdfEntrada.parse(fecha);
            return sdfSalida.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
			e.printStackTrace();
		}

        return null;
    }
	
}
