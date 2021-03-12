package numberlist.objectlist;

/**
 * This Copiable interface is implemented by other classes. This interface is
 * designed to be implemented by classes which are capable of having a deep copy
 * produced of their contents.
 * 
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date   03/06/2021
 * @version 1.0
 */

public interface Copiable {

    /**
     * This copy method is implemented within the classes using this interface.
     * Each method returns a deep copy of the object.
     *
     * @return Copiable, a new deep copy of the object
     */
    Copiable copy();

}
