package Probabilities;

import Utils.Filter;
import Utils.Key;
import Utils.Probabilities;
import Utils.Values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    // List of combinations with order = 1
    private Map<String, Values> associations;
    // List of counting words after context
    private Map<String, Integer> contextCounter;
    // List of probabilities in context
    private Map<String, Probabilities> contextProbs;
    // List of counting associations
    private Map<String, Integer> assocCounter;
    // List of probabilities in associations
    private Map<String, Probabilities> assocProbs;
    // List of combinations created in context
    private List<String> contextCombinations;
    // List of alphabet. 27 characters [A-Z] and whitespace
    private List<String> alphabet;
    private double alpha;

    /**
     * Constructor
     *
     * @param context
     * @param contextCombinations
     * @param alpha
     * @param alphabet
     * @param associations
     */
    public ProbManager(Map<String, Values> context, List<String> contextCombinations, double alpha,
                       List<String> alphabet, Map<String, Values> associations) {
        this.context = context;
        contextCounter = new HashMap<>();
        contextProbs = new HashMap<>();
        assocCounter = new HashMap<>();
        assocProbs = new HashMap<>();
        this.contextCombinations = contextCombinations;
        this.alpha = alpha;
        this.alphabet = alphabet;
        this.associations = associations;
        sumOccurrences(contextCombinations, contextCounter, context);
        sumOccurrences(alphabet, assocCounter, associations);
        calculateProbabilities(contextCounter, context, contextProbs);
        calculateProbabilities(assocCounter, associations, assocProbs);
    }

    /**
     * Calculate entropy
     *
     * @return entropy
     */
    public double getEntropy() {
        // Get number of total contextCombinations created
        int totalCombinations = getNumberOfCombinationsInContext();
        double entropy = 0;
        for (Map.Entry<String, Integer> wordOccurrences : contextCounter.entrySet()) {
            String word = wordOccurrences.getKey();
            // Get number of occurrences of certain word
            int occurrences = wordOccurrences.getValue();
            double h = 0;
            // Get probabilities of certain word
            Map<String, Probabilities> filter = Filter.filterContextProbs(contextProbs, word);
            // Calculate entropy row by row
            for (Map.Entry<String, Probabilities> termProb : filter.entrySet()) {
                Probabilities probs = termProb.getValue();
                for (Map.Entry<String, Double> entry : probs.getProbs().entrySet()) {
                    double prob = entry.getValue();
                    // log a (x) = log b (x) / log b (a)
                    h += (prob * (Math.log10(prob) / Math.log10(2))) * (-1);
                }
            }
            entropy += h * (occurrences * 1.0 / totalCombinations);
        }
        return entropy;
    }

    /**
     * Get all probabilities from context
     *
     * @return probabilities of context
     */
    public Map<String, Probabilities> getContextProbs() {
        return contextProbs;
    }

    /**
     * Get all probabilities from associations
     *
     * @return probabilities of associations
     */
    public Map<String, Probabilities>  getAssocProbs() {
        return assocProbs;
    }

    /**
     * Get number of occurrences in each word of context
     *
     * @return number of occurrences in context
     */
    public Map<String, Integer> getContextCounter() {
        return contextCounter;
    }

    /**
     * Get number of occurrences in each association
     *
     * @return number of occurrences in associations
     */
    public Map<String, Integer>  getAssocCounter() {
        return assocCounter;
    }

    // Calculate the total number of occurrences, row by row
    private void sumOccurrences(List<String> domain, Map<String, Integer> counter,
                                Map<String, Values> context) {
        for (int i = 0; i < domain.size(); i++) {
            int sum = 0;
            String word = domain.get(i);
            Map<String, Values> filter = Filter.filterContext(context ,word);
            for(Map.Entry<String, Values> entry : filter.entrySet()) {
                Values values = entry.getValue();
                for(Map.Entry<String, Integer> vals : values.getValues().entrySet())
                    sum += vals.getValue();
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

    /**
     * Return total number of contextCombinations create in context
     *
     * @return total number of contextCombinations in context
     */
    private int getNumberOfCombinationsInContext() {
        int sum = 0;
        for (Map.Entry<String, Integer> wordOccurrences : contextCounter.entrySet())
            sum += wordOccurrences.getValue();
        return sum;
    }
}
