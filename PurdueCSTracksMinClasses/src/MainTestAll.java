import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class MainTestAll {
    public static void main(String[] args) throws Exception {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "CG");
        map.put(2, "D");
        map.put(3, "A");
        map.put(4, "MI");
        map.put(5, "PL");
        map.put(6, "Sec");
        map.put(7, "Sof");
        map.put(8, "Sys");
        map.put(0, "CS");

        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                System.out.println(map.get(i) + " " + map.get(j));
                SmallestNumberOfClasses classes = new SmallestNumberOfClasses();
                classes.switchList[i] = true;
                classes.switchList[j] = true;
                TreeSet<Integer> ans = classes.getMinClasses();
                //System.out.println(ans);
                ArrayList<String> output = new ArrayList<>();
                for (Integer num : ans) {
                    output.add(CourseDatabase.getCourse(num));
                }
                Collections.sort(output);
                String s = "[";
                for (String str : output) {
                    s += str + ", ";
                }
                s = s.substring(0, s.length() - 2) + "] " + output.size();
                System.out.println(s);
            }
        }
    }
}
