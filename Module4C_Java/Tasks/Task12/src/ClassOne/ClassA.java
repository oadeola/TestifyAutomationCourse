package ClassOne;

import ClassTwo.ClassB;

public class ClassA {
    public void displayMessage() {
        System.out.println("This method can be accessed anywhere in the project.");
    }

    public static void main(String[] args) {

        ClassA objectA = new ClassA();
            objectA.displayMessage();

    }
}