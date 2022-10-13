package com.injin.starttdd.ch03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calculatorExpiryDate(LocalDate billingDate, int payAmount) {
        return billingDate.plusMonths(1);
    }
}
