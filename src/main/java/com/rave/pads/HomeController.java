package com.rave.pads;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.Collections;
import java.util.Map;

@Controller("/home")
public class HomeController {

    @Get
    public Map<String, Object> index() {
        return Collections.singletonMap("message", "Hello World HOME");
    }

    @Get("/greet")
    public Map<String, Object> greet() {
        return Collections.singletonMap("message", "Hello World greet");
    }

    @Get("/greet/{name}")
    public Map<String, Object> greet(String name) {
        return Collections.singletonMap("message", "Hello World " + name);
    }
}
