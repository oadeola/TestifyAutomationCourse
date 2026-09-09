import java.util.ArrayList;
import java.util.Arrays;

public class JavaArrays {

static void main(String[] args){

    int [] array1 = new int [5];
    array1[3] = 10;
    array1[2] = 2;
    array1[1] = 3;
    array1[4] = 5;

    //System.out.println(Arrays.toString(array1));

    String [] fruits = {"mango", "apple", "berries"};

    System.out.println(Arrays.toString(fruits));

    int  [] [] doubleDim = new int [2] [3];
    doubleDim[0] [0] = 20;
    doubleDim[0] [1] = 30;
    doubleDim[0] [2] = 10;
    doubleDim[1] [0] = 22;
    doubleDim[1] [1] = 18;

    System.out.println(Arrays.deepToString(doubleDim));

//ArrayList
    ArrayList arr = new ArrayList();

    arr.add("test");
    arr.add(10);
    System.out.println(arr);

    //ArrayList String only
    ArrayList<String> arr1 = new ArrayList<String>();

    arr1.add("test1");
    arr1.add("20");
    //System.out.println(arr1);
    //System.out.println(arr1.get(0));

    arr1.remove(0);
    System.out.println(arr1);

}
}
