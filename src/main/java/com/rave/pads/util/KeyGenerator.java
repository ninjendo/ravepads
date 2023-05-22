package com.rave.pads.util;

import com.rave.pads.model.PropertyLead;

public class KeyGenerator {

	private KeyGenerator(){}
	
	public static String getPropertyId(PropertyLead prop){
		if (prop.getFhaCaseNumber() != null){
			return prop.getFhaCaseNumber().toUpperCase();
		}
		if (prop.getZillowId() != null)
		{
			return prop.getZillowId().toString(); 
		}
		return prop.getPropertyAddress().getStreetAddress().toString().toUpperCase();
	}

	public static String getAlternateId(PropertyLead prop){
		if (prop.getFhaCaseNumber() != null){
			return prop.getFhaCaseNumber().toUpperCase();
		}
		if (prop.getZillowId() != null)
		{
			return prop.getZillowId().toString();
		}
		return null;
	}
}
