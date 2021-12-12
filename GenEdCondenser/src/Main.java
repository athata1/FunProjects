import java.io.File;
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
                System.out.println("2. Quit");
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
                System.out.println(courses.size() + "remains.");
                System.out.println();
            }
            else if (decision == 2)
                break;
            else
                System.out.println("Error: select a number between 1 and 2 inclusive");
        }
        System.out.println("Thank you for using GenEd Filter");
    }
}
