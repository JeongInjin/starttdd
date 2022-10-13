package com.injin.starttdd.ch02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);
        Assertions.assertThat(result).isEqualTo(3);
        Assertions.assertThat(Calculator.plus(4, 1)).isEqualTo(5);
    }
}
