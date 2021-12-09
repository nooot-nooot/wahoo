import java.util.Scanner;
import java.util.ArrayList;
public class Mario{
    Item currentItem = new Item("redShell", 100, 1, 2);
    /*
    buffed version:
    Item currentItem = new Item("redShell", 5, 6, 1);
     */
    ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
    Location currentLocation;
    boolean superStar = false;
    int decision, lives = 999999999, actions = 3, numKOs;
    public Mario(){

    }
    public void move(){
        System.out.println("there are " + currentLocation.getNumEnemies() + " enemies around. This travel will cost Mario " + (currentLocation.getNumEnemies() + 1) + " Actions" );
        currentLocation.printConnections();
        if (currentLocation.getNumEnemies() + 1 > actions){
            System.out.println("Mario does not have enough Actions");
            increaseActions();
            return;
        }
        System.out.println("Would you still like to move? \n 1:yes \n 2:no");
        decision = getSelection();
        if (decision == 1){

        }
        else if (decision == 2){
            increaseActions();
            return;
        }
        System.out.println("Where would you like to move?");
        //change to a getter instead of direct access for the array
        for (int i = 1; i <= (currentLocation.connections).size(); i++){
            System.out.println(i + ":" + currentLocation.connections.get(i-1).getName()); //man
        }
        decision = getSelection();
        actions -= currentLocation.getNumEnemies();
        currentLocation = currentLocation.connections.get(decision - 1);
    }

    public void bulletBillMove(){
        System.out.println("Where would Mario like to move?");
        //change to a getter instead of direct access for the array
        for (int i = 1; i <= (currentLocation.connections).size(); i++){
            System.out.println(i + ":" + currentLocation.connections.get(i-1).getName()); //man
        }
        decision = getSelection();
        currentLocation = currentLocation.connections.get(decision - 1);
    }

    public void attack(){
        int numHits = currentItem.checkHits();
        System.out.println("Mario hit "+ numHits + " times");
        if (numHits > currentLocation.getNumEnemies()){
            numKOs += currentLocation.getNumEnemies();
        }
        else{
            numKOs += numHits;
        }
        if (currentItem.getDamage() == 2){
            if (currentLocation.getNumKoopa() < numHits){
                // currentLocation.hitNumGoomba(currentLocation.getnNumGoomba); does not work for some reason
                if(currentLocation.getNumGoomba() < (numHits - currentLocation.getNumKoopa())){
                    currentLocation.setNumGoomba(0);
                }
                else{
                    currentLocation.hitGoomba(numHits - currentLocation.getNumKoopa());
                }
                currentLocation.setNumKoopa(0);
            }
            else{
                currentLocation.hitKoopa(numHits);
            }

        }
        else if (currentItem.getDamage() == 1){
            if (currentLocation.getNumGoomba() > numHits){
                currentLocation.setNumGoomba(0);
            }
            else{
                currentLocation.hitGoomba(numHits);
            }
        }

        System.out.println("There are now " + currentLocation.getNumGoomba() + " goombas left and " + currentLocation.getNumKoopa() + " koopas left");
    }

    public void useItem(){
        if (powerUps.size() == 0){
            System.out.println("Mario have no powerUps");
            increaseActions();
            return;
        }
        System.out.println("What powerup would Mario like to use?");
        for (int i = 1; i <= powerUps.size(); i++){
            System.out.println(i + ":" + powerUps.get(i-1).getName());
        }
        decision = getSelection();
        PowerUp usePowerUp = powerUps.get(decision - 1);
        if (usePowerUp.getName().equals("oneUp")){
            resetHealth();
        }
        else if (usePowerUp.getName().equals("superStar")){
            superStar = true;
        }
        else if (usePowerUp.getName().equals("superMushroom")){
            //twice because otherwise it just does nothing becauase it costs a move to use an item
            increaseActions();
            increaseActions();
        }
        else if (usePowerUp.getName().equals("bulletBill")){
            bulletBillMove();
        }
        else if (usePowerUp.getName().equals("bobomb")){
            numKOs += currentLocation.getNumEnemies();
            currentLocation.bobomb();
            if (currentLocation.getBowserPresence()){
                currentLocation.defeatBowser();
                numKOs++;
            }


        }
        powerUps.remove(usePowerUp);
    }

    public boolean search(){
        int decision;
        if (currentLocation.getNumEnemies() > 0){
            System.out.println("There are enemies around, Mario can not search");
            actions++;
            return false;
        }
        if (currentLocation.getSearched()){
            System.out.println("This area has already been searched");
            actions++;
            return false;
        }
        if (currentLocation.searchPowerUp() != null){
            System.out.println("Mario found a " + currentLocation.searchPowerUp().getName() + " \n would you like to take it? \n 1:Yes \n 2:No");
            decision = getSelection();
            if (decision == 1){
                System.out.println("Mario has taken the powerup");
                powerUps.add(currentLocation.searchPowerUp());
                currentLocation.setPowerUp(null);
            }
            else{
                System.out.println("Mario has not taken the powerUp");
            }
        }
        else if (currentLocation.searchItem() != null){
            System.out.println("Mario have found a " + currentLocation.searchItem().getName() + " \n would you like to take it? \n 1:Yes \n 2:No");
            decision = getSelection();
            if (decision == 1){
                System.out.println("Mario has taken the item");
                currentItem = currentLocation.searchItem();
                currentLocation.setItem(null);
            }
            else{
                System.out.println("Mario has not taken the item");
            }
        }
        else{
            System.out.println("There are no items or powerups nothing here");
        }
        currentLocation.setSearched();
        return true;
    }


    public void printStats(){
        System.out.println("Mario have " + lives + " lives left");
        System.out.println("Mario have " + actions + " Actions left");
        System.out.println("Mario has " + currentItem.getName() + " as his item");
        System.out.println("There are " + currentLocation.getNumGoomba() + " goombas around." );
        System.out.println("There are " + currentLocation.getNumKoopa() + " koopas around." );
        System.out.println("Mario has KOed " + numKOs + " enemies");
        if (powerUps.size() > 0) {
            System.out.print("In Mario's powerup inventory he has: ");
            for (PowerUp p : powerUps) {
                System.out.print(p.getName() + " ");
            }
            System.out.println();
        }
        else{
            System.out.println("Mario has no powerups in his inventory");
        }

    }
    public void resetActions(){
        actions = 3;
    }
    public int getActions(){
        return actions;
    }
    public void increaseActions(){
        actions++;
    }
    public void decreaseActions(){
        actions--;
    }
    public int getLives(){
        return lives;
    }
    public void wound(){
        lives--;
    }
    public void heal() {lives++;}
    public void resetHealth(){
        lives = 3;
    }

    public int getSelection()
    {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    public Location getCurrentLocation(){
        return currentLocation;
    }
    public void setCurrentLocation(Location l){
        currentLocation = l;
    }

}