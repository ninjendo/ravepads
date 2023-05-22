package com.rave.pads.util;

import com.rave.pads.common.CommonConstants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GAMillageRate {
	
	private static final String[] MILL_RATES = {
	"Canton=32.372",
	"Woodstock=33.272",
	"Acworth=37.31",
	"Kennesaw=39.21",
	"Marietta=31.77",
	"Powder Springs=38.21",
	"Smyrna=35.64",
	"Austell=29.71",
	"Atlanta=43.38",
	"Chamblee=42.84",
	"Decatur=43.18",
	"Doraville=45",
	"Brookhaven=40.45",
	"Stone Mountain=58.69",
	"Avondale=47.83",
	"Clarkston=59.82",
	"Dunwoody=40.39",
	"PineLake=67.56",
	"Cumming=26.624",
	"Sandy Springs=35.484",
	"Johns Creek=35.267",
	"Milton=35.384",
	"Alpharetta=35.603",
	"Atlanta=45.441",
	"College Park=43.272",
	"Roswell=36.108",
	"East Point=49.423",
	"Berkley Lake=37.88",
	"Buford=26.44",
	"Duluth=39.73",
	"Lawrenceville=35.81",
	"Norcross=39.74",
	"Snellville=38.36",
	"Suwanee=38.67",
	"Dacula=40.78",
	"Grayson=37.44",
	"Lilburn=38.19",
	"Sugar Hill=39.14"
	};
	
	private static Map<String,BigDecimal> millRateMap = new HashMap<String,BigDecimal>();
	private static final BigDecimal THOUSAND = new  BigDecimal(1000);
	private static final BigDecimal FORTY_PERCENT = new  BigDecimal(0.40);
	
	static
	{
		for (String keyValue : MILL_RATES) {
			String[] pair = keyValue.split(CommonConstants.EQUAL_SIGN);
			millRateMap.put(pair[0].toUpperCase(), new BigDecimal(pair[1]));
		}
	}
	
	public static BigDecimal getAnnualTaxAmount(String city, BigDecimal assessedValue)
	{
		BigDecimal taxAmount = null;
		if (city != null){
			BigDecimal millRate = millRateMap.get(city.toUpperCase());
			
			if (millRate != null)
			{
				taxAmount = assessedValue.multiply(FORTY_PERCENT).divide(THOUSAND).multiply(millRate);
			}
		}
		
		return taxAmount;
	}
}
