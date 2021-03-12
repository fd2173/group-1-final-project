package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class provides a growable array for primitive long values.
 *
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
class LongArrayList implements Serializable {

    //fields
    private long[] list;
    private int count;

    /**
     * Constructor. Initializes the underlying array to 10 elements.
     */
    LongArrayList() {
        list = new long[10];
        count = 0;
    }

    /**
     * Inserts the given long value at the given index. The index is assumed to
     * be a value between 0 and count. Existing elements are moved up as needed
     * to make room for the new value.
     *
     * @param index the index where the new value should be stored
     * @param value the value to be stored
     */
    public void add(int index, long value) throws IndexException {
        if (index < 0 || index > count) {
            throw new IndexException(0, count, index);
        }
        long[] newList = new long[list.length];
        if (count == list.length) {
            newList = new long[list.length * 2];
        }
        for (int i = 0; i <= count; i++) {

            if (i < index) {
                newList[i] = list[i];
            }
            if (i == index) {
                newList[i] = value;
            }
            if (i > index) {
                newList[i] = list[i - 1];
            }
        }
        list = newList;
        count++;
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
    public long set(int index, long value) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, (count - 1), index);
        }
        long replaced = list[index];
        list[index] = value;
        return replaced;
    }

    /**
     * Deletes the value at the given index. The index is assumed to be a value
     * between 0 and count - 1. Existing elements are moved down as needed to
     * keep all values contiguous, without any empty spaces in the array.
     *
     * @param index the index of the element that should be removed
     * @return the value that was removed
     */
    public long remove(int index) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, (count - 1), index);
        }
        long[] newList = new long[(list.length - 1)];
        long removedValue = list[index];
        for (int i = 0; i < (count - 1); i++) {
            if (i < index) {
                newList[i] = list[i];
            }
            if (i >= index) {
                newList[i] = list[i + 1];
            }
        }
        list = newList;
        count--;
        return removedValue;
    }

    /**
     * Deletes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param value the value to remove
     */
    public void remove(long value) throws IndexException {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (list[i] == value && found == false) {
                try {
                    remove(i);
                    found = true;
                } catch (IndexException ex) {
                    System.out.println("IndexException caught");
                    throw ex;
                }
            }
        }
    }

    /**
     * Returns the value at the given index without removing it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element
     * @return the value at that index
     */
    public long getValue(int index) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, (count - 1), index);
        }
        long value = list[index];
        return value;
    }

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param value the value to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    public int findFirstIndex(long value) {
        int index = -1;
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (list[i] == value && found == false) {
                index = i;
                found = true;
            }
        }
        return index;
    }

    /**
     * Provides access to the number of values currently in the array.
     *
     * @return the number of values in the array
     */
    public int getCount() {
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
        for (int i = 0; i < count; i++) {
            output += list[i] + ", ";
        }
        if (count > 0) {
            output = output.substring(0, output.length() - 2);
        } else {
            output = output.substring(0, output.length() - 1);
        }
        output += " ]";
        return output;
    }
}
