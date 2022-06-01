import java.util.TreeSet;

public class Systems extends Track{

    public Systems() {
        super("systems.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<Integer> courses) {
        for (Integer s: super.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }
        int matches = 0;
        for (Integer s: super.elective) {
            if (courses.contains(s))
                matches++;
            if (matches == 3)
                return true;
        }

        return false;
    }
}
