package com.rave.pads.util;

import java.util.*;

public class AttributeHeaderMapper{

	private static Map<String,String> headerAttribMap = new HashMap<String,String>(); 
	private static Map<String,List<String>> attribHeaderMap = new HashMap<String,List<String>>(); 
	
	public final static String STATUS="status";
	public final static String PRIMARYLEADTYPE="primaryLeadType";
	public final static String SECONDARYLEADTYPE="secondaryLeadType";
	
	public final static String SELLERFISRTNAME="sellerFName";
	public final static String SELLERLASTNAME="sellerLName";
	public final static String SELLERFULLNAME="sellerFullName";
	
	
	public static final String PROPERTY_FULL_ADDRESS="FullPropertyAddress";
	public static final String PROPERTY_STREETADDRESS="PROPERTY_STREETADDRESS";
	public static final String PROPERTYADDRESS_STREETNAME="PropertyStName";
	public static final String PROPERTYADDRESS_STREETTYPE="PropertyStType";
	public static final String PROPERTYADDRESS_STREETPREFIX="PropertyStPrefix";
	public static final String PROPERTYADDRESS_STREETDIR="PropertyStDir";
	public static final String PROPERTYADDRESS_UNITNUMBER="PropertyUnitNo";
	public static final String PROPERTYADDRESS_STREETNUMBER="PropertyStNumber";
	public static final String PROPERTYADDRESS_CITY="PropertyCity";
	public static final String PROPERTYADDRESS_ZIP="PropertyZip";
	public static final String PROPERTYADDRESS_STATE="PropertyState";
	public static final String PROPERTYADDRESS_COUNTRY="PropertyCountry";

	public static final String PROPERTY_LAND_LOT="LandLot";
	public static final String PROPERTY_DISTRICT="District";
	public static final String PROPERTY_SECTION="Section";
	public static final String PROPERTY_ZONE="Zone";
	
	//SELLER'S MAILING ADDRESS
	public static final String MAILING_STREETADDRESS="MAILING_STREETADDRESS";
	public static final String MAILING_STREETNAME="MailingStName";
	public static final String MAILING_STREETTYPE="MailingStType";
	public static final String MAILING_STREETPREFIX="MailingStPrefix";
	public static final String MAILING_STREETDIR="MailingStDir";
	public static final String MAILING_UNITNUMBER="MailingUnitNo";
	public static final String MAILING_STREETNUMBER="MailingStNumber";
	public static final String MAILING_CITY="MailingCity";
	public static final String MAILING_ZIP="MailingZip";
	public static final String MAILING_STATE="MailingState";
	public static final String MAILING_CITY_STATE="MAILING_CITY_STATE";
	public static final String MAILING_COUNTRY="MailingCountry";

	
	public final static String LISTPRICE="listedPrice";
	public final static String LISTDATE="listDate";
	public final static String MORTGAGEBALANCE="mortgageBalance";
	public final static String MORTGAGEORIGINATIONDATE="mortgageOriginationDate";
	public final static String MORTGAGETYPE="mortgageType";
	public final static String MORTGAGELENDER="mortgageLender";
	public final static String MORTGAGE_RATE="mortgageRate";
	
	public final static String LOANATTORNEYNAME="loanAttorneyName";
	public final static String LOANATTORNEYCONTACT="loanAttorneyContact";
	public final static String AUCTIONDATE="auctionDate=";
	public final static String ANNUALTAX="annualTax";
	public final static String ASSESSEDVALUE="assessedValue";
	public final static String MARKETVALUE="marketValue";
	public final static String LASTPURCHASEPRICE="lastPurchasePrice";
	public final static String LASTPURCHASEDATE="lastPurchaseDate";
	public final static String LASTMARKETRECDATE="lastMarketRecDate";
	public final static String LASTSETTLEMENTDATE="lastSettlementDate";
	public final static String SUBDIVISION="subdivision";
	public final static String ZONING="zoning";
	public final static String COUNTY="county";
	public final static String YEARBUILT="yearBuilt";
	public final static String BEDROOMS="bedrooms";
	public final static String BATHROOMS="bathrooms";
	public final static String HALFBATHS="halfBaths";
	public final static String BASEMENTSQFT="basementSqft";
	public final static String SQUAREFOOTAGE="squareFootage";
	public final static String STORIES="stories";
	public final static String LOTSIZE="lotSize";
	public final static String REMARKS="remarks";
	
