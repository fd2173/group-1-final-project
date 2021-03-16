package bakeryplanner;

import java.io.File;
import numberlist.IndexException;
import numberlist.objectlist.Money;
import numberlist.objectlist.Temperature;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Robert Crocker
 * @author Feny Dai
 * @author Octavia Stappart
 * @date 03/14/21
 * @version 1.0
 */
public class BakedGoodsTest {

    public BakedGoodsTest() {
    }
    BakedGoods bakedGoods;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        File ser = new File("bakedGoods.ser");
        if (ser.exists()) {
            ser.delete();
        }
        bakedGoods = new BakedGoods();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addGood method, of class BakedGoods.
     */
    @Test
    public void testAddGood() {
        assertEquals(0, bakedGoods.getCount());
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(3, bakedGoods.getCount());
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        assertEquals(6, bakedGoods.getCount());
    }

    /**
     * Test of updateGood method, of class BakedGoods.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testUpdateGood() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        bakedGoods.updateGood(1, "Cookies", 8, new Temperature(200, 'C'), 15, new Money(10, (byte) 20));
        assertEquals(bakedGoods.getName(1), "Cookies");
        assertEquals(bakedGoods.getBatch(1), 8);
        assertEquals(bakedGoods.getTemp(1), new Temperature(200, 'C'));
        assertEquals(bakedGoods.getDuration(1), 15);
        assertEquals(bakedGoods.getCost(1), new Money(10, (byte) 20));
        bakedGoods.updateGood(5, "Creme Brulee", 8, new Temperature(200, 'C'), 15, new Money(10, (byte) 20));
        assertEquals(bakedGoods.getName(5), "Creme Brulee");
        assertEquals(bakedGoods.getBatch(5), 8);
        assertEquals(bakedGoods.getTemp(5), new Temperature(200, 'C'));
        assertEquals(bakedGoods.getDuration(5), 15);
        assertEquals(bakedGoods.getCost(5), new Money(10, (byte) 20));
    }

    /**
     * Test of deleteGood method, of class BakedGoods.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteGood() throws Exception {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        assertEquals(6, bakedGoods.getCount());
        bakedGoods.deleteGood(1);
        assertEquals(5, bakedGoods.getCount());
        assertEquals(bakedGoods.getName(1), "Muffin");
        assertEquals(bakedGoods.getBatch(1), 7);
        assertEquals(bakedGoods.getTemp(1), new Temperature(355, 'F'));
        assertEquals(bakedGoods.getDuration(1), 30);
        assertEquals(bakedGoods.getCost(1), new Money(8, (byte) 50));
        bakedGoods.deleteGood(0);
        assertEquals(4, bakedGoods.getCount());
        assertEquals(bakedGoods.getName(0), "Muffin");
        assertEquals(bakedGoods.getBatch(0), 7);
        assertEquals(bakedGoods.getTemp(0), new Temperature(355, 'F'));
        assertEquals(bakedGoods.getDuration(0), 30);
        assertEquals(bakedGoods.getCost(0), new Money(8, (byte) 50));
    }

    /**
     * Test of getGoodString method, of class BakedGoods.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetGoodString() throws Exception {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        System.out.println(this.bakedGoods.getGoodString(0));
        assertEquals(this.bakedGoods.getGoodString(0),
                "Pie - 5x  375° F , 50min. $12.50");
    }

    @Test
    public void testgetGoodStringMultiple() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        bakedGoods.addGood("Cookies", 8, new Temperature(200, 'C'), 15, new Money(10, (byte) 20));
        assertEquals("Cheese Danish - 11x  200° F , 70min. $8.50", this.bakedGoods.getGoodString(3));
        assertEquals("Cookies - 8x  200° C , 15min. $10.20", this.bakedGoods.getGoodString(6));
    }

    /**
     * Test of getGoodArray method, of class BakedGoods.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetGoodArray() throws Exception {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 55));
        String[] arr = bakedGoods.getGoodArray(0);
        assertEquals("Pie", arr[0]);
        assertEquals("5", arr[1]);
        assertEquals("375.0", arr[2]);
        assertEquals("F", arr[3]);
        assertEquals("50", arr[4]);
        System.out.println(arr[5]);
        assertEquals("12.55", arr[5]);
    }

    /**
     * Test of getName method, of class BakedGoods.
     */
    @Test
    public void testGetName() {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getName(0), "Pie");
        assertEquals(bakedGoods.getName(1), "Banana Bread");
        assertEquals(bakedGoods.getName(2), "Muffin");
    }

    /**
     * Test of getBatch method, of class BakedGoods.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetBatch() throws Exception {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getBatch(0), 5);
        assertEquals(bakedGoods.getBatch(1), 6);
        assertEquals(bakedGoods.getBatch(2), 7);
    }

    /**
     * Test of getTemp method, of class BakedGoods.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTemp() throws Exception {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getTemp(0), new Temperature(375, 'F'));
        assertEquals(bakedGoods.getTemp(1), new Temperature(420, 'F'));
        assertEquals(bakedGoods.getTemp(2), new Temperature(355, 'F'));
    }

    /**
     * Test of getDurations method, of class BakedGoods.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testGetDurations() throws Exception {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        long[] durations = bakedGoods.getDurations();
        assertEquals(50, durations[0]);
        assertEquals(12, durations[1]);
        assertEquals(30, durations[2]);
    }

    /**
     * Test of getCost method, of class BakedGoods.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCost() throws Exception {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getCost(0), new Money(12, (byte) 50));
        assertEquals(bakedGoods.getCost(1), new Money(10, (byte) 50));
        assertEquals(bakedGoods.getCost(2), new Money(8, (byte) 50));
    }

    /**
     * Test of getCount method, of class BakedGoods.
     */
    @Test
    public void testGetCount() {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(3, bakedGoods.getCount());
    }

    @Test
    public void testGetCountEmpty() {
        assertEquals(0, bakedGoods.getCount());
    }

    /**
     * Test of sortByName method, of class BakedGoods.
     */
    @Test
    public void testSortByName() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        bakedGoods.addGood("29a", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("10b", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("5c", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.sortByName();
        assertEquals(bakedGoods.getName(0), "10b");
        assertEquals(bakedGoods.getName(1), "29a");
        assertEquals(bakedGoods.getName(2), "5c");
        assertEquals(bakedGoods.getName(3), "Banana Bread");
        assertEquals(bakedGoods.getName(4), "Cheese Danish");
        assertEquals(bakedGoods.getName(5), "Chocolate Croissant");
        assertEquals(bakedGoods.getName(6), "French Garlic Bread");
        assertEquals(bakedGoods.getName(7), "Muffin");
        assertEquals(bakedGoods.getName(8), "Pie");
        assertEquals(bakedGoods.getDuration(3), 12);
        assertEquals(bakedGoods.getDuration(4), 70);
        assertEquals(bakedGoods.getDuration(5), 63);
        assertEquals(bakedGoods.getDuration(6), 70);
        assertEquals(bakedGoods.getDuration(7), 30);
        assertEquals(bakedGoods.getDuration(8), 50);
        assertEquals(bakedGoods.getTemp(3), new Temperature(420, 'F'));
        assertEquals(bakedGoods.getTemp(4), new Temperature(200, 'F'));
        assertEquals(bakedGoods.getTemp(5), new Temperature(350, 'F'));
        assertEquals(bakedGoods.getTemp(6), new Temperature(200, 'F'));
        assertEquals(bakedGoods.getTemp(7), new Temperature(355, 'F'));
        assertEquals(bakedGoods.getTemp(8), new Temperature(375, 'F'));
    }

    /**
     * Test of sortByBatches method, of class BakedGoods.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testSortByBatches() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        bakedGoods.sortByBatches();
        assertEquals(bakedGoods.getName(0), "Pie");
        assertEquals(bakedGoods.getName(1), "Banana Bread");
        assertEquals(bakedGoods.getName(2), "Muffin");
        assertEquals(bakedGoods.getName(3), "Cheese Danish");
        assertEquals(bakedGoods.getName(4), "Chocolate Croissant");
        assertEquals(bakedGoods.getName(5), "French Garlic Bread");
        assertEquals(bakedGoods.getBatch(0), 5);
        assertEquals(bakedGoods.getBatch(1), 6);
        assertEquals(bakedGoods.getBatch(2), 7);
        assertEquals(bakedGoods.getBatch(3), 11);
        assertEquals(bakedGoods.getBatch(4), 13);
        assertEquals(bakedGoods.getBatch(5), 15);
        assertEquals(bakedGoods.getDuration(0), 50);
        assertEquals(bakedGoods.getDuration(1), 12);
        assertEquals(bakedGoods.getDuration(2), 30);
        assertEquals(bakedGoods.getDuration(3), 70);
        assertEquals(bakedGoods.getDuration(4), 63);
        assertEquals(bakedGoods.getDuration(5), 70);
        assertEquals(bakedGoods.getTemp(0), new Temperature(375, 'F'));
        assertEquals(bakedGoods.getTemp(1), new Temperature(420, 'F'));
        assertEquals(bakedGoods.getTemp(2), new Temperature(355, 'F'));
        assertEquals(bakedGoods.getTemp(3), new Temperature(200, 'F'));
        assertEquals(bakedGoods.getTemp(4), new Temperature(350, 'F'));
        assertEquals(bakedGoods.getTemp(5), new Temperature(200, 'F'));
    }

    /**
     * Test of sortByTemps method, of class BakedGoods.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testSortByTemps() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        bakedGoods.sortByTemps();
        assertEquals(bakedGoods.getName(0), "Cheese Danish");
        assertEquals(bakedGoods.getName(1), "French Garlic Bread");
        assertEquals(bakedGoods.getName(2), "Chocolate Croissant");
        assertEquals(bakedGoods.getName(3), "Muffin");
        assertEquals(bakedGoods.getName(4), "Pie");
        assertEquals(bakedGoods.getName(5), "Banana Bread");
        assertEquals(bakedGoods.getDuration(0), 70);
        assertEquals(bakedGoods.getDuration(1), 70);
        assertEquals(bakedGoods.getDuration(2), 63);
        assertEquals(bakedGoods.getDuration(3), 30);
        assertEquals(bakedGoods.getDuration(4), 50);
        assertEquals(bakedGoods.getDuration(5), 12);
        assertEquals(bakedGoods.getTemp(0), new Temperature(200, 'F'));
        assertEquals(bakedGoods.getTemp(1), new Temperature(200, 'F'));
        assertEquals(bakedGoods.getTemp(2), new Temperature(350, 'F'));
        assertEquals(bakedGoods.getTemp(3), new Temperature(355, 'F'));
        assertEquals(bakedGoods.getTemp(4), new Temperature(375, 'F'));
        assertEquals(bakedGoods.getTemp(5), new Temperature(420, 'F'));
    }

    /**
     * Test of sortByDuration method, of class BakedGoods.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testSortByDuration() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        bakedGoods.sortByDuration();
        assertEquals(bakedGoods.getName(0), "Banana Bread");
        assertEquals(bakedGoods.getName(1), "Muffin");
        assertEquals(bakedGoods.getName(2), "Pie");
        assertEquals(bakedGoods.getName(3), "Chocolate Croissant");
        assertEquals(bakedGoods.getName(4), "Cheese Danish");
        assertEquals(bakedGoods.getName(5), "French Garlic Bread");
        assertEquals(bakedGoods.getDuration(0), 12);
        assertEquals(bakedGoods.getDuration(1), 30);
        assertEquals(bakedGoods.getDuration(2), 50);
        assertEquals(bakedGoods.getDuration(3), 63);
        assertEquals(bakedGoods.getDuration(4), 70);
        assertEquals(bakedGoods.getDuration(5), 70);
        assertEquals(bakedGoods.getTemp(0), new Temperature(420, 'F'));
        assertEquals(bakedGoods.getTemp(1), new Temperature(355, 'F'));
        assertEquals(bakedGoods.getTemp(2), new Temperature(375, 'F'));
        assertEquals(bakedGoods.getTemp(3), new Temperature(350, 'F'));
        assertEquals(bakedGoods.getTemp(4), new Temperature(200, 'F'));
        assertEquals(bakedGoods.getTemp(5), new Temperature(200, 'F'));
    }

    /**
     * Test of sortByCosts method, of class BakedGoods.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testSortByCosts() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        bakedGoods.sortByCosts();
        assertEquals(bakedGoods.getName(0), "Cheese Danish");
        assertEquals(bakedGoods.getName(1), "Muffin");
        assertEquals(bakedGoods.getName(2), "Banana Bread");
        assertEquals(bakedGoods.getName(3), "French Garlic Bread");
        assertEquals(bakedGoods.getName(4), "Chocolate Croissant");
        assertEquals(bakedGoods.getName(5), "Pie");
        assertEquals(bakedGoods.getDuration(0), 70);
        assertEquals(bakedGoods.getDuration(1), 30);
        assertEquals(bakedGoods.getDuration(2), 12);
        assertEquals(bakedGoods.getDuration(3), 70);
        assertEquals(bakedGoods.getDuration(4), 63);
        assertEquals(bakedGoods.getDuration(5), 50);
        assertEquals(bakedGoods.getCost(0), new Money(8, (byte) 50));
        assertEquals(bakedGoods.getCost(1), new Money(8, (byte) 50));
        assertEquals(bakedGoods.getCost(2), new Money(10, (byte) 50));
        assertEquals(bakedGoods.getCost(3), new Money(11, (byte) 50));
        assertEquals(bakedGoods.getCost(4), new Money(12, (byte) 50));
        assertEquals(bakedGoods.getCost(5), new Money(12, (byte) 50));
    }

    /**
     * Test of totalCost method, of class BakedGoods.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testTotalCost() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.totalCost(), new Money(31, (byte) 50));
        bakedGoods.deleteGood(1);
        assertEquals(bakedGoods.totalCost(), new Money(21, (byte) 0));
    }

    /**
     * Test of averageCost method, of class BakedGoods.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testAverageCost() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.averageCost(), new Money(10, (byte) 50));
        bakedGoods.deleteGood(1);
        assertEquals(bakedGoods.averageCost(), new Money(10, (byte) 50));
    }

    @Test
    public void testAverageCostCountZero() throws IndexException {
        assertEquals(0, bakedGoods.getCount());
        assertEquals(new Money(0, (byte) 0), bakedGoods.averageCost());
    }

    /**
     * Test of totalTemp method, of class BakedGoods.
     *
     * @throws numberlist.IndexException
     */
    @Test
    public void testTotalTemp() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.totalTemp(), new Temperature(1150, 'F'));
        bakedGoods.deleteGood(1);
        assertEquals(bakedGoods.totalTemp(), new Temperature(730, 'F'));
    }

    /**
     * Test of averageTemp method, of class BakedGoods.
     */
    @Test
    public void testAverageTemp() {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        Temperature actualAverage = bakedGoods.averageTemp();
        Temperature expectedAverage = new Temperature(383.3333333333333, 'F');
        assertEquals(expectedAverage, actualAverage);
    }

    @Test
    public void testWriteAndReadCollection() {
        File ser = new File("bakedGoods.ser");
        assertEquals(false, ser.exists());
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.addGood("Cheese Danish", 11, new Temperature(200, 'F'), 70, new Money(8, (byte) 50));
        bakedGoods.addGood("French Garlic Bread", 15, new Temperature(200, 'F'), 70, new Money(11, (byte) 50));
        bakedGoods.addGood("Chocolate Croissant", 13, new Temperature(350, 'F'), 63, new Money(12, (byte) 50));
        bakedGoods.writeCollection();
        assertEquals(true, ser.exists());
        bakedGoods = new BakedGoods();
        bakedGoods.readCollection();
        assertEquals(bakedGoods.getName(0), "Pie");
        assertEquals(bakedGoods.getName(1), "Banana Bread");
        assertEquals(bakedGoods.getName(2), "Muffin");
        assertEquals(bakedGoods.getName(3), "Cheese Danish");
        assertEquals(bakedGoods.getName(4), "French Garlic Bread");
        assertEquals(bakedGoods.getName(5), "Chocolate Croissant");
    }

    /**
     * Test of getDuration method, of class BakedGoods.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDuration() throws Exception {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getDuration(0), 50);
        assertEquals(bakedGoods.getDuration(1), 12);
        assertEquals(bakedGoods.getDuration(2), 30);
    }

    /**
     * Test of totalDuration method, of class BakedGoods.
     */
    @Test
    public void testTotalDuration() {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        double total = bakedGoods.totalDuration();
        assertEquals(92, total, 0.1);
    }

    @Test
    public void testTotalDurationZero() {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 0, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 0, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 0, new Money(8, (byte) 50));
        double total = bakedGoods.totalDuration();
        assertEquals(0, total, 0.1);
    }
}