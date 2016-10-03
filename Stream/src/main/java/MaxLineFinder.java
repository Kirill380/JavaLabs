import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class MaxLineFinder {

    public static Pair<Integer, String> findMaxLine(InputStream in) {
        try (InputStreamReader inr = new InputStreamReader(in);
             BufferedReader br = new BufferedReader(inr)) {
            Stream<String> lines = br.lines();
            return lines
                    .map(l -> $(l.split("\\s").length, l))
                    .max((p1, p2) -> p1.getKey().compareTo(p2.getKey()))
                    .orElse($(0, "File is empty"));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return $(-1, "");
        }
    }

    private static <K, V> Pair<K, V> $(K key, V value) {
        return new Pair<>(key, value);
    }
}
