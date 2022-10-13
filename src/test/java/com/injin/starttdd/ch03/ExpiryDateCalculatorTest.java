package com.injin.starttdd.ch03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * 매달 비용을 지불해야 사용할 수 있는 유료 서비스가 있다고 해보자. 서비스는 다음 규칙에 따라 서비스 만료일을 결정한다.
 * 1.서비스를 사용하려면 매달 1만 원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
 * 2.2개월 이상 요금을 납부할 수 있다.
 * 3.10만 원을 납부하면 서비스를 1년 제공한다.
 */
public class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_된다() {
        assertExpiryDate(
                LocalDate.of(2022, 3, 1),10_000,
                LocalDate.of(2022, 4, 1));
        assertExpiryDate(
                LocalDate.of(2022, 5, 5),10_000,
                LocalDate.of(2022, 6, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                LocalDate.of(2019, 1, 31),10_000,
                LocalDate.of(2019, 2, 28));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculatorExpiryDate(billingDate, payAmount);
        assertThat(expiryDate).isEqualTo(expectedExpiryDate);
    }
}
