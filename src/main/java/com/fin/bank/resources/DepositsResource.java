package com.fin.bank.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
public class DepositsResource {

    @GET
    @Timed
    public Response sayHello() {

        return Response.ok("{status:pong}").build();

    }
}
