package Utils;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * TAI, November 2017
 *
 * Assignment 2 - Language recognition system
 *
 * @author Bárbara Jael, 73241, barbara.jael@ua.pt
 * @author Miriam Cardoso, 72181, miriamcardoso@ua.pt
 * @author Tiago Faria, 73714, tiagohpf@ua.pt
 */


// Class that filter in objects
public final class Filter {

    /**
     * Remove all special characters from text
     *
     * @param text
     */
    public static String removeSpecialCharacters(String text) {
        text = text.replaceAll("[-+.^:,;\'!?_/\"–„“]", "")
                .replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll("[0-9]", "")
                .replaceAll("  \\+", " ");
        return text;
    }

    /**
     * Filter context given a certain word
     * @param context
     * @param word
     *
     * @return filtered context
     */
    public static Map<String, Values> filterContext(Map<String, Values> context, String word) {
        return  context.entrySet().stream()
                .filter(map -> map.getKey().equals(word))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Filter context with probabilities given a certain word
     * @param context
     * @param word
     *
     * @return filtered context
     */
    public static Map<String, Probabilities> filterContextProbs(Map<String, Probabilities> context,
                                                                String word) {
        return context.entrySet().stream()
                .filter(map -> map.getKey().equals(word))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }
}
