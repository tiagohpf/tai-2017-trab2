package Probabilities;

import Utils.Filter;
import Utils.Probabilities;
import Utils.Values;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * TAI, November 2017
 *
 * Assignment 2 - Language recognition system
 *
 * @author Bárbara Jael, 73241, barbara.jael@ua.pt
 * @author Miriam Cardoso, 72181, miriamcardoso@ua.pt
 * @author Tiago Faria, 73714, tiagohpf@ua.pt
 */

// Class that count and calculate probabilities of context
public class ProbManager {
    // List that counts the appearance of contexts in text
    private Map<String, Values> context;
    // List of counting words after context
    private Map<String, Integer> counter;
    // List of probabilities in context
    private Map<String, Probabilities> probabilities;
    // List of combinations created in context
    private List<String> combinations;
    // List of alphabet. 27 characters [A-Z] and whitespace
    private List<String> alphabet;
    private double alpha;

    /**
     * Constructor
     *
     * @param context
     * @param combinations
     * @param alpha
     * @param alphabet
     */
    public ProbManager(Map<String, Values> context, List<String> combinations, double alpha,
                       List<String> alphabet) {
        this.context = context;
        counter = new HashMap<>();
        probabilities = new HashMap<>();
        this.combinations = combinations;
        this.alpha = alpha;
        this.alphabet = alphabet;
        countWordsOccurrences(combinations, counter, context);
        calculateProbabilities(counter, context, probabilities);
    }

    public double getLanguageEntropy(File file, int order) {
        double result = 0;
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
                    result += getEntropyFromContext(word, letter);
                }
            }
        } catch(FileNotFoundException ex) {
            System.err.println("ERROR: File not found!");
            System.exit(1);
        }
        return result;
    }

    /**
     * Get number of occurrences in each word of context
     *
     * @return number of occurrences in context
     */
    public Map<String, Integer> getCounter() {
        return counter;
    }

    // Calculate the total number of occurrences, row by row
    private void countWordsOccurrences(List<String> combinations,
                                       Map<String, Integer> counter,
                                       Map<String, Values> context) {
        for (int i = 0; i < combinations.size(); i++) {
            int sum = 0;
            String word = combinations.get(i);
            Map<String, Values> filter = Filter.filterContext(context ,word);
            for(Map.Entry<String, Values> entry : filter.entrySet()) {
                Values values = entry.getValue();
                for(Map.Entry<String, Integer> val : values.getValues().entrySet())
                    sum += val.getValue();
            }
            counter.put(word, sum);
        }
    }

    // Calculate probabilities in context
    private void calculateProbabilities(Map<String, Integer> counter,
                                        Map<String, Values>  context,
                                        Map<String, Probabilities>  probabilities) {
        for (Map.Entry<String, Integer> wordOccurrences : counter.entrySet()) {
            String word = wordOccurrences.getKey();
            Map<String, Values> filter = Filter.filterContext(context ,word);
            for(Map.Entry<String, Values> termOccurrences : filter.entrySet()) {
                String term = termOccurrences.getKey();
                Values values = termOccurrences.getValue();
                Probabilities probs = new Probabilities();
                for (Map.Entry<String, Integer> map : values.getValues().entrySet()) {
                    String letter = map.getKey();
                    int number = map.getValue();
                    double prob = (number + alpha) / (wordOccurrences.getValue() + alphabet.size() * alpha);
                    probs.addValue(letter, prob);
                }
                probabilities.put(term, probs);
            }
        }
    }

    private double getEntropyFromContext(String word, String letter) {
        double entropy, prob;
        Probabilities probs = probabilities.get(word);
        if (probs == null)
            prob = (double) 1 / alphabet.size();
        else {
            Map <String, Double> letters = probs.getProbs();
            if (letters.get(letter) == null)
                prob = letters.get("?");
            else
                prob = letters.get(letter);
        }
        // log a (x) = log b (x) / log b (a)
        entropy = Math.log10(prob) / Math.log10(2) * (-1);
        return entropy;
    }
}
