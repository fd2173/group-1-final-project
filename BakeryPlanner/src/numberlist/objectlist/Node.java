package numberlist.objectlist;

import java.io.Serializable;

/**
 * This class provides a node linked structure. Each instance of this Node class
 * holds a Copiable object and a link to the next node.
 *
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
class Node implements Serializable {

    //Data Fields
    private Node nextNode;
    private Copiable value;

    /**
     * Default constructor. This creates a new node, sets the value field, and
     * sets a reference to the next node.
     *
     * @param obj the object for the node
     */
    public Node(Copiable obj) {
        this.value = obj;
        this.nextNode = null;
    }

    /**
     * Returns the value of a node.This returns the Copiable value that the
     * calling object's node contains. return the Copiable value within the node
     *
     * @return value at the index
     */
    public Copiable getValue() {
        return this.value;
    }

    /**
     * Sets the value of the node to the specified object.
     *
     * @param value the value to be set in the node
     */
    public void setValue(Copiable value) {
        this.value = value;
    }

    /**
     * Get the following node in the linked list. This returns the value of the
     * node following the calling object.
     *
     * @return the node following the calling object
     */
    public Node getNextNode() {
        return nextNode;
    }

    /**
     * Sets the value of the next node in the linked list.
     *
     * @param node the object for the node
     */
    public void setNextNode(Node node) {
        this.nextNode = node;
    }

}
