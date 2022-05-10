import java.util.ArrayList;

public class Tester {
    static int count = 0;
    static int total = 0;
    static ArrayList<String> output = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList<Character> arr = new ArrayList<>();
        arr.add('A');
        arr.add('S');
        arr.add('S');
        arr.add('E');
        arr.add('S');
        arr.add('S');
        arr.add('E');
        arr.add('S');
        getAllPerms(arr,"");
        System.out.println(count);
    }
    public static void getAllPerms(ArrayList<Character> arr, String s) {
        if (s.length() == 7) {
            if (s.charAt(0) == 'S' && !output.contains(s)) {
                output.add(s);
                System.out.println(s);
                count++;
            }
            total++;
            return;
        }
        for (int i = arr.size() - 1; i >= 0; i--) {
            s += arr.get(i) + "";
            arr.remove(i);
            getAllPerms(arr,s);
            arr.add(i, s.charAt(s.length() - 1));
            s = s.substring(0,s.length()-1);
        }
    }
}
