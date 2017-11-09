package Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * TAI, November 2017
 * <p>
 * Assignment 2 - Language recognition system
 *
 * @author Bárbara Jael, 73241, barbara.jael@ua.pt
 * @author Miriam Cardoso, 72181, miriamcardoso@ua.pt
 * @author Tiago Faria, 73714, tiagohpf@ua.pt
 */

// Class that represents probabilities in values of Maps
public class Probabilities {
    // Pair <letter, probability>
    private Map<String, Double> probs;

    /**
     * Constructor
     */
    public Probabilities() {
        probs = new HashMap<>();
    }

    /**
     * Get probabilities
     * @return probabilities
     */
    public Map<String, Double> getProbs() {
        return probs;
    }

    /**
     * Add a new probability
     * @param letter
     * @param prob
     */
    public void addValue(String letter, double prob) {
        probs.put(letter, prob);
    }
}
