public class ChildB extends ParentA {

        public void methodFour() {
            System.out.println("This is method four from Class ChildB");
        }

        public void methodFive() {
            System.out.println("This is method five from Class ChildB");
        }

        public static void main(String[] args) {

            ChildB objectB = new ChildB();

            objectB.methodOne();
            objectB.methodTwo();
            objectB.methodThree();
            objectB.methodFour();
            objectB.methodFive();
        }
    }

