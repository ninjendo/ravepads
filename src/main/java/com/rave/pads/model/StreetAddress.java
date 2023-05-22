package com.rave.pads.model;

import com.rave.pads.common.CommonConstants;

import java.io.Serializable;

public class StreetAddress implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5050712096990172467L;
	private String unitNumber;
	private String streetNumber;
	private String streetPreDirection;
	private String streetName;
	private String streetType;
	private String streetPostDirection;
	private String fullStreetAddress;
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		if (unitNumber != null){
			sb.append(unitNumber).append(CommonConstants.SPACE);
		}
		if (streetNumber != null){
			sb.append(streetNumber).append(CommonConstants.SPACE);
		}
		if (streetPreDirection != null){
			sb.append(streetPreDirection).append(CommonConstants.SPACE);
		}
		if (streetName != null){
			sb.append(streetName).append(CommonConstants.SPACE);
		}
		if (streetType != null){
			sb.append(streetType).append(CommonConstants.SPACE);
		}
		if (streetPostDirection != null){
			sb.append(streetPostDirection).append(CommonConstants.SPACE);
		}
		
		String street = sb.toString().trim();
		if (!street.isEmpty()){
			return street;
		}
		else
		{
			return fullStreetAddress;
		}
	}
	
	public String toStringUrl(){
		return this.toString().replace(CommonConstants.SPACE, CommonConstants.DASH);
	}
	
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreetPreDirection() {
		return streetPreDirection;
	}
	public void setStreetPreDirection(String streetPreDirection) {
		this.streetPreDirection = streetPreDirection;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetType() {
		return streetType;
	}
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	public String getStreetPostDirection() {
		return streetPostDirection;
	}
	public void setStreetPostDirection(String streetPostDirection) {
		this.streetPostDirection = streetPostDirection;
	}


	public String getFullStreetAddress() {
		return fullStreetAddress;
	}


	public void setFullStreetAddress(String fullStreetAddress) {
		this.fullStreetAddress = fullStreetAddress;
	}
	
}
