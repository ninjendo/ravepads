package com.rave.pads.util;

import com.rave.pads.common.CommonConstants;

public class ParserUtil {

	public static String NOT_APPLICABLE ="N/A";
	
	protected ParserUtil(){}
	
    public static boolean isValid(String value)
    {
    	return value != null && !value.isEmpty() && !NOT_APPLICABLE.equals(value);
    }

    public static boolean isValid(Object value)
    {
    	return value != null;
    }
    
    public static void logParseError(String header, String value){
    	System.err.println("Error parsing: " + header + CommonConstants.SPACE + value);
    }

}
