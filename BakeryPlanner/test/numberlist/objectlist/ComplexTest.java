package numberlist.objectlist;

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
public class ComplexTest {

    Complex c1, c2, c3, c4, c5, c6;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        c1 = new Complex(1.2, 2.3);
        c2 = new Complex(4.5, 5.6);
        c3 = new Complex(1.2, 2.3);
        c4 = new Complex(-5.6, -6.7);
        c5 = new Complex(1.2, 2.3);
    }

    /**
     * Test of getReal method, of class Complex.
     */
    @Test
    public void testGetRealPos() {
        assertEquals(c1.getReal(), 1.2, 0.1);
    }

    @Test
    public void testGetRealNeg() {
        assertEquals(c4.getReal(), -5.6, 0.1);
    }

    /**
     * Test of getImaginary method, of class Complex.
     */
    @Test
    public void testGetImaginaryPos() {
        assertEquals(c1.getImaginary(), 2.3, 0.1);
    }

    @Test
    public void testGetImaginaryNeg() {
        assertEquals(c4.getImaginary(), -6.7, 0.1);
    }

    /**
     * Test of add method, of class Complex.
     */
    @Test
    public void testAddPos() {
        Complex c = c1.add(c2);
        assertTrue(c != c1);
        assertTrue(c != c2);
        assertEquals(c1.getReal(), 1.2, 0.1);
        assertEquals(c1.getImaginary(), 2.3, 0.1);
        assertEquals(c2.getReal(), 4.5, 0.1);
        assertEquals(c2.getImaginary(), 5.6, 0.1);
        assertEquals(c.getReal(), 5.7, 0.1);
        assertEquals(c.getImaginary(), 7.9, 0.1);
    }

    @Test
    public void testAddNeg() {
        Complex c = c1.add(c4);
        assertTrue(c != c1);
        assertTrue(c != c4);
        assertEquals(c1.getReal(), 1.2, 0.1);
        assertEquals(c1.getImaginary(), 2.3, 0.1);
        assertEquals(c4.getReal(), -5.6, 0.1);
        assertEquals(c4.getImaginary(), -6.7, 0.1);
        assertEquals(c.getReal(), -4.4, 0.1);
        assertEquals(c.getImaginary(), -4.4, 0.1);
    }

    /**
     * Test of subtract method, of class Complex.
     */
    @Test
    public void testSubtractPos() {
        Complex c = c2.subtract(c1);
        assertTrue(c != c1);
        assertTrue(c != c2);
        assertEquals(c1.getReal(), 1.2, 0.1);
        assertEquals(c1.getImaginary(), 2.3, 0.1);
        assertEquals(c2.getReal(), 4.5, 0.1);
        assertEquals(c2.getImaginary(), 5.6, 0.1);
        assertEquals(c.getReal(), 3.3, 0.1);
        assertEquals(c.getImaginary(), 3.3, 0.1);
    }

    @Test
    public void testSubtractNeg() {
        Complex c = c1.subtract(c4);
        assertTrue(c != c1);
        assertTrue(c != c4);
        assertEquals(c1.getReal(), 1.2, 0.1);
        assertEquals(c1.getImaginary(), 2.3, 0.1);
        assertEquals(c4.getReal(), -5.6, 0.1);
        assertEquals(c4.getImaginary(), -6.7, 0.1);
        assertEquals(c.getReal(), 6.7, 0.1);
        assertEquals(c.getImaginary(), 9.0, 0.1);
    }

    /**
     * Test of toString method, of class Complex.
     */
    @Test
    public void testToString() {
        assertEquals(c1.toString(), "1.2 + 2.3i");
        assertEquals(c4.toString(), "-5.6 - 6.7i");
        assertEquals((new Complex(0, 1.2)).toString(), "1.2i");
        assertEquals((new Complex(1.2, 0)).toString(), "1.2");
        assertEquals((new Complex(0, -1.2)).toString(), "-1.2i");
        assertEquals((new Complex(-1.2, 0)).toString(), "-1.2");
        assertEquals(c1.toString(), c3.toString());
    }

    /**
     * Test of equals method and hashCodes, of class Complex.
     */
    @Test
    public void testReflexivity() {
        assertEquals(true, c1.equals(c1));
        Assert.assertTrue(c1.hashCode() == c1.hashCode());
    }

    @Test
    public void testSymmetry() {
        assertEquals(true, c1.equals(c3));
        assertEquals(c3.equals(c1), c1.equals(c3));
        Assert.assertTrue(c1.hashCode() == c3.hashCode());
    }

    @Test
    public void testTransitivity() {
        assertEquals(true, c1.equals(c3));
        assertEquals(true, c3.equals(c5));
        assertEquals(true, c1.equals(c5));
        Assert.assertTrue(c1.hashCode() == c3.hashCode());
        Assert.assertTrue(c3.hashCode() == c5.hashCode());
        Assert.assertTrue(c1.hashCode() == c5.hashCode());
    }

    @Test
    public void testConsistency() {
        for (int i = 0; i < 10; i++) {
            Assert.assertFalse(c1.equals(c2) && c1.equals(c3));
            Assert.assertFalse(c1.hashCode() == c2.hashCode());
            Assert.assertTrue(c1.hashCode() == c3.hashCode());
        }
    }

    @Test
    public void testNonnullity() {
        assertEquals(false, c1.equals(null));
    }

    /**
     * Test of copy method, of class Complex.
     */
    @Test
    public void testCopyContents() {
        c6 = c1.copy();
        Assert.assertTrue(c6.getReal() == c1.getReal() && c6.getImaginary() == c1.getImaginary());
    }

    @Test
    public void testCopyMemoryAddress() {
        c6 = c1.copy();
        Assert.assertTrue(c1.equals(c6));
        Assert.assertFalse(c1 == c6);
    }

    /**
     * Test of compareTo method, of class Complex.
     */
    @Test
    public void testComplexLessThan() {
        assertEquals(-1, c1.compareTo(c2));
        assertEquals(-1, c4.compareTo(c1));
    }

    @Test
    public void testComplexEqualTo() {
        assertEquals(0, c1.compareTo(c3));
        assertEquals(0, c3.compareTo(c5));
        assertEquals(0, c1.compareTo(c5));
    }

    @Test
    public void testComplexGreaterThan() {
        assertEquals(1, c1.compareTo(c4));
        assertEquals(1, c2.compareTo(c1));
        assertEquals(1, c2.compareTo(c5));
    }
}
