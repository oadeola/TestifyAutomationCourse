public class ReverseStringRecursion {

    // Method to reverse a string using recursion
    public static String reverseString(String text) {

        // Base case: Stop when the string is empty
        // or contains only one character
        if (text.length() <= 1) {
            return text;
        }

        // Recursive call
        return reverseString(text.substring(1))
                + text.charAt(0);
    }

    public static void main(String[] args) {

        // Original string
        String word = "TestifyAutomation";

        // Call the recursive method
        String reversedWord = reverseString(word);

        // Print the results
        System.out.println("Original String: " + word);
        System.out.println("Reversed String: " + reversedWord);
    }
}
