package com.rave.pads.model;

public enum EventType {
	
    PRICE_REDUCED("Price Reduced","PR"),
    EXCLUSIVE("Exclusive Bidding","EB"),
    BACK_IN_MARKET("Back in the Market", "BIM");

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
    private EventType(final String description, String code) {
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
