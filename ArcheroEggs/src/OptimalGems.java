import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OptimalGems {
    public static void main(String[] args) {

        for (int i = 0; i < 20; i++)
        {
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(new Item(true, false, false));
            items.add(new Item(false,true,false));
            items.add(new Item(false,false,true));
            items.add(new Item(false,false,true));
            items.add(new Item(true,false,false));
            items.add(new Item(false,true,false));
            items.add(new Item(false,true,true));
            items.add(new Item(true,false,true));
            items.add(new Item(true,true,false));

            ArrayList<Gem> gems = new ArrayList<Gem>();
            gems.add(new Gem(1,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(2,"attack"));
            gems.add(new Gem(3,"attack"));
            gems.add(new Gem(3,"attack"));
            gems.add(new Gem(3,"attack"));

            gems.add(new Gem(1,"hp"));
            gems.add(new Gem(1,"hp"));
            gems.add(new Gem(2,"hp"));
            gems.add(new Gem(2,"hp"));
            gems.add(new Gem(2,"hp"));
            gems.add(new Gem(3,"hp"));
            gems.add(new Gem(3,"hp"));
            gems.add(new Gem(3,"hp"));
            gems.add(new Gem(3,"hp"));

            gems.add(new Gem(1,"defense"));
            gems.add(new Gem(1,"defense"));
            gems.add(new Gem(2,"defense"));
            gems.add(new Gem(2,"defense"));
            gems.add(new Gem(2,"defense"));
            gems.add(new Gem(3,"defense"));
            gems.add(new Gem(3,"defense"));
            gems.add(new Gem(4,"defense"));
            gems.add(new Gem(4,"defense"));

            /*Collections.sort(gems, new Comparator<Gem>() {
                @Override
                public int compare(Gem o1, Gem o2) {
                    return o2.number - o1.number;
                }
            });*/

            Collections.shuffle(gems);

            /*Collections.sort(gems, new Comparator<Gem>() {
                @Override
                public int compare(Gem o1, Gem o2) {
                    return o1.number - o2.number;
                }
            });*/
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Item> optimizedGems = determineBestGemLoadout(items, gems);
                }
            });
            th.start();
        }
    }
    public static Object gate = new Object();
    public static ArrayList<Item> output;
    public static int currentHighestVal;
    public static ArrayList<Item> determineBestGemLoadout(ArrayList<Item> items, ArrayList<Gem> gems)
    {
        currentHighestVal = Integer.MIN_VALUE;
        output = new ArrayList<Item>();
        helper(items,gems);
        return output;
    }
    public static void helper(ArrayList<Item> items, ArrayList<Gem> gems)
    {
        boolean addedGem = false;
        for (int i = 0; i < items.size(); i++)
        {
            Item currentItem = items.get(i);
            for (int j = 0; j < gems.size(); j++)
            {
                Gem currentGem = gems.get(j);
                if (currentItem.canPutGem(currentGem))
                {
                    currentItem.addGem(currentGem);
                    gems.remove(j);
                    addedGem = true;
                    helper(items,gems);
                    currentItem.removeGem();
                    gems.add(j,currentGem);
                }
            }
        }
        if (!addedGem)
        {
            int value = calculateItemListValue(items);
            synchronized (gate) {
                if (value > currentHighestVal) {
                    currentHighestVal = value;
                    printArray(items, value);
                    output = getCopiedArray(items);
                }
            }
        }
    }
    public static ArrayList<Item> getCopiedArray(ArrayList<Item> array)
    {
        ArrayList<Item> copiedArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i++)
        {
            copiedArray.add(new Item(array.get(i)));
        }
        return copiedArray;
    }
    public static int calculateItemListValue(ArrayList<Item> items)
    {
        double value = 0;
        double value2 = 0;
        for (Item item: items)
        {
            int totalItemValue = 0;
            for(Gem gem: item.gems)
            {
                totalItemValue += gem.number;
                value2 += gem.number * 1.63;
            }
            int heuristic = 0;
            if (totalItemValue >= 28)
                heuristic = 28;
            else if (totalItemValue >= 16)
                heuristic = 16;
            else if (totalItemValue >= 8)
                heuristic = 8;
            else if (totalItemValue >= 4)
                heuristic = 4;
            value += (int)Math.pow(1.0*heuristic/4,2);
        }
        return (int)value + (int)value2;
    }
    public static synchronized void printArray(ArrayList<Item> items,int value)
    {
        System.out.println("________________________");
        System.out.println("Val = " + value);
        for (int i = 0; i < items.size(); i++)
        {
            System.out.println("Item " + (i+1));
            System.out.println(items.get(i));
        }
        System.out.println("________________________");
    }
}
