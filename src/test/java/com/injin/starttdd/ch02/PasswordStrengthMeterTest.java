package com.injin.starttdd.ch02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 검사할 규칙은 다음 세 가지이다.
 *  길이가 8글자 이상
 *  0부터 9 사이의 숫자를 포함
 *  대문자 포함
 *
 *  세 규칙을 모두 충족하면 암호는 강함이다.
 *  2개의 규칙을 충족하면 암호는 보통이다.
 *  1개 이하의 규칙을 충족하면 암호는 약함이다.
 */
public class PasswordStrengthMeterTest {


    private static final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertThat(result).isEqualTo(expStr);
    }

    @Test
    public void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    public void meetsOtherCriteria_except_for_Length_Then_Normal() throws Exception {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    public void meetsOtherCriteria_except_for_number_Then_Normal(){
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }
    @Test
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @Test
    void meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
