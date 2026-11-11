public class PalindromeCheck {

    public static void main(String[] args) {

        String word = "racecar";
        int number = 10801;

        // Check the string
        String reversedWord = new StringBuilder(word)
                .reverse()
                .toString();

        if (word.equals(reversedWord)) {
            System.out.println(word + " is a palindrome");
        } else {
            System.out.println(word + " is not a palindrome");
        }

        // Check the number
        int originalNumber = number;
        int reversedNumber = 0;

        while (number > 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number = number / 10;
        }

        if (originalNumber == reversedNumber) {
            System.out.println(originalNumber + " is a palindrome");
        } else {
            System.out.println(originalNumber + " is not a palindrome");
        }
    }
}
