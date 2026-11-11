import java.util.Arrays;
import java.util.Random;

public class SortCollection {

    // Method to sort an array using Counting Sort
    public static void countingSort(int[] numbers) {

        // Frequency array for numbers 1 to 9
        int[] count = new int[10];

        // Step 1: Count how often each number appears
        for (int number : numbers) {
            count[number]++;
        }

        // Step 2: Rebuild the array in sorted order
        int index = 0;

        for (int number = 1; number <= 9; number++) {

            while (count[number] > 0) {
                numbers[index] = number;
                index++;
                count[number]--;
            }
        }
    }

    public static void main(String[] args) {

        // Create an array of one million integers
        int[] numbers = new int[1_000_000];

        Random random = new Random();

        // Generate random numbers between 1 and 9
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(9) + 1;
        }

        // Display first 20 numbers before sorting
        System.out.println("First 20 Numbers Before Sorting:");
        System.out.println(
                Arrays.toString(Arrays.copyOf(numbers, 20))
        );

        // Sort the array
        countingSort(numbers);

        // Display first 20 numbers after sorting
        System.out.println("\nFirst 20 Numbers After Sorting:");
        System.out.println(
                Arrays.toString(Arrays.copyOf(numbers, 20))
        );

        // Print total array size
        System.out.println(
                "\nTotal Numbers Sorted: " + numbers.length
        );

        // Verify that the array is sorted
        boolean isSorted = true;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                isSorted = false;
                break;
            }
        }

        System.out.println("Array Sorted Successfully: " + isSorted);
    }
}

