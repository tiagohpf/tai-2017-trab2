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

public class Probabilities {
    private Map<String, Double> probs;

    public Probabilities() {
        probs = new HashMap<>();
    }

    public Map<String, Double> getProbs() {
        return probs;
    }

    public void addValue(String letter, double prob) {
        probs.put(letter, prob);
    }
}
