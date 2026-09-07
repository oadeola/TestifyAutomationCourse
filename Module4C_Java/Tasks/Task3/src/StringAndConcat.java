public class StringAndConcat {
        static void main(String[] args) {
            //string and primitive concat
            String ageString = "My age is: ";
            int age = 32;

            String myAge = ageString + age;

            System.out.println(myAge);


            //.concat()
            System.out.println(ageString.concat("" + age));
        }
}
