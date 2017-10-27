package Collector;

import Utils.Creator;
import Utils.Values;

import java.io.File;
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
    // Name of file to read
    private File file;
    // Order of context
    private int order;
    // English alphabet
    private List<String> alphabet;
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of combinations with order = 1
    private Map<String, Values> associations;
    // List of combinations created in context
    private List<String> contextCombinations;
    private static Scanner sc;

    public EnglishCollector(String path, int order) {
        super();
        this.context = super.getContext();
        this.associations = super.getAssociations();
        this.contextCombinations = super.getContextCombinations();
        this.alphabet = Creator.createAlphabet(path);
        this.order = order;
        Creator.readFile(path, order, context, alphabet, associations, contextCombinations);
        System.out.println("English Alphabet: " + alphabet);
    }
}
