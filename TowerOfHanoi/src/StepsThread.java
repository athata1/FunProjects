import java.util.LinkedList;
import java.util.Queue;

public class StepsThread implements Runnable
{
    private Queue<Move> moves;
    private int numBoxes;
    private int destination;
    private static Object gate = new Object();
    public StepsThread(int numBoxes, int destination)
    {
        moves = new LinkedList<Move>();
        this.numBoxes = numBoxes;
        this.destination = destination;
    }

    public void run()
    {
        getTowerOfHanoiMoves(numBoxes,1,destination,6 - 1 - destination);
        System.out.println("Done");
    }

    public void getTowerOfHanoiMoves(int numBoxes, int from, int to, int placeholder)
    {
        if (numBoxes == 1)
        {
            synchronized (gate) {
                moves.add(new Move(from, to));
            }
            try
            {
                Thread.sleep(10);
            } catch(InterruptedException e) {}
            return;
        }
        getTowerOfHanoiMoves(numBoxes - 1, from, placeholder, to);
        synchronized (gate) {
            moves.add(new Move(from, to));
        }
        try
        {
            Thread.sleep(10);
        } catch(InterruptedException e) {}
        getTowerOfHanoiMoves(numBoxes - 1, placeholder, to, from);
    }

    public Move getNextMove()
    {
        synchronized (gate) {
            try
            {
                if (!moves.isEmpty()) {
                    return moves.poll();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}
