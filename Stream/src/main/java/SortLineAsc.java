import java.io.*;
import java.util.stream.Stream;

import static java.lang.Integer.compare;

public class SortLineAsc {

    public static void sort(InputStream targetFile, FileWriter destFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(targetFile));
              PrintWriter pw = new PrintWriter(new BufferedWriter(destFile))) {
            Stream<String> lines = br.lines();
            lines.sorted((o1, o2) -> compare(o1.length(), o2.length())).forEach(pw::println);
        }
    }
}
