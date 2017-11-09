import Collector.*;
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
        int order = 3;
        double alpha = 0.1;
        File file = null;
        if (args.length == 1) {
            file = new File(args[0]);
            if (!file.exists()) {
                System.err.println("ERROR: File not found!");
                System.exit(1);
            }
        } else {
            System.err.println("ERROR: Invalid number of arguments! You must provide the path of file");
            System.err.println("USAGE: filePath");
            System.exit(1);
        }
        Map<String, String> languages = Creator.getLanguagesInUse();
        Map<String, Double> entropyValues = new HashMap<>();
        for (Map.Entry<String, String> language : languages.entrySet()) {
            WordsCollector collector = new WordsCollector(language.getValue(), order);
            List<String> alphabet = collector.getAlphabet();
            System.out.println(language.getKey() + ": " + alphabet);
            Map<String, Values> context = collector.getContext();
            List<String> combinations = collector.getContextCombinations();
            ProbManager probs = new ProbManager(context, combinations, alpha, alphabet);
            double entropy = probs.getLanguageEntropy(file, order);
            entropyValues.put(language.getKey(), entropy);
        }
        List<Map.Entry<String, Double>> list = new LinkedList<>(entropyValues.entrySet());
        Collections.sort(list, Comparator.comparing(o -> (o.getValue())));
        System.out.println("ENTROPY OF LANGUAGES");
        System.out.println("--------------------------------");
        int i = 0;
        for (Map.Entry<String, Double> entry : list)
            System.out.format("%02d. %10s: %8.3f bits\n", ++i, entry.getKey(), entry.getValue());
        System.out.println("--------------------------------");
        System.out.println("LANGUAGE: " + list.get(0).getKey());
    }
}