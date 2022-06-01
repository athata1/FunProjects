import java.util.TreeSet;

public class Algorithms extends Track{

    public Algorithms() {
        super("algorithms.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<String> courses) {
        for (String s: super.required) {
            if (!courses.contains(s))
                return false;
        }

        if (!courses.contains("CS352") && !courses.contains("CS354"))
            return false;

        if (!courses.contains("CS373") && !courses.contains("CS471"))
            return false;

        int matches = 0;
        for (String s: super.elective) {
            if (s.equals("MA341") || s.equals("MA353") || s.equals("MA362")
                    || s.equals("MA366") || s.equals("MA385") || s.equals("MA421")
                    || s.equals("MA453"))
                continue;

            if (s.equals("CS352") || s.equals("CS354"))
                continue;

            if (s.equals("CS373") || s.equals("CS471"))
                continue;

            if (courses.contains(s)) {
                matches++;
                if (matches >= 3)
                    return true;
            }
        }


        if (courses.contains("MA341") || courses.contains("MA353") || courses.contains("MA362")
            || courses.contains("MA366") || courses.contains("MA385") || courses.contains("MA421")
            || courses.contains("MA453")) {
            matches++;
        }

        return matches >= 3;
    }
}
