package com.rave.pads.parser;

import com.rave.pads.model.LeadType;
import com.rave.pads.model.PostalAddress;
import com.rave.pads.model.PropertyLead;
import com.rave.pads.model.Seller;
import com.rave.pads.util.*;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by jettolano on 12/6/2015.
 */
public class FileProcessor {

	final static Logger logger = LoggerFactory.getLogger(FileProcessor.class);
	
    public static PropertyLead parsePropertyLead(CSVRecord record, Set<String> headerNames, LeadType leadType)
    {
    	PropertyLead lead = null;
    	
    	if (record != null && headerNames != null){
    		lead = new PropertyLead();
			lead.setSeller(new Seller());
			lead.getSeller().setMailingAddress(new PostalAddress());
			lead.setPropertyAddress(new PostalAddress());
    		int i =0;
    		for (String header : headerNames) 
    		{
    			logger.debug(i +" header: " + header + " == " + AttributeHeaderMapper.getAttribute(header) + "===" + record.get(header));
    			
    			if (AttributeHeaderMapper.SELLERFISRTNAME.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().setFirstname(record.get(header));
    			}
    			else if (AttributeHeaderMapper.SELLERLASTNAME.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().setLastname(record.get(header));
    			}    
    			else if (AttributeHeaderMapper.SELLERFULLNAME.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().setFullName(record.get(header));
    			}  
    			
    			//SUBJECT PROPERTY ADDRESS
    			else if (AttributeHeaderMapper.PROPERTY_FULL_ADDRESS.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().setFullAddress(record.get(header));
    			}
    			else if (AttributeHeaderMapper.PROPERTY_STREETADDRESS.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().getStreetAddress().setFullStreetAddress(record.get(header));
    			}
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_STREETNAME.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().getStreetAddress().setStreetName(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_STREETTYPE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().getStreetAddress().setStreetType(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_STREETPREFIX.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().getStreetAddress().setStreetPreDirection(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_STREETDIR.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().getStreetAddress().setStreetPostDirection(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_UNITNUMBER.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().getStreetAddress().setUnitNumber(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_STREETNUMBER.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().getStreetAddress().setStreetNumber(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_CITY.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().setCity(record.get(header));
    				
        			//check if city is in the included list
        			if (!LeadType.HUD.equals(leadType) && !FilterAttributes.INCLUDED_CITIES_SET.contains(lead.getPropertyAddress().getCity().toUpperCase())){
        				return null;
        			} 
    			}
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_ZIP.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().setZipCode(record.get(header));
    			}    			
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_STATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().setStateProvinceCode(record.get(header));
    			}
    			else if (AttributeHeaderMapper.PROPERTYADDRESS_COUNTRY.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getPropertyAddress().setCountry(record.get(header));
    			}  

    			//SELLER'S MAILING ADDRESS
    			else if (AttributeHeaderMapper.MAILING_STREETADDRESS.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().getStreetAddress().setFullStreetAddress(record.get(header));
    			}
    			else if (AttributeHeaderMapper.MAILING_STREETNAME.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().getStreetAddress().setStreetName(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.MAILING_STREETTYPE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().getStreetAddress().setStreetType(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.MAILING_STREETPREFIX.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().getStreetAddress().setStreetPreDirection(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.MAILING_STREETDIR.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().getStreetAddress().setStreetPostDirection(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.MAILING_UNITNUMBER.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().getStreetAddress().setUnitNumber(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.MAILING_STREETNUMBER.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().getStreetAddress().setStreetNumber(record.get(header));
    			}   
    			else if (AttributeHeaderMapper.MAILING_CITY.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().setCity(record.get(header));
    			}
    			else if (AttributeHeaderMapper.MAILING_ZIP.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().setZipCode(record.get(header));
    			}
    			else if (AttributeHeaderMapper.MAILING_STATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().setStateProvinceCode(record.get(header));
    			}
    			else if (AttributeHeaderMapper.MAILING_COUNTRY.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.getSeller().getMailingAddress().setCountry(record.get(header));
    			}  
    			
    			//MORTGAGE INFORMATION
    			else if (AttributeHeaderMapper.MORTGAGEBALANCE.equals(AttributeHeaderMapper.getAttribute(header))){
					lead.setMortgageBalance(NumberParser.parseBigDecimal(record.get(header)));
    			} 
    			else if (AttributeHeaderMapper.MORTGAGEORIGINATIONDATE.equals(AttributeHeaderMapper.getAttribute(header))){
					lead.setMortgageOriginationDate(DateParser.parse(record.get(header)));
    			} 
    			else if (AttributeHeaderMapper.MORTGAGETYPE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setMortgageType(record.get(header));
    			} 
    			else if (AttributeHeaderMapper.MORTGAGELENDER.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setMortgageLender(record.get(header));
    			} 
    			else if (AttributeHeaderMapper.MORTGAGE_RATE.equals(AttributeHeaderMapper.getAttribute(header))){
					lead.setMortgageRate(NumberParser.parseFloat(record.get(header)));
    			} 
    			
    			//FORECLOSURE INFORMATION
    			else if (AttributeHeaderMapper.LOANATTORNEYNAME.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setLoanAttorneyName(record.get(header));
    			} 
    			else if (AttributeHeaderMapper.LOANATTORNEYCONTACT.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setLoanAttorneyContact(record.get(header));
    			} 
    			else if (AttributeHeaderMapper.AUCTIONDATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setAuctionDate(DateParser.parse(record.get(header)));
    			} 
    			
    			//property market valuation
    			else if (AttributeHeaderMapper.ANNUALTAX.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setAnnualTax(NumberParser.parseBigDecimal(record.get(header)));
    			} 
    			else if (AttributeHeaderMapper.ASSESSEDVALUE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setAssessedValue(NumberParser.parseBigDecimal(record.get(header)));
    			}   
    			else if (AttributeHeaderMapper.MARKETVALUE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setMarketValue(NumberParser.parseBigDecimal(record.get(header)));
    			}    	
    			else if (AttributeHeaderMapper.LASTPURCHASEPRICE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setLastPurchasePrice(NumberParser.parseBigDecimal(record.get(header)));
    			}    
    			else if (AttributeHeaderMapper.LASTPURCHASEDATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setLastPurchaseDate(DateParser.parse(record.get(header)));
    			}  
    			else if (AttributeHeaderMapper.LASTMARKETRECDATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setLastMarketRecDate(DateParser.parse(record.get(header)));
    			}
    			else if (AttributeHeaderMapper.LASTSETTLEMENTDATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setLastSettlementDate(DateParser.parse(record.get(header)));
    			}
    			else if (AttributeHeaderMapper.LISTPRICE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setListPrice(NumberParser.parseBigDecimal(record.get(header)));
    			}
    			else if (AttributeHeaderMapper.LISTDATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setListDate(DateParser.parse(record.get(header)));
    			}
    			//property features
    			else if (AttributeHeaderMapper.SUBDIVISION.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setSubdivision(record.get(header));
    			} 
    			else if (AttributeHeaderMapper.ZONING.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setZoning(record.get(header));
    			} 
    			else if (AttributeHeaderMapper.COUNTY.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setCounty(record.get(header));
    			} 
    			else if (AttributeHeaderMapper.YEARBUILT.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setYearBuilt(NumberParser.parseInteger(record.get(header)));
    			} 
    			else if (AttributeHeaderMapper.BEDROOMS.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setBedrooms(NumberParser.parseInteger(record.get(header)));
    			} 
    			else if (AttributeHeaderMapper.BATHROOMS.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setBathrooms(NumberParser.parseFloat(record.get(header)));    				
    			} 
    			else if (AttributeHeaderMapper.HALFBATHS.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setHalfBaths(NumberParser.parseInteger(record.get(header)));
    			} 
    			else if (AttributeHeaderMapper.SQUAREFOOTAGE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setSquareFootage(NumberParser.parseFloat(record.get(header)));
    			} 
    			else if (AttributeHeaderMapper.STORIES.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setStories(record.get(header));
    			} 
    			else if (AttributeHeaderMapper.LOTSIZE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setLotSize(NumberParser.parseDouble(record.get(header)));
    			}     
    			else if (AttributeHeaderMapper.REMARKS.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setRemarks(record.get(header));
    			}
    			
    			//Hud-specific attributes
    			else if (AttributeHeaderMapper.NET_TO_HUD.equals(AttributeHeaderMapper.getAttribute(header))){
    				//lead.setNetToHud(NumberParser.parseBigDecimal(record.get(header)));
    				lead.setCounteredNetToHud(NumberParser.parseBigDecimal(record.get(header)));
    			}
    			else if (AttributeHeaderMapper.PURCHASER_TYPE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setPurchaserType(record.get(header));
    			}
    			else if (AttributeHeaderMapper.BID_SUBMIT_DATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setBidSubmitDate(DateParser.parse(record.get(header)));
    			}
    			else if (AttributeHeaderMapper.BID_ACCEPT_DATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setBidAcceptanceDate(DateParser.parse(record.get(header)));    				
    			}
    			else if (AttributeHeaderMapper.FHA_CASE_NUMBER.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setFhaCaseNumber(record.get(header));
    			}
    			else if (AttributeHeaderMapper.HUD_URL.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setHudUrl(record.get(header));
    			}
    			else if (AttributeHeaderMapper.FHA_FINANCING.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setFhaFinancing(record.get(header));
    			}
    			else if (AttributeHeaderMapper.HUD_STATUS.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setHudStatus(record.get(header));
    			}
    			else if (AttributeHeaderMapper.HUD_LISTING_PERIOD.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setHudListingPeriod(record.get(header));
    			}
    			else if (AttributeHeaderMapper.HUD_BID_OPEN_DATE.equals(AttributeHeaderMapper.getAttribute(header))){
    				lead.setHudBidOpenDate(DateParser.parse(record.get(header)));
    			}
    			
    			i++;
			}
    		logger.debug("next record!");
    	}
    	
    	//TODO: add all kinds of filters here to optimize iteration
    	//remove AKA from seller's namertf5ghu78i9o
    	logger.debug(lead.getFhaCaseNumber() + " => " + lead.getPropertyAddress());
    	
    	if (lead.getPropertyAddress() != null && lead.getPropertyAddress().getStreetAddress() != null 
    			&& lead.getPropertyAddress().getStreetAddress().toString() != null
    			&& lead.getPropertyAddress().getStreetAddress().toString().indexOf("#") > -1 
    			&& lead.getPropertyAddress().getStreetAddress().getStreetNumber()
    				.equals(lead.getSeller().getMailingAddress().getStreetAddress().getStreetNumber())
    			&& lead.getPropertyAddress().getCity().equals(lead.getSeller().getMailingAddress().getCity()))
    	{
    		lead.setMailAddressFixed(true);
    		lead.setOldMailStreetAddress(lead.getSeller().getMailingAddress().getStreetAddress().toString());
    		lead.getSeller().getMailingAddress().setStreetAddress(lead.getPropertyAddress().getStreetAddress());
    		
    	}
    	if (lead.getMortgagePayment() == null){
    		lead.setMortgagePayment(MortgageCalculator.calculateMonthlyPayment(lead));
    	}
    	
    	lead.setId(KeyGenerator.getPropertyId(lead));
		lead.setAlternateId(KeyGenerator.getPropertyId(lead));
    	return lead;
    }
}
