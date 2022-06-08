package com.fin.bank.health;

import com.codahale.metrics.health.HealthCheck;

public class DepositHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
