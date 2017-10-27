package Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * TAI, November 2017
 *
 * Assignment 2 - Language recognition system
 *
 * @author Bárbara Jael, 73241, barbara.jael@ua.pt
 * @author Miriam Cardoso, 72181, miriamcardoso@ua.pt
 * @author Tiago Faria, 73714, tiagohpf@ua.pt
 */

// Class that create objects
public final class Creator {

    /**
     * Create alphabet of 27 characters
     *
     * @return alphabet
     */
    public static List<String> createAlphabet() {
        List alphabet = new ArrayList();
        // Add all letters of alphabet
        for (char c = 'A'; c <= 'Z'; c++)
            alphabet.add("" + c);
        // Add whitespace
        alphabet.add(" ");
        return alphabet;
    }
}
