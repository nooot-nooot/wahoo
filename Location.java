import java.util.ArrayList;
public class Location{
    private int numGoomba, numKoopa, tier;
    public String name;
    private boolean space = true;
    ArrayList<Location> connections = new ArrayList<Location>();
    ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
    // ArrayList<int>

    /*
    public void updateSpace(){
      if (connections.size() >= 2){
      space = false;
      }
    }
    */
    public void setTier(int tierNum){
        tier = tierNum;
    }

    public void printConnections(){
        System.out.println(name + " is connected to:");
        for(Location i : connections){
            System.out.print(i.getName()+ " ");
        }
        System.out.println();
        System.out.println("----------------");
    }

    public void addConnection(Location l){
        connections.add(l);
    }
    public int getSpace(){
        return 3 - connections.size();
    }
    /*
    public boolean getSpace(){
      return space;
    }
    */
    public String getName(){
        return name;
    }
    public Location(String nameVar){
        name = nameVar;

    }




}  