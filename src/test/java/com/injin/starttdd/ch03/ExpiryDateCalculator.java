package com.injin.starttdd.ch03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    int addedMonths = 1;

    public LocalDate calculatorExpiryDate(PayData payData) {
        if (payData.getFirstBillingDate() != null) {
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if (payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(1);
    }
}
