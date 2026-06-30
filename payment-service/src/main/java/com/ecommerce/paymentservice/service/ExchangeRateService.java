package com.ecommerce.paymentservice.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeRateService {

    public BigDecimal getUsdToLkrRate() {
        return new BigDecimal("300.00");
    }

    public BigDecimal convertLkrToUsd(BigDecimal lkrAmount) {
        BigDecimal rate = getUsdToLkrRate();
        return lkrAmount.divide(rate, 2, RoundingMode.HALF_UP);
    }
}