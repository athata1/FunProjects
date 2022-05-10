import java.util.ArrayList;
import java.util.HashSet;

public class Test {
    static ArrayList<ArrayList<String>> combinations = new ArrayList<>();
    static String[] arr = {"B1",
            "B2",
            "B3",
            "B4",
            "B5",
            "G1",
            "G2",
            "G3",
    };
    public static void main(String[] args) {
        getAllCombinations(3,0,new ArrayList<String>());
        System.out.println(combinations.size());
        for(ArrayList<String> s: combinations) {
            System.out.println(s);
        }

    }
    public static void getAllCombinations(int length, int start, ArrayList<String> output) {
        if (length == 0) {
            int countBoys = 0;
            int countGirls = 0;
            for (int i = 0; i < output.size(); i++) {
                if (output.get(i).charAt(0) == 'B') {
                    countBoys++;
                }
                else {
                    countGirls++;
                }
            }
            //if (countBoys >= 2 && countGirls >= 2) {
                ArrayList<String> res = new ArrayList<>(output);
                combinations.add(res);
            //}
            return;
        }
        for (int i = start; i < arr.length - length + 1; i++) {
            output.add(arr[i]);
            getAllCombinations(length - 1,i+1,output);
            output.remove(output.size() - 1);
        }
    }
}
