import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import ua.kpi.collections.lists.MyLinkedList;
import ua.kpi.collections.lists.MyList;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Arrays.asList;

@RunWith(Theories.class)
public class MyLinkedListTest extends Assert implements MyListTest {

    private MyList<String> list;

    @DataPoints
    public static int[] candidates = { -1, 0 };

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
        list = new MyLinkedList<>(of("a", "b", "c", "d"));
        Object[] array = list.toArray();
        assertArrayEquals(new String[]{"a", "b", "c", "d"}, array);
    }

    @Test
    @Override
    public void testRemove() {
        list = new MyLinkedList<>(of("a", "b", "c", "d"));
        list.remove(1);
        Object[] array = list.toArray();
        assertArrayEquals( new String[]{"a", "c", "d"}, array);
    }

    @Test
    @Override
    public void testIndexOf() {
        list = new MyLinkedList<>(of("a", "b", "c", "d"));
        int index = list.indexOf("c");
        assertEquals(index, 2);
    }

    @Test
    @Override
    public void testIndexOfNull() {
        list = new MyLinkedList<>(asList("a", null, "c", "d", null, "e"));
        int index = list.indexOf(null);
        assertEquals(index, 1);
    }

    @Test
    @Override
    public void testIndexOfNotExist() {
        list = new MyLinkedList<>(of("a", "b", "c", "d"));
        int index = list.indexOf("e");
        assertEquals(index, -1);
    }

    @Test
    @Override
    public void testSet() {
        list = new MyLinkedList<>(of("a", "b", "c", "d"));
        list.set(1, "z");
        assertEquals(list.get(1), "z");
    }

    @Test
    @Override
    public void testAddToIndex() {
        list = new MyLinkedList<>(of("a", "b", "c", "d"));
        list.add(1, "z");
        Object[] array = list.toArray();
        assertArrayEquals(array, new String[]{"a", "z", "b", "c", "d"});
    }

    @Test
    @Override
    public void testAddAll() {
        list = new MyLinkedList<>(of("a", "b", "c", "d"));
        list.addAll(of("e", "f", "g", "h"));
        Object[] array = list.toArray();
        assertArrayEquals(array, new String[]{"a", "b", "c", "d", "e", "f", "g", "h"});
    }

    @Test
    @Override
    public void testAddAllToIndex() {
        list = new MyLinkedList<>(of("a", "b", "c", "d"));
        list.addAll(2, of("e", "f", "g", "h"));
        Object[] array = list.toArray();
        assertArrayEquals(array, new String[]{"a", "b", "e", "f", "g", "h", "c", "d",});
    }

    @Theory
    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckGet(int wrongIndex) {
        list.get(wrongIndex);
    }



    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckAdd() {
        list.add(-1, "item");
    }



    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckAddAll() {
        list.addAll(-1, of("a", "b", "c", "d"));
    }


    @Theory
    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckRemove(int wrongIndex) {
        list.remove(wrongIndex);
    }


    @Theory
    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckSet(int wrongIndex) {
        list.set(wrongIndex, "a");
    }
}
