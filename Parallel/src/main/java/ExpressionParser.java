import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {

    private String operators = "([+\\-*\\/\\(\\)])";
    private String number = "(-?\\d+(\\.\\d+)?)";
    private String trigonometry = "(sin|cos|tg|ctg)";

    public List<String> parse(String expression) {
        List<String> tokens = new ArrayList<>();
        Pattern p = Pattern.compile(String.join("|", number, operators, trigonometry));
        Matcher m = p.matcher(expression);
        Node curr;
        while(m.find()) {
            String token;
            if((token = m.group(1)) != null)  {

            } else if((token = m.group(2)) != null) {

            } else if((token = m.group(3)) != null) {

            }

            tokens.add(token);
        }
        return tokens;
    }
}
