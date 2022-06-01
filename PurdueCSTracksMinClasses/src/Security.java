import java.util.TreeSet;

public class Security extends Track{

    public Security() {
        super("security.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<String> courses) {
        for (String s: super.required) {
            if (!courses.contains(s))
                return false;
        }
        int matches = 0;

        if (courses.contains("CS307") || courses.contains("CS408"))
            matches++;

        if (courses.contains("CS348") || courses.contains("CS448") ||
            courses.contains("CS473"))
            matches++;
        if (courses.contains("CS352"))
            matches++;

        if (courses.contains("CS353") || courses.contains("CS456"))
            matches++;

        if (courses.contains("CS373") || courses.contains("CS471"))
            matches++;

        if (courses.contains("CS381"))
            matches++;

        if (courses.contains("CS422"))
            matches++;

        if (courses.contains("CS489") || courses.contains("CS490-DSO"))
            matches++;

        if (courses.contains("CS490-SWS"))
            matches++;

        return matches >= 3;
    }
}
