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
        text = text.replaceAll("[-+.^:,;’\'!?\\[\\]_\"–„“]", "")
                .replaceAll("[0-9]", "")
                .replaceAll("  \\+", " ");
        return text;
    }

    public static String upperCaseCharacters(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String character = Character.toString(text.charAt(i));
            if (character.equals("ß"))
                sb.append("ß");
            else
                sb.append(character.toUpperCase());
        }
        return sb.toString();
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
