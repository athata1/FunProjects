import java.util.HashMap;

public class CourseDatabase {
    static HashMap<String, Integer> courseToInt = new HashMap<>();
    static HashMap<Integer, String> intToCourse = new HashMap<>();
    static int count = 0;
    static void put(String s) {
        if (courseToInt.containsKey(s))
            return;
        courseToInt.put(s, count);
        intToCourse.put(count,s);
        count++;
    }

    static String getCourse(int n) {
        if (!intToCourse.containsKey(n))
            return null;
        return intToCourse.get(n);
    }

    static int getInt(String s) {
        if (!courseToInt.containsKey(s))
            return -1;
        return courseToInt.get(s);
    }
}
