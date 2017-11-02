package Collector;

import Utils.Creator;
import Utils.Values;

import java.util.List;
import java.util.Map;

public class SpanishCollector extends WordsCollector {
    // Order of context
    private int order;
    // Spanish alphabet
    private List<String> alphabet;
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of combinations created in context
    private List<String> contextCombinations;

    public SpanishCollector(String path, int order) {
        super(path);
        this.context = super.getContext();
        this.contextCombinations = super.getContextCombinations();
        this.alphabet = super.getAlphabet();
        this.order = order;
        Creator.loadWords(path, order, context, alphabet, contextCombinations);
    }
}