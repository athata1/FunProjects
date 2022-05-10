public class Time
{
    private long total;
    private long ms;
    private long s;
    private long m;
    private long h;
    private long d;

    public Time()
    {
        total = 0;
        ms = 0;
        s = 0;
        m = 0;
        h = 0;
        d = 0;
    }

    public void setTime(long num)
    {
        total = num;
        update();
    }

    private void update()
    {
        long temp = total;
        ms = temp % 1000;
        temp /= 1000;
        s = temp % 60;
        temp /= 60;
        m = temp % 60;
        temp /= 60;
        h = temp % 24;
        temp/= 24;
        d = temp;
    }

    public String toString()
    {
        return String.format("%02d:%02d:%02d:%02d:%03d",d,h,m,s,ms);
    }
}