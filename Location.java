import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.*;

public class Location{
    private int numGoomba, numKoopa;
    public String name;
    private boolean space = true;
    private boolean bowserPresence = false;
    private boolean marioPresence = false;
    private boolean peachPresence = false;
    private boolean exitPresence = false;
    private boolean searched = false;
    ArrayList<Location> connections = new ArrayList<Location>();
    Item item = null;
    PowerUp powerUp = null;

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

    public void spawn(){
        numGoomba += Game.getRandomInteger(0,5);
        if (numGoomba != 0){
            int koopaTemp = Game.getRandomInteger(0,1);
            numKoopa += koopaTemp;
            numGoomba -= koopaTemp;
        }
    }
    public void spawnBowser(){
        if (Game.getRandomInteger(1,100) <= 5){
            bowserPresence = true;
        }
    }

    public void addKoopa(int n){
        numKoopa += n;
    }
    public void addGoomba(int n){
        numGoomba += n;
    }
    public void hitKoopa(int hits){
        numKoopa -= hits;
    }
    public void hitGoomba(int hits){
        numGoomba -= hits;
    }

    public void bobomb(){
        numKoopa = 0;
        numGoomba = 0;
    }

    public void defeatBowser(){ bowserPresence = false;}
    public void setPeachPresent(){ peachPresence = true;}
    public void setNumGoomba(int num){ numGoomba = num;}
    public void setNumKoopa(int num){ numKoopa = num;}
    public void setSearched(){ searched = true;}
    public void setAsExit(){ exitPresence = true;}
    public void setPresence(boolean x){
        marioPresence = x;
    }
    public void setItem(Item i){
        item = i;
    }
    public void setPowerUp(PowerUp p){
        powerUp = p;
    }

    public int getNumEnemies(){
        if (bowserPresence){
            return numGoomba + numKoopa + 1;
        }
        return numKoopa + numGoomba;
    }

    public Item searchItem(){ return item;}
    public boolean getSearched(){ return searched;}
    public boolean getPeachPresence(){ return peachPresence;}
    public boolean getBowserPresence(){ return bowserPresence;}
    public boolean getExitPresence(){ return exitPresence;}
    public PowerUp searchPowerUp(){
        return powerUp;
    }
    public boolean getPresence(){
        return marioPresence;
    }
    public int getNumGoomba(){ return numGoomba;}
    public int getNumKoopa(){ return numKoopa;}
    public String getName(){
        return name;
    }


    public Location(String nameVar){
        name = nameVar;
        spawnBowser();


    }




}  