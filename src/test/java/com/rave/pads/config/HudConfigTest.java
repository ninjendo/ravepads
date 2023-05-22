package com.rave.pads.config;

import io.micronaut.context.ApplicationContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class HudConfigTest {

    @Test
    void testHudConfig() {
        Map<String, Object> items = new HashMap< >();
        items.put("hud.defaultSearchUrl", "http://www.hudhomestore.com/pages/ListExportToExcel.aspx?sState=GA");
        items.put("hud.searchUrl", "http://www.hudhomestore.com/pages/ListExportToExcel.aspx?sState=");

        ApplicationContext ctx = ApplicationContext.run(items);
        HudConfig config = ctx.getBean(HudConfig.class);

        assertEquals("http://www.hudhomestore.com/pages/ListExportToExcel.aspx?sState=GA", config.getDefaultSearchUrl());

        ctx.close();
    }
}
