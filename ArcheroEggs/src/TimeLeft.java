import java.util.Scanner;

public class TimeLeft {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter total amount");
        int total = scan.nextInt();
        System.out.println("Enter amount completed");
        int partial = scan.nextInt();
        System.out.println("Enter time per tick");
        int tick = scan.nextInt();
        System.out.println("Enter boost multiplier");
        double percent = scan.nextDouble();

        long timeInMins = (total - partial) * (int)(5 * Math.ceil(((tick * (1 - percent)) / 5)));
        System.out.println(Time.convertToTimeString(timeInMins));
    }
}
