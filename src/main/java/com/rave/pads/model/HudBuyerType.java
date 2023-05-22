package com.rave.pads.model;

public enum HudBuyerType {


	OWNEROCCUPANT("Owner Occupant"),
	INVESTOR("Investor"),
	GOODNEIGHBOR("Good Neighbor Next Door"),
	GOVTAGENCY("Government Agency"),
	NONPROFIT("Non Profit"),
	DOLLARHOMES("Dollar Homes");

	
	private String name;
	
	private HudBuyerType(final String stateName){
		this.name = stateName;
	}

	public String getDescription() {
		return name;
	}

	public void setDescription(String name) {
		this.name = name;
	}
	
	
}
