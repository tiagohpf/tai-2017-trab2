import Collector.EnglishCollector;
import Collector.GermanCollector;
import Collector.ItalianCollector;
import Utils.*;
import Probabilities.ProbManager;

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


// Main class that runs the project
public class Language {
    public static void main(String[] args) {
        String filePath = new String();
        int order = 0, generateLength = 0;
        double alpha = 1;

        /*
         * Program needs at least 2 arguments: filename and order, both valid
         * It's possible to use also 3 arguments (add alpha) and 4 (add alpha and length of text generated)
         * Text is just generated when program have all 4 arguments
         * Alpha = 1 when is not inserted
         * Length of text must >= order
         * Alpha must be in [0.001, 1]
         * USAGE: <filename> <order> <alpha> <text length>
         * Examples of Usage:
         *  - "I Have a Dream.txt" 3
         *  - "I Have a Dream.txt" 2 0.4 33
         */
        if (args.length >= 2 && args.length <= 4) {
            filePath = args[0];
            if (Validator.isIntegerValid(args[1])) {
                order = Integer.parseInt(args[1]);
            } else {
                System.err.println("ERROR: Invalid argument, order must be an INTEGER > 0");
                System.exit(1);
            }
            if (args.length == 2) {
                alpha = 1;
            } else if (args.length >= 3) {
                if (Validator.isAlphaValid(args[2])) {
                    alpha = Double.parseDouble(args[2]);
                } else {
                    System.err.println("ERROR: Invalid argument, alpha must be a DOUBLE within [0.001, 1]");
                    System.exit(1);
                }
                if (args.length == 4) {
                    if (Validator.isIntegerValid(args[3])) {
                        generateLength = Integer.parseInt(args[3]);
                        if (order > generateLength)
                            System.err.println("ERROR: Order must be less or equal than text length.");
                    } else {
                        System.err.println("ERROR: Invalid argument, text length must be an INTEGER > 0");
                        System.exit(1);
                    }
                }
            }
        } else {
            System.err.println("ERROR: Invalid number of arguments");
            System.err.println("USAGE: filePath order (alpha)");
            System.exit(1);
        }
        Map<String, Double> entropyValues = new HashMap<>();
        GermanCollector germanCollection = new GermanCollector("GermanText.txt", order);
        List<String> germanAlphabet = germanCollection.getAlphabet();
        Map<String, Values> germanContext = germanCollection.getContext();
        List<String> germanCombinations = germanCollection.getContextCombinations();
        ProbManager germanProb = new ProbManager(germanContext, germanCombinations, alpha, germanAlphabet);
        double germanEntropy = germanProb.getLanguageEntropy(new File("GermanText.txt"), order);
        entropyValues.put("German", germanEntropy);

        EnglishCollector englishCollection = new EnglishCollector("EnglishText.txt", order);
        List<String> englishAlphabet = englishCollection.getAlphabet();
        Map<String, Values> englishContext = englishCollection.getContext();
        List<String> englishCombinations = englishCollection.getContextCombinations();
        ProbManager englishProb = new ProbManager(englishContext, englishCombinations, alpha, englishAlphabet);
        double englishEntropy = englishProb.getLanguageEntropy(new File("EnglishText.txt"), order);
        entropyValues.put("English", englishEntropy);

        ItalianCollector italianCollector = new ItalianCollector("ItalianText.txt", order);
        List<String> italianAlphabet = italianCollector.getAlphabet();
        System.out.println(italianAlphabet);
        System.out.println();

        List<Map.Entry<String, Double>> list = new LinkedList<>(entropyValues.entrySet());
        Collections.sort(list, Comparator.comparing(o -> (o.getValue())));
        System.out.println("ENTROPY OF LANGUAGES");
        System.out.println("-------------------------");
        for (Map.Entry<String, Double> entry : list)
            System.out.format("%10s: %.3f bits\n", entry.getKey(), entry.getValue());
        System.out.println("-------------------------");
        System.out.println("LANGUAGE: " + list.get(0).getKey());
    }
}