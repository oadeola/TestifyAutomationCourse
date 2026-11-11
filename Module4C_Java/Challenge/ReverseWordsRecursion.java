public class ReverseWordsRecursion {

    // Recursive method to reverse the order of words
    public static String reverseWords(String[] words, int index) {

        // Base case: Stop when first word is reached
        if (index == 0) {
            return words[0];
        }

        // Recursive call
        return words[index] + " " + reverseWords(words, index - 1);
    }

    public static void main(String[] args) {

        // Original sentence
        String sentence = "I am the best Automation Tester";

        // Split the sentence into individual words
        String[] words = sentence.split(" ");

        // Call the recursive method
        String reversedSentence = reverseWords(words, words.length - 1);

        // Print results
        System.out.println("Original Sentence: " + sentence);
        System.out.println("Reversed Sentence: " + reversedSentence);
    }
}
