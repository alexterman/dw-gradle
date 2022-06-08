package com.fin.bank.core;

import com.fin.bank.core.exceptions.ValidationException;

import java.util.UUID;

public class DepositService {

    public String deposit (double amount) {

        if(amount <= 0.0) {
            throw new ValidationException("The deposit amount can't be negative");
        }
        return UUID.randomUUID().toString();
    }
}
