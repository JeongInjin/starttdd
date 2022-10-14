package com.injin.starttdd.ch07.autodebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.injin.starttdd.ch07.autodebit.CardValidity.THEFT;
import static com.injin.starttdd.ch07.autodebit.CardValidity.VALID;
import static org.assertj.core.api.Assertions.assertThat;

public class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard() {
        // 업체에서 받은 테스트용 유효한 카드 번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        assertThat(result.getValidity()).isEqualTo(VALID);
    }

    @Test
    void theftCard() {
        // 업체에서 받은 도난 테스트용 카드 번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);
        assertThat(result.getValidity()).isEqualTo(THEFT);
    }
}
