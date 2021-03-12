package bakeryplanner;

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
    }

    /**
     * Test of updateGood method, of class BakedGoods.
     */
    @Test
    public void testUpdateGood() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.updateGood(1, "Cookies", 8, new Temperature(200, 'C'), 15, new Money(10, (byte) 20));
        assertEquals(bakedGoods.getNames().get(1), "Cookies");
        assertEquals(bakedGoods.getBatches().getValue(1), 8);
        assertEquals(bakedGoods.getTemps().getValue(1), new Temperature(200, 'C'));
        assertEquals(bakedGoods.getDurations().getValue(1), 15);
        assertEquals(bakedGoods.getCosts().getValue(1), new Money(10, (byte) 20));
    }

    /**
     * Test of deleteGood method, of class BakedGoods.
     */
    @Test
    public void testDeleteGood() throws Exception {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(3, bakedGoods.getCount());
        bakedGoods.deleteGood(1);
        assertEquals(2, bakedGoods.getCount());
        assertEquals(bakedGoods.getNames().get(1), "Muffin");
        assertEquals(bakedGoods.getBatches().getValue(1), 7);
        assertEquals(bakedGoods.getTemps().getValue(1), new Temperature(355, 'F'));
        assertEquals(bakedGoods.getDurations().getValue(1), 30);
        assertEquals(bakedGoods.getCosts().getValue(1), new Money(8, (byte) 50));
    }

    /**
     * Test of getNames method, of class BakedGoods.
     */
    @Test
    public void testGetNames() {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getNames().get(0), "Pie");
        assertEquals(bakedGoods.getNames().get(1), "Banana Bread");
        assertEquals(bakedGoods.getNames().get(2), "Muffin");
    }

    /**
     * Test of getBatches method, of class BakedGoods.
     */
    @Test
    public void testGetBatches() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        assertEquals(bakedGoods.getBatches().getValue(0), 5);
        assertEquals(bakedGoods.getBatches().getValue(1), 6);
        assertEquals(bakedGoods.getBatches().getValue(2), 7);
    }

    /**
     * Test of getTemps method, of class BakedGoods.
     */
    @Test
    public void testGetTemps() {
        System.out.println("getTemps");
        BakedGoods instance = new BakedGoods();
        NumericArrayList expResult = null;
        NumericArrayList result = instance.getTemps();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDurations method, of class BakedGoods.
     */
    @Test
    public void testGetDurations() {
        System.out.println("getDurations");
        BakedGoods instance = new BakedGoods();
        IntegerArrayList expResult = null;
        IntegerArrayList result = instance.getDurations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCosts method, of class BakedGoods.
     */
    @Test
    public void testGetCosts() {
        System.out.println("getCosts");
        BakedGoods instance = new BakedGoods();
        NumericArrayList expResult = null;
        NumericArrayList result = instance.getCosts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortByName method, of class BakedGoods.
     */
    @Test
    public void testSortByName() throws IndexException {
        bakedGoods.addGood("Pie", 5, new Temperature(375, 'F'), 50, new Money(12, (byte) 50));
        bakedGoods.addGood("Banana Bread", 6, new Temperature(420, 'F'), 12, new Money(10, (byte) 50));
        bakedGoods.addGood("Muffin", 7, new Temperature(355, 'F'), 30, new Money(8, (byte) 50));
        bakedGoods.sortByName();
        assertEquals(bakedGoods.getNames().get(0), "Banana Bread");
        assertEquals(bakedGoods.getNames().get(1), "Muffin");
        assertEquals(bakedGoods.getNames().get(2), "Pie");
        assertEquals(bakedGoods.getDurations().getValue(0), 12);
        assertEquals(bakedGoods.getDurations().getValue(1), 30);
        assertEquals(bakedGoods.getDurations().getValue(2), 50);
        assertEquals(bakedGoods.getTemps().getValue(0), new Temperature(420, 'F'));
        assertEquals(bakedGoods.getTemps().getValue(1), new Temperature(355, 'F'));
        assertEquals(bakedGoods.getTemps().getValue(2), new Temperature(375, 'F'));
    }

    /**
     * Test of sortByBatches method, of class BakedGoods.
     */
    @Test
    public void testSortByBatches() {
        System.out.println("sortByBatches");
        BakedGoods instance = new BakedGoods();
        instance.sortByBatches();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortByTemps method, of class BakedGoods.
     */
    @Test
    public void testSortByTemps() {
        System.out.println("sortByTemps");
        BakedGoods instance = new BakedGoods();
        instance.sortByTemps();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortByDuration method, of class BakedGoods.
     */
    @Test
    public void testSortByDuration() {
        System.out.println("sortByDuration");
        BakedGoods instance = new BakedGoods();
        instance.sortByDuration();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortByCosts method, of class BakedGoods.
     */
    @Test
    public void testSortByCosts() {
        System.out.println("sortByCosts");
        BakedGoods instance = new BakedGoods();
        instance.sortByCosts();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of swapStringArrayList method, of class BakedGoods.
     */
    @Test
    public void testSwapStringArrayList() {
        System.out.println("swapStringArrayList");
        ArrayList<String> names = null;
        int i = 0;
        int j = 0;
        BakedGoods instance = new BakedGoods();
        instance.swapStringArrayList(names, i, j);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of swapNumericArrayList method, of class BakedGoods.
     */
    @Test
    public void testSwapNumericArrayList() throws Exception {
        System.out.println("swapNumericArrayList");
        NumericArrayList list = null;
        int i = 0;
        int j = 0;
        BakedGoods instance = new BakedGoods();
        instance.swapNumericArrayList(list, i, j);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of swapIntegerArrayList method, of class BakedGoods.
     */
    @Test
    public void testSwapIntegerArrayList() throws Exception {
        System.out.println("swapIntegerArrayList");
        IntegerArrayList list = null;
        int i = 0;
        int j = 0;
        BakedGoods instance = new BakedGoods();
        instance.swapIntegerArrayList(list, i, j);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalCost method, of class BakedGoods.
     */
    @Test
    public void testTotalCost() {
        System.out.println("totalCost");
        BakedGoods instance = new BakedGoods();
        Money expResult = null;
        Money result = instance.totalCost();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcAveCost method, of class BakedGoods.
     */
    @Test
    public void testCalcAveCost() {
        System.out.println("calcAveCost");
        BakedGoods instance = new BakedGoods();
        Money expResult = null;
        Money result = instance.calcAveCost();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalTemp method, of class BakedGoods.
     */
    @Test
    public void testTotalTemp() {
        System.out.println("totalTemp");
        BakedGoods instance = new BakedGoods();
        Temperature expResult = null;
        Temperature result = instance.totalTemp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcAveTemp method, of class BakedGoods.
     */
    @Test
    public void testCalcAveTemp() {
        System.out.println("calcAveTemp");
        BakedGoods instance = new BakedGoods();
        Temperature expResult = null;
        Temperature result = instance.calcAveTemp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
