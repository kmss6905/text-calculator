import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@TestInstance(Lifecycle.PER_CLASS)
class TextCalculatorTest {
    private TextCalculator cal;

    @BeforeAll
    void init() {
        cal = new TextCalculator();
    }

    @Test
    @DisplayName("빈 문자열 또는 null 값을 입력할 경우 0을 반환한다.")
    void notNullAndEmptyTest() {
        String emptyText = "";
        int parse = cal.parse(emptyText);
        assertThat(parse).isEqualTo(0);
    }

    @ParameterizedTest
    @DisplayName("(,) 또는 (:)콜론을 구분자로 구분하여 문자열 합을 구한다.")
    @ValueSource(strings = {"1,2,3", "1,2:3", "1:2,3"})
    void sum(String arg) {
        // when
        int sum = cal.parse(arg);

        // then
        assertThat(sum).isEqualTo(6);
    }

    @ParameterizedTest
    @DisplayName("(,) 또는 (:)콜론을 구분자로 구분하여 문자열 합을 구한다. 단, 음수의 경우 예외를 반환한다.")
    @ValueSource(strings = {"-1,2,3", "1,2:-3", "1:-2,3"})
    void shouldExceptionWhenNegative(String arg) {
        // then
        assertThatThrownBy(() -> cal.parse(arg))
                .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @DisplayName("앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 \"//\" 와 \"\\n\"사이에 위치하는 문자를 커스텀 구분자로 사용한다.")
    @ValueSource(strings = {"//;\n1,2,3", "//;;;;\n1,2,3", "1,2//;\n3"})
    void customSplitFinalTest(String arg) {
        // when
        int sum = cal.parse(arg);

        // then
        assertThat(sum).isEqualTo(6);
    }
}