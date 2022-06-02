import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class SmallestNumberOfClasses {
    boolean[] switchList;
    Track[] trackList;
    HashMap<Integer, Integer> frequencyCount;
    private final TreeSet<Integer> requiredCourses;
    private TreeSet<Integer> totalElectives;
    int count = 0;
    public SmallestNumberOfClasses() {
        frequencyCount = new HashMap<>();
        requiredCourses = new TreeSet<>();
        totalElectives = new TreeSet<>();
        switchList = new boolean[9];
        trackList = new Track[9];
    }
    private void toggleAll() {
        toggleComputationalScience(switchList[0]);
        toggleComputerGraphics(switchList[1]);
        toggleDatabases(switchList[2]);
        toggleAlgorithms(switchList[3]);
        toggleMachineIntelligence(switchList[4]);
        toggleProgrammingLanguage(switchList[5]);
        toggleSecurity(switchList[6]);
        toggleSoftware(switchList[7]);
        toggleSystems(switchList[8]);
        for (boolean b: switchList) {
            count += b ? 1 : 0;
        }
    }
    public TreeSet<Integer> getMinClasses() {
        toggleAll();
        getRequiredClasses();
        getAllElectives();

        /*
         * Special Note: This is an optimization meant to help when there are too little courses in the required
         * courses. This optimization can lead to a non-optimal but close solution. If the program can complete
         * without optimization, then remove it from code.
         */
        for (Integer n: totalElectives) {
            if (frequencyCount.get(n) == count) {
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

                    boolean failFlag = allCoursesSatisfied(newCurr);
                    if (!failFlag) {
                        return newCurr;
                    }
                    queue.add(new TreeSet[]{newCurr, newRemaining});
                }
            }
        }
        return null;
    }

    private boolean allCoursesSatisfied(TreeSet<Integer> newCurr) {
        for (int j = 0; j < trackList.length; j++) {
            if (switchList[j] == false)
                continue;
            Track t = trackList[j];
            if (!t.isCompleted(newCurr)) {
                return true;
            }
        }
        return false;
    }

    private void getAllElectives() {
        for (int i = 0; i < trackList.length; i++) {
            if (switchList[i] == false)
                continue;
            for (int n: trackList[i].elective) {
                if (requiredCourses.contains(n))
                    continue;

                frequencyCount.put(n, frequencyCount.getOrDefault(n,0) + 1);
                totalElectives.add(n);
            }
        }

    }
    private void getRequiredClasses() {
        for (int i = 0; i < trackList.length; i++) {
            if (switchList[i] == false)
                continue;
            requiredCourses.addAll(trackList[i].required);
        }
    }

    public void toggleComputationalScience(boolean b) {
        switchList[0] = b;
        if (b)
            trackList[0] = new ComputationalScience();
    }

    public void toggleComputerGraphics(boolean b) {
        switchList[1] = b;
        if (b)
            trackList[1] = new ComputerGraphics();
    }

    public void toggleDatabases(boolean b) {
        switchList[2] = b;
        if (b)
            trackList[2] = new Databases();
    }

    public void toggleAlgorithms(boolean b) {
        switchList[3] = b;
        if (b)
            trackList[3] = new Algorithms();
    }

    public void toggleMachineIntelligence(boolean b) {
        switchList[4] = b;
        if (b)
            trackList[4] = new MachineIntelligence();
    }

    public void toggleProgrammingLanguage(boolean b) {
        switchList[5] = b;
        if (b)
            trackList[5] = new ProgrammingLanguage();
    }

    public void toggleSecurity(boolean b) {
        switchList[6] = b;
        if (b)
            trackList[6] = new Security();
    }

    public void toggleSoftware(boolean b) {
        switchList[7] = b;
        if (b)
            trackList[7] = new Software();
    }

    public void toggleSystems(boolean b) {
        switchList[8] = b;
        if (b)
            trackList[8] = new Systems();
    }
}
