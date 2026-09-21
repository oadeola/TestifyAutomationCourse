public class JavaMethods {
    static void main(String[] args){
        JavaMethods methodVar = new JavaMethods();
        methodVar.printName();
        methodVar.myAge(30);
        int userAge = methodVar.ageCalculation(2012, 2026);
        System.out.println(userAge+ " is the calculated age");
    }
    public void printName(){
       // System.out.println("My name is Adeola");
    }
    public void myAge(int age){
       // System.out.println("My age is "+ age);
    }
    public int ageCalculation(int dob, int presentYear){
        int age = presentYear - dob;
        return age;
    }
}
