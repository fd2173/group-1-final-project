package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class uses composition to create a floating point version of
 * LongArrayList.
 *
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
class DoubleArrayList implements Serializable {

    //Data fields
    LongArrayList list = new LongArrayList();

    /**
     * Constructor.
     */
    public DoubleArrayList() {

    }

    /**
     * Inserts the given double value at the given index.
     *
     * @param index the index where the new value should be stored
     * @param value the value to be stored
     */
    public void add(int index, double value) throws IndexException {
        try {
            list.add(index, Double.doubleToRawLongBits(value));
        } catch (IndexException ex) {
            throw ex;
        }
    }

    /**
     * Replaces the value at the specified index with a new specified value.
     * Given a specified index and object, this replaces the value with the
     * specified value and returns the removed value.
     *
     * @param index the index of the object to replace
     * @param value the value added
     * @return the Copiable object removed
     * @throws numberlist.IndexException
     */
    public double set(int index, double value) throws IndexException {
        double removedValue = Double.longBitsToDouble(list.set(index, Double.doubleToRawLongBits(value)));
        return removedValue;
    }

    /**
     * Deletes the value at the given index.
     *
     * @param index the index of the element that should be removed
     * @return the value that was removed
     */
    public double remove(int index) throws IndexException {
        double removedValue;
        try {
            removedValue = Double.longBitsToDouble(list.remove(index));
        } catch (IndexException ex) {
            throw ex;
        }
        return removedValue;
    }

    /**
     * Deletes the first instance of the given value.
     *
     * @param value the value to remove
     */
    public void remove(double value) throws IndexException {
        try {
            list.remove(Double.doubleToRawLongBits(value));
        } catch (IndexException ex) {
            System.out.println("IndexException caught in DoubleArrayList and Thrown.");
            throw ex;
        }
    }

    /**
     * Returns the value at the given index without removing it.
     *
     * @param index the index of the element
     * @return the value at that index
     */
    public double getValue(int index) throws IndexException {
        try {
            double value = Double.longBitsToDouble(list.getValue(index));
            return value;
        } catch (IndexException ex) {
            throw ex;
        }
    }

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param value the value to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    public int findFirstIndex(double value) {
        int firstIndex = list.findFirstIndex(Double.doubleToRawLongBits(value));
        return firstIndex;
    }

    /**
     * Provides access to the number of values currently in the array.
     *
     * @return the number of values in the array
     */
    public int getCount() {
        int count = list.getCount();
        return count;
    }

    /**
     * Provides a string representation of the growable array, displaying all
     * values currently in the array using the format [ value1, value2, ... ].
     *
     * @return the string representation of the array
     */
    @Override
    public String toString() {
        String output = "[ ";
        try {
            for (int i = 0; i < list.getCount(); i++) {
                output += Double.longBitsToDouble(list.getValue(i)) + ", ";
            }
            if (list.getCount() > 0) {
                output = output.substring(0, output.length() - 2);
            } else {
                output = output.substring(0, output.length() - 1);
            }
            output += " ]";
        } catch (IndexException ex) {
            System.out.println("IndexException caught");
        }
        return output;
    }
}
