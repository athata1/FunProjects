public class Gem {
    int number;
    String type;
    boolean isAttack;
    boolean isHp;
    boolean isDefense;
    public Gem (int number, String s)
    {
        this.number = number;
        this.type = s;
        isAttack = type.toLowerCase().equals("attack");
        isHp = type.toLowerCase().equals("hp");
        isDefense = type.toLowerCase().equals("defense");
    }
    public Gem(Gem gem)
    {
        this.number = gem.number;
        this.type = gem.type;
        this.isAttack = gem.isAttack;
        this.isHp = gem.isHp;
        this.isDefense = gem.isDefense;
    }
    public String toString()
    {
        return String.format("Number: %s,type: %s\n", number,type);
    }
}
