public class Time {

    //Time is in minutes
    public static String convertToTimeString(long time)
    {
        long min = time % 60;
        time /= 60;

        long hour = time % 24;
        time /= 24;

        return String.format("%02d:%02d:%02d",time,hour,min);
    }
}
