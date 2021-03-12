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
public class DoubleArrayListTest {

    DoubleArrayList list;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new DoubleArrayList();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of add method, of class DoubleArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testAdd() throws IndexException {
        list.add(0, 1.00);
        assertEquals(1.00, list.getValue(0), 0);
    }

    @Test
    public void testAddNonexistent() {
        boolean flag = false;
        try {
            list.add(-1, 1.00);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount(), ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testAddNonexistent Exception Handled");
        }
        assertEquals(0, list.getCount());
        assertTrue(flag);
    }

    /**
     * Test of set method with index, of class DoubleArrayList.
     */
    @Test
    public void testSetEmpty() {
        boolean flag = false;
        try {
            list.set(0, 0.0);
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
        list.add(0, 0.0);
        int listSize = list.getCount();
        try {
            list.set(0, 1.0);
        } catch (IndexException ex) {
            Assert.fail("testSetFirst threw an exception when it should not have.");
        }
        assertEquals(1.0, list.getValue(0), 0.1);
        assertEquals(listSize, list.getCount());
    }

    @Test
    public void testSetMiddle() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, i * 1.0);
        }
        int listSize = list.getCount();
        list.set(5, 0.0);
        assertEquals(0.0, list.getValue(5), 0.1);
        assertEquals(listSize, list.getCount());
    }

    @Test
    public void testSetLast() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i, i * 1.0);
        }
        int listSize = list.getCount();
        list.set(9, 0.0);
        assertEquals(0.0, list.getValue(9), 0.1);
        assertEquals(listSize, list.getCount());
    }

    /**
     * Test of remove(index) method, of class DoubleArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveWhenCountZero() throws IndexException {
        boolean flag = false;
        list.add(0, 1.00);
        list.remove(0);
        try {
            list.getValue(0);
        } catch (IndexException ex) {
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(0, ex.getValue());
            System.out.println("testRemoveWhenCountZero Exception Handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testRemoveWhenCountZeroNegative() throws IndexException {
        boolean flag = false;
        list.add(0, 1.00);
        list.remove(0);
        try {
            list.getValue(-1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testRemoveWhenCountZeroNegative Exception Handled");
        }
        assertTrue(flag);
    }

    /**
     * Test of remove(value) method, of class DoubleArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveValueNegativeIndex() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i + 1);
        }
        boolean flag = false;
        try {
            list.getValue(-1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame((list.getCount() - 1), ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testRemoveValueNegativeIndex Exception Handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testRemoveValueLargerIndex() throws IndexException {
        for (int i = 0; i < 5; i++) {
            list.add(i, i + 1);
        }
        boolean flag = false;
        try {
            list.getValue(list.getCount() + 1);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount() - 1, ex.getMax());
            assertSame(list.getCount() + 1, ex.getValue());
            System.out.println("testRemoveValueLargerIndex Exception Handled");
        }
        assertTrue(flag);
    }

    /**
     * Test of getValue method, of class DoubleArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetValue() throws IndexException {
        list.add(0, 1.00);
        double value = list.getValue(0);
        assertEquals(value, list.getValue(0), 0);
    }

    /**
     * Test of findFirstIndex method, of class DoubleArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetFirstValue() throws IndexException {
        list.add(0, 1.00);
        list.add(1, 1.00);
        int index = list.findFirstIndex(1.00);
        assertEquals(0, index, 0);
    }

    /**
     * Test of getCount method, of class DoubleArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetCount() throws IndexException {
        list.add(0, 1.00);
        list.add(1, 1.00);
        int count = list.getCount();
        assertEquals(2, count, 0);
    }

    /**
     * Test of toString method, of class DoubleArrayList.
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
        assertEquals("[ 0.0, 10.0, 20.0, 30.0, 40.0 ]", list.toString());
    }
}
