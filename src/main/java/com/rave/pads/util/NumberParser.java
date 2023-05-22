package com.rave.pads.util;

import com.rave.pads.common.CommonConstants;

import java.math.BigDecimal;

public class NumberParser extends ParserUtil{

	public NumberParser(){
		super();
	}
	
    public static Integer parseInteger(String value){
    	
    	Integer number = null;
    	if (isValid(value))
    	{
    		try {
    			number = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				logParseError(e.getMessage(), value);
			}
    	}
        return number;
    }
    
    public static BigDecimal parseBigDecimal(String value){
    	
    	BigDecimal number = null;
    	if (isValid(value)) 
    	{
    		value = value.replace(CommonConstants.COMMA, CommonConstants.EMPTY);
    		try {
    			number = new BigDecimal(value);
			} catch (NumberFormatException e) {
				logParseError(e.getMessage(), value);
			}
    	}
        return number;
    }
    
    public static BigDecimal parseBigDecimal(Integer value){
    	
    	BigDecimal number = null;
    	if (isValid(value)) 
    	{
    		try {
    			number = new BigDecimal(value);
			} catch (NumberFormatException e) {
				logParseError(e.getMessage(), String.valueOf(value));
			}
    	}
        return number;
    }
    
//    public static Integer parseInteger(Amount value){
//
//    	Integer number = null;
//    	if (isValid(value))
//    	{
//    		try {
//    			number = value.getValue().intValue();
//			} catch (NumberFormatException e) {
//				logParseError(e.getMessage(), value.toString());
//			}
//    	}
//        return number;
//    }
//
    public static Float parseFloat(String value){
    	
    	Float number = null;
    	if (isValid(value))
    	{
    		try {
    			number = Float.parseFloat(value);
			} catch (NumberFormatException e) {
				logParseError(e.getMessage(), value);
			}
    	}
        return number;
    }
    public static Double parseDouble(String value){
    	
    	Double number = null;
    	if (isValid(value))
    	{
    		try {
    			number = Double.parseDouble(value);
			} catch (NumberFormatException e) {
				logParseError(e.getMessage(), value);
			}
    	}
        return number;
    }
    
}
