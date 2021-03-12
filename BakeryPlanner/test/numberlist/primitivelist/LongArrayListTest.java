package numberlist.primitivelist;

import numberlist.IndexException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
public class LongArrayListTest {

    LongArrayList list;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new LongArrayList();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of add method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testAddFirst() throws IndexException {
        list.add(0, 123L);
        assertEquals(1, list.getCount());
        assertEquals(123, list.getValue(0));
    }

    @Test
    public void testAddLots() throws IndexException {
        for (int i = 0; i < 1000; i++) {
            list.add(i, i * 10);
        }
        assertEquals(1000, list.getCount());
        for (int i = 0; i < 1000; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
    }

    @Test
    public void testAddMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        list.add(2, 123);
        assertEquals(6, list.getCount());
        assertEquals(0, list.getValue(0));
        assertEquals(10, list.getValue(1));
        assertEquals(123, list.getValue(2));
        assertEquals(20, list.getValue(3));
        assertEquals(30, list.getValue(4));
        assertEquals(40, list.getValue(5));
    }

    @Test
    public void testAddNegative() {
        for (int i = 0; i < 10; i++) {
            try {
                list.add(i, i * 10);
            } catch (IndexException ex) {
                Assert.fail("testAddNegative threw an exception and it should not have.");
            }
        }
        int expectedSize = list.getCount();
        boolean flag = false;
        try {
            list.add(-1, 0);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount(), ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testAddNegative Exception Handled");
        }
        assertEquals(expectedSize, list.getCount());
        assertTrue(flag);
    }

