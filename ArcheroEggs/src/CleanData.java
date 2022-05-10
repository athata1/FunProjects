import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class CleanData
{
    public static void main(String[] args) throws Exception
    {
        Scanner scan = new Scanner(new File("egg-data.txt"));
        PrintWriter writer = new PrintWriter(new File("egg-data-cleaned.txt"));
        while (scan.hasNextLine())
        {
            String s = scan.nextLine();
            if (s.contains("min"))
            {
                int n = s.indexOf("min");
                writer.write(s.substring(0,n) + "\n");
            }
        }
        writer.flush();
        writer.close();
        scan.close();

        scan = new Scanner(new File("egg-data-cleaned.txt"));
        writer = new PrintWriter("egg-data-cleaned2.txt");

        while (scan.hasNextLine())
        {
            String s = scan.nextLine();
            String[] arr = s.split("[\\ | \\t]+");
            if (arr.length >= 3)
            {
                writer.write(s + "\n");
            }
        }
        writer.flush();
        writer.close();
        scan.close();

        scan = new Scanner(new File("egg-data-cleaned2.txt"));
        writer = new PrintWriter("egg-data-cleaned.txt");
        while (scan.hasNextLine())
        {
            String s = scan.nextLine();
            writer.write(s + "\n");
        }
        writer.flush();
        writer.close();
        scan.close();
        File f = new File("egg-data-cleaned2.txt");
        f.delete();
    }
}
