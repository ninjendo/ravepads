package com.rave.pads.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

//@Getter
//@Setter
@ConfigurationProperties("hud")
public class HudConfig {
    @NotNull
    private String defaultSearchUrl;
    private String searchUrl;
    private String stateFilter;
    private String buyerTypeFilter;
    private String statusFilter;

    public String getDefaultSearchUrl() {
        return defaultSearchUrl;
    }
    public void setDefaultSearchUrl(String defaultSearchUrl) {
        this.defaultSearchUrl = defaultSearchUrl;
    }
    public String getStateFilter() {
        return stateFilter;
    }
    public void setStateFilter(String stateFilter) {
        this.stateFilter = stateFilter;
    }
    public String getBuyerTypeFilter() {
        return buyerTypeFilter;
    }
    public void setBuyerTypeFilter(String buyerTypeFilter) {
        this.buyerTypeFilter = buyerTypeFilter;
    }
    public String getStatusFilter() {
        return statusFilter;
    }
    public void setStatusFilter(String statusFilter) {
        this.statusFilter = statusFilter;
    }
    public String getSearchUrl() {
        return searchUrl;
    }
    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }
}
