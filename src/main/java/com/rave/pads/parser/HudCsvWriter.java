package com.rave.pads.parser;

import com.rave.pads.common.CommonConstants;
import com.rave.pads.model.PropertyLead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class HudCsvWriter extends CsvWriter{

	final static Logger logger = LoggerFactory.getLogger(HudCsvWriter.class);
	
	public HudCsvWriter(List<PropertyLead> leads, String filename) {
		super(leads, filename);
	}

	public static final String FILE_PREFIX = "HUD";
	private static final Object [] FILE_HEADER = {
			"HUD Case#","Address", "City","County","Bed","Sq Ft","Year Built", 
			"FHA Financing","List Date", "Days In Market", "ARV", "List Price",
			"HUD Accepted Net Amount", "NetToHud %", "60% of List Price", "Bid Purchase Price", "Broker/FMLS Fee",
			"Potential Equity", "Rent Estimate"};

	@Override
	List buildRecord(PropertyLead lead) {
		List leadRecord = new ArrayList();
		try {
			leadRecord.add(lead.getFhaCaseNumber());
			leadRecord.add(lead.getPropertyAddress().toString());
			leadRecord.add(lead.getPropertyAddress().getCity());
			leadRecord.add(lead.getCounty());
			leadRecord.add(lead.getBedrooms());
			leadRecord.add(lead.getSquareFootage());
			leadRecord.add(lead.getYearBuilt());
			leadRecord.add(lead.getFhaFinancing());
			leadRecord.add(lead.getListDate());
			leadRecord.add(lead.getDaysInTheMarket());
			leadRecord.add(lead.getMarketValue());
			leadRecord.add(lead.getListPrice());
			leadRecord.add(lead.getCounteredNetToHud());
			
			//percent of Net To HUD vs List Price
			if (lead.getCounteredNetToHud() != null && lead.getListPrice() !=null){
				leadRecord.add(lead.getCounteredNetToHud().divide(lead.getListPrice(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
			}
			else
			{
				//else calculate equity based on list price
				leadRecord.add(CommonConstants.EMPTY);
			} 
				
				
//			try 
//			{
				leadRecord.add(lead.getPercentListPrice(60));
				leadRecord.add(lead.getBidPurchasePrice(60));
				leadRecord.add(lead.getHudClosingCostMinimum(lead.getBidPurchasePrice(60)));
				
				logger.info("Calculating Equity: CounteredPurchasePrice = " + lead.getCounteredPurchasePrice() + " ListPrice = " + lead.getListPrice());
				leadRecord.add(lead.getEquity(lead.getCounteredPurchasePrice()) != null? 
						lead.getEquity(lead.getCounteredPurchasePrice()) : lead.getEquityByListPrice());
//			} catch (MissingDataException e) {
//				logger.error(e.getLocalizedMessage());
//			}
			leadRecord.add(lead.getRentEstimate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return leadRecord;
	}

	@Override
	String getFilenamePrefix() {
		return FILE_PREFIX;
	}

	@Override
	Object[] getFileHeader() {
		return FILE_HEADER;
	}
	
	public void mergeNetBidInfo(){
		
	}
}
