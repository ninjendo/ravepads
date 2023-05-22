package com.rave.pads.util;

import com.rave.pads.model.PropertyLead;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortgageCalculator {
	
	public static String CONV = "CNV";
	public static String FHA = "FHA";
	public static String VA = "VA";
	public static String ARM = "ARM";

	
	public static BigDecimal CONV_MAX_LTV = new BigDecimal(0.95);
	public static BigDecimal FHA_MAX_LTV = new BigDecimal(0.965);
	public static BigDecimal VA_MAX_LTV = new BigDecimal(1);
	public static BigDecimal ARM_MAX_LTV = new BigDecimal(0.95);

	
	public static BigDecimal calculateMonthlyPayment(PropertyLead lead)
	{
		if (lead.getMortgageRate() != null){
			BigDecimal monthlyPayment = null;
			BigDecimal loanAmount = getLoanAmount(lead.getLastPurchasePrice(), lead.getMortgageType());
			if (loanAmount != null)
			{
				int termsInMonths = getTermsInMonths(lead.getMortgageType());
				double monthlyRate = (lead.getMortgageRate() / 100) / 12;
				double ratePlus1 = monthlyRate + 1;
				double rateExp =  Math.pow(ratePlus1, termsInMonths);
				
				double dividend = loanAmount.doubleValue() * monthlyRate * rateExp;
				double divisor = rateExp - 1;
				
				//System.out.println("ratePlus1=" + ratePlus1 + " termsInMonths=" + termsInMonths + " monthlyRate=" + monthlyRate + " loanAmount=" + loanAmount + " rateExp=" +rateExp);
				monthlyPayment = (new BigDecimal (dividend / divisor)).setScale(2,RoundingMode.CEILING);
			}
			
			return monthlyPayment;
		}
		return null;
	}
	
	private static BigDecimal getLoanAmount(BigDecimal purchasePrice, String mortgageType)
	{
		if (mortgageType != null && purchasePrice != null){
			switch (mortgageType) {
			case "CNV":
				return purchasePrice.multiply(CONV_MAX_LTV);
			case "VA":
				return purchasePrice.multiply(VA_MAX_LTV);
			case "FHA":
				return purchasePrice.multiply(FHA_MAX_LTV);			
			default:
				break;
			}
		}
		
		return purchasePrice;
	}
	
	private static int getTermsInMonths(String mortgageType)
	{
		int years = 30;
		if (mortgageType.equals(CONV) || mortgageType.equals(FHA) || mortgageType.equals(VA) || mortgageType.equals(ARM)){
			years = 30;
		}
		else if (mortgageType.indexOf("10") > -1) years = 10;
		else if (mortgageType.indexOf("20") > -1) years = 20;
		else if (mortgageType.indexOf("15") > -1) years = 15;
		else if (mortgageType.indexOf("25") > -1) years = 25;
		else if (mortgageType.indexOf("40") > -1) years = 40;
		else if (mortgageType.indexOf("27") > -1) years = 27;
		
		return years * 12;
	}
}
