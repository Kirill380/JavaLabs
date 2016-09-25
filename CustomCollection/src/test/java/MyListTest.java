public interface MyListTest {

    void testCopyConstructor();

    void testGet();

    void testAdd();

    void testToArray();

    void testRemove();

    void testIndexOf();

    void testIndexOfNull();

    void testIndexOfNotExist();

    void testSet();

    void testAddToIndex();

    void testAddAll();

    void testAddAllToIndex();

    void testRangeCheckGet(int wrongIndex);

    void testRangeCheckAdd();

    void testRangeCheckAddAll();

    void testRangeCheckRemove(int wrongIndex);

    void testRangeCheckSet(int wrongIndex);
}
