package com.rave.pads.model;

public enum LeadStatus {
	
	NEW("New Lead", "NL"),
	INITIALCONTACT("Contact intiated", "IC"),
    ACTIVE("Active Lead", "AL"),
    CLOSESUCCESS("Closed lead with profit", "CS"),
	DEAD("Dead Lead", "DL"),
    PRICE_CHANGED("Price Changed","PC"),
    EXCLUSIVE("Exclusive Bidding","EB"),
    INCLUSIVE("Bidding Open to All","IB"),
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
    private LeadStatus(final String description, String code) {
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

    public static LeadStatus getStatusByHud(String hudStatus){
    	
    	switch (hudStatus) {
		case "New Listing": return LeadStatus.NEW; 
		case "Price Reduced": return LeadStatus.PRICE_CHANGED;
		case "Extended": return LeadStatus.INCLUSIVE;
		case "Exclusive": return EXCLUSIVE;
		default:
			return ACTIVE;
		}
    } 
}
