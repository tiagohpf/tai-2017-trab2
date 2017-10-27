package Utils;

/**
 * TAI, November 2017
 * <p>
 * Assignment 2 - Language recognition system
 *
 * @author Bárbara Jael, 73241, barbara.jael@ua.pt
 * @author Miriam Cardoso, 72181, miriamcardoso@ua.pt
 * @author Tiago Faria, 73714, tiagohpf@ua.pt
 */

// Class that represents a key of HashMap
public class Key{
    private final String firstValue;
    private final String secondValue;

    /**
     * Constructor
     * @param firstValue
     * @param secondValue
     */
    public Key(String firstValue, String secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /**
     * Get first value
     * @return first value
     */
    public String getFirstValue() {
        return firstValue;
    }

    /**
     * Get second value
     * @return first value
     */
    public String getSecondValue() {
        return secondValue;
    }

    /**
     * Check if two objects are equal
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return firstValue.equals(key.firstValue) && secondValue.equals(key.secondValue);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstValue == null) ? 0 : firstValue.hashCode());
        result = prime * result
                + ((secondValue == null) ? 0 : secondValue.hashCode());
        return result;
    }

    /**
     * Print object
     * @return string
     */
    @Override
    public String toString() {
        return "(" + firstValue + "," + secondValue + ")";
    }
}