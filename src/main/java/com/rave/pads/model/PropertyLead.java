package com.rave.pads.model;

import com.fasterxml.classmate.GenericType;
import com.rave.pads.exception.MissingDataException;
import com.rave.pads.util.FilterAttributes;
import com.rave.pads.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyLead implements Comparable<PropertyLead>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String alternateId;

	private LeadStatus status;
	private LeadType primaryLeadType;
	private LeadType secondaryLeadType;
	
	private Seller seller;
	
	private PostalAddress propertyAddress;
//
//	private SimpleProperty zillowProperty;
	
	private BigDecimal longitude;
	private BigDecimal latitude;
	
	//zillow attributes
	private Long zillowId;
	private String zillowLinkHomeDetails;
	private BigDecimal zillowMarketValue;
	
	private BigDecimal mortgageBalance;
	private BigDecimal mortgagePayment;
	private Date mortgageOriginationDate;
	private String mortgageType;
	private Float mortgageRate;
	private String mortgageLender;
	private String loanAttorneyName;
	private String loanAttorneyContact;
	private Date auctionDate;
	
	private BigDecimal annualTax;
	private BigDecimal assessedValue;
	private Integer assessedValueYear;
	private BigDecimal marketValue;
	private BigDecimal lastPurchasePrice;
	private Date lastPurchaseDate;
	private Date lastMarketRecDate;
	private Date lastSettlementDate;
	private BigDecimal rentEstimate;
	
	private String subdivision;
	private String zoning;
	private String county;
	
	private Integer yearBuilt;
	private Integer bedrooms;
	private Float bathrooms;
	private Integer halfBaths;
	private Float squareFootage;
	private Double lotSize;
	private String stories;
	private String use;
	
	private String remarks;
	private BigDecimal listPrice;
	private Date listDate;
	//HUD attributes
	
	/**
	 * The Net to HUD based on Market value
	 */
	private BigDecimal netToHud;
	
	/**
	 * Minimum Amount that HUD will accept
	 */
	private BigDecimal counteredNetToHud;
	
	private String purchaserType;
	private Date bidSubmitDate;
	private Date bidAcceptanceDate;
	private String fhaCaseNumber;
	private String fhaFinancing;
	private String hudUrl;
	private String hudStatus;
	private String hudListingPeriod;
	private Date hudBidOpenDate;
	private long daysInTheMarket;
	
	List<Event> eventHistory;
	List<PropertyLead> comparables;
	
	boolean isMailAddressFixed;
	String oldMailStreetAddress;
	boolean isOutOfGAState = false;
	
	List<StatusHistory> history;
	
	public static GenericType<List<PropertyLead>> LIST_TYPE = new GenericType<List<PropertyLead>>(){};
	
	
	
	public String getZillowLinkHomeDetails() {
		return zillowLinkHomeDetails;
	}

	public void setZillowLinkHomeDetails(String zillowLinkHomeDetails) {
		this.zillowLinkHomeDetails = zillowLinkHomeDetails;
	}
//
//	public SimpleProperty getZillowProperty() {
//		return zillowProperty;
//	}
//
//	public void setZillowProperty(SimpleProperty zillowProperty) {
//		this.zillowProperty = zillowProperty;
//	}

	public Boolean isNonIndividualOwner(){
		return false;
	}

	public Boolean isCandidateForExclusion(){
		return false;
	}
