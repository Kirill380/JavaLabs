import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class SortLineAscTest extends Assert {

    @Test
    public void testRegularFile() throws IOException {
        final String targetFile = "target.txt";
        SortLineAsc.sort(
                MaxLine.class.getClassLoader().getResourceAsStream("regular_file.txt"),
                new FileWriter(targetFile)
        );

        List<String> lines = Files.readLines(new File(targetFile), Charset.defaultCharset());
        assertTrue(checkOrder(lines));
    }

    private boolean checkOrder(List<String> lines) {
        boolean res = true;
        for (int i = 0; i < lines.size() - 1; i++) {
            res &= lines.get(i + 1).length() >= lines.get(i).length();
        }
        return res;
    }

    @Test
    public void testEmptyFile() throws Exception {
        final String targetFile = "target.txt";
        SortLineAsc.sort(
                MaxLine.class.getClassLoader().getResourceAsStream("empty.txt"),
                new FileWriter(targetFile)
        );
        List<String> lines = Files.readLines(new File(targetFile), Charset.defaultCharset());
        assertTrue(lines.isEmpty());
    }


}
