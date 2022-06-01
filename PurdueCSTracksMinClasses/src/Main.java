import java.util.*;

public class Main {
    static Track software;
    static Track systems;
    public static void main(String[] args) throws Exception {
        software = new Track("software.txt");
        systems = new Track("systems.txt");
        TreeSet<String> requiredCourses = getTotalCourses(software, systems);
        requiredCourses.add("CS353");
        TreeSet<String> totalElectives = getTotalElectives(software, systems, requiredCourses);

        TreeSet<String> output = findSmallestNumberOfCourses(requiredCourses, totalElectives);
        System.out.println(output);
    }
    public static TreeSet<String> findSmallestNumberOfCourses(TreeSet<String> requiredCourses, TreeSet<String> remainingCourses) {
        Queue<TreeSet<String>[]> queue = new LinkedList<>();
        queue.add(new TreeSet[]{requiredCourses,remainingCourses});

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeSet<String>[] stackVal = queue.poll();
                TreeSet<String> currCourses = stackVal[0];
                if (isSoftware(currCourses, software) && isSystems(currCourses, systems))
                    return currCourses;
                TreeSet<String> remaining = stackVal[1];
                for (String s: remaining) {
                    TreeSet<String> newCurr = new TreeSet<String>(currCourses);
                    newCurr.add(s);
                    TreeSet<String> newRemaining = new TreeSet<String>(remaining);
                    newRemaining.remove(s);
                    queue.add(new TreeSet[]{newCurr, newRemaining});
                }
            }
        }
        return null;
    }
    public static boolean isSoftware(TreeSet<String> courses, Track software) {
        for (String s: software.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }
        if (courses.contains("CS354") && courses.contains("CS352")) {
            return true;
        }
        if (!courses.contains("CS354") && !courses.contains("CS352")) {
            return false;
        }

        for (String s: software.elective) {
            if (courses.contains(s))
                return true;
        }

        return false;
    }

    public static boolean isSystems(TreeSet<String> courses, Track systems) {
        for (String s: systems.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }
        int matches = 0;
        for (String s: systems.elective) {
            if (courses.contains(s))
                matches++;
            if (matches == 3)
                return true;
        }

        return false;
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
        for (String s: software.required) {
            output.add(s);
        }
        for (String s: systems.required) {
            output.add(s);
        }
        return output;
    }
}
