import java.util.TreeSet;

public class Software extends Track{

    public Software() {
        super("software.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<String> courses) {
        for (String s: super.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }

        if (courses.contains(("CS354")) && courses.contains("CS352")) {
            return true;
        }
        if (!courses.contains("CS354") && !courses.contains("CS352")) {
            return false;
        }

        for (String s: super.elective) {
            if (s.equals("CS354") || s.equals("CS352"))
                continue;
            if (courses.contains(s))
                return true;
        }

        return false;
    }
}
