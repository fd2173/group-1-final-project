package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class provides a growable array for Double Objects.
 *
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
class RealArrayList extends DoubleArrayList implements Serializable {

    /**
     * Insert the given double value at the end of the list.
     *
     * @param value the value to be stored
     * @return the index the value was inserted at
     */
    public int add(double value) {
        int count = super.getCount();
        try {
            super.add(count, value);
        } catch (IndexException ex) {
            System.out.println("IndexException caught in add of RealArrayList");
        }
        return count;
    }

    /**
     * Deletes all instances of the value from the list. Following removal of
     * each instance of the value, remaining elements in the array are moved so
     * that there are no empty spaces.
     *
     * @param value the value to be deleted
     */
    public void removeAll(double value) {
        for (int i = 0; i < super.getCount(); i++) {
            try {
                if (super.getValue(i) == value) {
                    super.remove(i);
                    i--;
                }
            } catch (IndexException ex) {
                System.out.println("IndexException caught in removeAll");
            }
        }
    }

    /**
     * Returns the index of the last element in the list containing the value.
     * If the value is not found, this method returns -1.
     *
     * @param value the value to be stored
     * @return the index the value was inserted at
     */
    public int findLastIndex(long value) {
        int lastIndex = this.findFirstIndex(value);
        int count = this.getCount() - 1;
        if (lastIndex != -1) {
            try {
                while (count >= lastIndex) {
                    if (this.getValue(count) == value) {
                        return count;
                    } else {
                        count--;
                    }
                }
            } catch (IndexException ex) {
                System.out.println("IndexException caught in findLastIndex");
            }
        }
        return lastIndex;
    }
}
