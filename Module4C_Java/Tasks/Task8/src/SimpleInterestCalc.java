import java.util.Scanner;

public class SimpleInterestCalc {
    static void main(String[] args){
        System.out.println("Welcome to Simple Interest Calculator App");

        Scanner input1 = new Scanner(System.in);
        System.out.println("Input Principal here: ");
        int principal = input1.nextInt();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Input Rate here: ");
        int rate = input2.nextInt();

        Scanner input3 = new Scanner(System.in);
        System.out.println("Input Time in Years here: ");
        int time = input3.nextInt();

        int simpleInterest = (principal * rate * time)/100;

        System.out.println("Simple Interest is: " + simpleInterest + "Naira after "+ time +"years" );

    }
}
