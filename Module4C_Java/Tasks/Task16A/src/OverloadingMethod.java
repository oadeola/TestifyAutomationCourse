public class OverloadingMethod {
        // Original method
        public void display(int number) {
            System.out.println("Number: " + number);
        }

        // Overloading by changing the number of parameters
        public void display(int number1, int number2) {
            System.out.println("Two numbers: " + number1 + " and " + number2);
        }

        // Overloading by changing the data type
        public void display(String name) {
            System.out.println("Name: " + name);
        }

        // Overloading by changing the order of parameter types
        public void display(String name, int age) {
            System.out.println("Name: " + name + ", Age: " + age);
        }

        public void display(int age, String name) {
            System.out.println("Age: " + age + ", Name: " + name);
        }

        public static void main(String[] args) {

            OverloadingMethod object = new OverloadingMethod();

            object.display(20);

            object.display(15, 40);

            object.display("Ben");

            object.display("Ben", 45);

            object.display(45, "Ben");
        }
    }
