public class StringOperationsReverse {
    static void main(String[] args){
            String word = "DEMOCRACY";

            // Reverse the string
            String reversedWord = new StringBuilder(word).reverse().toString();

            System.out.println(reversedWord); // YCARCOMED

            // Extract "COME"
            String result = reversedWord.substring(4, 8);

            System.out.println(result); // COME
        }
    }

