package workshop.task_1_1;

import java.util.List;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class AbbrivationBuilder {


    public static String build(List<String> list) {
        return list.stream()
                .filter(s -> s != null && !s.isEmpty())
                .map((s) -> s.substring(0, 1))
                .reduce("", (s, s2) -> s + s2);
    }

}
