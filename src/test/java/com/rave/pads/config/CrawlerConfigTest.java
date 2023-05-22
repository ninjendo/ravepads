package com.rave.pads.config;

import io.micronaut.context.ApplicationContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
@MicronautTest
public class CrawlerConfigTest {

    @Test
    void testCrawlerConfig() {
        Map<String, Object> items = new HashMap<>();
//        items.put("crawler.googleSearchUrl", "evolution");
//        items.put("crawler.queryResultLimit", "&num=20");
        items.put("crawler.downloadTimeout", 60000);

        ApplicationContext ctx = ApplicationContext.run(items);
        CrawlerConfig config = ctx.getBean(CrawlerConfig.class);

        assertEquals("https://www.google.com/#q=", config.getGoogleSearchUrl());
        assertEquals("", config.getRedfinUrlPattern());
        assertEquals(60000, config.getDownloadTimeout());

        ctx.close();
    }
}
