package com.rave.pads;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import io.micronaut.runtime.Micronaut;

//public class Application {
//    public static void main(String[] args) {
//        Micronaut.run(Application.class, args);
//    }
//}

public class Application extends MicronautLambdaHandler {

    public Application() throws ContainerInitializationException {
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}