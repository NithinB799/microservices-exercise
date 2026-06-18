class Computer{
    public void playMusic()
    {
        System.out.println("Music Playigng");
    }
    public String getMeApen(int cost)
    {
        if(cost>=10)
            return "Pen";
        else
            return "Nothing";
    }
}
public class Demo {
    static void main(String[] args) {
        Computer obj=new Computer();
        obj.playMusic();
        String str=obj.getMeApen(9);
        System.out.println(str);
    }
}
