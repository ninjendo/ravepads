package com.rave.pads.model;

public enum SellerType {

	HOMEOWNER("Pre-foreclosure","HO"),
	
    AGENT("Real Estate Agent","RA"),

    WHOLESALER("Wholesaler", "WS"),
	INVESTOR("Investor", "IN");
	
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
    private SellerType(final String description, String code) {
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
