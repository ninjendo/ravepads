package com.rave.pads.parser;

import com.rave.pads.common.CommonConstants;
import com.rave.pads.model.PropertyLead;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class CsvWriter {
	//Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String DEFAULT_FILE_SUFFIX= "-out.csv";
	protected String datePattern="yyyyMMdd";
	protected SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	abstract String getFilenamePrefix();
	abstract Object[] getFileHeader();
	abstract List buildRecord(PropertyLead lead);
	
	private List<PropertyLead> leads;
	private String inputFilename;
	private static int filenameCounter = 0;
	
	public CsvWriter(List<PropertyLead> leads, String filename){
		this.leads = leads;
		this.inputFilename = filename;
	}
	
	public void write() {
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		//Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
				
		try {
			//initialize FileWriter object
			fileWriter = new FileWriter(getOutputFilename(this.inputFilename));
			//initialize CSVPrinter object 
	        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
	        //Create CSV file header
	        csvFilePrinter.printRecord(getFileHeader());
			
			//Write a new student object list to the CSV file
			for (PropertyLead lead : this.leads) {
				List leadRecord = buildRecord(lead);
	            csvFilePrinter.printRecord(leadRecord);
			}

			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
			}
		}
	}
	
	public String getOutputFilename(String inputFilename)
	{
		return  inputFilename +  getTodayDate() + CommonConstants.DASH + getFilenamePrefix()  + (filenameCounter++) + DEFAULT_FILE_SUFFIX;
	}
	public String getTodayDate()
	{
		Date today = new Date();
		return dateFormatter.format(today);
	}

}
