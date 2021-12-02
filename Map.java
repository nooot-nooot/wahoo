import java.util.ArrayList;
import java.util.Collections;

public class Map {
    public static int getRandomInteger(int lower, int upper) {
        int multiplier = upper - (lower - 1);
        return (int) (Math.floor(Math.random() * multiplier)) + lower;
    }

    // tiering sytem
    // as the player moves up the tiers, an indicator shows them getting closer to
    // bowser
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
    //initalizing all power ups
    Item bobomb = new Item("bobomb", 2, 3, 1);
    Item greenShell = new Item("greenShell", 1, 4, 2);
    Item blueShell = new Item("blueShell", 5, 5, 2);

    //powerup array to pick random one from
    ArrayList<Item> items = new ArrayList<Item>();
    // inital list of random locations
    // sorted later
    ArrayList<Location> locations = new ArrayList<Location>();
    // rest of lists are index based | cloneing = >:^(

    // used to keep track of which locations are next
    ArrayList<Integer> currentIteration = new ArrayList<Integer>();
    //yep

    // what locations can be connected to
    ArrayList<Integer> avilableLocations = new ArrayList<Integer>();


/*
function to connect everything
dont like having to check for avilableLocations empty twice
otherwise not bad
actually its pretty bad since the map looks pretty similar each time
*/

    int x = getRandomInteger(1, 3);
    public void connectAll() {
        int firstLocation = getRandomInteger(0, 19);
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
            x = getRandomInteger(1, 2);
            connect(locations.get(currentIteration.get(i)));
            i++;
        }
    }
    public void connect(Location l) {
        for (int i = 0; i < x; i++) {
            if (avilableLocations.size() == 0) {
                return;
            }
            Location conectee = locations.get(avilableLocations.get(getRandomInteger(0, avilableLocations.size() - 1))); // yep
            l.addConnection(conectee);
            conectee.addConnection(l);
            currentIteration.add(locations.indexOf(conectee));
            avilableLocations.remove(avilableLocations.indexOf(locations.indexOf(conectee)));
        }

    l.printConnections();
    for (int i : avilableLocations) {
      System.out.print(i + " ");
    }
    System.out.println();

    }



    public Location getRandomLocation(){
        return (locations.get(getRandomInteger(0,19)));

    }
    public void shuffleLocations() {
        Collections.shuffle(locations);
    }

    public Map() {
        for (int i = 0; i < 20; i++) {
            avilableLocations.add(i);
        }
        //powerUps array

        items.add(bobomb);
        items.add(blueShell);
        items.add(greenShell);




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

    /*
     * int finalConnections[][] = new int[20][20]; public void declareInitalArray(){
     * for(int i = 0; i < 20; i++){ for(int j = 0; j < 20; j++){
     * finalConnections[i][j] = 0; } } }
     *
     *
     *
     * /* for (Location l : locations){ int index = getRandomInteger(0,19); Location
     * j = locations.get(index); while (locations.indexOf(l) == index){ index =
     * getRandomInteger(0,19); j = locations.get(index); }
     *
     * if (j.getSpace() > 0){ j.addConnection(l); l.addConnection(j);
     *
     *
     * } }
     *
     *
     */

    /*
     * for (Location l : locations){ for (Location l2 : locations){ if(l2.getSpace()
     * > 0){ l.addConnection(l2); l2.addConnection(l);
     *
     * } if(l.getSpace() <= 0){ break; } } System.out.println("yep"); }
     */

    /*
     * for(Location i : locations){ for(Location j : locations){ while
     * (i.getSpace()){ if(j.getSpace() && (locations.indexOf(i) !=
     * locations.indexOf(j))){ j.addConnection(i); i.addConnection(j);
     * j.updateSpace(); i.updateSpace(); } }
     *
     * break; } }
     */
    /*
     * public void connect(){
     *
     *
     *
     *
     *
     * int randomInt = getRandomInteger(1,3); int row = 0; int x = 0; for (int i =
     * 0; i < randomInt; i++){ finalConnections[row][x] = 1;
     * finalConnections[x][row] = 1; x++; row++; } }
     */

}
