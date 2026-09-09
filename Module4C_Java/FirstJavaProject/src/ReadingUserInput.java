import java.util.Scanner;

public class ReadingUserInput {
    static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome, please input your DOB: ");
        short dob = userInput.nextShort();
        short presentYear = 2026;
        int customerAge = presentYear - dob;

        System.out.println("You are " + customerAge+ " years old");

        if (customerAge >= 18){
            System.out.println("Kindly proceed");
        }
        else {
            System.out.println("Too young to bet. Bye");
        }
    }




}
