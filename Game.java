import java.util.Scanner;

public class Game {
    Map map = new Map();
    Mario mario = new Mario();
    int action;
    public int getSelection()
    {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static int getRandomInteger(int lower, int upper) {
        int multiplier = upper - (lower - 1);
        return (int) (Math.floor(Math.random() * multiplier)) + lower;
    }

    public Game(){

        map.shuffleLocations();
        map.connectAll();


    }
    public void start(){
        map.setSpawns();
        for(Location l : map.spawns){
            l.spawn();
        }
        mario.setCurrentLocation(map.getRandomLocation());
        System.out.println("You are at " + (mario.getCurrentLocation()).getName());
        (mario.getCurrentLocation()).printConnections();
        for (Location l : map.locations){
            System.out.println(l.getName() + " has this many enemies" +l.getNumEnemies());
        }
        marioTurn();
        enemyTurn();
        for (Location l : map.locations){
            System.out.println(l.getName() + " has this many enemies" +l.getNumEnemies());
        }


    }
    public void marioTurn(){
        System.out.println("-----------------");
        System.out.println("You are at " + mario.getCurrentLocation().getName());
        System.out.println("What would you like to do?");
        System.out.println("1:Move \n2:Search \n3:Use item\n4:Attack");
        action = getSelection();
        if (action == 1){
            mario.move();
        }

    }
    public void enemyTurn(){
        mario.getCurrentLocation().setPresence(true);
        for (int i = 0; i < mario.getCurrentLocation().getNumEnemies(); i++){
            if(getRandomInteger(0,10) <= 8){
                mario.wound();
            }
        }
        for (Location l : map.locations){
            if (l.getNumEnemies() > 0){
                l.trasnferEnemies(l.getClosest());

            }
        }

        for(Location l : map.spawns){
            l.spawn();
        }
    }
}
