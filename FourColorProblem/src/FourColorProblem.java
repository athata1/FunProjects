import java.util.*;
public class FourColorProblem
{
    public static void main(String[] args)
    {

        ArrayList<Shape> shapes = new ArrayList<Shape>();
      /*for (int i = 0; i < 16; i++)
      {
         shapes.add(new Shape("object" + i));
      }*/
      
      /*shapes.get(0).connectsTo(shapes.get(1));
      shapes.get(0).connectsTo(shapes.get(2));
      shapes.get(0).connectsTo(shapes.get(3));
      
      shapes.get(1).connectsTo(shapes.get(0));
      shapes.get(1).connectsTo(shapes.get(2));
      shapes.get(1).connectsTo(shapes.get(3));
      shapes.get(1).connectsTo(shapes.get(4));
      shapes.get(1).connectsTo(shapes.get(6));
      
      shapes.get(2).connectsTo(shapes.get(0));
      shapes.get(2).connectsTo(shapes.get(1));
      shapes.get(2).connectsTo(shapes.get(3));
      shapes.get(2).connectsTo(shapes.get(5));
      shapes.get(2).connectsTo(shapes.get(4));
      
      shapes.get(3).connectsTo(shapes.get(0));
      shapes.get(3).connectsTo(shapes.get(1));
      shapes.get(3).connectsTo(shapes.get(2));
      shapes.get(3).connectsTo(shapes.get(5));
      shapes.get(3).connectsTo(shapes.get(6));
      
      shapes.get(4).connectsTo(shapes.get(1));
      shapes.get(4).connectsTo(shapes.get(2));
      shapes.get(4).connectsTo(shapes.get(5));
      shapes.get(4).connectsTo(shapes.get(6));
      shapes.get(4).connectsTo(shapes.get(8));
      shapes.get(4).connectsTo(shapes.get(9));
      
      shapes.get(5).connectsTo(shapes.get(2));
      shapes.get(5).connectsTo(shapes.get(3));
      shapes.get(5).connectsTo(shapes.get(4));
      shapes.get(5).connectsTo(shapes.get(6));
      shapes.get(5).connectsTo(shapes.get(7));
      shapes.get(5).connectsTo(shapes.get(8));
      
      shapes.get(6).connectsTo(shapes.get(1));
      shapes.get(6).connectsTo(shapes.get(3));
      shapes.get(6).connectsTo(shapes.get(4));
      shapes.get(6).connectsTo(shapes.get(5));
      shapes.get(6).connectsTo(shapes.get(7));
      shapes.get(6).connectsTo(shapes.get(9));
      
      shapes.get(7).connectsTo(shapes.get(5));
      shapes.get(7).connectsTo(shapes.get(6));
      shapes.get(7).connectsTo(shapes.get(8));
      shapes.get(7).connectsTo(shapes.get(9));
      
      shapes.get(8).connectsTo(shapes.get(4));
      shapes.get(8).connectsTo(shapes.get(5));
      shapes.get(8).connectsTo(shapes.get(7));
      shapes.get(8).connectsTo(shapes.get(9));
      
      shapes.get(9).connectsTo(shapes.get(4));
      shapes.get(9).connectsTo(shapes.get(6));
      shapes.get(9).connectsTo(shapes.get(7));
      shapes.get(9).connectsTo(shapes.get(8));*/
      
      /*for (int i = 0; i < 16; i++)
      {
         shapes.add(new Shape("object" + i));
      }
      
      shapes.get(0).connectsTo(shapes.get(1));
      shapes.get(0).connectsTo(shapes.get(3));
      shapes.get(0).connectsTo(shapes.get(4));
      shapes.get(0).connectsTo(shapes.get(5));
      shapes.get(0).connectsTo(shapes.get(8));
      
      shapes.get(1).connectsTo(shapes.get(0));
      shapes.get(1).connectsTo(shapes.get(2));
      shapes.get(1).connectsTo(shapes.get(5));
      shapes.get(1).connectsTo(shapes.get(6));
      shapes.get(1).connectsTo(shapes.get(9));
      
      shapes.get(2).connectsTo(shapes.get(1));
      shapes.get(2).connectsTo(shapes.get(3));
      shapes.get(2).connectsTo(shapes.get(6));
      shapes.get(2).connectsTo(shapes.get(7));
      shapes.get(2).connectsTo(shapes.get(10));
      
      shapes.get(3).connectsTo(shapes.get(0));
      shapes.get(3).connectsTo(shapes.get(2));
      shapes.get(3).connectsTo(shapes.get(4));
      shapes.get(3).connectsTo(shapes.get(7));
      shapes.get(3).connectsTo(shapes.get(11));
      
      shapes.get(4).connectsTo(shapes.get(0));
      shapes.get(4).connectsTo(shapes.get(3));
      shapes.get(4).connectsTo(shapes.get(8));
      shapes.get(4).connectsTo(shapes.get(11));
      shapes.get(4).connectsTo(shapes.get(12));
      
      shapes.get(5).connectsTo(shapes.get(0));
      shapes.get(5).connectsTo(shapes.get(1));
      shapes.get(5).connectsTo(shapes.get(8));
      shapes.get(5).connectsTo(shapes.get(9));
      shapes.get(5).connectsTo(shapes.get(13));
      
      shapes.get(6).connectsTo(shapes.get(1));
      shapes.get(6).connectsTo(shapes.get(2));
      shapes.get(6).connectsTo(shapes.get(9));
      shapes.get(6).connectsTo(shapes.get(10));
      shapes.get(6).connectsTo(shapes.get(14));
      
      shapes.get(7).connectsTo(shapes.get(2));
      shapes.get(7).connectsTo(shapes.get(3));
      shapes.get(7).connectsTo(shapes.get(10));
      shapes.get(7).connectsTo(shapes.get(11));
      shapes.get(7).connectsTo(shapes.get(15));
      
      shapes.get(8).connectsTo(shapes.get(0));
      shapes.get(8).connectsTo(shapes.get(4));
      shapes.get(8).connectsTo(shapes.get(5));
      shapes.get(8).connectsTo(shapes.get(12));
      shapes.get(8).connectsTo(shapes.get(13));
      
      shapes.get(9).connectsTo(shapes.get(1));
      shapes.get(9).connectsTo(shapes.get(5));
      shapes.get(9).connectsTo(shapes.get(6));
      shapes.get(9).connectsTo(shapes.get(13));
      shapes.get(9).connectsTo(shapes.get(14));
      
      shapes.get(10).connectsTo(shapes.get(2));
      shapes.get(10).connectsTo(shapes.get(6));
      shapes.get(10).connectsTo(shapes.get(7));
      shapes.get(10).connectsTo(shapes.get(14));
      shapes.get(10).connectsTo(shapes.get(15));
      
      shapes.get(11).connectsTo(shapes.get(3));
      shapes.get(11).connectsTo(shapes.get(4));
      shapes.get(11).connectsTo(shapes.get(7));
      shapes.get(11).connectsTo(shapes.get(12));
      shapes.get(11).connectsTo(shapes.get(15));
      
      shapes.get(12).connectsTo(shapes.get(4));
      shapes.get(12).connectsTo(shapes.get(8));
      shapes.get(12).connectsTo(shapes.get(11));
      shapes.get(12).connectsTo(shapes.get(13));
      shapes.get(12).connectsTo(shapes.get(15));
      
      shapes.get(13).connectsTo(shapes.get(5));
      shapes.get(13).connectsTo(shapes.get(8));
      shapes.get(13).connectsTo(shapes.get(9));
      shapes.get(13).connectsTo(shapes.get(12));
      shapes.get(13).connectsTo(shapes.get(14));
      
      shapes.get(14).connectsTo(shapes.get(6));
      shapes.get(14).connectsTo(shapes.get(9));
      shapes.get(14).connectsTo(shapes.get(10));
      shapes.get(14).connectsTo(shapes.get(13));
      shapes.get(14).connectsTo(shapes.get(15));
      
      shapes.get(15).connectsTo(shapes.get(7));
      shapes.get(15).connectsTo(shapes.get(10));
      shapes.get(15).connectsTo(shapes.get(11));
      shapes.get(15).connectsTo(shapes.get(12));
      shapes.get(15).connectsTo(shapes.get(14));*/

        Shape alabama = new Shape("Alabama");
        Shape arizona = new Shape("Arizona");
        Shape arkansas = new Shape("Arkansas");
        Shape california = new Shape("California");
        Shape colorado = new Shape("Colorado");
        Shape connecticut = new Shape("Connecticut");
        Shape delaware = new Shape("Delaware");
        Shape florida = new Shape("Florida");
        Shape georgia = new Shape("Georgia");
        Shape idaho = new Shape("Idaho");
        Shape illinois = new Shape("Illinois");
        Shape indiana = new Shape("Indiana");
        Shape iowa = new Shape("Iowa");
        Shape kansas = new Shape("Kansas");
        Shape kentucky = new Shape("Kentucky");
        Shape louisiana = new Shape("Louisiana");
        Shape maine = new Shape("Maine");
        Shape maryland = new Shape("Maryland");
        Shape massachusetts = new Shape("Massachusetts");
        Shape michigan = new Shape("Michigan");
        Shape minnesota = new Shape("Minnesota");
        Shape mississippi = new Shape("Mississippi");
        Shape missouri = new Shape("Missouri");
        Shape montana = new Shape("Montana");
        Shape nebraska = new Shape("Nebraska");
        Shape nevada = new Shape("Nevada");
        Shape newHampshire = new Shape("New Hampshire");
        Shape newJersey = new Shape("New Jersey");
        Shape newMexico = new Shape("New Mexico");
        Shape newYork = new Shape("New York");
        Shape northCarolina = new Shape("North Carolina");
        Shape northDakota = new Shape("North Dakota");
        Shape ohio = new Shape("Ohio");
        Shape oklahoma = new Shape("Oklahoma");
        Shape oregon = new Shape("Oregon");
        Shape pennsylvania = new Shape("Pennsylvania");
        Shape rhodeIsland = new Shape("Rhode Island");
        Shape southCarolina = new Shape("South Carolina");
        Shape southDakota = new Shape("South Dakota");
        Shape tennessee = new Shape("Tennessee");
        Shape texas = new Shape("Texas");
        Shape utah = new Shape("Utah");
        Shape vermont = new Shape("Vermont");
        Shape virginia = new Shape("Virginia");
        Shape washington = new Shape("Washington");
        Shape westVirginia = new Shape("West Virginia");
        Shape wisconsin = new Shape("Wisconsin");
        Shape wyoming = new Shape("Wyoming");

        alabama.connectsTo(florida);
        alabama.connectsTo(georgia);
        alabama.connectsTo(mississippi);
        alabama.connectsTo(tennessee);

        arizona.connectsTo(california);
        arizona.connectsTo(colorado);
        arizona.connectsTo(nevada);
        arizona.connectsTo(newMexico);
        arizona.connectsTo(utah);

        arkansas.connectsTo(louisiana);
        arkansas.connectsTo(mississippi);
        arkansas.connectsTo(missouri);
        arkansas.connectsTo(oklahoma);
        arkansas.connectsTo(tennessee);
        arkansas.connectsTo(texas);

        california.connectsTo(arizona);
        california.connectsTo(nevada);
        california.connectsTo(oregon);

        colorado.connectsTo(arizona);
        colorado.connectsTo(kansas);
        colorado.connectsTo(nebraska);
        colorado.connectsTo(newMexico);
        colorado.connectsTo(oklahoma);
        colorado.connectsTo(utah);
        colorado.connectsTo(wyoming);

        connecticut.connectsTo(massachusetts);
        connecticut.connectsTo(newYork);
        connecticut.connectsTo(rhodeIsland);

        delaware.connectsTo(maryland);
        delaware.connectsTo(newJersey);
        delaware.connectsTo(pennsylvania);

        florida.connectsTo(alabama);
        florida.connectsTo(georgia);

        georgia.connectsTo(alabama);
        georgia.connectsTo(florida);
        georgia.connectsTo(northCarolina);
        georgia.connectsTo(southCarolina);
        georgia.connectsTo(tennessee);

        idaho.connectsTo(montana);
        idaho.connectsTo(nevada);
        idaho.connectsTo(oregon);
        idaho.connectsTo(utah);
        idaho.connectsTo(washington);
        idaho.connectsTo(wyoming);

        illinois.connectsTo(indiana);
        illinois.connectsTo(iowa);
        illinois.connectsTo(michigan);
        illinois.connectsTo(kentucky);
        illinois.connectsTo(missouri);
        illinois.connectsTo(wisconsin);

        indiana.connectsTo(illinois);
        indiana.connectsTo(kentucky);
        indiana.connectsTo(ohio);
        indiana.connectsTo(michigan);

        iowa.connectsTo(illinois);
        iowa.connectsTo(minnesota);
        iowa.connectsTo(missouri);
        iowa.connectsTo(nebraska);
        iowa.connectsTo(southDakota);
        iowa.connectsTo(wisconsin);

        kansas.connectsTo(colorado);
        kansas.connectsTo(missouri);
        kansas.connectsTo(nebraska);
        kansas.connectsTo(oklahoma);

        kentucky.connectsTo(illinois);
        kentucky.connectsTo(indiana);
        kentucky.connectsTo(missouri);
        kentucky.connectsTo(ohio);
        kentucky.connectsTo(tennessee);
        kentucky.connectsTo(virginia);
        kentucky.connectsTo(westVirginia);

        louisiana.connectsTo(texas);
        louisiana.connectsTo(mississippi);
        louisiana.connectsTo(arkansas);

        maine.connectsTo(newHampshire);

        maryland.connectsTo(delaware);
        maryland.connectsTo(pennsylvania);
        maryland.connectsTo(virginia);
        maryland.connectsTo(westVirginia);

        massachusetts.connectsTo(connecticut);
        massachusetts.connectsTo(newHampshire);
        massachusetts.connectsTo(newYork);
        massachusetts.connectsTo(rhodeIsland);
        massachusetts.connectsTo(vermont);

        michigan.connectsTo(illinois);
        michigan.connectsTo(indiana);
        michigan.connectsTo(minnesota);
        michigan.connectsTo(ohio);
        michigan.connectsTo(wisconsin);

        minnesota.connectsTo(iowa);
        minnesota.connectsTo(michigan);
        minnesota.connectsTo(northDakota);
        minnesota.connectsTo(southDakota);
        minnesota.connectsTo(wisconsin);

        mississippi.connectsTo(alabama);
        mississippi.connectsTo(arkansas);
        mississippi.connectsTo(louisiana);
        mississippi.connectsTo(tennessee);

        missouri.connectsTo(arkansas);
        missouri.connectsTo(illinois);
        missouri.connectsTo(iowa);
        missouri.connectsTo(kansas);
        missouri.connectsTo(kentucky);
        missouri.connectsTo(nebraska);
        missouri.connectsTo(oklahoma);
        missouri.connectsTo(tennessee);

        montana.connectsTo(northDakota);
        montana.connectsTo(southDakota);
        montana.connectsTo(wyoming);
        montana.connectsTo(idaho);

        nebraska.connectsTo(colorado);
        nebraska.connectsTo(iowa);
        nebraska.connectsTo(kansas);
        nebraska.connectsTo(missouri);
        nebraska.connectsTo(southDakota);
        nebraska.connectsTo(wyoming);

        nevada.connectsTo(arizona);
        nevada.connectsTo(california);
        nevada.connectsTo(idaho);
        nevada.connectsTo(oregon);
        nevada.connectsTo(utah);

        newHampshire.connectsTo(maine);
        newHampshire.connectsTo(massachusetts);
        newHampshire.connectsTo(vermont);

        newJersey.connectsTo(newYork);
        newJersey.connectsTo(delaware);
        newJersey.connectsTo(pennsylvania);

        newMexico.connectsTo(arizona);
        newMexico.connectsTo(colorado);
        newMexico.connectsTo(oklahoma);
        newMexico.connectsTo(texas);
        newMexico.connectsTo(utah);

        newYork.connectsTo(connecticut);
        newYork.connectsTo(massachusetts);
        newYork.connectsTo(newJersey);
        newYork.connectsTo(pennsylvania);
        newYork.connectsTo(rhodeIsland);
        newYork.connectsTo(vermont);

        northCarolina.connectsTo(georgia);
        northCarolina.connectsTo(southCarolina);
        northCarolina.connectsTo(virginia);

        northDakota.connectsTo(minnesota);
        northDakota.connectsTo(montana);
        northDakota.connectsTo(southDakota);

        ohio.connectsTo(indiana);
        ohio.connectsTo(kentucky);
        ohio.connectsTo(michigan);
        ohio.connectsTo(pennsylvania);
        ohio.connectsTo(westVirginia);

        oklahoma.connectsTo(arkansas);
        oklahoma.connectsTo(colorado);
        oklahoma.connectsTo(kansas);
        oklahoma.connectsTo(missouri);
        oklahoma.connectsTo(newMexico);
        oklahoma.connectsTo(texas);

        oregon.connectsTo(california);
        oregon.connectsTo(idaho);
        oregon.connectsTo(nevada);
        oregon.connectsTo(washington);

        pennsylvania.connectsTo(delaware);
        pennsylvania.connectsTo(newJersey);
        pennsylvania.connectsTo(newYork);
        pennsylvania.connectsTo(maryland);
        pennsylvania.connectsTo(ohio);
        pennsylvania.connectsTo(westVirginia);

        rhodeIsland.connectsTo(connecticut);
        rhodeIsland.connectsTo(massachusetts);
        rhodeIsland.connectsTo(newYork);

        southCarolina.connectsTo(georgia);
        southCarolina.connectsTo(northCarolina);

        southDakota.connectsTo(iowa);
        southDakota.connectsTo(minnesota);
        southDakota.connectsTo(montana);
        southDakota.connectsTo(nebraska);
        southDakota.connectsTo(northDakota);
        southDakota.connectsTo(wyoming);

        tennessee.connectsTo(alabama);
        tennessee.connectsTo(arkansas);
        tennessee.connectsTo(georgia);
        tennessee.connectsTo(kentucky);
        tennessee.connectsTo(mississippi);
        tennessee.connectsTo(missouri);
        tennessee.connectsTo(northCarolina);
        tennessee.connectsTo(virginia);

        texas.connectsTo(arkansas);
        texas.connectsTo(louisiana);
        texas.connectsTo(newMexico);
        texas.connectsTo(oklahoma);

        utah.connectsTo(arizona);
        utah.connectsTo(colorado);
        utah.connectsTo(idaho);
        utah.connectsTo(nevada);
        utah.connectsTo(newMexico);
        utah.connectsTo(wyoming);

        vermont.connectsTo(massachusetts);
        vermont.connectsTo(newHampshire);
        vermont.connectsTo(newYork);

        virginia.connectsTo(kentucky);
        virginia.connectsTo(maryland);
        virginia.connectsTo(northCarolina);
        virginia.connectsTo(tennessee);
        virginia.connectsTo(westVirginia);

        washington.connectsTo(idaho);
        washington.connectsTo(oregon);

        westVirginia.connectsTo(kentucky);
        westVirginia.connectsTo(maryland);
        westVirginia.connectsTo(ohio);
        westVirginia.connectsTo(pennsylvania);
        westVirginia.connectsTo(virginia);

        wisconsin.connectsTo(illinois);
        wisconsin.connectsTo(iowa);
        wisconsin.connectsTo(michigan);
        wisconsin.connectsTo(minnesota);

        wyoming.connectsTo(colorado);
        wyoming.connectsTo(idaho);
        wyoming.connectsTo(montana);
        wyoming.connectsTo(nebraska);
        wyoming.connectsTo(southDakota);
        wyoming.connectsTo(utah);

        shapes.add(alabama);
        shapes.add(arizona);
        shapes.add(arkansas);
        shapes.add(california);
        shapes.add(colorado);
        shapes.add(connecticut);
        shapes.add(delaware);
        shapes.add(florida);
        shapes.add(georgia);
        shapes.add(idaho);
        shapes.add(illinois);
        shapes.add(indiana);
        shapes.add(iowa);
        shapes.add(kansas);
        shapes.add(kentucky);
        shapes.add(louisiana);
        shapes.add(maine);
        shapes.add(maryland);
        shapes.add(massachusetts);
        shapes.add(michigan);
        shapes.add(minnesota);
        shapes.add(mississippi);
        shapes.add(missouri);
        shapes.add(montana);
        shapes.add(nebraska);
        shapes.add(nevada);
        shapes.add(newHampshire);
        shapes.add(newJersey);
        shapes.add(newMexico);
        shapes.add(newYork);
        shapes.add(northCarolina);
        shapes.add(northDakota);
        shapes.add(ohio);
        shapes.add(oklahoma);
        shapes.add(oregon);
        shapes.add(pennsylvania);
        shapes.add(rhodeIsland);
        shapes.add(southCarolina);
        shapes.add(southDakota);
        shapes.add(tennessee);
        shapes.add(texas);
        shapes.add(utah);
        shapes.add(vermont);
        shapes.add(virginia);
        shapes.add(washington);
        shapes.add(westVirginia);
        shapes.add(wisconsin);
        shapes.add(wyoming);

        System.out.println(shapes.size());
        System.out.println("Done");
        long time = System.currentTimeMillis();
        findColors(shapes.get(16), 1,shapes);
        System.out.println((System.currentTimeMillis()-time));
    }
    static boolean stillRunning = true;
    static String[] colors = {"red","blue","green","yellow"};
    public static void findColors(Shape s, int gen,ArrayList<Shape> shapes)
    {
        if (stillRunning)
        {
            //Check and find colors that are adjacent to current shape
            ArrayList<Shape> temp = s.getConnections();
            boolean[] isColored = new boolean[colors.length];
            Arrays.fill(isColored, false);
            for (Shape shape: temp)
            {
                for (int i = 0; i < isColored.length; i++)
                {
                    if (colors[i].equals(shape.getColor()))
                    {
                        isColored[i] = true;
                    }
                }
            }

            //Sets current shape to color not adjacent and continues to next shape recursively
            for (int i = 0; i < isColored.length; i++)
            {
                if (!isColored[i])
                {
                    s.setColor(colors[i]);
                    if (gen < shapes.size())
                    {
                        for (int j = 0; j < temp.size(); j++)
                        {
                            if (temp.get(j).getColor().equals("N/A"))
                            {
                                findColors(temp.get(j),gen+1,shapes);
                            }
                        }
                    }

                    else
                    {
                        for (Shape shape: shapes)
                        {
                            System.out.println(shape);
                        }
                        System.out.println("");
                        stillRunning = false;
                        return;
                    }
                    //If no color is currently valid, change color back to N/A and go back to last call;
                    s.setColor("N/A");
                }
            }
        }
    }
}
   