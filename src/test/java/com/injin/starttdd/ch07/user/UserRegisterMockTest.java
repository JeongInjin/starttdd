package com.injin.starttdd.ch07.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

public class UserRegisterMockTest {

    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);
        assertThatThrownBy(() -> userRegister.register("id", "pw", "email")).isInstanceOf(WeakPasswordException.class);
    }

    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        then(mockPasswordChecker).should().checkPasswordWeak(Mockito.matches("pw"));
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupId() {
        fakeRepository.save(new User("id", "pw", "email@eamil.com"));

        assertThatThrownBy(() -> userRegister.register("id", "pw2", "email"))
                .isInstanceOf(DupIdException.class);
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@eamil.com");

        //captor 메서드를 사용하면, 메서드를 호출할 때 전달한 인자가 ArgumentCaptor 에 담긴다.
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier).should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertThat(realEmail).isEqualTo("email@eamil.com");
    }
}
