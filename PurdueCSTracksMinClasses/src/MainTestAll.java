import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class MainTestAll {
    public static void main(String[] args) throws Exception {

        HashMap<Integer, Track> map = new HashMap<>();
        map.put(0, new ComputationalScience());
        map.put(1, new ComputerGraphics());
        map.put(2, new Databases());
        map.put(3, new Algorithms());
        map.put(4, new MachineIntelligence());
        map.put(5, new ProgrammingLanguage());
        map.put(6, new Security());
        map.put(7, new Software());
        map.put(8, new Systems());


        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                System.out.println(map.get(i).getName() + " " + map.get(j).getName());
                SmallestNumberOfClasses classes = new SmallestNumberOfClasses();
                classes.addTrack(map.get(i));
                classes.addTrack(map.get(j));
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
