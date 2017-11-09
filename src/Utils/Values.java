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

public class Values {
    private Map<String, Integer> values;

    public Values() {
        values = new HashMap<>();
    }

    public Map<String, Integer> getValues() {
        return values;
    }

    public void addValue(String letter, int number) {
        values.put(letter, number);
    }

    public int getNumber(String letter) {
        return values.get(letter);
    }
}
