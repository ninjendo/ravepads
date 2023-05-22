/*******************************************************************************
 * Copyright (C) 2011 Incomm Inc. The information contained in this document is
 * the exclusive property of Incomm Inc. This work is protected under United
 * States copyright law and the copyright laws of given countries of origin and
 * international laws, treaties and/or conventions. No part of this document may
 * be reproduced or transmitted in any form or by any means, electronic or
 * mechanical including photocopying or by any informational storage or
 * retrieval system, unless as expressly permitted by Incomm.
 ******************************************************************************/
package com.rave.pads.model;

import com.rave.pads.common.CommonConstants;
import io.micronaut.core.util.StringUtils;


import java.io.Serializable;
import java.util.List;
// TODO: Auto-generated Javadoc
/**
 * Represents a address contact mechanism. Stores the details of the address.
 *
 * @author Cory Lucas
 */

public class PostalAddress  implements Serializable, Comparable<PostalAddress> {
    /** serial version id for serialization. */
    private static final long serialVersionUID = 8893482411301950691L;

	private Long id;
    /**
     * The lines of the address. (Line 1, line 2, etc.)
     */
    private List<String> _addressLines;

    /**
     * The city name of the address.
     */
    private String _city;

    /**
     * The postal code of the address.
     */
    private String _zipCode;

    /** The postal code extension.  Only valid for U.S. addresses. */
    private String _postalCodeExtension;

    /**
     * The country name of the address.
     */
    private String _country;

    /**
     * The country coid of the address.
     */
    private String _countryCode;
    
    /**
     * The state or province name of the address.
     */
    private String _stateProvince;
    
    /**
     * The state or province Code of the address.
     */
    private String _stateProvinceCode;

    /** The "To" field of the address. */
    private String _toName;

    /** The "Attn" field of the address. */
    private String _attnName;

    /** Flag for use of DST. */
    private boolean _useDST;

    /** Is this postal address a default shipping address?. */
    private boolean _isDefault;

    private StreetAddress _streetAddress = new StreetAddress();
    
    private String _fullAddress = null;
    /**
     * Gets the address lines.
     *
     * @return the address lines
     */
    public List<String> getAddressLines() {
        return _addressLines;
    }

    /**
     * Sets the address lines.
     *
     * @param addressLines
     *            the new address lines
     */
    public void setAddressLines(final List<String> addressLines) {
        _addressLines = addressLines;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return _city;
    }

    /**
     * Sets the city.
     *
     * @param city
     *            the new city
     */
    public void setCity(final String city) {
        _city = city;
    }

    /**
     * Gets the postal code.
     *
     * @return the postal code
     */
    public String getZipCode() {
        return _zipCode;
    }

    /**
     * Sets the postal code.
     *
     * @param postalCode
     *            the new postal code
     */
    public void setZipCode(final String postalCode) {
        _zipCode = postalCode;
    }

    /**
     * Gets the postal code extension.
     *
     * @return the postal code extension
     */
    public String getPostalCodeExtension() {
        return _postalCodeExtension;
    }

