import java.util.Scanner;

public class WhileLoops {
    static void main(String[] args) {
        //while loop
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while (true) {
            if (!userInput.equalsIgnoreCase("exit")) {
                System.out.println("Welcome, type \"exit\" to exit this loop");
                userInput = scanner.nextLine();
               continue;
            } else if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("you are out of this loop");
                break;
            }
            System.out.println("hello");
        }

        //do while loop - break and continue

        do{
            System.out.println("welcome, type exit to leave");
            userInput = scanner.nextLine();
        } while (!userInput.equalsIgnoreCase("exit"));
    }
}