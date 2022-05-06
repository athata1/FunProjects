import java.awt.*;
import java.util.ArrayList;

public class AStarThread implements Runnable{
    Node[][] board;
    int startC;
    int startR;
    int endR;
    int endC;
    int width;
    int height;
    ArrayList<int[]> points;
    public AStarThread(Node[][] board, int startR, int startC, int endR, int endC) {
        this.board = board;
        this.startC = startC;
        this.startR = startR;
        this.endC = endC;
        this.endR = endR;
        height = board.length;
        width = board[0].length;
    }

    public void run() {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                board[r][c].setHCost(c,r,endC,endR);
            }
        }

        points = new ArrayList<int[]>();
        points.add(new int[]{startR, startC});
        board[startR][startC].setGCost(0);

        while (points.size() != 0) {
            //Find coords with smallest F cost
            int size = points.size();
            int currIndex = 0;
            for (int i = 1; i < size; i++) {
                int r1 = points.get(i)[0];
                int c1 = points.get(i)[1];
                int r2 = points.get(currIndex)[0];
                int c2 = points.get(currIndex)[1];
                if (board[r1][c1].getFCost() <= board[r2][c2].getFCost()) {
                    currIndex = i;
                }
            }
            int r1 = points.get(currIndex)[0];
            int c1 = points.get(currIndex)[1];
            points.remove(currIndex);
            board[r1][c1].setColor(Color.MAGENTA);


            //find minimum gCost around node and make the middle node's g cost ming + 10
            int[][] vel = {{1,0},
                    {-1,0},
                    {0,1},
                    {0,-1},
                    /*{1,1},
                    {1,-1},
                    {-1,1},
                    {-1,-1}*/};
            int minG = Integer.MAX_VALUE;
            for (int i = 0; i < vel.length; i++) {
                int currR = vel[i][0] + r1;
                int currC = vel[i][1] + c1;
                boolean decisionR = currR >= 0 && currR < height;
                boolean decisionC = currC >= 0 && currC < width;
                if (decisionC && decisionR && board[currR][currC].getColor() == Color.MAGENTA) {
                    minG = Math.min(minG, board[currR][currC].getGCost());
                }
            }
            if (minG == Integer.MAX_VALUE)
                minG = -10;
            board[r1][c1].setGCost(minG + 10);

            //End Condition
            if (r1 == endR && c1 == endC) {
                System.out.println("Found");
                createPath(r1, c1);
                return;
            }

            for (int i = 0; i < vel.length; i++) {
                int currR = vel[i][0] + r1;
                int currC = vel[i][1] + c1;
                boolean decisionR = currR >= 0 && currR < height;
                boolean decisionC = currC >= 0 && currC < width;

                if (decisionC && decisionR && board[currR][currC].getColor() != Color.MAGENTA && board[currR][currC].getColor() != Color.BLACK) {
                    board[currR][currC].setGHCost(board[r1][c1].getGCost()+10,currC,currR,endC,endR,c1,r1);
                    if (board[currR][currC].getColor() != Color.YELLOW)
                    {
                        board[currR][currC].setColor(Color.YELLOW);
                        points.add(new int[]{currR,currC});
                    }
                }
            }
        }
        System.out.println("Done");
    }
    public void createPath(int r, int c) {

        while (true) {
            board[r][c].setColor(Color.CYAN);
            if (r == startR && c == startC)
                return;
            System.out.println(r  + " " + c);
            int temp = r;
            r = board[r][c].getAttachedArrY();
            c = board[temp][c].getAttachedArrX();
        }
    }
}
