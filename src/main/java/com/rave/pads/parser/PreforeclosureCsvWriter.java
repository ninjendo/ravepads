package com.rave.pads.parser;

import com.rave.pads.common.CommonConstants;
import com.rave.pads.model.PropertyLead;

import java.util.ArrayList;
import java.util.List;

public class PreforeclosureCsvWriter extends CsvWriter{
	
	public PreforeclosureCsvWriter(List<PropertyLead> leads, String filename) {
		super(leads, filename);
	}

	private static final Object [] FILE_HEADER = {"City","Property Address", "Mailing Address", "Seller Name", "Mort Balance", "Market Value", "Equity","MOnthly Payment", "Est. Rent", "Rental profit","has 2nd Mort", "Fix name", "old mail address", "Out of State"};
	private static final String FILE_PREFIX = "PREFORECLOSE";

	@Override
	String getFilenamePrefix() {
		return FILE_PREFIX;
	}

	@Override
	Object[] getFileHeader() {
		return FILE_HEADER;
	}

	@Override
	List buildRecord(PropertyLead lead) {
		List leadRecord = new ArrayList();
		leadRecord.add(lead.getPropertyAddress().getCity());
		leadRecord.add(lead.getPropertyAddress().toString());
		leadRecord.add(lead.getSeller().getMailingAddress().toString());
		leadRecord.add(lead.getSeller().getFullName());
		//TODO: format output proper currency
		leadRecord.add(lead.getMortgageBalance());
		//TODO: format output proper currency
		leadRecord.add(lead.getMarketValue());
		leadRecord.add(lead.getEquity(lead.getMortgageBalance()) != null? lead.getEquity(lead.getMortgageBalance()) : CommonConstants.EMPTY);
		leadRecord.add(lead.getMortgagePayment());
		leadRecord.add(lead.getRentEstimate());
		leadRecord.add(lead.getPotentialRentProfit());
		leadRecord.add(lead.hasPotential2ndMortgage());
		leadRecord.add(lead.getSeller().getFullName().indexOf("/") > -1);
		leadRecord.add(lead.getOldMailStreetAddress());
		leadRecord.add(lead.isOutOfGAState());
        return leadRecord;
	}
}
