package com.fin.bank;

import com.fin.bank.resources.DepositsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DepositsAppApplication extends Application<DepositsAppConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DepositsAppApplication().run(args);
    }

    @Override
    public String getName() {
        return "DepositsApp";
    }

    @Override
    public void initialize(final Bootstrap<DepositsAppConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DepositsAppConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(DepositsResource.class);
    }

}
