package com.rave.pads.controller;
import com.rave.pads.exception.MissingDataException;
import com.rave.pads.model.State;
import com.rave.pads.sevice.HudHomePropertyService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.Map;

@Controller("/hud")
public class HudController {

    @Get("/greet")
    public Map<String, Object> greetHello() {
        return Collections.singletonMap("message", "Hello HUD");
    }

    @Inject
    private HudHomePropertyService hudService;
    @Get
    public Map<String, Object> index() {
        return Collections.singletonMap("message", "Hello World HOME");
    }

    @Get(value = "/properties/{stateCode}", consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    public Map<String, Object> downloadByState(String stateCode) throws MissingDataException {
        hudService.downloadHudProperties(State.valueOf(stateCode), null);
        return Collections.singletonMap("message", "Success download!");
    }

    @Get("/greet/{name}")
    public Map<String, Object> greet(String name) {
        return Collections.singletonMap("message", "Hello World " + name);
    }
}
