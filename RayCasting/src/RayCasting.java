/*
 * Akhil Thata
 *
 * Inspiration:
 * Youtube video: https://www.youtube.com/watch?v=fFq5So-UB0E
 *
 * How the code works:
 * The code starts by reading the cursor and where it is on the screen.
 * Then it released a specific amount of rays out of the cursor. The
 * eays will either intersect with a wall, or go on forever.
 *
 * Application:
 * Raycasting is used in some games to use a two dimention screnn
 * to create a three dimentional area to play the game in. One game
 * that does this is called Wolfenstein 3D.
 *
 * Problems that I encountered:
 * When the rays would go out, it would go through the closest wall
 * because there was another wall behind it that is casted itself to.
 * I had to create an array that found the shortest distance between
 * the cursor and the wall, which would tell which wall the ray was being
 * blocked by.
 *
 * What I learned:
 * 1. I learned how to use object classes in an effective way to ensure
 * the code works to how I want it to
 * 2. I learned about intersection of objects and their purpose. While
 * researching how to do this project, I learned I needed to know
 * line-line intersection. I figured it out and also found out that
 * intersection equations are essential to game development
 */
import java.awt.*;
import java.util.*;
public class RayCasting
{
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of lines: ");
        int lines = scan.nextInt();

        //import classes
        DrawingPanel panel = new DrawingPanel(1000,1000);
        Graphics g = panel.getGraphics();

        final double PI = Math.PI;
        ArrayList<Ray> rays = new ArrayList<Ray>();

        for (double i = 0; i < 2*PI; i+= PI/(1.0/2*lines))
        {
            rays.add(new Ray(1000,i,g));
        }

        ArrayList<Wall> walls = new ArrayList<Wall>();
        for (int i = 0; i < 10; i++)
        {
            int rand01 = (int)(Math.random()*1000);
            int rand02 = (int)(Math.random()*1000);
            int rand03 = (int)(Math.random()*1000);
            int rand04 = (int)(Math.random()*1000);
            walls.add(new Wall(rand01,rand02,rand03,rand04,g));
        }

        while(true)
        {
            g.setColor(Color.BLACK);
            //Find position of cursor
            int x = panel.findX();
            int y = panel.findY();

            //Draw walls
            for (Wall w: walls)
            {
                w.drawWall();
            }

            //Draw rays and alter size based on if it intersects wall
            for (Ray r: rays)
            {
                ArrayList<Wall> temp01 = new ArrayList<Wall>();
                ArrayList<Double> distances = new ArrayList<Double>();

                double min = Integer.MAX_VALUE;
                Wall index = null;

                //Find intersection with the shortest distance
                for (Wall w: walls)
                {
                    //Determine if ray intersects wall
                    if (r.intersects(w,x,y))
                    {
                        r.findNewXandY(x,y,w);
                        if (r.findDist(x,y) < min)
                        {
                            index = w;
                            min = r.findDist(x,y);
                        }
                    }
                }
                //Draw ray that intersects wall
                if (index != null)
                    r.drawRay(index,x,y);
                    //Draw ray that does not intersect wall
                else
                    r.drawOtherRay(x,y);
            }
            panel.sleep(100);
            clearBoard(g);
        }

    }
    public static void clearBoard(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1000,1000);
    }
}