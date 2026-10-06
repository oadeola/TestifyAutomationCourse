
import java.util.Scanner;

public class InputLogic {
     static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter a word: ");
            String input = scanner.nextLine();

            while (!input.equalsIgnoreCase("testify")) {
                System.out.println("Try again");

                System.out.print("Enter a word: ");
                input = scanner.nextLine();
            }

            System.out.println("Correct!");

            scanner.close();
        }
    }

