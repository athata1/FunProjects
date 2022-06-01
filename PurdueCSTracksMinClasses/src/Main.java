import java.util.*;

public class Main {
    static Track software;
    static Track systems;
    public static void main(String[] args) throws Exception {
        software = new ProgrammingLanguage();
        systems = new Databases();
        TreeSet<String> requiredCourses = getTotalCourses(software, systems);
        TreeSet<String> totalElectives = getTotalElectives(software, systems, requiredCourses);

        TreeSet<String> output = findSmallestNumberOfCourses(requiredCourses, totalElectives);
        System.out.println(output);
    }
    public static TreeSet<String> findSmallestNumberOfCourses(TreeSet<String> requiredCourses, TreeSet<String> remainingCourses) {
        Queue<TreeSet<String>[]> queue = new LinkedList<>();
        queue.add(new TreeSet[]{requiredCourses,remainingCourses});
        System.out.println(remainingCourses);
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                TreeSet<String>[] stackVal = queue.poll();
                TreeSet<String> currCourses = stackVal[0];
                TreeSet<String> remaining = stackVal[1];
                for (String s: remaining) {
                    TreeSet<String> newCurr = new TreeSet<String>(currCourses);
                    newCurr.add(s);
                    TreeSet<String> newRemaining = new TreeSet<String>(remaining);
                    newRemaining.remove(s);
                    if (software.isCompleted(newCurr) && systems.isCompleted(newCurr))
                        return newCurr;
                    queue.add(new TreeSet[]{newCurr, newRemaining});
                }
            }
        }
        return null;
    }

    public static TreeSet<String> getTotalElectives(Track software, Track systems,
                                                    TreeSet<String> required) {
        TreeSet<String> output = new TreeSet<String>();
        for (String s: software.elective) {
            if (!required.contains(s))
                output.add(s);
        }
        for (String s: systems.elective) {
            if (!required.contains(s))
                output.add(s);
        }
        return output;
    }

    public static TreeSet<String> getTotalCourses(Track software, Track systems) {
        TreeSet<String> output = new TreeSet<String>();
        output.addAll(software.required);
        output.addAll(systems.required);
        return output;
    }
}
