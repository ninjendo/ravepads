package com.rave.pads.parser;

import com.rave.pads.exception.MissingDataException;
import com.rave.pads.model.LeadType;
import com.rave.pads.model.PropertyLead;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.ReaderInputStream;
import org.json.CDL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;


public class CsvParser {

	final static Logger logger = LoggerFactory.getLogger(CsvParser.class);
	
	public static final String encoding = "UTF-8";
	private InputStream fileStream;
	private String fileName;
	
	
	public CsvParser(String filename){
		initStream();
	}
	
	public CsvParser(InputStream inputstream){
		this.fileStream = inputstream;
	}
	
    private void initStream() {
		if (this.fileName == null){
			logger.error("FileStream or FileName is not set.");
		}
        //initialize FileReader object
    	FileReader fileReader = null;
		try 
		{
			fileReader = new FileReader(this.fileName);
			this.fileStream = new ReaderInputStream(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
            try {
                fileReader.close();
            } catch (IOException e) {
            	logger.error("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }
    }
    
    public List<PropertyLead> parse(LeadType leadType) throws MissingDataException{
    	
    	if (leadType == null){
    		throw new MissingDataException("Lead type is required.");
    	}
    	
    	List<PropertyLead> leads = null;
        CSVParser csvFileParser = null;

        //Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader();

        try {
            //initialize CSVParser object
    		InputStreamReader stream = new InputStreamReader(this.fileStream, encoding);
    		csvFileParser = new CSVParser(stream, csvFileFormat);
            
            
            Map<String, Integer> headerMap = csvFileParser.getHeaderMap();
            Set<String> headerNames = headerMap.keySet();

            logger.info("Headers =" + headerNames);
            
            //Get a list of CSV file records
            List csvRecords = csvFileParser.getRecords();
            if(csvRecords != null && !csvRecords.isEmpty())
            {
                //Create a new list of student to be filled by CSV file data
                leads = new ArrayList<PropertyLead>();
            	//Set<String> cities = new HashSet<String>();
            	SortedSet<String> cities=new TreeSet<String>();
            	//Read the CSV file records starting from the second record to skip the header
                StringBuilder rowArray = new StringBuilder(headerNames.toString().replace("[", "").replace("[", "")).append("/n");
                for (int i = 1; i < csvRecords.size(); i++) {
                    CSVRecord record = (CSVRecord)csvRecords.get(i);
//                    logger.info(i + " row = " + qQA);

                    //String json = CDL.toJSONArray(record.toString()).toString();
                    //logger.info(" headerNames===> " +headerNames);
                    PropertyLead lead = FileProcessor.parsePropertyLead(record, headerNames, leadType);
                    if (lead != null){
                        leads.add(lead);
                        
                        //TODO: what is this map for???
                        if (lead.getPropertyAddress() != null && lead.getPropertyAddress().getCity() != null){
                        	cities.add(lead.getPropertyAddress().getCity());	
                        }
                    }
                    else{
                    	logger.info("NO LEAD!!");
                    }
                    
                    //System.out.println(lead.getPropertyAddress());
                }
            }

        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
            	fileStream.close();
                csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }
        return leads;
    }

    public List<PropertyLead> parse() {
    	
    	List<PropertyLead> leads = null;
        CSVParser csvFileParser = null;

        //Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader();

        try {
            //initialize CSVParser object
    		InputStreamReader stream = new InputStreamReader(this.fileStream, encoding);
    		csvFileParser = new CSVParser(stream, csvFileFormat);
            
            
            Map<String, Integer> headerMap = csvFileParser.getHeaderMap();
            Set<String> headerNames = headerMap.keySet();

            for (String string : headerNames) {
				logger.debug(string);
			}
            //Get a list of CSV file records
            List csvRecords = csvFileParser.getRecords();
            if(csvRecords != null && !csvRecords.isEmpty())
            {
                //Create a new list of student to be filled by CSV file data
                leads = new ArrayList<PropertyLead>();
            	//Set<String> cities = new HashSet<String>();
            	SortedSet<String> cities=new TreeSet<String>();
            	//Read the CSV file records starting from the second record to skip the header
                for (int i = 1; i < csvRecords.size(); i++) {
                    CSVRecord record = (CSVRecord)csvRecords.get(i);
                    logger.debug(i + " row = " + record.toString());
                    logger.debug(" headerNames===> " +headerNames);
                    PropertyLead lead = FileProcessor.parsePropertyLead(record, headerNames, null);
                    if (lead != null){
                        leads.add(lead);
                        
                        //TODO: what is this map for???
                        if (lead.getPropertyAddress() != null && lead.getPropertyAddress().getCity() != null){
                        	cities.add(lead.getPropertyAddress().getCity());	
                        }
                    }
                    else{
                    	logger.info("NO LEAD!!");
                    }
                    
                    //System.out.println(lead.getPropertyAddress());
                }
            }

        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
            	fileStream.close();
                csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }
        return leads;
    }
    
//    public static void main(String[] args) {
//    	String filename = "C:\\Users\\khani\\Google Drive\\Projects\\Ninjendo Realty\\Marketing\\HUD Homes\\Search Results\\12-20-2015_Case_List.csv";
//    	CsvParser fr = new CsvParser(filename);
//    	
//    	fr.parse();
//	}

	public InputStream getFileStream() {
		return fileStream;
	}

	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}