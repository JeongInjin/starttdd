package com.injin.starttdd.appc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GameGenMockTest {

    @Test
    void mockTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        assertThat(genMock).isNotNull();

        given(genMock.generate(GameLevel.EASY)).willReturn("123");
        String num = genMock.generate(GameLevel.EASY);
        assertThat(num).isEqualTo("123");
    }

    @Test
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> genMock.generate(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
