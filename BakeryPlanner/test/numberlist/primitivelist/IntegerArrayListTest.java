package numberlist.primitivelist;

import numberlist.IndexException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date   03/06/2021
 * @version 1.0
 */

public class IntegerArrayListTest {

    IntegerArrayList list;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new IntegerArrayList();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of add method, of class IntegerArrayList.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testAdd() throws IndexException {
        int actual = (list.add(123));
        assertEquals(123, list.getValue(actual));
    }

    /**
     * Test of removeAll method, of class IntegerArrayList.
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

    /**
     * Test of findLastIndex method, of class IntegerArrayList.
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

    /**
     * Test of findLastIndex method, of class IntegerArrayList.
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
        int actualIndex = list.findLastIndex(expectedValue);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testRemoveAllFives() throws IndexException {
        list.add(0, 5);
        list.add(1, 5);
        list.add(2, 5);
        list.add(3, 0);
        list.add(4, 0);
        list.removeAll(5);
        assertEquals(0, list.getValue(0));
    }
}
