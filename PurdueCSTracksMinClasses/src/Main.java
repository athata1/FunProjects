import java.util.*;

public class Main {
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

        SmallestNumberOfClasses classes = new SmallestNumberOfClasses();

        classes.toggleComputationalScience(true);
        classes.toggleComputerGraphics(false);
        classes.toggleDatabases(true);
        classes.toggleAlgorithms(false);
        classes.toggleMachineIntelligence(false);
        classes.toggleProgrammingLanguage(false);
        classes.toggleSecurity(false);
        classes.toggleSoftware(false);
        classes.toggleSystems(false);
        TreeSet<Integer> ans = classes.getMinClasses();
        System.out.println(ans);
        ArrayList<String> output = new ArrayList<>();
        for (Integer num: ans) {
            output.add(CourseDatabase.getCourse(num));
        }
        Collections.sort(output);
        String s = "[";
        for (String str: output) {
            s += str + ", ";
        }
        s = s.substring(0, s.length() - 2) + "]";
        System.out.println(s);
    }
}
