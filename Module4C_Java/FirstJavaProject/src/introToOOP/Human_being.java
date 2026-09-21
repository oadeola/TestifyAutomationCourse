package introToOOP;

public class Human_being {

    String name;
    int age;
    boolean african;

    public void aboutMe(String myName, int myAge){
        name = myName;
        age = myAge;
        System.out.println(name +" "+ age);
    }
}
