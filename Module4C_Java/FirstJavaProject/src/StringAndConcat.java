public class StringAndConcat {
    static void main(String[] args) {
        //string and string concat
        String animal = "Lion";
        String title  = " is a king of the jungle";

        String newString = animal + title;

        //System.out.println(newString);


        //string and primitive concat

        String movieName = "Matrix: ";
        int parts = 5;
        boolean isReleased = false;

       // System.out.println(movieName + parts);

        //.concat() method

        System.out.println(movieName.concat("" + parts));
    }
}
