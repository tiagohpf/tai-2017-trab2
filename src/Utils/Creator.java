package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    public static List<String> createAlphabet(String path) {
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            List<String> alphabet = new ArrayList<>();
            while(sc.hasNext()) {
                String line = sc.nextLine();
                line = Filter.removeSpecialCharacters(line);
                line = Filter.upperCaseCharacters(line);
                for (int i = 0; i <  line.length(); i++) {
                    String character = Character.toString(line.charAt(i));
                    if (!alphabet.contains(character))
                        alphabet.add(character);
                }
            }
            Collections.sort(alphabet);
            return alphabet;
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found!");
            System.exit(1);
        }
        return null;
    }

    public static void loadWords(String path, int order, Map<String, Values> context, List<String> alphabet,
                                 List<String> combinations) {
        // Create the file
        File file = new File(path);
        // Read the file
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                line = Filter.removeSpecialCharacters(line);
                line = Filter.upperCaseCharacters(line);
                // First, get the character in text
                for (int i = order; i < line.length(); i++) {
                    String word = new String();
                    String letter = Character.toString(line.charAt(i));
                    // Second, get the context give an order
                    for (int j = i - order; j < i; j++)
                        word += line.charAt(j);
                    // Add a new context
                    addNewWord(context, word, letter, alphabet, combinations, order);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: " + path + " not found!");
            System.exit(1);
        }
    }

    /**
     * Create a new word in context or increment its value
     *
     * If the word already exists in the context, increment its value
     * If not, create a new instance
     *
     * @param context
     * @param word
     * @param letter
     */
    private static void addNewWord(Map<String, Values> context, String word, String letter,
                                   List<String> alphabet, List<String> combinations, int order) {
        Map<String, Values> filter = Filter.filterContext(context, word);
        if (filter.size() > 0) {
            Values values = filter.get(word);
            int number = values.getNumber(letter) + 1;
            values.addValue(letter, number);
            context.put(word, values);
        } else
            createNewInstance(context, word, letter, alphabet, combinations, order);
    }

    /**
     * Create a new instance in context
     * If the association was found in text, create with value 1. Otherwise, create with value 0
     *
     * @param context
     * @param word
     * @param letter
     */
    private static void createNewInstance(Map<String, Values> context, String word, String letter,
                                          List<String> alphabet, List<String> combinations, int order) {
        Values values = new Values();
        for (String character : alphabet) {
            if (letter.equals(character))
                values.addValue(character, 1);
            else
                values.addValue(character, 0);
        }
        // Add column for unknown characters
        values.addValue("?", 0);
        context.put(word, values);
        if (word.length() == order)
            combinations.add(word);
    }
}
