import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theory;
import ua.kpi.collections.lists.MyLinkedList;
import ua.kpi.collections.lists.MyList;

import static com.google.common.collect.ImmutableList.of;

public class MyLinkedListTest extends Assert implements MyListTest {

    private MyList<String> list;


    @Before
    public void startUp() {
        list = new MyLinkedList<>();
    }

    @Test
    @Override
    public void testCopyConstructor() {
        list = new MyLinkedList<>(of("a", "b", "c", "d"));
        assertEquals(list.size(), 4);
    }

    @Test
    @Override
    public void testGet() {
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals(list.get(1), "b");
    }

    @Test
    @Override
    public void testAdd() {
        list.add("a");
        list.add("b");
        assertEquals(list.size(), 2);
    }

    @Test
    @Override
    public void testToArray() {

    }

    @Test
    @Override
    public void testRemove() {

    }

    @Test
    @Override
    public void testIndexOf() {

    }

    @Test
    @Override
    public void testIndexOfNull() {

    }

    @Override
    public void testIndexOfNotExist() {

    }

    @Test
    @Override
    public void testSet() {

    }

    @Test
    @Override
    public void testAddToIndex() {

    }

    @Test
    @Override
    public void testAddAll() {

    }

    @Test
    @Override
    public void testAddAllToIndex() {

    }

    @Theory
    @Override
    public void testRangeCheckGet(int wrongIndex) {

    }

    @Theory
    @Override
    public void testRangeCheckAdd(int wrongIndex) {

    }

    @Theory
    @Override
    public void testRangeCheckAddAll(int wrongIndex) {

    }

    @Theory
    @Override
    public void testRangeCheckRemove(int wrongIndex) {

    }

    @Theory
    @Override
    public void testRangeCheckSet(int wrongIndex) {

    }
}
