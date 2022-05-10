import java.util.ArrayList;

public class Item {
    boolean isAttack;
    boolean isHp;
    boolean isDefense;
    ArrayList<Gem> gems;
    public Item(boolean isAttack, boolean isHp, boolean isDefense) {
        this.isHp = isHp;
        this.isAttack = isAttack;
        this.isDefense = isDefense;
        gems = new ArrayList<Gem>();
    }
    public Item(Item item)
    {
        this.isAttack = item.isAttack;
        this.isDefense = item.isDefense;
        this.isHp = item.isHp;
        this.gems = new ArrayList<>();
        for (int i = 0; i < item.gems.size(); i++)
        {
            gems.add(new Gem(item.gems.get(i)));
        }
    }
    boolean canPutGem(Gem gem)
    {
        if (gems.size() == 3)
            return false;
        if (gem.isAttack && !this.isAttack)
            return false;
        else if (gem.isHp && !this.isHp)
            return false;
        else if (gem.isDefense && !this.isDefense)
            return false;
        return true;
    }
    public void addGem(Gem gem)
    {
        gems.add(gem);
    }
    public void removeGem()
    {
        if (gems.size() > 0)
        {
            gems.remove(gems.size() - 1);
        }
        else
        {
            System.out.println("Error: Attempted to remove gem that doesn't exist");
        }
    }
    public String toString()
    {
        String s = "";
        for (int i = 0; i < gems.size(); i++)
        {
            s += gems.get(i).toString();
        }
        return s;
    }
}
