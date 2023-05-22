package com.rave.pads.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ConfigurationProperties("crawler")
public class CrawlerConfig {
 	/*

	 	userAgent: Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)
	googleSearchUrl: https://www.google.com/#q=
	queryResultLimit: &num=20
	zillowUrlPattern: http[s]{0,1}://www\\.zillow.+_zpid
	redfinUrlPattern:


	 * */

    private String userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
    @NotNull
    private String googleSearchUrl;
    private String queryResultLimit;
    @NotNull
    private String zillowUrlPattern;
    private String redfinUrlPattern;
    private String hudSearchResultInExcelUrl;
    private int downloadTimeout;

    public String getHudSearchResultInExcelUrl() {
        return hudSearchResultInExcelUrl;
    }
    public void setHudSearchResultInExcelUrl(String hudSearchResultInExcelUrl) {
        this.hudSearchResultInExcelUrl = hudSearchResultInExcelUrl;
    }
    public String getUserAgent() {
        return userAgent;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public String getGoogleSearchUrl() {
        return googleSearchUrl;
    }
    public void setGoogleSearchUrl(String googleSearchUrl) {
        this.googleSearchUrl = googleSearchUrl;
    }
    public String getQueryResultLimit() {
        return queryResultLimit;
    }
    public void setQueryResultLimit(String queryResultLimit) {
        this.queryResultLimit = queryResultLimit;
    }
    public String getZillowUrlPattern() {
        return zillowUrlPattern;
    }
    public void setZillowUrlPattern(String zillowUrlPattern) {
        this.zillowUrlPattern = zillowUrlPattern;
    }
    public String getRedfinUrlPattern() {
        return redfinUrlPattern;
    }
    public void setRedfinUrlPattern(String redfinUrlPattern) {
        this.redfinUrlPattern = redfinUrlPattern;
    }

    public void setDownloadTimeout(int downloadTimeout) {
        this.downloadTimeout = downloadTimeout;
    }
    public int getDownloadTimeout() {
        return downloadTimeout;
    }
}
