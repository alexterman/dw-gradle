package com.fin.bank.api;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {

    public String id;
    public Status status;
    public String error;
}
