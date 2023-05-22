package com.rave.pads.sevice;

import com.rave.pads.common.CommonConstants;
import com.rave.pads.config.CrawlerConfig;
import com.rave.pads.model.PropertyLead;
import io.micronaut.context.annotation.Bean;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Singleton
@Getter
public class CrawlerService {
    @Inject
    private final CrawlerConfig crawlerConfig;
    private Pattern patternDomainName;
    private Matcher matcher;
    private static final String HREF = "href";
    private static final String LINK_HREF = "a[href]";
    private static final String URL_START = "/url?q=";

    final static Logger logger = LoggerFactory.getLogger(CrawlerService.class);

    public CrawlerService(CrawlerConfig crawlerConfig) {
        this.crawlerConfig = crawlerConfig;
        logger.info("this.config =" + this.crawlerConfig);
        patternDomainName = Pattern.compile(this.crawlerConfig.getZillowUrlPattern());
    }

    public String getURL(String url) {

        logger.info("dirty url = " + url);
        String domainName = "";

        matcher = patternDomainName.matcher(url);
        if (matcher.find()) {
            domainName = matcher.group(0).toLowerCase().trim();
        }
        logger.info(domainName);
        return domainName;

    }

    public String getUrlFromGoogle(PropertyLead lead) {
        String urlStr = "";
        String query = lead.getPropertyAddress().toStringUrl();
        String request = this.crawlerConfig.getGoogleSearchUrl() + query + this.crawlerConfig.getQueryResultLimit();
        logger.info("Sending request..." + request);

        try {

            // need http protocol, set this as a Google bot agent :)
            Document doc = Jsoup.connect(request)
                    .userAgent(this.crawlerConfig.getUserAgent()).timeout(5000)
                    .get();

            // get all links
            Elements links = doc.select(LINK_HREF);
            for (Element link : links) {

                String temp = link.attr(HREF);
                if (temp.startsWith(URL_START)) {
                    urlStr = urlStr.toUpperCase();

                    String result = getURL(temp).toUpperCase();
                    if (result.indexOf(lead.getPropertyAddress().getStreetAddress().getStreetNumber().toUpperCase()) > -1
                            && result.indexOf(lead.getPropertyAddress().getStreetAddress().getStreetName().toUpperCase()) > -1) {

                        return urlStr;
                    }
                }
            }
            System.err.println("URL not found: " + lead.getPropertyAddress().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] downloadFile(String linkUrl)
    {
        byte[] bytes = null;

        try
        {
            bytes = Jsoup.connect(linkUrl)
                    .header("Accept-Encoding", "gzip, deflate")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                    .referrer(linkUrl)
                    .ignoreContentType(true)
                    .maxBodySize(0)
                    .timeout(this.crawlerConfig.getDownloadTimeout())
                    .execute()
                    .bodyAsBytes();

            logger.info(new String(bytes));


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytes;
    }

    public byte[] downloadResults(String searchUrl, @Nullable String filename){
        String csv = convertHtmlToCsv(searchUrl);
        byte[] recordBytes = csv.getBytes();
        if (filename != null){
            saveToFile(recordBytes, filename);
        }
        return recordBytes;
    }

    private void saveToFile(byte[] fileStream, String filename){
        try
        {
            FileUtils.writeByteArrayToFile(new File(filename), fileStream);
        } catch (IOException e) {
            logger.error("Unable to save downloaded HUD: " + filename);
            throw new RuntimeException(e);
        }
    }

    public String convertHtmlToCsv(String url){
        logger.info("Converting HTML to CSV...");
        StringBuilder sb = new StringBuilder();
        Document doc;
        try {
            doc = Jsoup.connect(url)
                    .header("Accept-Encoding", "gzip, deflate")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                    .ignoreContentType(true)
                    .maxBodySize(0)
                    .timeout(600000)
                    .get();
            Element table = doc.body();
            Elements rows = table.select("tr");
            Elements ths = rows.select("th");

            StringBuilder sbRow = new StringBuilder();

            for (Element th : ths) {

                sbRow.append(CommonConstants.DOUBLE_QUOTE).append(th.text()).append(CommonConstants.DOUBLE_QUOTE).append(CommonConstants.COMMA);
            }

            //remove last comma
            sb.append(sbRow.toString().substring(0,sbRow.toString().length()-1)).append(CommonConstants.NEW_LINE);
            logger.debug("header=> " + sb.toString());

            for (Element row : rows) {
                Elements tds = row.select("td");

                sbRow = new StringBuilder();
                for (Element td : tds) {
                    sbRow.append(CommonConstants.DOUBLE_QUOTE).append(td.text()).append(CommonConstants.DOUBLE_QUOTE).append(CommonConstants.COMMA);
                }
                logger.debug("tds => " + sbRow.toString());

                if (sbRow.length() > 0)
                {
                    sb.append(sbRow.toString().substring(0,sbRow.length()-1)).append(CommonConstants.NEW_LINE);
                }

            }
            logger.debug("body=> " + sb.toString());

            // logger.info(table);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }

}
