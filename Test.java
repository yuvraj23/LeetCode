import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String []args){

        List<String> myList = new ArrayList<>();
        List<String> targetList = new ArrayList<>();

        myList.add("apple");
        myList.add("banana");
        myList.add("cherry");

        targetList.add("apple");
        targetList.add("banana");
        targetList.add("cherry");

        if (myList.contains(targetList)) {
            System.out.println("myList contains the targetList");
        } else {
            System.out.println("myList does not contain the targetList");
        }
    }
}
