package Collector;

import Utils.Creator;
import Utils.Values;

import java.util.List;
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

public class ItalianCollector extends WordsCollector {
    // Order of context
    private int order;
    // Italian alphabet
    private List<String> alphabet;
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of combinations created in context
    private List<String> contextCombinations;

    public ItalianCollector(String path, int order) {
        super(path);
        this.context = super.getContext();
        this.contextCombinations = super.getContextCombinations();
        this.alphabet = super.getAlphabet();
        this.order = order;
        Creator.loadWords(path, order, context, alphabet, contextCombinations);
    }
}