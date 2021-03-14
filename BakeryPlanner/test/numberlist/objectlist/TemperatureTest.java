package numberlist.objectlist;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Name added later, pastebin public
 * @author Name
 * @author Name
 */
public class TemperatureTest {

    Temperature t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;

    public TemperatureTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        t1 = new Temperature(0.0, 'F');
        t2 = new Temperature(1.0, 'F');
        t3 = new Temperature(-1.0, 'F');
        t4 = new Temperature(400.00, 'F');
        t5 = new Temperature(1.0, 'F');
        t6 = new Temperature(1.0, 'C');
        t7 = new Temperature(1.0, 'K');
        t8 = new Temperature(99.99, 'F');
    }

    /**
     * Test of getValue method, of class Temperature.
     */
    @Test
    public void testGetValueZero() {
        assertTrue(t1.getValue() == 0.0);
    }

    @Test
    public void testGetValueOne() {
        assertTrue(t2.getValue() == 1.0);
    }

    @Test
    public void testGetValueHigh() {
        assertTrue(t4.getValue() == 400.00);
    }

    //Remember we don't want negative values, this would throw exception purposely. 
    @Test
    public void testGetValueNegative() {
        assertTrue(t3.getValue() == -1.0);
    }

    /**
     * Test of getUnit method, of class Temperature.
     */
    @Test
    public void testGetUnitFahrenheit() {
        assertTrue(t1.getUnit() == 'F');
    }

    @Test
    public void testGetUnitKelvin() {
        assertTrue(t7.getUnit() == 'K');
    }

    @Test
    public void testGetUnitCelsius() {
        assertTrue(t6.getUnit() == 'C');
    }

    /**
     * Test of add method, of class Temperature.
     */
    @Test
    public void testAddFahrenheit() {
        t1 = new Temperature(1.0, 'F');
        Temperature total = t1.add(t1);
        assertTrue(total.getValue() == 2.0);
        assertTrue(total.getUnit() == 'F');
    }

    @Test
    public void testAddKelvin() {
        t1 = new Temperature(1.0, 'K');
        Temperature total = t1.add(t1);
        assertTrue(total.getValue() == 2.0);
        assertTrue(total.getUnit() == 'K');
    }

    @Test
    public void testAddCelsius() {
        t1 = new Temperature(1.0, 'C');
        Temperature total = t1.add(t1);
        assertTrue(total.getValue() == 2.0);
        assertTrue(total.getUnit() == 'C');
    }

    @Test
    public void testAddZeroFandF() {
        t1 = new Temperature(0.0, 'F');
        Temperature total = t1.add(t1);
        assertTrue(total.getValue() == 0.0);
        assertTrue(total.getUnit() == 'F');
    }

    @Test
    public void testAddZeroCandC() {
        t1 = new Temperature(0.0, 'C');
        Temperature total = t1.add(t1);
        assertTrue(total.getValue() == 0.0);
        assertTrue(total.getUnit() == 'C');
    }

    @Test
    public void testAddZeroKandK() {
        t1 = new Temperature(0.0, 'K');
        Temperature total = t1.add(t1);
        assertTrue(total.getValue() == 0.0);
        assertTrue(total.getUnit() == 'K');
    }

    @Test
    public void testAddZeroFandC() {
        t1 = new Temperature(0.0, 'F');
        t2 = new Temperature(0.0, 'C');
        Temperature total = t1.add(t2);
        assertTrue(total.getValue() == 32.0);
        assertTrue(total.getUnit() == 'F');
    }

    @Test
    public void testAddZeroFandK() {
        t1 = new Temperature(0.0, 'F');
        t2 = new Temperature(0.0, 'K');
        Temperature total = t1.add(t2);
        assertTrue(total.getValue() == -459.67);
        assertTrue(total.getUnit() == 'F');
    }

    @Test
    public void testAddZeroCandF() {
        t1 = new Temperature(0.0, 'C');
        t2 = new Temperature(0.0, 'F');
        Temperature total = t1.add(t2);
        System.out.println(total.getValue());
        assertEquals(-17.7778, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'C');
    }

    @Test
    public void testAddZeroCandK() {
        t1 = new Temperature(0.0, 'C');
        t2 = new Temperature(0.0, 'K');
        Temperature total = t1.add(t2);
        assertEquals(-273.15, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'C');
    }

    @Test
    public void testAddZeroKandF() {
        t1 = new Temperature(0.0, 'K');
        t2 = new Temperature(0.0, 'F');
        Temperature total = t1.add(t2);
        assertEquals(255.372, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'K');
    }

    @Test
    public void testAddZeroKandC() {
        t1 = new Temperature(0.0, 'K');
        t2 = new Temperature(0.0, 'C');
        Temperature total = t1.add(t2);
        assertEquals(273.15, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'K');
    }

    @Test
    public void testAddNonzeroFandC() {
        t1 = new Temperature(100.0, 'F');
        t2 = new Temperature(1.0, 'C');
        Temperature total = t1.add(t2);
        assertEquals(133.8, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'F');
    }

    @Test
    public void testAddNonzeroFandK() {
        t1 = new Temperature(100.0, 'F');
        t2 = new Temperature(0.0, 'K');
        Temperature total = t1.add(t2);
        assertTrue(total.getValue() == -359.67);
        assertTrue(total.getUnit() == 'F');
    }

    @Test
    public void testAddNonzeroCandF() {
        t1 = new Temperature(100.0, 'C');
        t2 = new Temperature(1.0, 'F');
        Temperature total = t1.add(t2);
        assertEquals(82.778, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'C');
    }

    @Test
    public void testAddNonzeroCandK() {
        t1 = new Temperature(100.0, 'C');
        t2 = new Temperature(1.0, 'K');
        Temperature total = t1.add(t2);
        assertEquals(-172.15, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'C');
    }

    @Test
    public void testAddNonzeroKandF() {
        t1 = new Temperature(100.0, 'K');
        t2 = new Temperature(1.0, 'C');
        Temperature total = t1.add(t2);
        assertEquals(374.15, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'K');
    }

    @Test
    public void testAddNonzeroKandC() {
        t1 = new Temperature(100.0, 'K');
        t2 = new Temperature(1.0, 'F');
        Temperature total = t1.add(t2);
        assertEquals(355.928, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'K');
    }

    /**
     * Test of subtract method, of class Temperature.
     */
    @Test
    public void testSubtractOne() {
        t1 = new Temperature(1.0, 'F');
        t2 = new Temperature(1.0, 'C');
        t3 = new Temperature(1.0, 'K');
        Temperature totalF = t1.subtract(t1);
        Temperature totalC = t2.subtract(t2);
        Temperature totalK = t3.subtract(t3);
        assertTrue(totalF.getValue() == 0.0);
        assertTrue(totalC.getValue() == 0.0);
        assertTrue(totalK.getValue() == 0.0);
        assertTrue(totalF.getUnit() == 'F');
        assertTrue(totalC.getUnit() == 'C');
        assertTrue(totalK.getUnit() == 'K');
    }

    @Test
    public void testSubtractZero() {
        t1 = new Temperature(0.0, 'F');
        t2 = new Temperature(0.0, 'C');
        t3 = new Temperature(0.0, 'K');
        Temperature totalF = t1.subtract(t1);
        Temperature totalC = t2.subtract(t2);
        Temperature totalK = t3.subtract(t3);
        assertTrue(totalF.getValue() == 0.0);
        assertTrue(totalC.getValue() == 0.0);
        assertTrue(totalK.getValue() == 0.0);
        assertTrue(totalF.getUnit() == 'F');
        assertTrue(totalC.getUnit() == 'C');
        assertTrue(totalK.getUnit() == 'K');
    }

    @Test
    public void testSubtractZeroF() {
        t1 = new Temperature(0.0, 'F');
        t2 = new Temperature(0.0, 'C');
        t3 = new Temperature(0.0, 'K');
        Temperature totalF1 = t1.subtract(t2);
        Temperature totalF2 = t1.subtract(t3);
        assertTrue(totalF1.getValue() == -32.0);
        assertTrue(totalF1.getUnit() == 'F');
        assertTrue(totalF2.getValue() == 459.67);
        assertTrue(totalF2.getUnit() == 'F');
    }

    @Test
    public void testSubtractZeroC() {
        t1 = new Temperature(0.0, 'C');
        t2 = new Temperature(0.0, 'F');
        t3 = new Temperature(0.0, 'K');
        Temperature totalC1 = t1.subtract(t2);
        Temperature totalC2 = t1.subtract(t3);
        assertEquals(17.7778, totalC1.getValue(), 0.1);
        assertEquals(273.15, totalC2.getValue(), 0.1);
        assertTrue(totalC1.getUnit() == 'C');
        assertTrue(totalC2.getUnit() == 'C');
    }

    @Test
    public void testSubtractZeroK() {
        t1 = new Temperature(0.0, 'K');
        t2 = new Temperature(0.0, 'F');
        t3 = new Temperature(0.0, 'C');
        Temperature totalK1 = t1.subtract(t2);
        Temperature totalK2 = t1.subtract(t3);
        assertEquals(-255.372, totalK1.getValue(), 0.1);
        assertEquals(-273.15, totalK2.getValue(), 0.1);
        assertTrue(totalK1.getUnit() == 'K');
        assertTrue(totalK2.getUnit() == 'K');
    }

    @Test
    public void testSubtractNonzeroFandC() {
        t1 = new Temperature(100.0, 'F');
        t2 = new Temperature(1.0, 'C');
        Temperature total = t1.subtract(t2);
        assertEquals(66.2, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'F');
    }

    @Test
    public void testSubtractNonzeroFandK() {
        t1 = new Temperature(100.0, 'F');
        t2 = new Temperature(0.0, 'K');
        Temperature total = t1.subtract(t2);
        assertEquals(559.67, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'F');
    }

    @Test
    public void testSubtractNonzeroCandF() {
        t1 = new Temperature(100.0, 'C');
        t2 = new Temperature(1.0, 'F');
        Temperature total = t1.subtract(t2);
        assertEquals(117.2222, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'C');
    }

    @Test
    public void testSubtractNonzeroCandK() {
        t1 = new Temperature(100.0, 'C');
        t2 = new Temperature(1.0, 'K');
        Temperature total = t1.subtract(t2);
        assertEquals(372.15, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'C');
    }

    @Test
    public void testSubtractNonzeroKandF() {
        t1 = new Temperature(100.0, 'K');
        t2 = new Temperature(1.0, 'C');
        Temperature total = t1.subtract(t2);
        assertEquals(-174.15, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'K');
    }

    @Test
    public void testSubtractNonzeroKandC() {
        t1 = new Temperature(100.0, 'K');
        t2 = new Temperature(1.0, 'F');
        Temperature total = t1.subtract(t2);
        assertEquals(-155.928, total.getValue(), 0.1);
        assertTrue(total.getUnit() == 'K');
    }

    /**
     * Test of convertTo method, of class Temperature.
     */
    @Test
    public void testConvertZeroKtoF() {
        t1 = new Temperature(0.0, 'K');
        Temperature temp = t1.convertTo('F');
        assertEquals(-459.67, temp.getValue(), 0.1);
        assertTrue(temp.getUnit() == 'F');
    }

    @Test
    public void testConvertZeroKtoC() {
        t1 = new Temperature(0.0, 'K');
        Temperature temp = t1.convertTo('C');
        assertEquals(-273.15, temp.getValue(), 0.1);
        assertTrue(temp.getUnit() == 'C');
    }

    @Test
    public void testConvertZeroCtoF() {
        t1 = new Temperature(0.0, 'C');
        Temperature temp = t1.convertTo('F');
        assertEquals(32, temp.getValue(), 0.1);
        assertTrue(temp.getUnit() == 'F');
    }

    @Test
    public void testConvertZeroCtoK() {
        t1 = new Temperature(0.0, 'C');
        Temperature temp = t1.convertTo('K');
        assertEquals(273.15, temp.getValue(), 0.1);
        assertTrue(temp.getUnit() == 'K');
    }

    @Test
    public void testConvertZeroFtoC() {
        t1 = new Temperature(0.0, 'F');
        Temperature temp = t1.convertTo('C');
        assertEquals(-17.7778, temp.getValue(), 0.1);
        assertTrue(temp.getUnit() == 'C');
    }

    @Test
    public void testConvertZeroFtoK() {
        t1 = new Temperature(0.0, 'F');
        Temperature temp = t1.convertTo('K');
        assertEquals(255.372, temp.getValue(), 0.1);
        assertTrue(temp.getUnit() == 'K');
    }

    /**
     * Test of convertCelsiusAndFahrenheit method, of class Temperature.
     */
    @Test
    public void testConvertCelsiusAndFahrenheit() {
        t1 = new Temperature(0.0, 'F');
        Temperature newTemp = t1.convertCelsiusAndFahrenheit(t1);
        assertEquals(-17.7778, newTemp.getValue(), 0.1);
        assertTrue(newTemp.getUnit() == 'C');
    }

    /**
     * Test of convertKelvinAndFahrenheit method, of class Temperature.
     */
    @Test
    public void testConvertKelvinAndFahrenheit() {
        //Tested within above tests.
    }

    /**
     * Test of convertKelvinAndCelsius method, of class Temperature.
     */
    @Test
    public void testConvertKelvinAndCelsius() {
        //Tested within above tests.
    }

    /**
     * Test of equals method and hash codes, of class Temperature.
     */
    @Test
    public void testReflexivitySameUnit() {
        assertEquals(true, t2.equals(t5));
        Assert.assertTrue(t2.hashCode() == t5.hashCode());
    }

