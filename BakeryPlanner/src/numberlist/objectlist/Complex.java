package numberlist.objectlist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * This class represents a single complex number of the form x + yi.
 *
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
public final class Complex implements Copiable, Comparable<Complex>, Serializable {

    //fields
    private double real;
    private double imaginary;

    /**
     * Default constructor. Creates a new Complex object. Sets both real and
     * imaginary to zero.
     */
    public Complex() {
        real = 0;
        imaginary = 0;
    }

    /**
     * Full constructor. Creates a new Complex object.
     *
     * @param real the value of the real portion of the complex number
     * @param imaginary the value of the imaginary portion of the complex number
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Provides access to the real portion of the complex number
     *
     * @return the value of the real portion of the complex number
     */
    public double getReal() {
        return real;
    }

    /**
     * Provides access to the imaginary portion of the complex number
     *
     * @return the value of the imaginary portion of the complex number
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Adds the current and the given complex numbers together, and stores the
     * sum in a new Complex object. The current and given complex numbers are
     * not altered in the process.
     *
     * @param other the other complex number to add to this one
     * @return the new Complex object that holds the result of the addition
     */
    public Complex add(Complex other) {

        double realValue = real;
        realValue += other.real;

        double imaginaryValue = imaginary;
        imaginaryValue += other.imaginary;

        return new Complex(realValue, imaginaryValue);
    }

    /**
     * Subtracts the other complex number from this one, and stores the result
     * in a new Complex object. The current and given complex numbers are not
     * altered in the process.
     *
     * @param other the other complex number to subtract from this one
     * @return the new Complex object that holds the result of the subtraction
     */
    public Complex subtract(Complex other) {

        double realValue = real;
        realValue -= other.real;
        double imaginaryValue = imaginary;
        imaginaryValue -= other.imaginary;

        return new Complex(realValue, imaginaryValue);
    }

    /**
     * Provides a string representation of the current complex number, in the
     * form x + yi.
     *
     * @return the string representation of the current complex number
     */
    @Override
    public String toString() {
        BigDecimal bd = new BigDecimal(real);
        bd = bd.round(new MathContext(2));
        real = bd.doubleValue();
        bd = new BigDecimal(imaginary);
        bd = bd.round(new MathContext(2));
        imaginary = bd.doubleValue();
        if (imaginary == 0) {
            return real + "";
        }
        if (real == 0) {
            return imaginary + "i";
        }
        if (imaginary < 0) {
            return real + " - " + (-imaginary) + "i";
        }
        return real + " + " + imaginary + "i";
    }

    /**
     * Tests whether two Complex objects are equal. This method determines if
     * two distinct Complex objects have the same contents.
     *
     * @param other the object to evaluate
     * @return true if the objects have identical content, else false
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Complex) {
            return (real == ((Complex) other).real && imaginary == ((Complex) other).imaginary);
        } else {
            return false;
        }
    }

    /**
     * This method overrides the hashCode method due to the use of the
     * overridden Object equals method. This method determines if two equal
     * objects return the same hash code.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.real) ^ (Double.doubleToLongBits(this.real) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.imaginary) ^ (Double.doubleToLongBits(this.imaginary) >>> 32));
        return hash;
    }

    /**
     * Returns a deep copy of a Complex object. Implements the copy() method
     * from the Copiable interface.
     *
     * @return a deep copy of the Complex object
     */
    @Override
    public Complex copy() {
        return new Complex(this.real, this.imaginary);
    }

    /**
     * Compares two Complex objects. Returns -1 if the object is less than, 0 if
     * equal, and 1 if greater than.
     *
     * @param other
     * @return 0 if same, -1 if smaller, 1 if larger
     */
    @Override
    public int compareTo(Complex other) {

        if (this.getReal() < other.getReal()) {
            return -1;
        }
        if (this.getReal() == other.getReal()) {
            if (this.getImaginary() < other.getImaginary()) {
                return -1;
            } else if (this.getImaginary() > other.getImaginary()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }
}