	//HUD-SPECIFIC ATTRIBS
	public final static String NET_TO_HUD="netToHud";
	public final static String PURCHASER_TYPE="purchaserType";
	public final static String BID_SUBMIT_DATE="bidSubmitDate";
	public final static String BID_ACCEPT_DATE="bidAcceptDate";
	public final static String FHA_CASE_NUMBER="fhaCaseNumber";
	public final static String HUD_URL="hudUrl";
	public final static String FHA_FINANCING="FHA_FINANCING";
	public final static String HUD_STATUS="HUD_STATUS";
	public final static String HUD_LISTING_PERIOD="HUD_LISTING_PERIOD";
	public final static String HUD_BID_OPEN_DATE="HUD_BID_OPEN_DATE";

	
	static{
		//Preforeclosure
		//Street No	Street Prefix	Street Name	Street Type	Street Dir	City	Zip	OwnerLName	OwnerFName	Land Lot	
		//District	Section	Zone	Tax Value	Purch Price	Date Purch	Yr Built	Mortgage Amount	Mtg Type	
		//Year Orig	Int Rate	MgorLName	MgorFName	Mortgagee	Deed Book	Page No	Atty Name	Atty No	
		//Subject To	Month Adv	County	Realcheck	OrigDate	Subdivision	Stories	SquareFt	Bedrooms	Bathrooms	
		//HalfBathrooms	CensusBlockTract	LotSize	
		
		//Owner Street No	Owner Street Prefix	Owner Street Name	Owner Street Type	Owner Street Dir	Owner City	
		//Owner Zip	LoadDt	FCSeq	Parcel

//Property Case	Address	City	State	Zip Code	County	Price	Bed	Bath	Square Footage	Year Built	FHA Financing	List Date	Bid Open Date	Listing Period	Status


		attribHeaderMap.put(SELLERFISRTNAME, new ArrayList<String>(Arrays.asList("OwnerFName")));
		attribHeaderMap.put(SELLERLASTNAME, new ArrayList<String>(Arrays.asList("OwnerLName")));
		attribHeaderMap.put(SELLERFULLNAME, new ArrayList<String>(Arrays.asList("Mail Owner Name")));

		//SUBJECT PROPERTY ADDRESS
		attribHeaderMap.put(PROPERTY_FULL_ADDRESS, new ArrayList<String>(Arrays.asList("address", "Property Address", "Full Address")));
		attribHeaderMap.put(PROPERTY_STREETADDRESS, new ArrayList<String>(Arrays.asList("Address")));
		
		attribHeaderMap.put(PROPERTYADDRESS_STREETNAME, new ArrayList<String>(Arrays.asList("Street Name")));
		attribHeaderMap.put(PROPERTYADDRESS_STREETTYPE, new ArrayList<String>(Arrays.asList("Street Type","Street Suffix")));
		attribHeaderMap.put(PROPERTYADDRESS_STREETPREFIX, new ArrayList<String>(Arrays.asList("Street Prefix")));
		attribHeaderMap.put(PROPERTYADDRESS_STREETDIR, new ArrayList<String>(Arrays.asList("Street Dir","Post-Direction")));
		attribHeaderMap.put(PROPERTYADDRESS_UNITNUMBER, new ArrayList<String>(Arrays.asList("Unit #")));
		attribHeaderMap.put(PROPERTYADDRESS_STREETNUMBER, new ArrayList<String>(Arrays.asList("Street No","Standardized House Number")));
		attribHeaderMap.put(PROPERTYADDRESS_CITY, new ArrayList<String>(Arrays.asList("City", "Property City")));
		attribHeaderMap.put(PROPERTYADDRESS_ZIP, new ArrayList<String>(Arrays.asList("Zip","Zip Code")));
		attribHeaderMap.put(PROPERTYADDRESS_STATE, new ArrayList<String>(Arrays.asList("State")));
		attribHeaderMap.put(PROPERTYADDRESS_COUNTRY, new ArrayList<String>(Arrays.asList("")));
		
		attribHeaderMap.put(PROPERTY_LAND_LOT, new ArrayList<String>(Arrays.asList("Land Lot")));
		attribHeaderMap.put(PROPERTY_DISTRICT, new ArrayList<String>(Arrays.asList("District")));
		attribHeaderMap.put(PROPERTY_SECTION, new ArrayList<String>(Arrays.asList("Section")));
		
		//SELLER'S MAILING ADDRESS
		attribHeaderMap.put(MAILING_STREETADDRESS, new ArrayList<String>(Arrays.asList("Tax Billing Address")));
		attribHeaderMap.put(MAILING_STREETNAME, new ArrayList<String>(Arrays.asList("Owner Street Name")));
		attribHeaderMap.put(MAILING_STREETTYPE, new ArrayList<String>(Arrays.asList("Owner Street Type")));
		attribHeaderMap.put(MAILING_STREETPREFIX, new ArrayList<String>(Arrays.asList("Owner Street Prefix")));
		attribHeaderMap.put(MAILING_STREETDIR, new ArrayList<String>(Arrays.asList("Owner Street Dir")));
		attribHeaderMap.put(MAILING_UNITNUMBER, new ArrayList<String>(Arrays.asList(""))); //TODO: add header here!
		attribHeaderMap.put(MAILING_STREETNUMBER, new ArrayList<String>(Arrays.asList("Owner Street No")));
		attribHeaderMap.put(MAILING_CITY, new ArrayList<String>(Arrays.asList("Owner City","Tax Billing City")));
		attribHeaderMap.put(MAILING_ZIP, new ArrayList<String>(Arrays.asList("Owner Zip", "Tax Billing Zip")));
		attribHeaderMap.put(MAILING_STATE, new ArrayList<String>(Arrays.asList("Owner State", "Tax Billing State")));
		attribHeaderMap.put(MAILING_CITY_STATE, new ArrayList<String>(Arrays.asList("Tax Billing City & State")));
		
		attribHeaderMap.put(MAILING_COUNTRY, new ArrayList<String>(Arrays.asList("")));
				
		attribHeaderMap.put(MORTGAGEBALANCE, new ArrayList<String>(Arrays.asList("Mortgage Amount")));
		attribHeaderMap.put(MORTGAGEORIGINATIONDATE, new ArrayList<String>(Arrays.asList("OrigDate")));
		attribHeaderMap.put(MORTGAGETYPE, new ArrayList<String>(Arrays.asList("Mtg Type")));
		attribHeaderMap.put(MORTGAGELENDER, new ArrayList<String>(Arrays.asList("Mortgagee")));
		attribHeaderMap.put(MORTGAGE_RATE, new ArrayList<String>(Arrays.asList("Int Rate")));
		
		attribHeaderMap.put(LOANATTORNEYNAME, new ArrayList<String>(Arrays.asList("Atty Name")));
		attribHeaderMap.put(LOANATTORNEYCONTACT, new ArrayList<String>(Arrays.asList("Atty No")));
		attribHeaderMap.put(AUCTIONDATE, new ArrayList<String>(Arrays.asList("Month Adv")));
		
		attribHeaderMap.put(LISTPRICE, new ArrayList<String>(Arrays.asList("Price")));
		attribHeaderMap.put(ANNUALTAX, new ArrayList<String>(Arrays.asList("Annual Tax")));
		attribHeaderMap.put(ASSESSEDVALUE, new ArrayList<String>(Arrays.asList("Tax Value")));
		attribHeaderMap.put(MARKETVALUE, new ArrayList<String>(Arrays.asList("Market Value")));
		attribHeaderMap.put(LASTPURCHASEPRICE, new ArrayList<String>(Arrays.asList("Purch Price","Last Mkt Sale Price")));
		attribHeaderMap.put(LASTPURCHASEDATE, new ArrayList<String>(Arrays.asList("Date Purch")));
		attribHeaderMap.put(LASTMARKETRECDATE, new ArrayList<String>(Arrays.asList("Last Mkt Rec Date")));
		attribHeaderMap.put(LASTSETTLEMENTDATE, new ArrayList<String>(Arrays.asList("Settle Date (TAX|MLS)")));
		
		
		attribHeaderMap.put(SUBDIVISION, new ArrayList<String>(Arrays.asList("Subdivision")));
		attribHeaderMap.put(ZONING, new ArrayList<String>(Arrays.asList("Zone","Zoning")));
		attribHeaderMap.put(COUNTY, new ArrayList<String>(Arrays.asList("County")));
		attribHeaderMap.put(YEARBUILT, new ArrayList<String>(Arrays.asList("Yr Built","Effective Yr Built","Year Built")));
		attribHeaderMap.put(BEDROOMS, new ArrayList<String>(Arrays.asList("Bedrooms","Bedrooms (TAX|MLS)","Bed")));
		attribHeaderMap.put(BATHROOMS, new ArrayList<String>(Arrays.asList("Bathrooms","Full Baths (TAX|MLS)","Bath")));
		attribHeaderMap.put(HALFBATHS, new ArrayList<String>(Arrays.asList("HalfBathrooms")));
		attribHeaderMap.put(SQUAREFOOTAGE, new ArrayList<String>(Arrays.asList("SquareFt","Square Footage")));
		attribHeaderMap.put(BASEMENTSQFT, new ArrayList<String>(Arrays.asList("Basement Sq Ft")));
		attribHeaderMap.put(LOTSIZE, new ArrayList<String>(Arrays.asList("LotSize","Lot Acres")));
		attribHeaderMap.put(STORIES, new ArrayList<String>(Arrays.asList("Stories")));
		attribHeaderMap.put(REMARKS, new ArrayList<String>(Arrays.asList("Subject To")));

		
		//"address","netToHud","purchaserType","dateSubmitted","dateAccepted","propertyCase.href","propertyCase.text","index","url"
		
		attribHeaderMap.put(NET_TO_HUD, new ArrayList<String>(Arrays.asList("netToHud","Net Bid Amount", "Net To HUD")));
		attribHeaderMap.put(PURCHASER_TYPE, new ArrayList<String>(Arrays.asList("purchaserType")));
		attribHeaderMap.put(BID_SUBMIT_DATE, new ArrayList<String>(Arrays.asList("dateSubmitted")));
		attribHeaderMap.put(BID_ACCEPT_DATE, new ArrayList<String>(Arrays.asList("dateAccepted")));
		attribHeaderMap.put(FHA_CASE_NUMBER, new ArrayList<String>(Arrays.asList("Property Case","propertyCase.text", "HUD Case #")));		
		attribHeaderMap.put(HUD_URL, new ArrayList<String>(Arrays.asList("url")));
		attribHeaderMap.put(FHA_FINANCING, new ArrayList<String>(Arrays.asList("FHA Financing")));
		attribHeaderMap.put(HUD_STATUS, new ArrayList<String>(Arrays.asList("Status")));
		attribHeaderMap.put(LISTDATE, new ArrayList<String>(Arrays.asList("List Date")));
		attribHeaderMap.put(HUD_LISTING_PERIOD, new ArrayList<String>(Arrays.asList("Listing Period")));
		attribHeaderMap.put(HUD_BID_OPEN_DATE, new ArrayList<String>(Arrays.asList("Bid Open Date")));
		
//		attribHeaderMap.put(STATUS,"status"
//		attribHeaderMap.put(PRIMARYLEADTYPE="primaryLeadType"
//		attribHeaderMap.put(SECONDARYLEADTYPE="secondaryLeadType"
		
		
		for (String key : attribHeaderMap.keySet()) {
			List<String> headers = attribHeaderMap.get(key);
			for (String header : headers) {
				headerAttribMap.put(header, key);
			}
		}
	}
	
	public static String getAttribute(String headerName){
		
		return headerAttribMap.get(headerName);
	}
}
