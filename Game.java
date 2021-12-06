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
            System.out.println("spawn:" +l.getName());
            l.spawn();
        }
        mario.setCurrentLocation(map.getRandomLocation());
        /*
        for (Location l : map.locations){
            System.out.println(l.getName() + " is connected to:");
            for (Location j : l.connections){
                System.out.println(j.getName());

            }
            System.out.println("------------");
        }
        for (Location l : map.locations){
            System.out.println(l.getName() + " has this many enemies" +l.getNumEnemies());
        }

         */



        /*
        for (Location l : map.locations){
            System.out.println(l.getName() + " has this many enemies" +l.getNumEnemies());
        }

         */




    }
    public void mainGame(){
        while (true) {
            marioTurn();
            enemyTurn();
            if (mario.getLives() <= 0){
                gameOver();
                return;
            }
        }

    }
    public void marioTurn(){

        System.out.println("-----------------");
        while(true) {
            if (mario.getMoves() <= 0){
                System.out.println("End of turn");
                return;
            }

            System.out.println("You are at " + mario.getCurrentLocation().getName());
            mario.printStats();
            System.out.println("What would you like to do?");
            System.out.println("1:Move \n2:Search \n3:Use item\n4:Attack");
            action = getSelection();
            if (action == 1) {
                if (mario.getCurrentLocation().getNumEnemies() > 0) {
                    System.out.println("There are enemies around, you can not move");
                } else {
                    mario.move();

                }

            }
            mario.decreaseMoves();
        }

    }
    public void enemyTurn(){
        (mario.getCurrentLocation()).setPresence(true);
        System.out.println("mario is at " + mario.getCurrentLocation().getName());
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
    public void gameOver(){
        System.out.println("Mario has run out of lives! Game Over.");
    }
}