    /**
     * Sets the postal code extension.
     *
     * @param postalCodeExtension the new postal code extension
     */
    public void setPostalCodeExtension(String postalCodeExtension) {
        _postalCodeExtension = postalCodeExtension;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public String getCountry() {
        return _country;
    }

    /**
     * Sets the country.
     *
     * @param country
     *            the new country
     */
    public void setCountry(final String country) {
        _country = country;
    }

    /**
     * Gets the state province.
     *
     * @return the state province
     */
    public String getStateProvince() {
        return _stateProvince;
    }

    /**
     * Sets the state province.
     *
     * @param stateProvince the new state province
     */
    public void setStateProvince(final String stateProvince) {
        _stateProvince = stateProvince;
    }

    /**
     * Gets the to name.
     *
     * @return the to name
     */
    public String getToName() {
        return _toName;
    }

    /**
     * Sets the to name.
     *
     * @param toName the new to name
     */
    public void setToName(String toName) {
        _toName = toName;
    }

    /**
     * Gets the attn name.
     *
     * @return the attn name
     */
    public String getAttnName() {
        return _attnName;
    }

    /**
     * Sets the attn name.
     *
     * @param attnName the new attn name
     */
    public void setAttnName(String attnName) {
        _attnName = attnName;
    }

    /**
     * Checks if is use dst.
     *
     * @return true, if is use dst
     */
    public boolean isUseDST() {
        return _useDST;
    }

    /**
     * Sets the use dst.
     *
     * @param useDST the new use dst
     */
    public void setUseDST(boolean useDST) {
        _useDST = useDST;
    }

    /**
     * Checks if is default.
     *
     * @return true, if is default
     */
    public boolean isDefault() {
        return _isDefault;
    }

    /**
     * Sets the default.
     *
     * @param isDefault the new default
     */
    public void setDefault(boolean isDefault) {
        _isDefault = isDefault;
    }
    
    public boolean isFilled() {
        return StringUtils.isNotEmpty(_attnName) || StringUtils.isNotEmpty(_city) || StringUtils.isNotEmpty(_country) || StringUtils.isNotEmpty(_zipCode) ||
               StringUtils.isNotEmpty(_postalCodeExtension) || StringUtils.isNotEmpty(_stateProvince) || StringUtils.isNotEmpty(_toName) || 
               (_addressLines!=null && !_addressLines.isEmpty());
    }
    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return _countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        _countryCode = countryCode;
    }

    /**
     * @return the stateProvinceCode
     */
    public String getStateProvinceCode() {
        return _stateProvinceCode;
    }

    /**
     * @param stateProvinceCode the stateProvinceCode to set
     */
    public void setStateProvinceCode(String stateProvinceCode) {
        _stateProvinceCode = stateProvinceCode;
    }
    
    public String getStreetAddressLine(){
        
        if (_addressLines != null)
        {
            StringBuilder sb = new StringBuilder();
            
            for (String addressLine : _addressLines) {
                sb.append(addressLine).append(CommonConstants.SPACE);
            }
            
            return sb.toString().replace(CommonConstants.NULL, CommonConstants.SPACE);
        }
        return CommonConstants.EMPTY;
    }
    
    public String getPostalArea(){
        StringBuilder sb = new StringBuilder();
        return sb.append(_city).append(CommonConstants.SPACE).append(CommonConstants.SPACE).append(_stateProvinceCode).append(CommonConstants.SPACE)
                .append(_zipCode).append(CommonConstants.SPACE).append(_countryCode).toString()
                .replace(CommonConstants.NULL, CommonConstants.SPACE);
    }
    
    @Override
    public String toString()
    {
    	if (getStreetAddressLine() != null && !getStreetAddressLine().isEmpty()){
    		return getStreetAddressLine() + CommonConstants.SPACE + getPostalArea();	
    	}
    	else if (getStreetAddress() != null){
    		return getStreetAddress().toString()+ CommonConstants.SPACE + getPostalArea();	
    	}
    	return getFullAddress();	
    	
    }

    public String toStringUrl()
    {
    	return this.toString().replace(CommonConstants.SPACE, CommonConstants.DASH);
    }
    
	public void setStreetAddress(StreetAddress streetAddress) {
		this._streetAddress = streetAddress;
	}
	
    public StreetAddress getStreetAddress(){
        
    	return this._streetAddress;
    }

	public String getFullAddress() {
		return _fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this._fullAddress = fullAddress;
	}

	@Override
	public int compareTo(PostalAddress other) {
		int i = this._city.compareTo(other.getCity());
        if (i != 0) return i;

        i = this._zipCode.compareTo(other.getZipCode());
        if (i != 0) return i;

        return this.getStreetAddressLine().compareTo(other.getStreetAddressLine());
	}
}
