public class FizzBuzz {
    static void main(String[] args){
        int myNumber = 35;

        if(myNumber%3==0 && myNumber%5==0){
            System.out.println("FizzBuzz");
        }
        else if(myNumber%5==0){
            System.out.println("Buzz");
        }
        else if(myNumber%3==0){
            System.out.println("Fizz");
        }
    }
}