//TODO: need proper method definition
	public Boolean hasHighEquity(){
		return true;
	}

	public Boolean hasPotential2ndMortgage(){
		return "2ND".equals(this.mortgageType);
	}

	public Boolean isExcluded(){
		// check filter for zip, city, market price
		return false;
	}

	public LeadType getPrimaryLeadType() {
		return primaryLeadType;
	}

	public void setPrimaryLeadType(LeadType primaryLeadType) {
		this.primaryLeadType = primaryLeadType;
	}

	public LeadType getSecondaryLeadType() {
		return secondaryLeadType;
	}

	public void setSecondaryLeadType(LeadType secondaryLeadType) {
		this.secondaryLeadType = secondaryLeadType;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public PostalAddress getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(PostalAddress propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public BigDecimal getMortgageBalance() {
		return mortgageBalance;
	}

	public void setMortgageBalance(BigDecimal mortgageBalance) {
		this.mortgageBalance = mortgageBalance;
	}

	public Date getMortgageOriginationDate() {
		return mortgageOriginationDate;
	}

	public void setMortgageOriginationDate(Date mortgageOriginationDate) {
		this.mortgageOriginationDate = mortgageOriginationDate;
	}

	public String getMortgageType() {
		return mortgageType;
	}

	public void setMortgageType(String mortgageType) {
		this.mortgageType = mortgageType;
	}

	public String getMortgageLender() {
		return mortgageLender;
	}

	public void setMortgageLender(String mortgageLender) {
		this.mortgageLender = mortgageLender;
	}

	public String getLoanAttorneyName() {
		return loanAttorneyName;
	}

	public void setLoanAttorneyName(String loanAttorneyName) {
		this.loanAttorneyName = loanAttorneyName;
	}

	public String getLoanAttorneyContact() {
		return loanAttorneyContact;
	}

	public void setLoanAttorneyContact(String loanAttorneyContact) {
		this.loanAttorneyContact = loanAttorneyContact;
	}

	public Date getAuctionDate() {
		return auctionDate;
	}

	public void setAuctionDate(Date auctionDate) {
		this.auctionDate = auctionDate;
	}

	public BigDecimal getAssessedValue() {
		return assessedValue;
	}

	public void setAssessedValue(BigDecimal assessedValue) {
		this.assessedValue = assessedValue;
	}

	public Date getLastPurchaseDate() {
		return lastPurchaseDate;
	}

	public void setLastPurchaseDate(Date lastPurchaseDate) {
		this.lastPurchaseDate = lastPurchaseDate;
	}

	public Date getLastMarketRecDate() {
		return lastMarketRecDate;
	}

	public void setLastMarketRecDate(Date lastMarketRecDate) {
		this.lastMarketRecDate = lastMarketRecDate;
	}

	public Date getLastSettlementDate() {
		return lastSettlementDate;
	}

	public void setLastSettlementDate(Date lastSettlementDate) {
		this.lastSettlementDate = lastSettlementDate;
	}

	public String getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}

	public String getZoning() {
		return zoning;
	}

	public void setZoning(String zoning) {
		this.zoning = zoning;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Integer getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(Integer yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public Integer getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(Integer bedrooms) {
		this.bedrooms = bedrooms;
	}

	public Float getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(Float bathrooms) {
		this.bathrooms = bathrooms;
	}

	public Integer getHalfBaths() {
		return halfBaths;
	}

	public void setHalfBaths(Integer halfBaths) {
		this.halfBaths = halfBaths;
	}

	public Float getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(Float squareFootage) {
		this.squareFootage = squareFootage;
	}

	public Double getLotSize() {
		return lotSize;
	}

	public void setLotSize(Double lotSize) {
		this.lotSize = lotSize;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<PropertyLead> getComparables() {
		return comparables;
	}

	public void setComparables(List<PropertyLead> comparables) {
		this.comparables = comparables;
	}

	public List<Event> getEventHistory() {
		return eventHistory;
	}

	public void setEventHistory(List<Event> eventHistory) {
		this.eventHistory = eventHistory;
	}

	public LeadStatus getStatus() {
		return status;
	}

	public void setStatus(LeadStatus status) {
		this.status = status;
	}

	public Float getMortgageRate() {
		return mortgageRate;
	}

	public void setMortgageRate(Float mortgageRate) {
		this.mortgageRate = mortgageRate;
	}

	public String getStories() {
		return stories;
	}

	public void setStories(String stories) {
		this.stories = stories;
	}

	public String getPurchaserType() {
		return purchaserType;
	}

	public void setPurchaserType(String purchaserType) {
		this.purchaserType = purchaserType;
	}

	public Date getBidSubmitDate() {
		return bidSubmitDate;
	}

	public void setBidSubmitDate(Date bidSubmitDate) {
		this.bidSubmitDate = bidSubmitDate;
	}

	public Date getBidAcceptanceDate() {
		return bidAcceptanceDate;
	}

	public void setBidAcceptanceDate(Date bidAcceptanceDate) {
		this.bidAcceptanceDate = bidAcceptanceDate;
	}

	public String getFhaCaseNumber() {
		return fhaCaseNumber;
	}

	public void setFhaCaseNumber(String fhaCaseNumber) {
		this.fhaCaseNumber = fhaCaseNumber;
	}

	public String getHudUrl() {
		return hudUrl;
	}

	public void setHudUrl(String hudUrl) {
		this.hudUrl = hudUrl;
	}

	public String getFhaFinancing() {
		return fhaFinancing;
	}

	public void setFhaFinancing(String fhaFinancing) {
		this.fhaFinancing = fhaFinancing;
	}

	public String getHudStatus() {
		return hudStatus;
	}

	public void setHudStatus(String hudStatus) {
		this.hudStatus = hudStatus;
	}


	public Long getZillowId() {
		return zillowId;
	}

	public void setZillowId(Long zillowId) {
		this.zillowId = zillowId;
	}


	public Integer getAssessedValueYear() {
		return assessedValueYear;
	}

	public void setAssessedValueYear(Integer assessedValueYear) {
		this.assessedValueYear = assessedValueYear;
	}


	public BigDecimal getAnnualTax() {
		return annualTax;
	}

	public void setAnnualTax(BigDecimal annualTax) {
		this.annualTax = annualTax;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}


	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}


	public BigDecimal getMarketValue() {
		if (marketValue != null && marketValue.intValue() > 0){
			return marketValue;
		}
		return this.zillowMarketValue;
	}

	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	public BigDecimal getLastPurchasePrice() {
		return lastPurchasePrice;
	}

	public void setLastPurchasePrice(BigDecimal lastPurchasePrice) {
		this.lastPurchasePrice = lastPurchasePrice;
	}

	public BigDecimal getRentEstimate() {
		return rentEstimate;
	}

	public void setRentEstimate(BigDecimal rentEstimate) {
		this.rentEstimate = rentEstimate;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getNetToHud() {
		return netToHud;
	}

	public void setNetToHud(BigDecimal netToHud) {
		this.netToHud = netToHud;
	}


	public BigDecimal getMortgagePayment() {
		return mortgagePayment;
	}

	public void setMortgagePayment(BigDecimal mortgagePayment) {
		this.mortgagePayment = mortgagePayment;
	}


	public static Comparator<PropertyLead> PropertyAddressComparator = new Comparator<PropertyLead>() {
        public int compare(PropertyLead ftinfo1, PropertyLead ftinfo2) {
        	
    		int i = ftinfo1.county.compareTo(ftinfo2.getCounty());
            if (i != 0) return i;

            return ftinfo1.getPropertyAddress().compareTo(ftinfo2.getPropertyAddress());
        }
    };
	
    public static Comparator<PropertyLead> PropertyEquityComparator = new Comparator<PropertyLead>() {
        public int compare(PropertyLead ftinfo1, PropertyLead ftinfo2) {
        	
    		int i = ftinfo1.county.compareTo(ftinfo2.getCounty());
            if (i != 0) return i;

            return ftinfo1.getPropertyAddress().compareTo(ftinfo2.getPropertyAddress());
        }
    };

	public boolean isMailAddressFixed() {
		return isMailAddressFixed;
	}

	public void setMailAddressFixed(boolean isMailAddressFixed) {
		this.isMailAddressFixed = isMailAddressFixed;
	}

	public String getOldMailStreetAddress() {
		return oldMailStreetAddress;
	}

	public void setOldMailStreetAddress(String oldMailStreetAddress) {
		this.oldMailStreetAddress = oldMailStreetAddress;
	}

	public boolean isOutOfGAState() {
		if (this.getSeller() != null && this.getSeller().getMailingAddress() != null
				&& this.getSeller().getMailingAddress().getZipCode() != null){
			try 
			{
				int zip = Integer.parseInt(this.getSeller().getMailingAddress().getZipCode());
				isOutOfGAState = zip < 30000 && zip > 40000;
			} catch (NumberFormatException e) {
				System.err.println("Zip code is not a digit : '" + this.getSeller().getMailingAddress().getZipCode() + ". " + e.getMessage());
			}
		}
		return isOutOfGAState;
	}

	public BigDecimal getEquity(BigDecimal _purchasePrice) 
	{
		if (this.getMarketValue() != null){
			if (_purchasePrice != null){
				return this.getMarketValue().subtract(_purchasePrice);
			}
		}
		
		return null;
	}
	
	public BigDecimal getEquityByListPrice() 
	{
		if (this.getMarketValue() != null){
			if (this.listPrice != null){
				return this.getMarketValue().subtract(this.listPrice);
			}
		}
		
		return new BigDecimal(0);
	}
	
	public BigDecimal getPotentialRentProfit()
	{ 
		if (this.getRentEstimate() != null && this.getMortgagePayment() != null){
			//calculate using 50% rule
			return this.getRentEstimate().subtract(this.getMortgagePayment()).multiply(new BigDecimal(0.5));
		}
		
		return null;
	}
	
	public boolean isPotentialRental(){
		
		return getPotentialRentProfit() != null && getPotentialRentProfit().doubleValue() > 100;
	}

	public BigDecimal getZillowMarketValue() {
		return zillowMarketValue;
	}

	public void setZillowMarketValue(BigDecimal zillowMarketValue) {
		this.zillowMarketValue = zillowMarketValue;
	}

	public Date getListDate() {
		return listDate;
	}

	public void setListDate(Date listDate) {
		this.listDate = listDate;
	}

	public BigDecimal getCounteredNetToHud() {
		return counteredNetToHud;
	}

	public void setCounteredNetToHud(BigDecimal counteredNetToHud) {
		this.counteredNetToHud = counteredNetToHud;
	}

	public long getDaysInTheMarket() {
		if (daysInTheMarket == 0 && this.listDate != null){
			Date today = new Date();
			return ChronoUnit.DAYS.between(this.listDate.toInstant(),today.toInstant());
		}
		return daysInTheMarket;
	}

	public void setDaysInTheMarket(long daysInTheMarket) {
		this.daysInTheMarket = daysInTheMarket;
	}
	
	public BigDecimal getPercentListPrice(int percentage) throws MissingDataException{
		if(this.listPrice != null){
			return this.listPrice.multiply((new BigDecimal(percentage)).divide(new BigDecimal(100)));
		}
		else
		{
			throw new MissingDataException("List Price");
		}
	}
	
	/**
	 * This returns the suggested Purchase Price to Bid on a property based on the desired discount
	 * 
	 * @param percentage Desired Percent discount on a property
	 * @return HUD Purchase Price
	 * @throws MissingDataException
	 */
	public BigDecimal getBidPurchasePrice(int percentage) throws MissingDataException{
		return getHudPurchasePrice(getPercentListPrice(percentage));	
	}
	
	/**
	 * This returns the Purchase Price Amount based on HUD minimum acceptable net bid amount. This amount
	 * will be accepted by HUD immediately if there are no other bidders with higher amount.
	 * 
	 * @return HUD Purchase Price
	 * @throws MissingDataException
	 */
	public BigDecimal getCounteredPurchasePrice(){
		if (this.counteredNetToHud != null){
			return getHudPurchasePrice(this.counteredNetToHud);	
		}
		return null;
	}
	
	/**
	 * Returns calculated purchase price that includes brokers's commission based on desired HUD net bid amount
	 * @param netBidAmount
	 * @return HUD Purchase Price
	 */
	public BigDecimal getHudPurchasePrice(BigDecimal netBidAmount){
		return (netBidAmount.add(FilterAttributes.BROKER_FEE)).divide(FilterAttributes.HUD_PURCHASE_PRICE_FACTOR, 0, RoundingMode.HALF_UP);
	}
	
	/**
	 * Returns the minimum closing cost amount. This only include the broker's fee and the fmls fee.
	 * 
	 * @param purchasePrice
	 * @return minimum closing cost amount
	 */
	public BigDecimal getHudClosingCostMinimum(BigDecimal purchasePrice){
		return FilterAttributes.BROKER_FEE.add(purchasePrice.multiply(FilterAttributes.FMLS_FEE_FACTOR));
	}

	public String getHudListingPeriod() {
		return hudListingPeriod;
	}

	public void setHudListingPeriod(String hudListingPeriod) {
		this.hudListingPeriod = hudListingPeriod;
	}

	@Override
	public int compareTo(PropertyLead other) {
		int i = this.county.compareTo(other.getCounty());
        if (i != 0) return i;

        return this.getPropertyAddress().compareTo(other.getPropertyAddress());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<StatusHistory> getHistory() {
		return history;
	}

	public void setHistory(List<StatusHistory> history) {
		this.history = history;
	}
	
	public Date getHudBidOpenDate() {
		return hudBidOpenDate;
	}

	public void setHudBidOpenDate(Date hudBidOpenDate) {
		this.hudBidOpenDate = hudBidOpenDate;
	}

	public String getAlternateId() {
		return alternateId;
	}

	public void setAlternateId(String alternateId) {
		this.alternateId = alternateId;
	}

	@Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.id).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        final PropertyLead other = (PropertyLead) obj;
        return new EqualsBuilder()
                .append(this.id, other.id).isEquals();
    }
    
    public StatusHistory getNewStatus(StatusHistory previousStatus)
    {
    	StatusHistory newStatus = null;
		LeadStatus newStat = null;
		int priceChangeSequence = 0;
    	if (previousStatus != null)
    	{
    		LeadStatus newHudStatus = null;
    		if (this.getHudStatus() != null)
    		{
    			newHudStatus = LeadStatus.getStatusByHud(this.getHudStatus());	
    		}
    		
    		//detect price change manually, i.e. don't relay to HUD status when it comes to price change
    		if (this.listPrice != null && previousStatus.getPrice()!= null && !this.listPrice.equals(previousStatus.getPrice()))
    		{
    			newStat = LeadStatus.PRICE_CHANGED;
    			priceChangeSequence = previousStatus.getPriceChangeSequence() + 1;
    		}
    		else if (newHudStatus != null && !LeadStatus.PRICE_CHANGED.equals(newHudStatus))
    		{
    			if (!previousStatus.getStatus().equals(newHudStatus))
    			{
    				newStat = newHudStatus;
    			}
    		}
    		else if (this.getHudListingPeriod() != null){
    			LeadStatus newListPeriod = LeadStatus.getStatusByHud(this.getHudListingPeriod());
    			
    			if(newListPeriod != previousStatus.getListingPeriod())
    			{
    				newStat = newListPeriod;
    			}
    		}
    	}
    	// this is an initial entry to status history
    	else
    	{
			if (this.getHudStatus() != null)
			{
				newStat = LeadStatus.getStatusByHud(this.getHudStatus());	
			}
			else
			{
				newStat = LeadStatus.ACTIVE;
			}
    	}

		if (newStat != null)
		{
			newStatus = new StatusHistory();
			newStatus.setDateModified(new Date());
			newStatus.setPrice(this.listPrice);
			newStatus.setPropertyId(this.id);
			newStatus.setStatus(newStat);
			newStatus.setPriceChangeSequence(priceChangeSequence);
			newStatus.setOpenBidDate(this.getHudBidOpenDate());
			
			if(this.getHudListingPeriod() != null)
			{
				newStatus.setListingPeriod(LeadStatus.getStatusByHud(this.getHudListingPeriod()));
			}
		}
    	return newStatus;
    }
    
    public String toJson() {
    	return JsonUtil.toJson(this);
    }
}
