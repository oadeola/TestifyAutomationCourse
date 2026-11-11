
public class MaximumProduct {

    public static void main(String[] args) {

        int[] numbers = {-12, -9, 2, 13, 6};

        if (numbers.length < 2) {
            System.out.println("At least two numbers are required.");
            return;
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int number : numbers) {

            // Find two largest numbers
            if (number >= largest) {
                secondLargest = largest;
                largest = number;
            } else if (number > secondLargest) {
                secondLargest = number;
            }

            // Find two smallest numbers
            if (number <= smallest) {
                secondSmallest = smallest;
                smallest = number;
            } else if (number < secondSmallest) {
                secondSmallest = number;
            }
        }

        // Use long to prevent integer multiplication overflow
        long product1 = (long) largest * secondLargest;
        long product2 = (long) smallest * secondSmallest;

        if (product1 >= product2) {
            System.out.println("First Number: " + largest);
            System.out.println("Second Number: " + secondLargest);
            System.out.println("Maximum Product: " + product1);
        } else {
            System.out.println("First Number: " + smallest);
            System.out.println("Second Number: " + secondSmallest);
            System.out.println("Maximum Product: " + product2);
        }
    }
}
