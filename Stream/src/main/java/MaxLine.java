import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class MaxLine {
    public static void main(String[] args) throws IOException {
        String fileName = "sortTest.txt";
        if (args.length > 0)
            fileName = args[0];

        InputStream in = MaxLine.class.getClassLoader().getResourceAsStream(fileName);
//        Pair<Integer, String> maxLine = MaxLineFinder.findMaxLine(in);
//        System.out.println("Max line is \"" + maxLine.getValue() + "\" with " + maxLine.getKey() + " words.");
        SortLineAsc.sort(in, new FileWriter("res.txt"));
    }


}
