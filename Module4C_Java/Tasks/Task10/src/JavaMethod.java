import java.util.Scanner;

public class JavaMethod {
    static void verifyVisitor() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Here for what training? ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("Testify")) {
            System.out.println("Welcome to Testify Trainings!");
        } else {
            System.out.println("Access denied. This Slack channel is for Testify Trainings.");
        }
    }

     static void main(String[] args) {
        verifyVisitor();
    }
}
