public class StringOperations {
    static void main(String[] args){
        String word = "orgANIZation";

        //uppercase and lowercase
        System.out.println(word.toLowerCase());
        System.out.println(word.toUpperCase());

        //length and indexof
        System.out.println(word.length());
        System.out.println(word.indexOf("a"));

        //char at a particular index
        System.out.println(word.charAt(word.length()-5));
       // System.out.println(word.charAt(Integer.parseInt(word.substring(0,4))));

    }


}
