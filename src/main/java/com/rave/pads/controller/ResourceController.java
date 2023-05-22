package com.rave.pads.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import java.util.Collections;
import java.util.Map;

import com.rave.pads.model.ScheduleUpdate;

@Controller("/grids")
public class ResourceController {

    @Get
    public Map<String, Object> index() {
        return Collections.singletonMap("message", "Hello GRIDS");
    }

    @Get(uri = "/{gridId}/rave", produces = MediaType.APPLICATION_JSON)
    public HttpResponse all(@PathVariable String gridId) {
        ScheduleUpdate su = new ScheduleUpdate();
        su.setId(gridId);
        return HttpResponse.ok(su).headers(Collections.singletonMap("Access-Control-Allow-Origin", "*"));
    }
}

