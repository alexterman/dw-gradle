package com.fin.bank.core.exceptions;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.fin.bank.api.ErrorResponse;
import com.fin.bank.api.Status;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;


@Provider
public class DepositsExceptionMapper implements ExceptionMapper<RuntimeException> {
    private final Meter exceptions;
    public DepositsExceptionMapper(MetricRegistry metrics) {
        exceptions = metrics.meter(MetricRegistry.name(getClass(), "exceptions"));
    }

    @Override
    public Response toResponse(RuntimeException e) {

        String id = UUID.randomUUID().toString();

        if (e instanceof ValidationException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(new ErrorResponse(id, Status.CLIENT_ERROR,
                            e.getMessage()))
                    .build();
        } else if (e instanceof NotHealthyException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(new ErrorResponse(id, Status.NO_PONG,
                            e.getMessage()))
                    .build();
        }

        exceptions.mark();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new ErrorResponse(id, Status.INTERNAL_ERROR,
                        e.getMessage()))
                .build();
    }
}
