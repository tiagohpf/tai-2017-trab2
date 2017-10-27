package Collector;

import Utils.Filter;
import Utils.Values;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * TAI, October 2017
 * <p>
 * Assignment 1 - Finite-context model and automatic text generation
 *
 * @author Bárbara Jael, 73241, barbara.jael@ua.pt
 * @author Miriam Cardoso, 72181, miriamcardoso@ua.pt
 * @author Tiago Faria, 73714, tiagohpf@ua.pt
 */

// Class that collects statistical information about texts, using finite-context models
public class WordsCollector {
    // Name of file to read
    private File file;
    // Order of context
    private int order;
    // List of alphabet. 27 characters [A-Z] and whitespace
    private List<String> alphabet;
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of combinations with order = 1
    private Map<String, Values> associations;
    // List of combinations created in context
    private List<String> contextCombinations;
    private static Scanner sc;

    /**
     * Constructor
     *
     * @param path
     * @param order
     * @param alphabet
     */
    public WordsCollector(String path, int order, List<String> alphabet) {
        this.context = new HashMap<>();
        this.associations = new HashMap<>();
        this.contextCombinations = new ArrayList<>();
        this.alphabet = alphabet;
        this.order = order;
        readFile(path);
    }

    /**
     * Return context of a given order
     *
     * @return context
     */
    public Map<String, Values> getContext() {
        return context;
    }

    /**
     * Return combinations of context
     *
     * @return combinations
     */
    public List<String> getContextCombinations() {
        return contextCombinations;
    }

    /**
     * Return context of order = 1
     *
     * @return context
     */
    public Map<String, Values> getAssociations() {
        return associations;
    }

    // Read text file and create two matrix
    private void readFile(String path) {
        // Create the file
        file = new File(path);
        // Read the file
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: " + path + " not found!");
            System.exit(1);
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            line = Filter.removeSpecialCharacters(line);
            // First, get the character in text
            for (int i = order; i < line.length(); i++) {
                String word = new String();
                char letter = line.charAt(i);
                // Second, get the context give an order
                for (int j = i - order; j < i; j++)
                    word += line.charAt(j);
                // Add a new context
                addOccurrence(context, word.toUpperCase(), Character.toUpperCase(letter));
            }
            // First, get the character in text
            for (int i = 1; i < line.length(); i++) {
                String word = new String();
                char letter = line.charAt(i);
                //Second, get the context give an order
                for (int j = i - 1; j < i; j++)
                    word += line.charAt(j);
                // Add a new association
                addOccurrence(associations, word.toUpperCase(), Character.toUpperCase(letter));
            }
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
    private void addOccurrence(Map<String, Values> context, String word, char letter) {
        Map<String, Values> filter = Filter.filterContext(context, word);
        if (filter.size() > 0) {
            Values values = filter.get(word);
            int number = values.getNumber(Character.toString(letter)) + 1;
            values.addValue(Character.toString(letter), number);
            context.put(word, values);
        } else
            createNewInstanceInList(context, word, letter);
    }

    /**
     * Create a new instance in context
     * If the association was found in text, create with value 1. Otherwise, create with value 0
     *
     * @param map
     * @param word
     * @param letter
     */
    private void createNewInstanceInList(Map<String, Values> map, String word, char letter) {
        Values values = new Values();
        for (String character : alphabet) {
            if (Character.toString(letter).equals(character))
                values.addValue(character, 1);
            else
                values.addValue(character, 0);
        }
        map.put(word, values);
        contextCombinations.add(word);
    }
}