    @Test
    public void testAddTooLarge() {
        for (int i = 0; i < 10; i++) {
            try {
                list.add(i, i * 10);
            } catch (IndexException ex) {
                Assert.fail("testAddTooLarge threw an exception and it should not have.");
            }
        }
        int expectedCount = list.getCount();
        boolean flag = false;
        try {
            list.add((expectedCount + 1), 0);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount(), ex.getMax());
            assertSame((expectedCount + 1), ex.getValue());
        }
        assertEquals(expectedCount, list.getCount());
        assertTrue(flag);
        System.out.println("testAddTooLarge Exception Handled");
    }

    @Test
    public void testAddLotsMiddle() throws IndexException {
        for (int i = 0; i < 100; i++) {
            list.add(i, i * 10);
        }
        list.add(15, 123);
        assertEquals(101, list.getCount());
        for (int i = 0; i < 15; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
        assertEquals(123, list.getValue(15));
        for (int i = 16; i < 100; i++) {
            assertEquals((i - 1) * 10, list.getValue(i));
        }
    }

    /**
     * Test of set method with index, of class LongArrayList.
     */
    @Test
    public void testSetEmpty() {
        boolean flag = false;
        try {
            list.set(0, 0);
        } catch (IndexException ex) {
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(0, ex.getValue());
            System.out.println("testSetEmpty exception handled");
        }
        assertTrue(flag);
        assertEquals(0, list.getCount());
    }

    @Test
    public void testSetFirst() throws IndexException {
        list.add(0, 1);
        int listSize = list.getCount();
        try {
            list.set(0, 0);
        } catch (IndexException ex) {
            Assert.fail("testSetEmpty threw an exception when it should not have.");
        }
        assertEquals(0, list.getValue(0));
        assertEquals(listSize, list.getCount());
    }

    @Test
    public void testSetMiddle() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, i);
        }
        int listSize = list.getCount();
        list.set(5, 0);
        assertEquals(0, list.getValue(5));
        assertEquals(listSize, list.getCount());
    }

    @Test
    public void testSetLast() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, i);
        }
        int listSize = list.getCount();
        list.set(9, 0);
        assertEquals(0, list.getValue(9));
        assertEquals(listSize, list.getCount());
    }

    /**
     * Test of remove method with index, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveFirstIndex() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        long value = list.remove(0);
        assertEquals(0, value);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals((i + 1) * 10, list.getValue(i));
        }
    }

    @Test
    public void testremoveLastIndex() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        long value = list.remove(4);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
    }

    /**
     * Test of remove method with value, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveMiddleValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        for (int i = 5; i < 10; i++) {
            list.add(i, (i - 5) * 10);
        }
        for (int i = 10; i < 15; i++) {
            list.add(i, (i - 10) * 10);
        }
        list.remove(20L);
        assertEquals(14, list.getCount());
        for (int i = 0; i < 2; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
        for (int i = 2; i < 4; i++) {
            assertEquals((i + 1) * 10, list.getValue(i));
        }
        for (int i = 4; i < 9; i++) {
            assertEquals((i - 4) * 10, list.getValue(i));
        }
        for (int i = 9; i < 14; i++) {
            assertEquals((i - 9) * 10, list.getValue(i));
        }
    }

    @Test
    public void testRemoveNonexistentValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        list.remove(123L);
        assertEquals(5, list.getCount());
        for (int i = 0; i < 5; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
    }

    @Test
    public void testRemoveFirstValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, (i + 1) * 10);
        }
        list.remove(10L);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals((i + 2) * 10, list.getValue(i));
        }
    }

    @Test
    public void testRemoveLastValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        list.remove(40L);
        assertEquals(4, list.getCount());
        for (int i = 0; i < 4; i++) {
            assertEquals(i * 10, list.getValue(i));
        }
    }

    @Test
    public void testRemoveNegativeIndex() {
        boolean flag = false;
        for (int i = 0; i < 10; i++) {
            try {
                list.add(i, i * 10);
            } catch (IndexException ex) {
                Assert.fail("testRemoveNegativeIndex threw an exception when it should not have.");
            }
        }
        int listCount = list.getCount();
        try {
            long returnValue = list.remove(-1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame((list.getCount() - 1), ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testRemoveNegativeIndex exception handled.");
        }
        assertEquals(listCount, list.getCount());
        assertTrue(flag);
    }

    @Test
    public void testRemoveLargerIndex() {
        boolean flag = false;
        for (int i = 0; i < 10; i++) {
            try {
                list.add(i, i * 10);
            } catch (IndexException ex) {
                Assert.fail("testRemoveLargerIndex threw an exception when it should not have.");
            }
        }
        int listCount = list.getCount();
        try {
            long returnValue = list.remove(list.getCount() + 1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame((list.getCount() - 1), ex.getMax());
            assertSame(11, ex.getValue());
            System.out.println("testRemoveLargerIndex exception handled.");
        }
        assertEquals(listCount, list.getCount());

    }

    /**
     * Test of findFirstIndex method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testFindOne() throws IndexException {
        list.add(0, 123);
        assertEquals(0, list.findFirstIndex(123));

    }

    public void testFindFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(0, list.findFirstIndex(0));
    }

    @Test
    public void testFindLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(4, list.findFirstIndex(40));
    }

    @Test
    public void testFindMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(2, list.findFirstIndex(20));
    }

    @Test
    public void testFindNonexistent() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(-1, list.findFirstIndex(123));
    }

    /**
     * Test of getCount method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetCountEmpty() throws IndexException {
        assertEquals(0, list.getCount());
    }

    @Test
    public void testGetCountPartFilled() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(5, list.getCount());
    }

    @Test
    public void testGetCountFilled() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, i * 10);
        }
        assertEquals(10, list.getCount());
    }

    /**
     * Test of toString method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testToStringEmpty() throws IndexException {
        assertEquals("[ ]", list.toString());
    }

    @Test
    public void testToString() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals("[ 0, 10, 20, 30, 40 ]", list.toString());
    }

    /**
     * Test of add method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testAdd() throws IndexException {
        System.out.println("add");
        int index = 0;
        long value = 123;
        LongArrayList instance = new LongArrayList();
        instance.add(index, value);
        assertEquals(1, instance.getCount());
        assertEquals(123, instance.getValue(0));
    }

    /**
     * Test of remove method, of class LongArrayList.
     */
    @Test
    public void testRemoveEmpty() {
        boolean flag = false;
        LongArrayList instance = new LongArrayList();
        try {
            list.remove(0);
        } catch (IndexException ex) {
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(0, ex.getValue());
            System.out.println("testRemoveEmpty exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testRemoveEmptyNegative() {
        boolean flag = false;
        LongArrayList instance = new LongArrayList();
        try {
            list.remove(-1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testRemoveEmptyNegative exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testRemove_int() throws IndexException {
            list.add(0, 123);
            list.remove(0);
            assertEquals(0, list.getCount());
    }

    @Test
    public void testRemove_long() throws IndexException {
        System.out.println("remove");
        long value = 0L;
        LongArrayList instance = new LongArrayList();
        instance.remove(value);
    }

    /**
     * Test of getValue method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetOne() throws IndexException {
        list.add(0, 123);
        assertEquals(123, list.getValue(0));
    }

    @Test
    public void testGetFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(0, list.getValue(0));
    }

    @Test
    public void testGetLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(40, list.getValue(4));
    }

    @Test
    public void testGetMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i * 10);
        }
        assertEquals(20, list.getValue(2));
    }

    @Test
    public void testGetValueEmptyAtZero() {
        boolean flag = false;
        try {
            list.getValue(0);
        } catch (IndexException ex) {
            System.out.println("testGetValueEmptyAtZero exception handled");
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(0, ex.getValue());
        }
        assertTrue(flag);
    }

    @Test
    public void testGetValueEmptyNegative() {
        boolean flag = false;
        int index = -1;
        LongArrayList instance = new LongArrayList();
        long result;
        try {
            result = instance.getValue(index);
        } catch (IndexException ex) {
            System.out.println("testGetValueEmptyNegative exception handled");
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(-1, ex.getValue());
        }
        assertTrue(flag);
    }

    @Test
    public void testGetValueEmpty() {
        boolean flag = false;
        LongArrayList instance = new LongArrayList();
        try {
            long result = instance.getValue(instance.getCount() + 1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(instance.getCount() + 1, ex.getValue());
            System.out.println("testGetValueEmpty exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testGetValueTooLarge() {
        boolean flag = false;
        try {
            for (int i = 0; i < 5; i++) {
                list.add(i, i * 10);
            }
            list.getValue(list.getCount() + 1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount()-1, ex.getMax());
            assertSame(list.getCount() + 1, ex.getValue());
            System.out.println("testGetValueTooLarge exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testGetValueNegative() {
        boolean flag = false;
        try {
            for (int i = 0; i < 5; i++) {
                list.add(i, i * 10);
            }
            list.getValue(-1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount()-1, ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testGetValueNegative exception handled");
        }
        assertTrue(flag);
    }

    /**
     * Test of findFirstIndex method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testFindFirstIndex() throws IndexException {
        long value = 0L;
        LongArrayList instance = new LongArrayList();
        int expResult = -1;
        int result = instance.findFirstIndex(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCount method, of class LongArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetCount() throws IndexException {
        System.out.println("getCount");
        LongArrayList instance = new LongArrayList();
        int expResult = 0;
        int result = instance.getCount();
        assertEquals(expResult, result);
    }
}
