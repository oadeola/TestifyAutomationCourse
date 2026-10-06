package ClassTwo;

public class ClassB {
    private void privateMessage() {
    System.out.println("This method can only be accessed inside ClassB.");
}

    public static void main(String[] args) {

        ClassB objectB = new ClassB();

        objectB.privateMessage();
    }
}
