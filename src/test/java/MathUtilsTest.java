import org.example.MathUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathUtilsTest {

    private MathUtils mathUtils;

    @BeforeAll
     void beforeAll()
    {
        System.out.println("Run Before All ...");
    }

    @BeforeEach
    public void beforeEach()
    {
        mathUtils = new MathUtils();
        System.out.println("Run Before Each");
    }


    @Nested
    @DisplayName("Testing add method")
    class AddTest {
        @Test
        @DisplayName("Testing for positive numbers")
         void testAddPositive() {

            int expected = 4;
            int actual = mathUtils.add(2, 2);

            /*Using supplier (lambda) for assert Message which optimizes computation speed
            by ignoring the error message to be computed if test case is success
            and only compute error message by method assertEquals if the test case fails
             */
            assertEquals(expected, actual, () -> "should return "+expected +" but returned "+actual);
        }

        @Test
        @DisplayName("Testing for negative numbers")
        void testAddNegative()
        {

            assertEquals(-2,mathUtils.add(-1,-1),"should return a correct addition");
        }
    }

    @Test
    @DisplayName("Testing multiply method")
    void testMultiply()
    {
        assertAll(
                () -> assertEquals(4,mathUtils.multiply(2,2)),
                () -> assertEquals(6,mathUtils.multiply(2,3)),
                () -> assertEquals(8,mathUtils.multiply(2,4))
        );
    }

    @RepeatedTest(3)
    @DisplayName("Testing computeCircleArea Method")
    public void testComputeCircleArea(RepetitionInfo repetitionInfo)
    {
       System.out.println("Current Repetition " +repetitionInfo.getCurrentRepetition());
        double expected = 12.566370614359172;
        double actual = mathUtils.computeCircleArea(2);
        assertEquals(expected,actual,"Output must be equals PI*(radius^2)");

    }

    @Test
    @DisplayName("Testing divide Method 1")
    @EnabledOnOs(OS.WINDOWS)

    public void testDivide()
    {

        assertThrows(ArithmeticException.class,() -> mathUtils.divide(1,0),"Use 0 on one of the arguments");
    }

    @Test
    @DisplayName("Testing divide Method 2")
    void testDivide2()
    {
        int a = 1;
        int b = 1;
        boolean isNonZero = a > 0 && b > 0;
        assumeTrue(isNonZero);
        assertEquals(1,mathUtils.divide(a,b));

    }


    @Test
    @DisplayName("This test must not run")
    @Disabled
    public void avoid()
    {
        fail("This test should be disabled");
    }
}
