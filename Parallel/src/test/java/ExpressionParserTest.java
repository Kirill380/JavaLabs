import org.junit.Test;

import java.util.List;

/**
 * Created by Kirill Liubun on 06/11/2016.
 */
public class ExpressionParserTest {

    @Test
    public void testExpression() {
        ExpressionParser parser = new ExpressionParser();
        List<String> list = parser.parse("-90/2 + 3*(5/6 + 788*76)");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
