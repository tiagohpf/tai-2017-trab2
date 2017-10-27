package Collector;

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

public class GermanCollector extends AbstractWordsCollector {
    // Name of file to read
    private File file;
    // Order of context
    private int order;
    // German alphabet
    private List<String> germanAlphabet;
    // Set with German special characters
    private Set<String> specialCharacters;
    // Array with the German special characters
    private String [] characters;
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of combinations with order = 1
    private Map<String, Values> associations;
    // List of combinations created in context
    private List<String> contextCombinations;
    private static Scanner sc;

    public GermanCollector(String path, int order, List<String> alphabet) {
        super();
        this.context = super.getContext();
        this.associations = super.getAssociations();
        this.contextCombinations = super.getContextCombinations();
        this.germanAlphabet = new ArrayList<>(alphabet);
        this.order = order;
        this.specialCharacters = new HashSet<>();
        this.characters = new String[]{"Ä", "Ö", "Ü", "ß"};
        readFile(path);
        System.out.println("German Alphabet: " + germanAlphabet);
    }

    @Override
    public void readFile(String path) {
        // Create the file
        file = new File(path);
        // Read the file
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: " + path + " not found!");
            System.exit(1);
        }

        // Add the special German characters to the alphabet
        while (sc.hasNext()) {
            String line = sc.nextLine();
            line = Filter.removeSpecialCharacters(line);

            for (int i = 0; i < characters.length; i++) {
                if (line.toUpperCase().contains(characters[i]))
                    specialCharacters.add(characters[i]);
            }
        }
        for (String character : specialCharacters) {
            germanAlphabet.add(character);
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
                addOccurrence(context, word.toUpperCase(), Character.toUpperCase(letter), germanAlphabet);
            }
            // First, get the character in text
            for (int i = 1; i < line.length(); i++) {
                String word = new String();
                char letter = line.charAt(i);
                //Second, get the context give an order
                for (int j = i - 1; j < i; j++)
                    word += line.charAt(j);
                // Add a new association
                addOccurrence(associations, word.toUpperCase(), Character.toUpperCase(letter), germanAlphabet);
            }
        }
    }
}
