import java.util.TreeSet;

public class Databases extends Track{

    public Databases() {
        super("databases.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<String> courses) {
        for (String s: super.required) {
            if (!courses.contains(s))
                return false;
        }


        //Requirements
        if (!courses.contains("CS373") && !courses.contains("CS473")) {
            return false;
        }

        //Category 1
        if (!courses.contains("CS352") && !courses.contains("CS353") && !courses.contains("CS354"))
            return false;

        //Category 2
        if (!courses.contains("CS355") && !courses.contains("CS426"))
            return false;

        //Category 3
        if (courses.contains("CS373") && courses.contains("CS473")) {
            return true;
        }

        if (courses.contains("CS490") || courses.contains("CS497") || courses.contains("EPICS411")
            || courses.contains("EPICS412"))
            return true;

        if (!courses.contains("CS422") && !courses.contains("CS471")
                && !courses.contains("CS478") && !courses.contains("CS483"))
            return false;

        return true;

    }
}
