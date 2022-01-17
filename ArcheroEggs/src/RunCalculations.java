import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class RunCalculations {
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(new File("egg-data-cleaned.txt"));
        double percentDecrease = .21;
        ArrayList<Monster> output = new ArrayList<Monster>();
        while (scan.hasNextLine())
        {
            String[] data = scan.nextLine().split("[\\ | \\t]+");
            long time = (long) (5 * Math.ceil(1.0 * Integer.parseInt(data[data.length - 1])*(1 - percentDecrease)/5)) * Integer.parseInt(data[data.length - 2]);
            String monster = "";
            String timeString = Time.convertToTimeString(time);
            for (int i = 0; i < data.length - 2; i++)
            {
                monster += data[i] + " ";
            }
            monster = monster.trim();
            output.add(new Monster(monster, timeString));
        }
        Collections.sort(output, new Comparator<Monster>() {
            @Override
            public int compare(Monster o1, Monster o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });

        PrintWriter writer = new PrintWriter("Results.csv");
        for (Monster m: output)
        {
            writer.write(m.toString().replaceFirst(":",",") + "\n");
            System.out.println(m);
        }
        writer.flush();
        writer.close();
    }
}
