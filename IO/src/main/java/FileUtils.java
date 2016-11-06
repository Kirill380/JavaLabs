import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

    public static List<String> readLines(InputStream in, Charset charset) throws IOException {
        List<String> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, charset))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.add(line);
            }
        }
        return res;
    }


    public static void writeLines(OutputStream out, List<String> lines, Charset charset) throws IOException {
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out, charset))) {
            for (String line : lines) {
                br.write(line);
            }
        }
    }

}
