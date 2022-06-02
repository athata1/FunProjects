import java.util.*;

public class SmallestNumberOfClasses {
    ArrayList<Track> trackList;
    HashMap<Integer, Integer> frequencyCount;
    private final TreeSet<Integer> requiredCourses;
    private TreeSet<Integer> totalElectives;
    public SmallestNumberOfClasses() {
        frequencyCount = new HashMap<>();
        requiredCourses = new TreeSet<>();
        totalElectives = new TreeSet<>();
        trackList = new ArrayList<>();
    }

    public void addTrack(Track t) {
        trackList.add(t);
    }

    public TreeSet<Integer> getMinClasses() {
        getRequiredClasses();
        getAllElectives();

        /*
         * Special Note: This is an optimization meant to help when there are too little courses in the required
         * courses. This optimization can lead to a non-optimal but close solution. If the program can complete
         * without optimization, then remove it from code.
         */
        for (Integer n: totalElectives) {
            if (frequencyCount.get(n) == trackList.size()) {
                requiredCourses.add(n);
                totalElectives.remove(n);
                break;
            }
        }
        //System.out.println(requiredCourses);
        //System.out.println(totalElectives);
        Queue<TreeSet<Integer>[]> queue = new LinkedList<>();
        queue.add(new TreeSet[]{requiredCourses,totalElectives});
        while (!queue.isEmpty()) {
            int size = queue.size();
            //System.out.println(size);
            for (int i = 0; i < size; i++) {
                TreeSet<Integer>[] stackVal = queue.poll();
                TreeSet<Integer> currCourses = stackVal[0];
                TreeSet<Integer> remaining = stackVal[1];
                for (Integer s: remaining) {
                    TreeSet<Integer> newCurr = new TreeSet<Integer>(currCourses);
                    newCurr.add(s);
                    TreeSet<Integer> newRemaining = new TreeSet<Integer>(remaining);
                    newRemaining.remove(s);

                    boolean successFlag = allCoursesSatisfied(newCurr);
                    if (successFlag) {
                        return newCurr;
                    }
                    queue.add(new TreeSet[]{newCurr, newRemaining});
                }
            }
        }
        return null;
    }

    private boolean allCoursesSatisfied(TreeSet<Integer> newCurr) {
        for (Track t: trackList) {
            if (!t.isCompleted(newCurr)) {
                return false;
            }
        }
        return true;
    }

    private void getAllElectives() {
        for (int i = 0; i < trackList.size(); i++) {
            for (int n: trackList.get(i).elective) {
                if (requiredCourses.contains(n))
                    continue;

                frequencyCount.put(n, frequencyCount.getOrDefault(n,0) + 1);
                totalElectives.add(n);
            }
        }

    }
    private void getRequiredClasses() {
        for (int i = 0; i < trackList.size(); i++) {
            requiredCourses.addAll(trackList.get(i).required);
        }
    }
}
