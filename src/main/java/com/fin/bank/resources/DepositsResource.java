package com.fin.bank.resources;

import com.codahale.metrics.annotation.Timed;
import com.fin.bank.api.ErrorResponse;
import com.fin.bank.api.deposit.DepositRequest;
import com.fin.bank.api.deposit.DepositResponse;
import com.fin.bank.api.deposit.DepositStatus;
import com.fin.bank.core.DepositService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Api
@Path("/deposit")
public class DepositsResource {

    private DepositService depositService;

    public DepositsResource (DepositService depositService) {
        this.depositService = depositService;
    }
    @POST
    @Timed
    @ApiOperation(value = "Deposit Request", response = Response.class)
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successful deposit operation",
                    response = DepositResponse.class),
            @ApiResponse(
                    code = 500, message = "Unsuccessful deposit operation", response = ErrorResponse.class)


    })


    public Response deposit(@NotNull DepositRequest depositRequest) {

        String operationId = depositService.deposit(depositRequest.amount);

        return Response.ok(new DepositResponse(operationId, DepositStatus.SUCCESS)).build();

    }
}
