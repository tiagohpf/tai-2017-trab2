package Collector;

import Utils.Creator;
import Utils.Filter;
import Utils.Values;

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

public class GermanCollector extends WordsCollector {
    // Name of file to read
    private File file;
    // Order of context
    private int order;
    // German alphabet
    private List<String> germanAlphabet;
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of combinations with order = 1
    private Map<String, Values> associations;
    // List of combinations created in context
    private List<String> contextCombinations;
    private static Scanner sc;

    public GermanCollector(String path, int order) {
        super();
        this.context = super.getContext();
        this.associations = super.getAssociations();
        this.contextCombinations = super.getContextCombinations();
        this.germanAlphabet = Creator.createAlphabet(path);
        this.order = order;
        Creator.readFile(path, order, context, germanAlphabet, associations,contextCombinations);
        System.out.println("German Alphabet: " + germanAlphabet);
    }
}
