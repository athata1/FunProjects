import java.util.TreeSet;

public class MachineIntelligence extends Track{

    public MachineIntelligence() {
        super("machineIntelligence.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<String> courses) {
        for (String s: super.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }

        int matches = 0;
        if (courses.contains("CS471") && courses.contains("CS473")) {
            matches++;
        }
        if (!courses.contains("CS471") && !courses.contains("CS473")) {
            return false;
        }

        for (String s: super.elective) {
            if (matches == 2)
                return true;
            if (s.equals("CS471") || s.equals("CS473"))
                continue;

            if (courses.contains(s))
                matches++;
        }

        return false;
    }
}
