import java.util.ArrayList;

public class Tester {
    static int count = 0;
    static int total = 0;
    public static void main(String[] args) {
        ArrayList<Character> arr = new ArrayList<>();
        arr.add('0');
        arr.add('1');
        getAllPerms(arr,"");
        System.out.println(count);
    }
    public static void getAllPerms(ArrayList<Character> arr, String s) {
        if (s.length() == 6) {
            if (s.contains("00") && s.charAt(0) == '0') {
                System.out.println(s);
                count++;
            }
            total++;
            return;
        }
        for (int i = 0; i < arr.size(); i++) {
            s += arr.get(i) + "";
            getAllPerms(arr,s);
            s = s.substring(0,s.length()-1);
        }
    }
}
