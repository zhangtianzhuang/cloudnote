package com.ztz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getDate(){
		return getDate(new Date());
	}
	
	public static String getDate(Date d){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(d);
	}
}
