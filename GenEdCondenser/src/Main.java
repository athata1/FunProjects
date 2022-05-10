import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(new File("data.txt"));
        ArrayList<String> courses = new ArrayList<String>();

        while (scan.hasNextLine())
        {
            courses.add(scan.nextLine());
        }

        scan = new Scanner(System.in);
        while(true)
        {
            int decision = 0;
            while (decision == 0) {
                System.out.println("What would you like to do");
                System.out.println("1. Filter by course type abbreviation.");
                System.out.println("2. Filter by level of course");
                System.out.println("3. Export to csv");
                System.out.println("4. Quit");
                try {
                    decision = Integer.parseInt(scan.nextLine());
                } catch (Exception e) {
                    System.out.println("Error: Enter a number between 1 and 2 inclusive");
                }
            }
            if (decision == 1)
            {
                System.out.println("Enter an abbreviation for a class");
                String input = scan.nextLine();
                int count = 0;
                for (int i = courses.size() - 1; i >= 0; i--)
                {
                    if (courses.get(i).contains(input))
                    {
                        courses.remove(i);
                        count++;
                    }
                }

                System.out.println("New courses Data:");
                for(String s: courses)
                {
                    System.out.println(s);
                }
                System.out.println("Removed " + count + " courses");
                System.out.println(courses.size() + " remains.");
                System.out.println();
            }
            else if (decision == 2)
            {
                System.out.println("Enter a the maximum level of course you want to take");
                System.out.println("Note: the level will be in the form of a number in the 100s divisible by 100" +
                        "ex: 100, 200, 300");
                int level = 0;
                try
                {
                    level = Integer.parseInt(scan.nextLine());
                    level += 100;
                } catch(NumberFormatException e)
                {
                    System.out.println("Error: Enter a number in the hundreds divisible by 100 ex: 100, 200, 300");
                    continue;
                }

                int count = 0;
                for (int i = courses.size() - 1; i >= 0; i--)
                {
                    int countUnitNum = 0;
                    String[] data = courses.get(i).split("[\\ | \\t]+");
                    while (countUnitNum < data.length)
                    {
                        try
                        {
                            if (Integer.parseInt(data[countUnitNum].trim()) > level * 100) {
                                courses.remove(i);
                                count++;
                            }
                            break;
                        } catch(Exception e)
                        {
                            countUnitNum++;
                            continue;
                        }
                    }
                }
                System.out.println();
                for(String s: courses)
                {
                    System.out.println(s);
                }
                System.out.println("Removed " + count + " courses");
                System.out.println(courses.size() + " remains.");
                System.out.println();
            }
            else if (decision == 3)
            {
                System.out.println("Enter filename for .txt");
                String input = scan.nextLine();
                if (!input.substring(input.length() - 4).equals(".txt"))
                {
                    input += ".txt";
                }
                System.out.println(input);
                File f = new File(input);
                if (f.exists())
                {
                    System.out.println("Error: file already exists");
                    continue;
                }
                f.createNewFile();
                PrintWriter writer = new PrintWriter(input);
                for (String s: courses)
                {
                    writer.write(s.replace("\\t"," ") + "\n");
                }
                writer.flush();
                writer.close();
            }
            else if (decision == 4)
            {
                break;
            }
            else
                System.out.println("Error: select a number between 1 and 2 inclusive");
        }
        System.out.println("Thank you for using GenEd Filter");
    }
}
