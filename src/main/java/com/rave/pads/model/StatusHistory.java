package com.rave.pads.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StatusHistory implements Comparable<StatusHistory>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5325221756719612849L;

	String propertyId;
	LeadStatus status;
	Date dateModified;
	BigDecimal price;
	BigDecimal pricePerSqFt;
	int priceChangeSequence;
	Date openBidDate;
	LeadStatus listingPeriod;

	public LeadStatus getStatus() {
		return status;
	}
	public void setStatus(LeadStatus status) {
		this.status = status;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date date) {
		this.dateModified = date;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
//	public Float getPriceChange() {
//		return priceChange;
//	}
//	public void setPriceChange(Float priceChange) {
//		this.priceChange = priceChange;
//	}
	public BigDecimal getPricePerSqFt() {
		return pricePerSqFt;
	}
	public void setPricePerSqFt(BigDecimal pricePerSqFt) {
		this.pricePerSqFt = pricePerSqFt;
	}

	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	
	public Boolean getPriceChanged() {
		return LeadStatus.PRICE_CHANGED.equals(this.status);
	}
	
	
	public LeadStatus getListingPeriod() {
		return listingPeriod;
	}
	public void setListingPeriod(LeadStatus listingPeriod) {
		this.listingPeriod = listingPeriod;
	}
	public Date getOpenBidDate() {
		return openBidDate;
	}
	public void setOpenBidDate(Date openBidDate) {
		this.openBidDate = openBidDate;
	}
	public int getPriceChangeSequence() {
		return priceChangeSequence;
	}
	public void setPriceChangeSequence(int priceChangeSequence) {
		this.priceChangeSequence = priceChangeSequence;
	}
	public int compareTo(StatusHistory other) {
        return this.dateModified.compareTo(other.dateModified);
	}
	@Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.propertyId).append(this.status).append(this.dateModified).toHashCode();
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
        final StatusHistory other = (StatusHistory) obj;
        return new EqualsBuilder()
                .append(this.propertyId, other.propertyId).append(this.status, other.status).append(this.dateModified, other.dateModified)
                .isEquals();
    }
    
}
