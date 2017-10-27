package Collector;

import Utils.Filter;
import Utils.Values;

import java.util.*;

/**
 * TAI, November 2017
 * <p>
 * Assignment 2 - Language recognition system
 *
 * @author Bárbara Jael, 73241, barbara.jael@ua.pt
 * @author Miriam Cardoso, 72181, miriamcardoso@ua.pt
 * @author Tiago Faria, 73714, tiagohpf@ua.pt
 */

// Class that collects statistical information about texts, using finite-context models
public abstract class AbstractWordsCollector {
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of combinations with order = 1
    private Map<String, Values> associations;
    // List of combinations created in context
    private List<String> contextCombinations;

    /**
     * Constructor
     */
    public AbstractWordsCollector() {
        this.context = new HashMap<>();
        this.associations = new HashMap<>();
        this.contextCombinations = new ArrayList<>();
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
    abstract public void readFile(String path);

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
    protected void addOccurrence(Map<String, Values> context, String word, char letter, List<String> alphabet) {
        Map<String, Values> filter = Filter.filterContext(context, word);
        if (filter.size() > 0) {
            Values values = filter.get(word);
            int number = values.getNumber(Character.toString(letter)) + 1;
            values.addValue(Character.toString(letter), number);
            context.put(word, values);
        } else
            createNewInstanceInList(context, word, letter, alphabet);
    }

    /**
     * Create a new instance in context
     * If the association was found in text, create with value 1. Otherwise, create with value 0
     *
     * @param map
     * @param word
     * @param letter
     */
    protected void createNewInstanceInList(Map<String, Values> map, String word, char letter, List<String> alphabet) {
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
