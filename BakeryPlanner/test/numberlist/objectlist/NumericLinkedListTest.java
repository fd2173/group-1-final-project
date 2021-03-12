package numberlist.objectlist;

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
public class NumericLinkedListTest {

    NumericLinkedList list;
    Complex c1, c2, c3, c4;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new NumericLinkedList();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of add method, of class NumericLinkedList.
     */
    @Test
    public void testAddNegativeIndex() {
        boolean flag = false;
        try {
            for (int i = 0; i < 10; i++) {
                list.add(i, new Complex(i, i));
            }
        } catch (IndexException ex) {
            Assert.fail("testAddNegativeIndex threw an exception when it should not have.");
        }
        int expectedCount = list.getCount();
        try {
            list.add(-1, new Complex(0, 0));
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount(), ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testAddNegativeIndex exception handled");
        }
        assertEquals(expectedCount, list.getCount());
        assertTrue(flag);
    }

    @Test
    public void testAddTooLargeIndex() {
        boolean flag = false;
        try {
            for (int i = 0; i < 10; i++) {
                list.add(i, new Complex(i, i));
            }
        } catch (IndexException ex) {
            Assert.fail("testAddTooLargeIndex threw an exception when it should not have.");
        }
        int expectedCount = list.getCount();
        try {
            list.add((expectedCount * 2), new Complex(0, 0));
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount(), ex.getMax());
            assertSame(expectedCount * 2, ex.getValue());
            System.out.println("testAddTooLargeIndex exception handled");
        }
        assertEquals(expectedCount, list.getCount());
        assertTrue(flag);
    }

    @Test
    public void testAddLots() throws IndexException {
        for (int i = 0; i < 1000; i++) {
            list.add(i, new Complex(i, i * 10));
        }
        assertEquals(1000, list.getCount());
        for (int i = 0; i < 1000; i++) {
            assertEquals(new Complex(i, i * 10), list.getValue(i));
        }
    }

    @Test
    public void testAddFirst() throws IndexException {
        c1 = new Complex(1, 1);
        list.add(0, c1);
        assertEquals(1, list.getCount());
        Object o = list.getValue(0);
        assertTrue(o instanceof Complex);
        Complex c = (Complex) o;
        assertEquals(1, c.getReal(), 0.1);
        assertEquals(1, c.getImaginary(), 0.1);

    }

    @Test
    public void testAddMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex(i, i * 10));
        }
        list.add(2, new Complex(12, 34));
        assertEquals(6, list.getCount());
        assertEquals(new Complex(0.0, 0.0), list.getValue(0));
        assertEquals(new Complex(1.0, 10.0), list.getValue(1));
        assertEquals(new Complex(12, 34), list.getValue(2));
        assertEquals(new Complex(2.0, 20.0), list.getValue(3));
        assertEquals(new Complex(3.0, 30.0), list.getValue(4));
        assertEquals(new Complex(4.0, 40.0), list.getValue(5));
    }

    @Test
    public void testAddLotsMiddle() throws IndexException {
        for (int i = 0; i < 100; i++) {
            list.add(i, new Complex(i, i));
        }
        list.add(15, new Complex(1, 2));
        assertEquals(101, list.getCount());
        for (int i = 0; i < 15; i++) {
            assertEquals(new Complex(i, i), list.getValue(i));
        }
        assertEquals(new Complex(1, 2), list.getValue(15));
        for (int i = 16; i < 100; i++) {
            assertEquals(new Complex(i - 1, i - 1), list.getValue(i));
        }
    }

    @Test
    public void testAdd() throws IndexException {
        NumericArrayList instance = new NumericArrayList();
        instance.add(new Complex(123, 123));
        assertEquals(1, instance.getCount());
        assertEquals(new Complex(123, 123), instance.getValue(0));
    }

    /**
     * Test of set method with index, of class NumericLinkedList.
     */
    @Test
    public void testSetEmpty() {
        boolean flag = false;
        try {
            list.set(0, new Complex(0, 0));
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
        list.add(new Complex(1, 1));
        int listSize = list.getCount();
        try {
            list.set(0, new Complex(0, 0));
        } catch (IndexException ex) {
            Assert.fail("testSetEmpty threw an exception when it should not have.");
        }
        assertEquals(new Complex(0, 0), list.getValue(0));
        assertEquals(listSize, list.getCount());
    }

    @Test
    public void testSetMiddle() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, new Complex(i, i));
        }
        int listSize = list.getCount();
        list.set(5, new Complex(0, 0));
        assertEquals(new Complex(0, 0), list.getValue(5));
        assertEquals(listSize, list.getCount());
    }

    @Test
    public void testSetLast() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, new Complex(i, i));
        }
        int listSize = list.getCount();
        list.set(9, new Complex(0, 0));
        assertEquals(new Complex(0, 0), list.getValue(9));
        assertEquals(listSize, list.getCount());
    }

    @Test
    public void testSetOutOfBoundsNegative() {
        boolean flag = false;
        try {
            for (int i = 0; i < 10; i++) {
                list.add(i, new Complex(i, i));
            }
            list.set(-1, new Complex(0, 0));
        } catch (IndexException ex) {
            System.out.println("testOutOfBoundsNegative exception handled");
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount() - 1, ex.getMax());
            assertSame(-1, ex.getValue());
        }
        assertTrue(flag);
    }

    @Test
    public void testSetOutOfBoundsNegativeEmpty() {
        boolean flag = false;
        try {
            list.set(-1, new Complex(0, 0));
        } catch (IndexException ex) {
            System.out.println("testOutOfBoundsNegativeEmpty exception handled");
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(-1, ex.getValue());
        }
        assertTrue(flag);
    }

    @Test
    public void testSetOutOfBoundsPositive() {
        boolean flag = false;
        try {
            for (int i = 0; i < 10; i++) {
                list.add(i, new Complex(i, i));
            }
            list.set(list.getCount(), new Complex(0, 0));
        } catch (IndexException ex) {
            System.out.println("testSetOutOfBoundsPositive exception handled");
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount() - 1, ex.getMax());
            assertSame(list.getCount(), ex.getValue());
        }
        assertTrue(flag);
    }

    @Test
    public void testSetOutOfBoundsPositiveEmpty() {
        boolean flag = false;
        try {
            list.set(list.getCount(), new Complex(0, 0));
        } catch (IndexException ex) {
            System.out.println("testOutOfBoundsNegativeEmpty exception handled");
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(list.getCount(), ex.getValue());
        }
        assertTrue(flag);
    }

    /**
     * Test of remove method with index, of class NumericLinkedList.
     */
    @Test
    public void testRemoveWhenCountZero() {
        boolean flag = false;
        try {
            list.remove(0);
        } catch (IndexException ex) {
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(0, ex.getValue());
            System.out.println("testRemoveWhenCountZero exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testRemoveNegativeIndex() {
        boolean flag = false;
        try {
            for (int i = 0; i < 10; i++) {
                list.add(i, new Complex(i, i));
            }
        } catch (IndexException ex) {
            Assert.fail("testRemoveNegativeIndex threw an exception when it should not have.");
        }
        int expectedCount = list.getCount();
        try {
            list.remove(-1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(expectedCount - 1, ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testRemoveNegativeIndex exception handled");
        }
        assertEquals(expectedCount, list.getCount());
        assertTrue(flag);
    }

    @Test
    public void testRemoveIndexAtCount() {
        boolean flag = false;
        try {
            for (int i = 0; i < 10; i++) {
                list.add(i, new Complex(i, i));
            }
        } catch (IndexException ex) {
            Assert.fail("testRemoveIndexAtCount threw an exception when it should not have.");
        }
        try {
            list.remove((list.getCount()));
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount() - 1, ex.getMax());
            assertSame(list.getCount(), ex.getValue());
            System.out.println("testRemoveIndexAtCount exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testRemoveFirstIndex() throws IndexException {
        for (int i = 0; i < 4; i++) {
            list.add(i, new Complex(i, i));
        }
        Copiable returnValue = list.remove(0);
        assertEquals(new Complex(0.0, 0.0), returnValue);
        assertEquals(3, list.getCount());
    }

    @Test
    public void testRemoveLastIndex() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, new Complex(i, i));
        }
        Copiable returnValue = list.remove(9);
        assertEquals(new Complex(9, 9), returnValue);
        assertEquals(9, list.getCount());

    }

    /**
     * Test of remove method with value, of class NumericLinkedList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveMiddleIndex() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, new Complex(i, i));
        }
        Copiable expected = list.getValue(5);
        assertEquals(10, list.getCount());
        Copiable removed = list.remove(5);
        assertEquals(9, list.getCount());
        assertEquals(expected, removed);
    }

    @Test
    public void testRemoveNonexistentValue() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(new Complex(i, i));
        }
        list.remove(new Complex(123, 123));
        assertEquals(5, list.getCount());
        for (int i = 0; i < 5; i++) {
            assertEquals(new Complex(i, i), list.getValue(i));
        }
    }

    /**
     * Test of getValue method, of class NumericArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetOne() throws IndexException {
        list.add(new Complex(1, 1));
        assertEquals(new Complex(1, 1), list.getValue(0));
    }

    @Test
    public void testGetFirst() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, new Complex(i + 1, i + 1));
        }
        assertEquals(new Complex(1.0, 1.0), list.getValue(0));
    }

    @Test
    public void testGetLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(new Complex(i + 1, i + 1));
        }
        assertEquals(new Complex(5, 5), list.getValue(4));
    }

    @Test
    public void testGetMiddleValue() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(new Complex(i + 1, i + 1));
        }
        assertEquals(new Complex(6, 6), list.getValue(5));
    }

    @Test
    public void testGetNegative() {
        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            list.add(new Complex(i, i));
        }
        try {
            list.getValue(-1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount() - 1, ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testGetNegative exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testGetValueAtCount() {
        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            list.add(new Complex(i, i));
        }
        try {
            list.getValue(list.getCount());
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount() - 1, ex.getMax());
            assertSame(list.getCount(), ex.getValue());
            System.out.println("testGetValueAtCount exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testGetValueEmptyAtIndex() {
        int index = 0;
        boolean flag = false;
        NumericLinkedList instance = new NumericLinkedList();
        try {
            instance.getValue(index);
        } catch (IndexException ex) {
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(index, ex.getValue());
            System.out.println("testGetValueEmptyAtIndex exception handled");
        }
        assertTrue(flag);
    }

    /**
     * Test of findFirstIndex method, of class NumericLinkedList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testFindAtZero() throws IndexException {
        list.add(new Complex(0, 123));
        assertEquals(0, list.findFirstIndex(new Complex(0, 123)));
    }

    @Test
    public void testFindAtOne() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(new Complex(1, 1));
        }
        int indexFound = list.findFirstIndex(new Complex(1, 1));
        assertEquals(0, indexFound);
    }

    @Test
    public void testFindAtLast() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(new Complex(i, i));
        }
        assertEquals(4, list.findFirstIndex(new Complex(4, 4)));
    }

    @Test
    public void testFindMiddle() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(new Complex(i, i));
        }
        assertEquals(2, list.findFirstIndex(new Complex(2, 2)));
    }

    @Test
    public void testFindNonexistent() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(new Complex(i, i));
        }
        assertEquals(-1, list.findFirstIndex(new Complex(0, 123)));
    }

    /**
     * Test of getCount method, of class NumericLinkedList.
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
            list.add(i, new Complex(i, i));
        }
        assertEquals(5, list.getCount());
    }

    @Test
    public void testGetCountFilled() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(new Complex(i, i * 10));
        }
        assertEquals(10, list.getCount());
    }

    /**
     * Test of toString method, of class NumericLinkedList.
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
            list.add(new Complex(i, i * 10));
        }
        assertEquals("[ 0.0, 1.0 + 10.0i, 2.0 + 20.0i, 3.0 + 30.0i, 4.0 + 40.0i ]", list.toString());
    }

    /**
     * Test of copy method, of class NumericLinkedList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testCopyContents() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, new Complex(i, i));
        }
        NumericLinkedList copy = list.copy();
        assertEquals(list.getCount(), copy.getCount());
        for (int i = 0; i < 10; i++) {
            assertEquals(list.getValue(i), copy.getValue(i));
        }
        System.out.println(list.toString());
        System.out.println(copy.toString());
    }

    @Test
    public void testCopyMemoryAddress() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, new Complex(i, i));
        }
        NumericLinkedList newList = list.copy();
        for (int i = 0; i < 10; i++) {
            assertFalse(list.getValue(i) == newList.getValue(i));
        }
        Assert.assertFalse(newList == list);
    }
}
