import java.io.File;
import java.util.*;

public class WordDiversity {
    public static void main(String[] args) throws Exception {
        Scanner getFile = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String fileName = getFile.nextLine();
        File f = new File(fileName);
        if (!f.exists()) {
            System.out.println("Error: Could not find file");
        }
        else {
            Scanner scan = new Scanner(new File(fileName));
            HashMap<String, Integer> map = new HashMap<String,Integer>();
            while (scan.hasNextLine()) {
                String[] array = scan.nextLine().toLowerCase().split("[ |!-/|:-@|\\[-`|-]+");
                for (int i = 0; i < array.length; i++) {
                    if (map.get(array[i]) == null) {
                        map.put(array[i],1);
                    }
                    else {
                        map.put(array[i],map.get(array[i])+1);
                    }
                }
            }
            map = sortByValue(map);
            for (Map.Entry<String,Integer> entry: map.entrySet()) {
                System.out.printf("Word: %15s, Value: %s\n",entry.getKey(), entry.getValue());
            }
        }
    }
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
