package com.fin.bank.resources;

import com.codahale.metrics.annotation.Timed;
import com.fin.bank.api.ErrorResponse;
import com.fin.bank.api.PingResponse;
import com.fin.bank.api.Status;
import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
@Api
public class DepositsResource {

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
    public Response sayHello() {

        return Response.ok(new PingResponse(UUID.randomUUID().toString(), Status.PONG)).build();

    }
}
