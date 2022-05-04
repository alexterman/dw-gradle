package com.fin.bank;

import com.fin.bank.resources.DepositsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

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
        bootstrap.addBundle(new SwaggerBundle<DepositsAppConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DepositsAppConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final DepositsAppConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(DepositsResource.class);
    }

}
