package com.rave.pads.model;

public enum LeadType {

	PREFORECLOSURE("Pre-foreclosure","PF"),
	
    ABSENTEE_OWNER("Absentee Owner","AO"),
    HUD("HUD Property","HUD"),
    OUT_OF_STATE_ABSENTEE("Out-of-State Absentee Owner", "AOSAO");

    /**
     * Description for the enum.
     */
    private String _description;

    /**
     * Code for the enum.
     */
    private String _code;
    
    /**
     * Instantiates a new party status.
     * 
     * @param description
     *            the description
     */
    private LeadType(final String description, String code) {
        _description = description;
        _code = code;
    }

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return _description;
    }

    public String getCode() {
        return _code;
    }
}
