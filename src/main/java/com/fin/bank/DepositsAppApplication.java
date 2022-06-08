package com.fin.bank;

import com.fin.bank.core.DepositService;
import com.fin.bank.core.exceptions.DepositsExceptionMapper;
import com.fin.bank.health.DepositHealthCheck;
import com.fin.bank.resources.DepositsHealthResource;
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
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DepositsAppConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final DepositsAppConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new DepositsHealthResource(environment.healthChecks()));
        environment.jersey().register(new DepositsResource(new DepositService()));
        environment.healthChecks().register("deposit", new DepositHealthCheck());
        environment.jersey().register(new DepositsExceptionMapper(environment.metrics()));
    }

}
