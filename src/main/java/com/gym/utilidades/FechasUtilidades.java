package com.gym.utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FechasUtilidades {
	
	public Calendar stringToCalendar(String fecha) {
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
	
}
