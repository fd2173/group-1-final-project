package numberlist.objectlist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class represents the List ADT and all its basic methods. It is not
 * dependent on the list implementation. The methods whose implementation varies
 * are declared abstract here, and are implemented appropriately in their child
 * classes.
 *
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
abstract class NumericList implements Serializable {

    //Data fields
    int count;

    /**
     * Inserts the given object at the end of the list. This method uses dynamic
     * binding to implement the appropriate add method at run-time according to
     * the calling variable's actual type.
     *
     * @param index the index to insert the object at
     * @param obj the Copiable object to insert
     */
    abstract void add(int index, Copiable obj) throws IndexException;

    /**
     * Adds the specified object to the list. This method calls the abstract add
     * method above, which in turn calls the appropriate method at run-time
     * according to the object's actual type.
     *
     * @param obj
     * @return the index the object was placed at
     */
    public int add(Copiable obj) {
        try {
            add(this.getCount(), obj);
        } catch (IndexException ex) {
            System.out.println("IndexException caught");
        }
        return (this.getCount() - 1);
    }

    /**
     * Replaces the value at the specified index with a new specified value.
     * This method uses dynamic binding to implement the appropriate remove
     * method at run-time according to the calling variable's actual type.
     *
     * @param index the index of the object to remove
     * @param obj the value added
     * @return the Copiable object removed
     */
    abstract Copiable set(int index, Copiable obj) throws IndexException;

    /**
     * Removes the object at the specified index. This method uses dynamic
     * binding to implement the appropriate remove method at run-time according
     * to the calling variable's actual type.
     *
     * @param index the index of the object to remove
     */
    abstract Copiable remove(int index) throws IndexException;

    /**
     * Removes the specified object. This method uses dynamic binding to
     * implement the appropriate remove method at run-time according to the
     * calling variable's actual type.
     *
     * @param obj the object to remove
     */
    abstract void remove(Copiable obj);

    /**
     * Returns the object at the specified index. This method uses dynamic
     * binding to implement the appropriate getValue method at run-time
     * according to the calling variable's actual type.
     *
     * @param index of the object to return
     * @return the Copiable object at the index
     */
    abstract Copiable getValue(int index) throws IndexException;

    /**
     * Finds the first index of the specified object. This method uses dynamic
     * binding to implement the appropriate findFirstIndex method at run-time
     * according to the calling variable's actual type.
     *
     * @param obj the object to find
     * @return the index of the object
     */
    abstract int findFirstIndex(Copiable obj);

    /**
     * Returns the number of objects in the array. This method is overridden in
     * all child classes to avoid recursion (hopefully).
     *
     * @return the number of objects in the array
     */
    public int getCount() {
        return count;
    }
}
