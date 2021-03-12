package numberlist;

/**
 * This class represents a custom exception IndexException.
 *
 * @author Octavia Stappart
 * @author Kirtiashna Chandra
 * @date 03/06/2021
 * @version 1.0
 */
public class IndexException extends Exception {

    //Fields
    private int min;
    private int max;
    private int value;

    /**
     * Full constructor. Creates a new IndexException object. Requires the
     * lowest (min) index and highest (max) index. Value is the index actually
     * sent.
     *
     * @param min the lowest index
     * @param max the highest index
     * @param value the specified index
     */
    public IndexException(int min, int max, int value) {
        super("INDEX EXCEPTION: Value must be between " + min + " and " + max);
        this.min = min;
        this.max = max;
        this.value = value;
    }

    /**
     * Provides access to the lowest allowed index.
     *
     * @return the lowest index
     */
    public int getMin() {
        return min;
    }

    /**
     * Provides access to the highest allowed index.
     *
     * @return the highest index
     */
    public int getMax() {
        return max;
    }

    /**
     * Provides access to the specified index.
     *
     * @return the specified index
     */
    public int getValue() {
        return value;
    }
}
