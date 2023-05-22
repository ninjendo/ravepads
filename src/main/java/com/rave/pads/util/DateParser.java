package com.rave.pads.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser extends ParserUtil{

	public static String DATE_FORMAT ="yyyy/MM/dd";
	public static String DATE_FORMAT1 ="MM/dd/yyyy";
	public static String DATE_FORMAT2 ="M/dd/yyyy";
	public static String DATE_FORMAT3 ="yyyy-MM-dd";
	public static String DATE_FORMAT4 ="M/d/yyyy";
	public static String DATE_FORMAT5 ="MM/d/yyyy";
	
	public static final String[] dateFormats = {DATE_FORMAT, DATE_FORMAT1,DATE_FORMAT2,DATE_FORMAT3,DATE_FORMAT4,DATE_FORMAT5};
	
	public static SimpleDateFormat formatter = new SimpleDateFormat();
	
    public static Date parse(String value){
    	
    	Date date = null;
    	if (isValid(value))
    	{
    		for (String pattern : dateFormats) {
    			formatter.applyPattern(pattern);
    			try {
    				date = formatter.parse(value);
				} catch (ParseException e) {
					continue;
				}
			}
    		if (date == null){
    			logParseError("Date: ", value);
    		}
    	}
        return date;
    }
}
