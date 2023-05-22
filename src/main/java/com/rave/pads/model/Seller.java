package com.rave.pads.model;


import com.rave.pads.common.CommonConstants;
import java.io.Serializable;

public class Seller implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3909491495250863239L;

	private SellerType type;
	
	private String firstname;
	private String middlename;
	private String lastname;
	private String fullName;
	private String sellerType;
	
	PostalAddress mailingAddress;
	
	private Contact contactInfo;

	
	public String getFullName() {
		
		if (fullName == null){
			return getFirstname() + getMiddlename() + CommonConstants.SPACE + getLastname();
		}
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstname() {
		return firstname != null? firstname : CommonConstants.EMPTY;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname != null? lastname : CommonConstants.EMPTY;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	

	public String getMiddlename() {
		return middlename != null? middlename : CommonConstants.EMPTY;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public Contact getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(Contact contactInfo) {
		this.contactInfo = contactInfo;
	}

	public PostalAddress getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(PostalAddress mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public SellerType getType() {
		return type;
	}

	public void setType(SellerType type) {
		this.type = type;
	}

	public String getSellerType() {
		return sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}
	

}
