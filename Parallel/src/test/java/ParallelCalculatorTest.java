import org.junit.Test;

public class ParallelCalculatorTest {

    @Test
    public void testCalculation()  {
        ParallelCalculator parallelCalculator = new ParallelCalculator();
        double res = parallelCalculator.calculate("");
        System.out.printf(String.valueOf(res));
    }
}
