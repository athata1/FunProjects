public class Move
{
    private int start;
    private int end;
    public Move(int num01, int num02)
    {
        start = num01;
        end = num02;
    }

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }
}