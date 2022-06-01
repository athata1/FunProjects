import java.util.*;

public class Main {
    static Track software;
    static Track systems;
    public static void main(String[] args) throws Exception {
        SmallestNumberOfClasses classes = new SmallestNumberOfClasses();

        classes.toggleComputationalScience(false);
        classes.toggleComputerGraphics(false);
        classes.toggleDatabases(false);
        classes.toggleAlgorithms(false);
        classes.toggleMachineInteligence(true);
        classes.toggleProgrammingLanguage(false);
        classes.toggleSecurity(false);
        classes.toggleSoftware(true);
        classes.toggleSystems(true);

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

    public static TreeSet<Integer> findSmallestNumberOfCourses(TreeSet<Integer> requiredCourses, TreeSet<Integer> remainingCourses) {
        Queue<TreeSet<Integer>[]> queue = new LinkedList<>();
        queue.add(new TreeSet[]{requiredCourses,remainingCourses});
        System.out.println(remainingCourses);
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                TreeSet<Integer>[] stackVal = queue.poll();
                TreeSet<Integer> currCourses = stackVal[0];
                TreeSet<Integer> remaining = stackVal[1];
                for (Integer s: remaining) {
                    TreeSet<Integer> newCurr = new TreeSet<Integer>(currCourses);
                    newCurr.add(s);
                    TreeSet<Integer> newRemaining = new TreeSet<Integer>(remaining);
                    newRemaining.remove(s);
                    if (software.isCompleted(newCurr) && systems.isCompleted(newCurr))
                        return newCurr;
                    queue.add(new TreeSet[]{newCurr, newRemaining});
                }
            }
        }
        return null;
    }

    public static TreeSet<Integer> getTotalElectives(Track software, Track systems,
                                                    TreeSet<Integer> required) {
        TreeSet<Integer> output = new TreeSet<Integer>();
        for (Integer s: software.elective) {
            if (!required.contains(s))
                output.add(s);
        }
        for (Integer s: systems.elective) {
            if (!required.contains(s))
                output.add(s);
        }
        return output;
    }

    public static TreeSet<Integer> getTotalCourses(Track software, Track systems) {
        TreeSet<Integer> output = new TreeSet<Integer>();
        output.addAll(software.required);
        output.addAll(systems.required);
        return output;
    }
}
