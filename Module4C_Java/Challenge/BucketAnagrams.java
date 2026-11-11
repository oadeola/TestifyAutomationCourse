import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BucketAnagrams {

    public static void main(String[] args) {

        // Input array
        String[] words = {"akka", "akak", "baab", "baba", "bbaa"};

        // Map to store groups of anagrams
        Map<String, List<String>> anagramBuckets =
                new LinkedHashMap<>();

        // Loop through each word
        for (String word : words) {

            // Convert word into character array
            char[] characters = word.toCharArray();

            // Sort the characters alphabetically
            Arrays.sort(characters);

            // Convert sorted characters back to a string
            String sortedWord = new String(characters);

            // Create a bucket if it doesn't exist
            anagramBuckets.putIfAbsent(
                    sortedWord, new ArrayList<>()
            );

            // Add the original word to its bucket
            anagramBuckets.get(sortedWord).add(word);
        }

        // Print the anagram buckets
        System.out.println("Anagram Buckets:");

        for (List<String> bucket : anagramBuckets.values()) {
            System.out.println(bucket);
        }
    }
}
