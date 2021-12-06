import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.*;

public class Location{
    private int numGoomba, numKoopa;
    public String name;
    private boolean space = true;
    private boolean marioPresence = false;
    ArrayList<Location> connections = new ArrayList<Location>();
    Item item = new Item(null, 0, 0, 0);
    PowerUp powerUp = new PowerUp(null);


    /*
    public void updateSpace(){
      if (connections.size() >= 2){
      space = false;
      }
    }
    */

    public Item SearchItem(){
        return item;
    }
    public PowerUp SearchPowerUp(int type){
        return powerUp;
    }
    public void addItem(Item i){
        item = i;
    }
    public void addPowerUp(PowerUp p){
        powerUp = p;
    }
    public void setPresence(boolean x){
        marioPresence = x;
    }
    public boolean getPresence(){
        return marioPresence;
    }
    public int getDistance(){
        int distance = 0;
        ArrayList<Location> currentIteration = new ArrayList<Location>();
        for (Location l : connections){
            distance++;
            if (l.getPresence()) {
                return distance;
            }

            currentIteration.add(l);



        }

        int i = 0;
        while (true){
            distance++;


            for (Location l : currentIteration.get(i).connections){
                if (l.getPresence()){

                    return distance;

                }

                if (!currentIteration.contains(l)){
                    currentIteration.add(l);

                }





            }
            i++;
        }


    }
    public Location getClosest(){
        ArrayList<Integer> distances = new ArrayList<Integer>();
        for (Location l : connections){
            distances.add(l.getDistance());
        }
        return connections.get(distances.indexOf(Collections.min(distances))); //wow another one of these



    }
    public void trasnferEnemies(Location transferee){
        transferee.addKoopa(numKoopa);
        transferee.addGoomba(numGoomba);
        numGoomba = 0;
        numKoopa = 0;

    }
    public void spawn(){
        numGoomba = Game.getRandomInteger(0,5);
        if (numGoomba != 0){
            numKoopa = Game.getRandomInteger(0,1);
            numGoomba -= numKoopa;
        }

    }
    public int getNumEnemies(){
        return numKoopa + numGoomba;
    }

    public void addKoopa(int n){
        numKoopa += n;
    }
    public void addGoomba(int n){
        numGoomba += n;
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


    public String getName(){
        return name;
    }

    public Location(String nameVar){
        name = nameVar;


    }




}  