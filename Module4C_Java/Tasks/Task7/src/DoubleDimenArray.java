import java.util.Arrays;

public class DoubleDimenArray {
    static void main(String[] args) {

        String[][] fruitsOfMyChoice = new String[4][3];

        fruitsOfMyChoice[0][0] = "Raspberry";
        fruitsOfMyChoice[0][1] = "";
        fruitsOfMyChoice[0][2] = "";
        fruitsOfMyChoice[1][0] = "Melon";
        fruitsOfMyChoice[1][1] = "";
        fruitsOfMyChoice[1][2] = "";
        fruitsOfMyChoice[2][0] = "Banana";
        fruitsOfMyChoice[2][1] = "";
        fruitsOfMyChoice[2][2] = "";
        fruitsOfMyChoice[3][0] = "Pomegranate";
        fruitsOfMyChoice[3][1] = "";
        fruitsOfMyChoice[3][2] = "";
        System.out.println(Arrays.deepToString(fruitsOfMyChoice));

    }
}