//    @Test
//        public void testReflexivityCandF() {
//        Temperature t00 = new Temperature(1.0, 'C');
//        Temperature t22 = new Temperature(33.8, 'F');
//        assertEquals(true, t1.equals(t2));
//        assertEquals(true, t2.equals(t1));
//        //Assert.assertTrue(t1.hashCode() == t2.hashCode());
//    }
    @Test
    public void testNonnullity() {
        t1 = new Temperature(0.0, 'F');
        assertEquals(false, t1.equals(null));
    }

    /**
     * Test of toString method, of class Temperature.
     */
    @Test
    public void testToStringKelvin() {
        assertEquals(t7.toString(), "1 K");
        assertEquals((new Temperature(100.50, 'K')).toString(), "101 K");
        assertEquals((new Temperature(0.0, 'K')).toString(), "0 K");
    }

    @Test
    public void testToStringCelsius() {
        assertEquals(t6.toString(), "1" + "\u00B0" + " C");
        assertEquals((new Temperature(100.50, 'C')).toString(), "101" + "\u00B0" + " C");
        assertEquals((new Temperature(999.50, 'C')).toString(), "1000" + "\u00B0" + " C");
    }

    @Test
    public void testToStringFahrenheit() {
        assertEquals(t8.toString(), "100" + "\u00B0" + " F");
        assertEquals((new Temperature(100.50, 'F')).toString(), "101" + "\u00B0" + " F");
        assertEquals((new Temperature(999.50, 'F')).toString(), "1000" + "\u00B0" + " F");
    }

    /**
     * Test of copy method, of class Temperature.
     */
    @Test
    public void testCopyContents() {
        t1 = t2.copy();
        Assert.assertTrue(t1.getValue() == t2.getValue() && t1.getUnit() == t2.getUnit());
    }

    @Test
    public void testCopyMemoryAddress() {
        t1 = t2.copy();
        Assert.assertTrue(t1.equals(t2));
        Assert.assertFalse(t1 == t2);
    }

    /**
     * Test of compareTo method, of class Temperature.
     */
    @Test
    public void testCompareToF() {
        t1 = new Temperature(0, 'F');
        t2 = new Temperature(0, 'F');
        t3 = new Temperature(1, 'F');
        int equals = t1.compareTo(t2);
        int lessThan = t1.compareTo(t3);
        int greaterThan = t3.compareTo(t1);
        assertTrue(equals == 0);
        assertTrue(lessThan < 0);
        assertTrue(greaterThan > 0);
    }

    @Test
    public void testCompareToC() {
        t1 = new Temperature(0, 'C');
        t2 = new Temperature(0, 'C');
        t3 = new Temperature(1, 'C');
        int equals = t1.compareTo(t2);
        int lessThan = t1.compareTo(t3);
        int greaterThan = t3.compareTo(t1);
        assertTrue(equals == 0);
        assertTrue(lessThan < 0);
        assertTrue(greaterThan > 0);
    }

    @Test
    public void testCompareToK() {
        t1 = new Temperature(0, 'K');
        t2 = new Temperature(0, 'K');
        t3 = new Temperature(1, 'K');
        int equals = t1.compareTo(t2);
        int lessThan = t1.compareTo(t3);
        int greaterThan = t3.compareTo(t1);
        assertTrue(equals == 0);
        assertTrue(lessThan < 0);
        assertTrue(greaterThan > 0);
    }

    @Test
    public void testCompareCandOthers() {
        t1 = new Temperature(0, 'C');
        t2 = new Temperature(0, 'F');
        t3 = new Temperature(0, 'K');
        int greaterOne = t1.compareTo(t2);
        int greaterTwo = t1.compareTo(t3);
        assertTrue(greaterOne > 0);
        assertTrue(greaterTwo > 0);
    }

    @Test
    public void testCompareKandOthers() {
        t1 = new Temperature(0, 'K');
        t2 = new Temperature(0, 'C');
        t3 = new Temperature(0, 'F');
        int lessThanOne = t1.compareTo(t2);
        int lessThanTwo = t1.compareTo(t3);
        assertTrue(lessThanOne < 0);
        assertTrue(lessThanTwo < 0);
    }

    @Test
    public void testCompareFandOthers() {
        t1 = new Temperature(0, 'F');
        t2 = new Temperature(0, 'C');
        t3 = new Temperature(0, 'K');
        int lessThan = t1.compareTo(t2);
        int greaterThan = t1.compareTo(t3);
        assertTrue(lessThan < 0);
        assertTrue(greaterThan > 0);
    }

}
