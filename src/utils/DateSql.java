package utils;

import java.sql.Date;
import java.util.Calendar;

public class DateSql {
	
	public static Date converteDataParaSql(java.util.Date data) {
		
		Calendar d = Calendar.getInstance();
		d.setTime(data);
		String retorno = String.valueOf(d.get(Calendar.YEAR)) + "-" +
		String.valueOf(d.get(Calendar.MONTH)+1) + "-" +
				String.valueOf(d.get(Calendar.DAY_OF_MONTH));
		
		return Date.valueOf(retorno);
	}
	
//	public static java.util.Date converteDataParaDateUtil(java.sql.Date data){
//		
//		
//	}

}
