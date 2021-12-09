import java.util.Scanner;

public class Game {
    Map map = new Map();
    Mario mario = new Mario();
    int action, state, targetKOs;
    /*state keeps track of what the objective is
    0 = exit
    1 = KO enemies
    2 = find peach
    */

    public int getSelection()
    {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static int getRandomInteger(int lower, int upper) {
        int multiplier = upper - (lower - 1);
        return (int) (Math.floor(Math.random() * multiplier)) + lower;
    }


    public void start(){
        map.shuffleLocations();
        map.connectAll();
        map.setSpawns();
        map.setItemsAndPowerUps();
        setObjective();
        for(Location l : map.spawns){

            l.spawn();
            System.out.println("spawn:" +l.getName() + " has" + l.getNumKoopa() + " koopas" + l.getNumGoomba() + " goombas");

        }
        mario.setCurrentLocation(map.getRandomLocation());
        printObjective();
        for (Location l : map.locations){
            if(l.getPeachPresence()){
                System.out.println(l.getName());
            }
            if(l.getExitPresence()){
                System.out.println(l.getName());
            }
        }
    }
    public void mainGame(){
        while (true) {
            marioTurn();
            enemyTurn();
            if (checkGameOver() == true){
                return;
            }
        }
    }

    public void marioTurn(){
        mario.resetActions();
        System.out.println("-----------------");
        while(true) {
            if (mario.getActions() <= 0){
                System.out.println("End of turn");
                return;
            }
            System.out.println("You are at " + mario.getCurrentLocation().getName());
            mario.printStats();
            checkBowser();
            checkExitOrPeach();
            System.out.println("What would you like to do?");
            System.out.println("1:Move \n2:Search \n3:Use item\n4:Attack");
            action = getSelection();
            if (action == 1) {
                mario.move();
                if (state == 0 && checkEscape()){
                    System.exit(0);
                }
            }
            else if (action == 2){
                mario.search();
                if (state == 2 && checkFoundPeach() && mario.search()){
                    System.exit(0);
                }
            }
            else if (action == 3){
                mario.useItem();
            }
            else if (action == 4){
                mario.attack();
                if (state == 1 && checkKOs()){
                    System.exit(0);
                }
            }
            mario.decreaseActions();
        }

    }

    public void enemyTurn(){
        (mario.getCurrentLocation()).setPresence(true);
        System.out.println("mario is at " + mario.getCurrentLocation().getName());
        if (!mario.superStar) {
            for (int i = 0; i < mario.getCurrentLocation().getNumEnemies(); i++) {
                if (getRandomInteger(0, 10) <= 8) {
                    mario.wound();
                }
            }
        }
        for (Location l : map.locations){
            if (l.getNumEnemies() > 0 && (mario.getCurrentLocation() != l)){
                l.trasnferEnemies(l.getClosest());

            }
        }
        for(Location l : map.spawns){
            l.spawn();
        }
    }

    public void setObjective(){
        int chance = getRandomInteger(0,2);
        if (chance == 0){
            map.setExit();
            state = 0;
        }
        if (chance == 1){
            targetKOs = getRandomInteger(10,20);
            state = 1;
        }
        if (chance == 2){
            map.setPeachLocation();
            state = 2;
        }
    }


    public void checkExitOrPeach(){
        if (mario.getCurrentLocation().getPeachPresence()){
            System.out.println("Peach is in the area!");
        }
        else if (mario.getCurrentLocation().getExitPresence()){
            System.out.println("The exit is nearby!");
        }
    }
    public void checkBowser(){
        if (mario.getCurrentLocation().getBowserPresence()){
            System.out.println("Bowser is in the area! Use a bobomb to defeat him.");
        }
        else{
            System.out.println("boweser is not here");
        }
    }
    public boolean checkKOs(){
        if (mario.numKOs >= targetKOs){
            System.out.println("Mario has defeated enough enemies!");
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkFoundPeach(){
        if(mario.currentLocation.getPeachPresence()){
            System.out.println("Mario has found Peach!");
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkEscape(){
        if (mario.currentLocation.getName() == "Exit"){
            System.out.println("Mario has found the exit and escaped!");
            return true;
        }
        else{
            return false;
        }

    }
    public boolean checkGameOver(){
        if (mario.getLives() <= 0){
            System.out.println("Mario has run out of lives! Game Over.");
            return true;
        }
        else{
            return false;
        }

    }
    public void printObjective(){
        if (state == 0){
            System.out.println("Find the exit!");
        }
        else if (state == 1){
            System.out.println("Bowser has invaded the Odyssey kingdoms! Defeat " + targetKOs + " enemies!");
        }
        else if (state == 2){
            System.out.println("Peach has been captured by Bowser! Find her in one of the worlds.");
        }
    }
    public Game(){
    }
}
