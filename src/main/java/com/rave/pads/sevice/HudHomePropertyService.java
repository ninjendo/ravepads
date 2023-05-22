package com.rave.pads.sevice;

import com.fasterxml.jackson.databind.DatabindException;
import com.rave.pads.config.HudConfig;
import com.rave.pads.exception.MissingDataException;
import com.rave.pads.model.HudBuyerType;
import com.rave.pads.model.LeadType;
import com.rave.pads.model.PropertyLead;
import com.rave.pads.model.State;
import com.rave.pads.parser.CsvParser;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
public class HudHomePropertyService {
    final static Logger logger = LoggerFactory.getLogger(HudHomePropertyService.class);
    @Inject
    HudConfig config;
    @Inject
    CrawlerService crawlerService;

    public HudHomePropertyService(HudConfig config) {
        this.config = config;
    }
    private Map<String, PropertyLead> parseHudNetBidResult(InputStream hudUploadedInputStream)
    {
        Map<String, PropertyLead> hudLeadMap = new HashMap<String, PropertyLead>();
        //String hudFileOut = uploadDirectory + this.hudNetBidFile;
        try {
            logger.info("Parsing HUD Net Bid Result... ");
            CsvParser hudNetBidReader = new CsvParser(hudUploadedInputStream);

            List<PropertyLead> hudNetLeads = hudNetBidReader.parse(LeadType.HUD);

            for (PropertyLead propertyLead : hudNetLeads)
            {
                if (!hudLeadMap.containsKey(propertyLead.getFhaCaseNumber())){
                    hudLeadMap.put(propertyLead.getFhaCaseNumber(), propertyLead);
                }
            }
        } catch (MissingDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hudLeadMap;
    }

    public void downloadHudProperties(State stateCode, @Nullable HudBuyerType buyerType) throws MissingDataException
    {
        //"http://www.hudhomestore.com/pages/ListExportToExcel.aspx?buyerType=Investor&sState=GA"

        StringBuilder url = new StringBuilder(this.config.getSearchUrl());
        if (stateCode == null){
            throw new MissingDataException("State Code is required.");
        }

        url.append(stateCode.name());

        if (buyerType != null){
            try {
                url.append("&buyerType=").append(URLEncoder.encode(buyerType.getDescription(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        downloadHudProperties(url.toString(), stateCode.name());
    }

    private String generateFilename(String stateCode){
        String filename = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd-ss");
        Date dateNow = Calendar.getInstance().getTime();
        filename = simpleDateFormat.format(dateNow) + "-" + stateCode + "-hud.csv";
        return filename;
    }

    public void downloadHudProperties(String searchUrl, String stateCode)
    {
        logger.info("Start Downloadfrom HUD Home Store.");

        try {
            byte[] hudSearchbytes = crawlerService.downloadResults(searchUrl, generateFilename(stateCode));
            InputStream hudSearchInputStream = new ByteArrayInputStream(hudSearchbytes);
            CsvParser hudSearchParser = new CsvParser(hudSearchInputStream);
            List<PropertyLead> leads = hudSearchParser.parse(LeadType.HUD);

            if (leads != null && leads.size() > 0)
            {
                logger.info("No. of parsed HUD Search Results: " + leads.size());
                if (leads.size() > 0)
                {
                    //TODO: call rave DB/GEO API when it's ready
                    String js = leads.get(0).toJson();
                    logger.info("JSON " + js);
                    logger.info("HUD homes saved in cache. " + leads.size());
                }
            }
            else{
                logger.warn("No HUD homes found.");
            }
        } catch (MissingDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
