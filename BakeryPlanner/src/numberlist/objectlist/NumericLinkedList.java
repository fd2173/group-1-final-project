package numberlist.objectlist;

import java.io.Serializable;
import numberlist.IndexException;

/**
 * This class implements a list ADT using nodes. The count value, inherited from
 * NumericList, holds the current size of the linked list. Each node has access
 * to the next node. Reaching the null node indicates the end of the list ADT.
 *
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
class NumericLinkedList extends NumericList implements Copiable, Serializable {

    //fields 
    private Node firstNode;

    /**
     * Default constructor. Creates a new NumericLinkedList object.
     */
    public NumericLinkedList() {
        this.firstNode = firstNode;
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
        if (index < 0 || index > count) {
            throw new IndexException(0, count, index);
        }
        Node newNode = new Node(obj);
        if (index == 0) {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
        }
        count++;
    }

    /**
     * Replaces the value at the specified index with a new specified value.
     * Given a specified index and object, this replaces the value with the
     * specified value and returns the removed value.
     *
     * @param index the index of the object to replace
     * @param obj the value added
     * @return the Copiable object removed
     * @throws numberlist.IndexException
     */
    @Override
    public Copiable set(int index, Copiable obj) throws IndexException {
        if (count == 0) {
            throw new IndexException(-1, -1, index);
        }
        if (index < 0 || index >= count) {
            throw new IndexException(0, count - 1, index);
        }
        Copiable temp;
        Node currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        temp = currentNode.getValue();
        currentNode.setValue(obj);
        return temp;
    }

    /**
     * Deletes the node at the given index.The index is assumed to be a value
     * between 0 and count - 1. Existing elements are moved down as needed to
     * keep all object contiguous, without any empty spaces in the array.
     *
     * @param index the index of the element that should be removed
     * @return the object that was removed
     * @throws numberlist.IndexException
     */
    @Override
    public Copiable remove(int index) throws IndexException {
        if (index < 0 || index > (count - 1)) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, (count - 1), index);
        }
        if (index == 0) {
            Copiable returnNode = firstNode.getValue();
            firstNode = firstNode.getNextNode();
            count--;
            return returnNode;
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            Node removedNode = currentNode.getNextNode();
            currentNode.setNextNode(removedNode.getNextNode());
            count--;
            return removedNode.getValue();
        }
    }

    /**
     * Deletes the first node containing the specified Copiable object. Existing
     * nodes are moved down as needed to keep all nodes contiguous. If no node
     * contains that object, this method returns without doing anything.
     *
     * @param obj the object to remove
     */
    @Override
    public void remove(Copiable obj) {
        if (findFirstIndex(obj) != -1) {
            try {
                remove(findFirstIndex(obj));
            } catch (IndexException ie) {
                System.out.println("IndexException Caught");
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
        if (index < 0 || index > (count - 1)) {
            if (count == 0) {
                throw new IndexException(-1, -1, index);
            }
            throw new IndexException(0, (count - 1), index);
        }
        Node currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getValue();
    }

    /**
     * Returns the index of the node containing the first instance of the given
     * object. If the object doesn't exist, -1 is returned.
     *
     * @param obj the value to find in the array
     * @return the index where the object was found, or -1 if not found
     */
    @Override
    public int findFirstIndex(Copiable obj) {
        Node current = firstNode;
        for (int i = 0; i < count; i++) {
            if (current.getValue().equals(obj)) {
                return i;
            } else {
                current = current.getNextNode();
            }
        }
        return -1;
    }

    /**
     * Provides a string representation of the list ADT, displaying all objects
     * currently in the array using the format [ object1, object2, ... ].
     *
     * @return the string representation of the list ADT
     */
    @Override
    public String toString() {
        String output = "[ ";
        Node currentNode = firstNode;
        for (int i = 0; i < count; i++) {
            output += currentNode.getValue() + ", ";
            currentNode = currentNode.getNextNode();
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
     * Returns a deep copy of a NumericLinkedList. Implements the copy() method
     * from the Copiable interface.
     *
     * @return a deep copy of the NumericLinkedList object
     */
    @Override
    public NumericLinkedList copy() {
        NumericLinkedList n = new NumericLinkedList();
        Node currentNode = firstNode;
        n.firstNode = new Node(currentNode.getValue().copy());
        n.count++;
        for (int i = 1; i < count; i++) {
            currentNode = currentNode.getNextNode();
            n.add(currentNode.getValue().copy());
        }
        return n;
    }
}
