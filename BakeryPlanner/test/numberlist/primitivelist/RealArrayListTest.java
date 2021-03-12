package numberlist.primitivelist;

import numberlist.IndexException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
public class RealArrayListTest {

    RealArrayList list;

    public RealArrayListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        list = new RealArrayList();
    }

    /**
     * Test of add method, of class RealArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testAdd() throws IndexException {
        list.add(0, 1.00);
        assertEquals(1.00, list.getValue(0), 0);
    }

    @Test
    public void testAddOutOfRangeNegative() {
        boolean flag = false;
        try {
            list.add(-1, 0);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount(), ex.getMax());
            assertSame(-1, ex.getValue());
            System.out.println("testAddOutOfRangeNegative exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testAddOutOfRangePositive() {
        boolean flag = false;
        try {
            list.add(0, 1);
            list.add(1, 1);
            list.add(list.getCount() + 1, 0);
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame(list.getCount(), ex.getMax());
            assertSame(list.getCount() + 1, ex.getValue());
            System.out.println("testAddOutOfRangePositive exception handled");
        }
        assertTrue(flag);
    }

    /**
     * Test of removeAll method, of class RealArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testRemoveAll() throws IndexException {
        for (int i = 0; i < 10; i++) {
            list.add(i * 10);
        }
        for (int i = 0; i < 10; i++) {
            list.removeAll(i * 10);
            assertEquals(-1, list.findFirstIndex(i * 10));
        }
    }

    @Test
    public void testRemoveAllNonexistent() {
        boolean flag = false;
        try {
            list.remove(list.getCount());
        } catch (IndexException ex) {
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(list.getCount(), ex.getValue());
            System.out.println("testRemoveAllNonexistent exception handled");
        }
        assertTrue(flag);
    }

    @Test
    public void testRemoveAllOutOfRange() {
        boolean flag = false;
        try {
            list.add(0, 1);
            list.add(1, 1);
            list.remove(list.getCount());
        } catch (IndexException ex) {
            flag = true;
            assertSame(0, ex.getMin());
            assertSame((list.getCount() - 1), ex.getMax());
            assertSame(list.getCount(), ex.getValue());
            System.out.println("testRemoveAllOutOfRange exception handled");
        }
        assertTrue(flag);
    }

    /**
     * Test of findLastIndex method, of class RealArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testFindLastIndex() throws IndexException {
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(4);
        int expectedIndex = 3;
        int expectedValue = 2;
        int actualIndex = list.findLastIndex(expectedValue);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testFindLastIndexEmpty() throws IndexException {
        boolean flag = false;
        int index = 0;
        try {
            list.getValue(index);
        } catch (IndexException ex) {
            System.out.println("testFindLastIndexEmpty exception handled");
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(0, ex.getValue());
        }
        assertTrue(flag);
    }

    @Test
    public void testFindLastIndexEmptyNegative() {
        boolean flag = false;
        int index = -1;
        try {
            list.getValue(index);
        } catch (IndexException ex) {
            System.out.println("testFindLastIndexEmptyNegative exception handled");
            flag = true;
            assertSame(-1, ex.getMin());
            assertSame(-1, ex.getMax());
            assertSame(-1, ex.getValue());
        }
        assertTrue(flag);
    }

    /**
     * Test of findLastIndex method, of class RealArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testFindLastIndexNonexistent() throws IndexException {
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(4);
        int expectedIndex = -1;
        int expectedValue = 5;
        int actualindex;
        int actualIndex = list.findLastIndex(expectedValue);
        assertEquals(expectedIndex, actualIndex);
    }
}
