package Collector;

import Utils.Creator;
import Utils.Filter;
import Utils.Values;
import java.io.File;
import java.io.FileNotFoundException;
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
public abstract class WordsCollector {
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of combinations with order = 1
    private Map<String, Values> associations;
    // List of combinations created in context
    private List<String> contextCombinations;

    /**
     * Constructor
     */
    public WordsCollector() {
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
}
