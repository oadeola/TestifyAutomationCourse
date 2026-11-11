import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;

public class FindAnagrams {

    public static void main(String[] args) {

        // Original sentence
        String sentence = "Despite commuting at the peek hour, she was able to keep to time";

        // Convert to lowercase and remove punctuation
        sentence = sentence.toLowerCase()
                .replaceAll("[^a-z\\s]", "");

        // Split sentence into words
        String[] words = sentence.split("\\s+");

        // Store words grouped by sorted characters
        Map<String, List<String>> anagramGroups =
                new LinkedHashMap<>();

        // Loop through each word
        for (String word : words) {

            // Convert word to character array
            char[] characters = word.toCharArray();

            // Sort characters alphabetically
            Arrays.sort(characters);

            // Convert sorted characters into a string
            String sortedWord = new String(characters);

            // Create a group if it doesn't exist
            anagramGroups.putIfAbsent(
                    sortedWord, new ArrayList<>()
            );

            // Add the original word to its group
            anagramGroups.get(sortedWord).add(word);
        }

                
        // Print only groups with different anagram words
        System.out.println("Anagrams found:");

        boolean found = false;

        for (List<String> group : anagramGroups.values()) {

            // Remove duplicate words
            Set<String> uniqueWords = new LinkedHashSet<>(group);

            // Print only if there are at least 2 different words
            if (uniqueWords.size() > 1) {
                System.out.println(uniqueWords);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No anagrams found.");
        }

    }
}
