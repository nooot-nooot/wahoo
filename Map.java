import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.*;


public class Map {

    // initalizing all allLocation
    Location NewDonkCity = new Location("NewDonkCity");
    Location WoodedKingdom = new Location("WoodedKingdom");
    Location CapKingdom = new Location("CapKingdom");
    Location CloudKingdom = new Location("CloudKingdom");
    Location SnowKingdom = new Location("SnowKingdom");
    Location SeasideKingdom = new Location("SeasideKingdom");
    Location LakeKingdom = new Location("LakeKingdom");
    Location SandKingdom = new Location("SandKingdom ");
    Location RuinedKingdom = new Location("RuinedKingdom");
    Location MushroomKingdom = new Location("MushroomKingdom");
    Location ForgotenIsle = new Location("ForgotenIsle");
    Location HoneyluneRidge = new Location("HoneyluneRidge");
    Location SteamGardens = new Location("SteamGardens");
    Location Crumbleden = new Location("Crumbleden");
    Location Shiveria = new Location("Shiveria");
    Location Tostarena = new Location("Tostarena");
    Location DeepWoods = new Location("DeepWoods");
    Location BowsersCastle = new Location("BowsersCastle");
    Location Bubblaine = new Location("Bubblaine");
    Location Bonneton = new Location("Bonneton");
    Location Exit = new Location("Exit");
    //initalizing all items
    Item greenShell = new Item("greenShell", 2, 3, 1);
    Item blueShell = new Item("blueShell", 1, 4, 2);
    Item hammer = new Item("hammer", 5, 5, 2);
    /*
    Buffed version:
    Item greenShell = new Item("greenShell", 5, 3, 1);
    Item blueShell = new Item("blueShell", 6, 4, 2);
    Item hammer = new Item("hammer", 10, 5, 2);

     */
    //initalizing all powerUps
    PowerUp oneUp = new PowerUp("oneUp");
    PowerUp superStar = new PowerUp("superStar");
    PowerUp superMushroom = new PowerUp("superMushroom");
    PowerUp bulletBill = new PowerUp("bulletBill");
    PowerUp bobomb = new PowerUp("bobomb");
    //spawns set
    Set<Location> spawns = new HashSet();

    //item array to pick random one from
    ArrayList<Item> allItems = new ArrayList<Item>();

    //powerup array to pick random one from
    ArrayList<PowerUp> allPowerUps = new ArrayList<PowerUp>();

    // inital list of random locations
    // sorted later
    ArrayList<Location> locations = new ArrayList<Location>();
    // rest of lists are index based | cloneing = >:^(

    // used to keep track of which locations are next
    ArrayList<Integer> currentIteration = new ArrayList<Integer>();
    //yep

    // what locations can be connected to
    ArrayList<Integer> avilableLocations = new ArrayList<Integer>();

    public void setPeachLocation(){
        getRandomLocation().setPeachPresent();
    }
    public void setExit(){
        Location exitLocation = getRandomLocation();
        exitLocation.addConnection(Exit);
        exitLocation.setAsExit();

    }
    public void setSpawns(){
        for (int i = 0; i < 3; i++){
            Location currentIterationLocation = getRandomLocation();
            while (!spawns.add(currentIterationLocation)){
                currentIterationLocation = getRandomLocation();
            }

        }

    }

    public void setItemsAndPowerUps(){
        for (Location l : locations){
            int chance = Game.getRandomInteger(1,10);
            if (chance <= 4){
                l.setItem(allItems.get(Game.getRandomInteger(0,2)));
            }
            else if (chance > 4 && chance <= 9){
                l.setPowerUp(allPowerUps.get(Game.getRandomInteger(0,4)));

            }
            else if (chance == 10){

            }
        }
    }

/*
function to connect everything
dont like having to check for avilableLocations empty twice
otherwise not bad
actually its pretty bad since the map looks pretty similar each time
*/

    int x;
    public void connectAll() {
        x = Game.getRandomInteger(1, 3);
        int firstLocation = Game.getRandomInteger(0, 19);
        avilableLocations.remove(firstLocation);
        connect(locations.get(firstLocation));
        // dont need the break here, can just have the break in connect, but need it to
        // break out of both functions can it do that?
        // is doing like this better than a for loop?
        int i = 0;
        while (true) {
            if (avilableLocations.size() == 0 || avilableLocations == null) {
                break;
            }
            x = Game.getRandomInteger(1, 2);
            connect(locations.get(currentIteration.get(i)));
            i++;
        }
    }
    public void connect(Location l) {

        for (int i = 0; i < x; i++) {
            if (avilableLocations.size() == 0) {
                return;
            }
            Location conectee = locations.get(avilableLocations.get(Game.getRandomInteger(0, avilableLocations.size() - 1))); // yep
            l.addConnection(conectee);
            conectee.addConnection(l);
            currentIteration.add(locations.indexOf(conectee));
            avilableLocations.remove(avilableLocations.indexOf(locations.indexOf(conectee)));
        }

    }

    public Location getRandomLocation(){
        return (locations.get(Game.getRandomInteger(0,19)));

    }
    public void shuffleLocations() {
        Collections.shuffle(locations);
    }

    public Map() {
        //avilable locations array
        for (int i = 0; i < 20; i++) {
            avilableLocations.add(i);
        }
        //items array
        allItems.add(hammer);
        allItems.add(blueShell);
        allItems.add(greenShell);

        //powerup array
        allPowerUps.add(oneUp);
        allPowerUps.add(superMushroom);
        allPowerUps.add(superStar);
        allPowerUps.add(bulletBill);
        allPowerUps.add(bobomb);

        // locations array
        locations.add(NewDonkCity);
        locations.add(WoodedKingdom);
        locations.add(CapKingdom);
        locations.add(CloudKingdom);
        locations.add(SnowKingdom);
        locations.add(SeasideKingdom);
        locations.add(LakeKingdom);
        locations.add(SandKingdom);
        locations.add(RuinedKingdom);
        locations.add(MushroomKingdom);
        locations.add(ForgotenIsle);
        locations.add(HoneyluneRidge);
        locations.add(SteamGardens);
        locations.add(Crumbleden);
        locations.add(Shiveria);
        locations.add(Tostarena);
        locations.add(DeepWoods);
        locations.add(BowsersCastle);
        locations.add(Bubblaine);
        locations.add(Bonneton);
    }


}
