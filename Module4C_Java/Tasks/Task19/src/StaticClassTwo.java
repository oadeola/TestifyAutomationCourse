public class StaticClassTwo {
    static void main(String[] args) {
        System.out.println(StaticClassOne.companyName);

        StaticClassOne.companyName = "Testify Training";

            System.out.println(StaticClassOne.companyName);
        }
    }
