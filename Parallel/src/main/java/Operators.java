import java.util.function.BiFunction;

/**
 * Created by Kirill Liubun on 06/11/2016.
 */
public enum Operators {
    ADD((a, b) -> a + b, "+"),
    SUB((a, b) -> a - b, "-"),
    MUL((a, b) -> a * b, "*"),
    DIV((a, b) -> a / b, "/"),
    FINAL((a, b) -> 0.0, "final");

    private final BiFunction<Double, Double, Double> function;
    private final String symbol;


    Operators(BiFunction<Double, Double, Double> function, String symbol) {
        this.function = function;
        this.symbol = symbol;
    }

    public BiFunction<Double, Double, Double> getFunction() {
        return function;
    }

    public String getSymbol() {
        return symbol;
    }
}
