package workshop.task_1_2;

import java.util.stream.LongStream;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class Factorial {


    public static long factorial(long i) {
        return LongStream
                .iterate(1, operand -> operand + 1)
                .limit(i)
                .reduce(1, (sum, curr) -> sum * curr);


    }
}
