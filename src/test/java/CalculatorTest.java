import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
class CalculatorTest {
    private Calculator cal;

    @BeforeAll
    public void setup() {
        cal = new Calculator();
        System.out.println("before");
    }

    @Test
    void add() {
        assertEquals(7, cal.add(3,4));
        System.out.println("add");
    }

    @Test
    void subtract() {
        assertEquals(-1, cal.subtract(3, 4));
        System.out.println("subtract");
    }

    @AfterAll
    void teardown() {
        System.out.println("teardown");
    }
}