import java.util.TreeSet;

public class MachineIntelligence extends Track{

    public MachineIntelligence() {
        super("machineIntelligence.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<Integer> courses) {
        for (Integer s: super.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }

        int matches = 0;
        if (courses.contains(CourseDatabase.getInt("CS471")) && courses.contains(CourseDatabase.getInt("CS473"))) {
            matches++;
        }
        if (!courses.contains(CourseDatabase.getInt("CS471")) && !courses.contains(CourseDatabase.getInt("CS473"))) {
            return false;
        }

        for (Integer s: super.elective) {
            if (s == (CourseDatabase.getInt("CS471")) || s == (CourseDatabase.getInt("CS473")))
                continue;

            if (courses.contains(s))
                matches++;
            if (matches >= 2)
                return true;
        }

        return false;
    }
}
