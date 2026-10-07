public class ThisClass {
        String name = "Delta";


        public void printName(String name) {

            System.out.println(this.name);

            System.out.println(name);
        }


        public static void main(String[] args) {

            ThisClass objectA = new ThisClass();

            objectA.printName("Sharon");
        }
    }
