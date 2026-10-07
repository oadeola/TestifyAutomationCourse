public class SuperClassTwo extends SuperClassOne {
        String name = "Anderson";

        public void printNames() {

            System.out.println("Class A name: " + super.name);

            System.out.println("Class B name: " + this.name);
        }


        public static void main(String[] args) {

            SuperClassTwo objectB = new SuperClassTwo();

            objectB.printNames();
        }
    }