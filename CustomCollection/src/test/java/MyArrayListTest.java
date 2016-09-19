import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import ua.kpi.collections.lists.MyArrayList;
import ua.kpi.collections.lists.MyList;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Arrays.asList;

@RunWith(Theories.class)
public class MyArrayListTest extends Assert implements MyListTest {

    private MyList<String> list;
    public static @DataPoints int[] candidates = { -1, 0 };


 
    @Before
    public void startUp() {
        list = new MyArrayList<>();
    }


    @Test
    @Override
    public void testCopyConstructor() {
        list = new MyArrayList<>(of("a", "b", "c", "d"));
        assertEquals(list.size(), 4);
    }

    @Test
    @Override
    public void testGet() {
        list = new MyArrayList<>(of("a", "b", "c", "d"));
        assertEquals(list.get(2), "c");
    }


    @Test
    @Override
    public void testAdd() {
        String item = "a";
        list.add(item);
        assertEquals(list.size(), 1);
        assertEquals(list.get(0), item);
    }



    @Test
    @Override
    public void testToArray() {
        String[] exp = new String[]{"a", "b", "c", "d"};
        list = new MyArrayList<String>(asList(exp));
        Object[] a = list.toArray();
        assertArrayEquals(a, exp);
    }


    @Test
    @Override
    public void testRemove() {
        list = new MyArrayList<>(of("a", "b", "c", "d"));
        list.remove(1);
        Object[] array = list.toArray();
        assertArrayEquals(array, new String[]{"a", "c", "d"});
    }


    @Test
    @Override
    public void testIndexOf() {
        list = new MyArrayList<>(of("a", "b", "c", "d"));
        int index = list.indexOf("c");
        assertEquals(index, 2);
    }


    @Test
    @Override
    public void testIndexOfNull() {
        list = new MyArrayList<>(asList("a", null, "c", "d", null, "e"));
        int index = list.indexOf(null);
        assertEquals(index, 1);
    }


    @Test
    @Override
    public void testIndexOfNotExist() {

    }


    @Test
    @Override
    public void testSet() {
        list = new MyArrayList<>(of("a", "b", "c", "d"));
        list.set(1, "z");
        assertEquals(list.get(1), "z");
    }



    @Test
    @Override
    public void testAddToIndex() {
        list = new MyArrayList<>(of("a", "b", "c", "d"));
        list.add(1, "z");
        Object[] array = list.toArray();
        assertArrayEquals(array, new String[]{"a", "z", "b", "c", "d"});
    }



    @Test
    @Override
    public void testAddAll() {
        list = new MyArrayList<>(of("a", "b", "c", "d"));
        list.addAll(of("e", "f", "g", "h"));
        Object[] array = list.toArray();
        assertArrayEquals(array, new String[]{"a", "b", "c", "d", "e", "f", "g", "h"});
    }


    @Test
    @Override
    public void testAddAllToIndex() {
        list = new MyArrayList<>(of("a", "b", "c", "d"));
        list.addAll(2, of("e", "f", "g", "h"));
        Object[] array = list.toArray();
        assertArrayEquals(array, new String[]{"a", "b", "e", "f", "g", "h", "c", "d",});
    }


    @Test
    public void testAddMoreThanTenTime() {
        list = new MyArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("a");
        }
        assertEquals(list.size(), 20);
    }


    // 10 -> 15, but add 8 new elements and size should be 18

    @Test
    public void testAddMoreThanSizeOfIncreasedArray() {
        list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("a");
        }
        list.addAll(of("a", "b", "c", "d", "e", "f", "g", "h"));

        assertEquals(list.size(), 18);
    }


    @Theory
    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckGet(int wrongIndex) {
        list = new MyArrayList<>();
        list.get(wrongIndex);
    }



    @Theory
    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckAdd(int wrongIndex) {
        list = new MyArrayList<>();
        list.add(wrongIndex, "item");
    }


    @Theory
    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckAddAll(int wrongIndex) {
        list = new MyArrayList<>();
        list.addAll(wrongIndex, of("a", "b", "c", "d"));
    }


    @Theory
    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckRemove(int wrongIndex) {
        list = new MyArrayList<>();
        list.remove(wrongIndex);
    }


    @Theory
    @Override
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckSet(int wrongIndex) {
        list = new MyArrayList<>();
        list.set(wrongIndex, "a");
    }
}
