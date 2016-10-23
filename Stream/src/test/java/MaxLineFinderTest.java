import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class MaxLineFinderTest extends Assert{

    @Test
    public void testRegularFile() throws Exception {
        final String expectedMaxLine = "Culpa deserunt doloremque ducimus error hic iusto laboriosam minima nam, quia totam vel vitae!";
        Pair<Integer, String> maxLine = MaxLineFinder.findMaxLine(MaxLine.class.getClassLoader().getResourceAsStream("regular_file.txt"));
        System.out.println("Max line is \"" + maxLine.getValue() + "\" with " + maxLine.getKey() + " words.");
        assertEquals(expectedMaxLine, maxLine.getValue());
    }

    @Test
    public void testEmptyFile() throws Exception {
        final String expectedMaxLine = "File is empty";
        Pair<Integer, String> maxLine = MaxLineFinder.findMaxLine(MaxLine.class.getClassLoader().getResourceAsStream("empty.txt"));
        System.out.println("Max line is \"" + maxLine.getValue() + "\" with " + maxLine.getKey() + " words.");
        assertEquals(expectedMaxLine, maxLine.getValue());
    }


}
