package bakeryplanner;

import java.io.File;
import java.util.ArrayList;
import numberlist.IndexException;
import numberlist.objectlist.Complex;
import numberlist.objectlist.Money;
import numberlist.objectlist.NumericArrayList;
import numberlist.objectlist.Temperature;
import numberlist.primitivelist.IntegerArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Feny Dai
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
     * Test of getNames method, of class BakedGoods.
     */
    @Test
    public void testGetNames() {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getName(0), "Pie");
        assertEquals(bakedGoods.getName(1), "Banana Bread");
        assertEquals(bakedGoods.getName(2), "Muffin");
    }

    /**
     * Test of getBatches method, of class BakedGoods.
     */
    @Test
    public void testGetBatches() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getBatch(0), 5);
        assertEquals(bakedGoods.getBatch(1), 6);
        assertEquals(bakedGoods.getBatch(2), 7);
    }

    /**
     * Test of getTemps method, of class BakedGoods.
     */
    @Test
    public void testGetTemps() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getTemp(0), new Temperature(375, 'F'));
        assertEquals(bakedGoods.getTemp(1), new Temperature(420, 'F'));
        assertEquals(bakedGoods.getTemp(2), new Temperature(355, 'F'));
    }

    /**
     * Test of getDurations method, of class BakedGoods.
     */
    @Test
    public void testGetDurations() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getDuration(0), 50);
        assertEquals(bakedGoods.getDuration(1), 12);
        assertEquals(bakedGoods.getDuration(2), 30);
    }

    /**
     * Test of getCosts method, of class BakedGoods.
     */
    @Test
    public void testGetCosts() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getCost(0), new Money(12, (byte) 50));
        assertEquals(bakedGoods.getCost(1), new Money(10, (byte) 50));
        assertEquals(bakedGoods.getCost(2), new Money(8, (byte) 50));
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
        bakedGoods.sortByName();
        assertEquals(bakedGoods.getName(0), "Banana Bread");
        assertEquals(bakedGoods.getName(1), "Cheese Danish");
        assertEquals(bakedGoods.getName(2), "Chocolate Croissant");
        assertEquals(bakedGoods.getName(3), "French Garlic Bread");
        assertEquals(bakedGoods.getName(4), "Muffin");
        assertEquals(bakedGoods.getName(5), "Pie");
        assertEquals(bakedGoods.getDuration(0), 12);
        assertEquals(bakedGoods.getDuration(1), 70);
        assertEquals(bakedGoods.getDuration(2), 63);
        assertEquals(bakedGoods.getDuration(3), 70);
        assertEquals(bakedGoods.getDuration(4), 30);
        assertEquals(bakedGoods.getDuration(5), 50);
        assertEquals(bakedGoods.getTemp(0), new Temperature(420, 'F'));
        assertEquals(bakedGoods.getTemp(1), new Temperature(200, 'F'));
        assertEquals(bakedGoods.getTemp(2), new Temperature(350, 'F'));
        assertEquals(bakedGoods.getTemp(3), new Temperature(200, 'F'));
        assertEquals(bakedGoods.getTemp(4), new Temperature(355, 'F'));
        assertEquals(bakedGoods.getTemp(5), new Temperature(375, 'F'));
    }

    /**
     * Test of sortByBatches method, of class BakedGoods.
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
     * Test of swapStringArrayList method, of class BakedGoods.
     */
    @Test
    public void testSwapStringArrayList() {
        //String temp = names.get(i);
        //names.set(i, names.get(j));
        //names.set(j, (temp));
        //swapStringArrayList(ArrayList<String> names, int i, int j)
        ArrayList<String> nameList = new ArrayList<String>();
        nameList.add("Pie");
        nameList.add("Banana Bread");
        nameList.add("Muffin");
        nameList.add("Cheese Danish");
        nameList.add("French Garlic Bread");
        nameList.add("Chocolate Croissant");
        bakedGoods.swapStringArrayList(nameList, 2, 5);
        assertEquals("Pie", nameList.get(0));
        assertEquals("Banana Bread", nameList.get(1));
        assertEquals("Chocolate Croissant", nameList.get(2));
        assertEquals("Cheese Danish", nameList.get(3));
        assertEquals("French Garlic Bread", nameList.get(4));
        assertEquals("Muffin", nameList.get(5));
    }

    /**
     * Test of swapNumericArrayList method, of class BakedGoods.
     */
    @Test
    public void testSwapNumericArrayList() throws IndexException {
        NumericArrayList costList = new NumericArrayList();
        costList.add(new Money(9, (byte) 40));
        costList.add(new Money(12, (byte) 50));
        costList.add(new Money(8, (byte) 50));
        costList.add(new Money(7, (byte) 30));
        costList.add(new Money(8, (byte) 50));
        costList.add(new Money(8, (byte) 25));
        bakedGoods.swapNumericArrayList(costList, 2, 5);
        assertEquals(new Money(9, (byte) 40), costList.getValue(0));
        assertEquals(new Money(12, (byte) 50), costList.getValue(1));
        assertEquals(new Money(8, (byte) 25), costList.getValue(2));
        assertEquals(new Money(7, (byte) 30), costList.getValue(3));
        assertEquals(new Money(8, (byte) 50), costList.getValue(4));
        assertEquals(new Money(8, (byte) 50), costList.getValue(5));
    }

    /**
     * Test of swapIntegerArrayList method, of class BakedGoods.
     */
    @Test
    public void testSwapIntegerArrayList() throws Exception {
        IntegerArrayList durationList = new IntegerArrayList();
        durationList.add(10);
        durationList.add(20);
        durationList.add(30);
        durationList.add(40);
        durationList.add(50);
        durationList.add(60);
        bakedGoods.swapIntegerArrayList(durationList, 2, 5);
        assertEquals(10, durationList.getValue(0));
        assertEquals(20, durationList.getValue(1));
        assertEquals(60, durationList.getValue(2));
        assertEquals(40, durationList.getValue(3));
        assertEquals(50, durationList.getValue(4));
        assertEquals(30, durationList.getValue(5));
    }

    /**
     * Test of totalCost method, of class BakedGoods.
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
     * Test of calcAveCost method, of class BakedGoods.
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

    /**
     * Test of totalTemp method, of class BakedGoods.
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

}
