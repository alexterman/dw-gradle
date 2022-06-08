package com.fin.bank.resources;

import com.codahale.metrics.annotation.Timed;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.fin.bank.api.ErrorResponse;
import com.fin.bank.api.ping.PingResponse;
import com.fin.bank.api.ping.PingStatus;
import com.fin.bank.core.exceptions.NotHealthyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.SortedMap;
import java.util.UUID;

@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
@Api
public class DepositsHealthResource {


    private HealthCheckRegistry healthCheckRegistry;

    public DepositsHealthResource (HealthCheckRegistry healthCheckRegistry) {

        this.healthCheckRegistry = healthCheckRegistry;
    }

    @GET
    @Timed
    @ApiOperation(value = "Request a ping", response = Response.class)
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successful ping operation",
                    response = PingResponse.class),
            @ApiResponse(
                    code = 500, message = "Unsuccessful ping operation", response = ErrorResponse.class)


    })
    public Response isAlive() {

        SortedMap<String, HealthCheck.Result> results = this.healthCheckRegistry.runHealthChecks();
        boolean isNotHealthy = results.entrySet().stream()
                .anyMatch(stringResultEntry -> !stringResultEntry.getValue().isHealthy());

        if(isNotHealthy) {
            throw new NotHealthyException("One of the health checks failed");
        }
        return Response.ok(new PingResponse(UUID.randomUUID().toString(), PingStatus.PONG)).build();

    }
}
