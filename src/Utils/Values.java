package Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * TAI, November 2017
 *
 * Assignment 2 - Language recognition system
 *
 * @author Bárbara Jael, 73241, barbara.jael@ua.pt
 * @author Miriam Cardoso, 72181, miriamcardoso@ua.pt
 * @author Tiago Faria, 73714, tiagohpf@ua.pt
 */

// Class that represents occurrences in values of Maps
public class Values {
    // Pair <letter, occurrence>
    private Map<String, Integer> values;

    /**
     * Constructor
     */
    public Values() {
        values = new HashMap<>();
    }

    /**
     * Get occurrences
     * @return probabilities
     */
    public Map<String, Integer> getValues() {
        return values;
    }

    /**
     * Add a new occurrence
     * @param letter
     * @param number
     */
    public void addValue(String letter, int number) {
        values.put(letter, number);
    }

    /**
     * Get occurrences
     * @param letter
     * @return
     */
    public int getNumber(String letter) {
        return values.get(letter);
    }
}
