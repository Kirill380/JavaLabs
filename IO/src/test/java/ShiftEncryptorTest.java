import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ShiftEncryptorTest extends Assert {
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    public final Character ENCODING_SYMBOL = '!';
    public static final String ORIGINAL_ONE_LINE_FILE = "one_line_original.txt";
    public static final String ENCODED_ONE_LINE_FILE = "one_line_encoded_by_!.txt";
    public static final String ORIGINAL = "AAAAAAA";
    public static final String ENCODED = "bbbbbbb";


    @Test
    public void testEncodeOneLineFile() throws IOException {
        ShiftEncryptor shiftEncryptor = new ShiftEncryptor();
        File testFile = folder.newFile("text.txt");
        InputStream source = ShiftEncryptorTest.class.getClassLoader().getResourceAsStream(ORIGINAL_ONE_LINE_FILE);
        shiftEncryptor.encode(source, new FileOutputStream(testFile), ENCODING_SYMBOL);
        String firstLine = Files.readFirstLine(testFile, Charset.defaultCharset());
        assertEquals(ENCODED, firstLine);
    }


    @Test
    public void testDecodeOneLineFile() throws IOException {
        ShiftEncryptor shiftEncryptor = new ShiftEncryptor();
        File testFile = folder.newFile("text.txt");
        InputStream source = ShiftEncryptorTest.class.getClassLoader().getResourceAsStream(ENCODED_ONE_LINE_FILE);
        shiftEncryptor.decode(source, new FileOutputStream(testFile), ENCODING_SYMBOL);
        String firstLine = Files.readFirstLine(testFile, Charset.defaultCharset());
        assertEquals(ORIGINAL, firstLine);
    }
}
