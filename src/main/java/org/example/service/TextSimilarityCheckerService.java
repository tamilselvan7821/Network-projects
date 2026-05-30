package org.example.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TextSimilarityCheckerService {

    public SimilarityResult checkSimilarity(String text1, String text2) {
        String[] stop = {"is", "am", "are", "the", "a", "an", "of", "to"};
        HashSet<String> stopWords = new HashSet<>(Arrays.asList(stop));
        Set<String> set1 = process(stopWords, text1);
        Set<String> set2 = process(stopWords, text2);
        Set<String> common = new HashSet<>(set1);
        common.retainAll(set2);
        double similarity = (2.0 * common.size()) / (set1.size() + set2.size()) * 100;
        return new SimilarityResult(Math.round(similarity), common);
    }

    private Set<String> process(HashSet<String> stopWords, String s) {
        s = s.toLowerCase().replaceAll("[^a-z ]", "");
        String[] words = s.split("\\s+");
        Set<String> result = new HashSet<>();
        for (String str : words) {
            if (!stopWords.contains(str)) result.add(str);
        }
        return result;
    }

    public static class SimilarityResult {
        public long similarityPercentage;
        public Set<String> commonWords;

        public SimilarityResult(long similarityPercentage, Set<String> commonWords) {
            this.similarityPercentage = similarityPercentage;
            this.commonWords = commonWords;
        }
    }
}
