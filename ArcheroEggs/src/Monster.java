public class Monster {
    private String name;
    private String time;
    public Monster(String name, String time)
    {
        this.name = name;
        this.time = time;
    }

    public String toString()
    {
        return String.format("%s: %s", name, time);
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
