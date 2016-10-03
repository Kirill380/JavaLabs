import javafx.util.Pair;

import java.io.InputStream;

public class MaxLine {
    public static void main(String[] args) {
        String fileName = "test.txt";
        if (args.length > 0)
            fileName = args[0];

        InputStream in = MaxLine.class.getClassLoader().getResourceAsStream(fileName);
        Pair<Integer, String> maxLine = MaxLineFinder.findMaxLine(in);
        System.out.println("Max line is \"" + maxLine.getValue() + "\" with " + maxLine.getKey() + " words.");
    }


}
