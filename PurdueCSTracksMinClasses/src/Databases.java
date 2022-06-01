import java.util.TreeSet;

public class Databases extends Track{

    public Databases() {
        super("databases.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<Integer> courses) {
        for (Integer s: super.required) {
            if (!courses.contains(s))
                return false;
        }


        //Requirements
        if (!courses.contains(CourseDatabase.getInt("CS373")) && !courses.contains(CourseDatabase.getInt("CS473"))) {
            return false;
        }

        //Category 1
        if (!courses.contains(CourseDatabase.getInt("CS352")) && !courses.contains(CourseDatabase.getInt("CS353")) && !courses.contains(CourseDatabase.getInt("CS354")))
            return false;

        //Category 2
        if (!courses.contains(CourseDatabase.getInt("CS355")) && !courses.contains(CourseDatabase.getInt("CS426")))
            return false;

        //Category 3
        if (courses.contains(CourseDatabase.getInt("CS373")) && courses.contains(CourseDatabase.getInt("CS473"))) {
            return true;
        }

        if (courses.contains(CourseDatabase.getInt("CS490")) || courses.contains(CourseDatabase.getInt("CS497")) || courses.contains(CourseDatabase.getInt("EPICS411"))
            || courses.contains(CourseDatabase.getInt("EPICS412")))
            return true;

        if (!courses.contains(CourseDatabase.getInt("CS422")) && !courses.contains(CourseDatabase.getInt("CS471"))
                && !courses.contains(CourseDatabase.getInt("CS478")) && !courses.contains(CourseDatabase.getInt("CS483")))
            return false;

        return true;

    }
}
