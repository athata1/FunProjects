import java.util.TreeSet;

public class Systems extends Track{

    public Systems() {
        super("systems.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<String> courses) {
        for (String s: super.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }
        int matches = 0;
        for (String s: super.elective) {
            if (courses.contains(s))
                matches++;
            if (matches == 3)
                return true;
        }

        return false;
    }
}
