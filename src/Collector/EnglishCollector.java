package Collector;

import Utils.Creator;
import Utils.Values;

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

public class EnglishCollector extends WordsCollector {
    // Order of context
    private int order;
    // English alphabet
    private List<String> alphabet;
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of combinations created in context
    private List<String> contextCombinations;

    public EnglishCollector(String path, int order) {
        super(path);
        this.context = super.getContext();
        this.contextCombinations = super.getContextCombinations();
        this.alphabet = super.getAlphabet();
        this.order = order;
        Creator.loadWords(path, order, context, alphabet, contextCombinations);
    }
}
