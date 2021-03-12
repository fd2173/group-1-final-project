package numberlist.objectlist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class provides a growable array for Copiable objects.
 *
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
public class NumericArrayList extends NumericList implements Copiable, Serializable {

    //fields
    private Copiable[] list;

    /**
     * Constructor. Initializes the underlying array to 10 elements.
     */
    public NumericArrayList() {
        list = new Copiable[10];
        count = 0;
    }

    /**
     * Inserts the given Copiable object at the given index.The index is assumed
     * to be a value between 0 and count. Existing elements are moved up as
     * needed to make room for the new object.
     *
     * @param index the index where the new value should be stored
     * @param obj the object to be stored
     * @throws numberlist.IndexException
     */
    @Override
    public void add(int index, Copiable obj) throws IndexException {
        Copiable[] newList = new Copiable[list.length];
        if (index < 0 || index > count) {
            throw new IndexException(0, count, index);
        } else if (index == list.length) {
            newList = new Copiable[list.length * 2];
        }

        for (int i = 0; i <= count; i++) {
            if (i < index) {
                newList[i] = list[i];
            }

            if (i == index) {
                newList[i] = obj;
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
     * @param index the index of the object to remove
     * @param obj the value added
     * @return the Copiable object removed
     * @throws numberlist.IndexException
     *
     */
    @Override
    public Copiable set(int index, Copiable obj) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, (count - 1), index);
        }
        Copiable replaced = this.getValue(index);
        list[index] = obj;
        return replaced;
    }

    /**
     * Deletes the object at the given index. The index is assumed to be a value
     * between 0 and count - 1. Existing elements are moved down as needed to
     * keep all object contiguous, without any empty spaces in the array.
     *
     * @param index the index of the element that should be removed
     * @return the object that was removed
     * @throws numberlist.IndexException
     */
    @Override
    public Copiable remove(int index) throws IndexException {
        if (index < 0 || index >= count) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, (count - 1), index);
        }
        Copiable[] newList = new Copiable[(list.length - 1)];
        Copiable removedValue = list[index];
        for (int i = 0; i < count - 1; i++) {

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
     * Deletes the first instance of the given object. Existing elements are
     * moved down as needed to keep all objects contiguous, without any empty
     * spaces in the array. If the objects does not exist, this method returns
     * without doing anything.
     *
     * @param obj the object to remove
     */
    @Override
    public void remove(Copiable obj) {
        if (findFirstIndex(obj) != -1) {
            try {
                remove(findFirstIndex(obj));
            } catch (IndexException ie) {
                System.out.println("IndexException caught");
            }
        }
    }

    /**
     * Returns the object at the given index without removing it.The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element
     * @return the object at that index
     * @throws numberlist.IndexException
     */
    @Override
    public Copiable getValue(int index) throws IndexException {
        if (count == 0) {
            throw new IndexException(-1, -1, index);
        }
        if (index < 0 || index >= count) {
            throw new IndexException(0, count - 1, index);
        }
        return list[index];
    }

    /**
     * Returns the index of the first instance of the given object in the array.
     * If the object doesn't exist, -1 is returned.
     *
     * @param obj the value to find in the array
     * @return the index where the object was found, or -1 if not found
     */
    @Override
    public int findFirstIndex(Copiable obj) {
        for (int i = 0; i < count; i++) {
            if (list[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Provides access to the number of objects currently in the array.
     *
     * @return the number of objects in the array
     */
    @Override
    public int getCount() {
        return count;
    }

    /**
     * Provides a string representation of the growable array, displaying all
     * objects currently in the array using the format [ object1, object2, ...
     * ].
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

    /**
     * Creates a deep copy of a NumericArrayList object. Implements the copy()
     * method from the Copiable interface.
     *
     * @return a deep copy of the NumericArrayList object
     */
    @Override
    public NumericArrayList copy() {
        NumericArrayList n = new NumericArrayList();
        for (int i = 0; i < count; i++) 
            try {
            Copiable obj = this.getValue(i).copy();
            n.add(obj);
        } catch (IndexException ex) {
            System.out.println("IndexException Caught");
        }
        return n;
    }
}
