import java.util.Scanner;
import java.util.ArrayList;
public class Mario{
    Item currentItem = new Item("redShell", 1, 6, 1);
    ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
    Location currentLocation;
    boolean superStar = false;
    int action, lives = 3, moves = 3, numKOs;
    public Mario(){

    }

    public void attack(){
        int numHits = currentItem.checkHits();
        System.out.println("You hit "+ numHits + " times");
        if (currentItem.getDamage() == 2){
            if (currentLocation.getNumKoopa() < numHits){
                currentLocation.hitKoopa(currentLocation.getNumKoopa());
                if(currentLocation.getNumGoomba() < (numHits - currentLocation.getNumKoopa())){
                    currentLocation.hitGoomba(currentLocation.getNumGoomba());
                }
                else{
                    currentLocation.hitGoomba(numHits - currentLocation.getNumKoopa());
                }
            }
            else{
                currentLocation.hitKoopa(numHits);
            }


        }
        if (numHits > currentLocation.getNumEnemies()){
            numKOs += currentLocation.getNumEnemies();
        }
        else{
            numKOs += numHits;
        }
        System.out.println("There are now " + currentLocation.getNumGoomba() + " goombas left and " + currentLocation.getNumKoopa() + " koopas left");
    }
    public void useItem(){
        if (powerUps.size() == 0){
            System.out.println("You have no powerUps");
            increaseMoves();
            return;
        }
        System.out.println("What powerup would you like to use?");
        for (int i = 1; i <= powerUps.size(); i++){
            System.out.println(i + ":" + powerUps.get(i-1).getName());
        }
        action = getSelection();
        PowerUp usePowerUp = powerUps.get(action - 1);
        if (usePowerUp.getName().equals("oneUp")){
            resetHealth();
        }
        else if (usePowerUp.getName().equals("superStar")){
            superStar = true;
        }
        else if (usePowerUp.getName().equals("superMushroom")){
            increaseMoves();
        }
        else if (usePowerUp.getName().equals("bulletBill")){
            bulletBillMove();
        }
        else if (usePowerUp.getName().equals("bobomb")){
            currentLocation.bobomb();
        }
        powerUps.remove(usePowerUp);
    }
    public void bulletBillMove(){
        System.out.println("Where would you like to move?");
        //change to a getter instead of direct access for the array
        for (int i = 1; i <= (currentLocation.connections).size(); i++){
            System.out.println(i + ":" + currentLocation.connections.get(i-1).getName()); //man
        }
        action = getSelection();
        currentLocation = currentLocation.connections.get(action - 1);
    }
    public void printStats(){
        System.out.println("You have " + lives + " lives left");
        System.out.println("You have " + moves + " moves left");
        System.out.println("You have " + currentItem.getName() + " as your item");
        System.out.println("There are " + currentLocation.getNumGoomba() + " goombas around." );
        System.out.println("There are " + currentLocation.getNumKoopa() + " koopas around." );
        System.out.println("You have KOed " + numKOs + " enemies");
        if (powerUps.size() > 0) {
            System.out.print("In your powerup inventory you have: ");
            for (PowerUp p : powerUps) {
                System.out.print(p.getName() + " ");
            }
            System.out.println();
        }
        else{
            System.out.println("You have no powerups in your inventory");
        }

    }
    public void resetMoves(){
        moves = 3;
    }
    public int getMoves(){
        return moves;
    }
    public void increaseMoves(){
        moves++;
    }
    public void decreaseMoves(){
        moves--;
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
    public void search(){
        if (currentLocation.searchPowerUp() != null){
            powerUps.add(currentLocation.searchPowerUp());
            System.out.println("You found a " + currentLocation.searchPowerUp().getName());
        }
        else if (currentLocation.searchItem() != null){
            currentItem = currentLocation.searchItem();
            System.out.println("You have found a " + currentLocation.searchItem().getName());
        }
        else{
            System.out.println("There is nothing here");
        }
    }
    public void move(){
        System.out.println("there are " + currentLocation.getNumEnemies() + " enemies around. This travel will cost you " + (currentLocation.getNumEnemies() + 1) + " moves" );        currentLocation.printConnections();
        if (currentLocation.getNumEnemies() + 1 > moves){
            System.out.println("You do not have enough moves");
            increaseMoves();
            return;
        }
        System.out.println("Would you still like to move? \n 1:yes \n 2:no");
        action = getSelection();
        if (action == 1){

        }
        else if (action == 2){
            increaseMoves();
            return;
        }
        System.out.println("Where would you like to move?");
        //change to a getter instead of direct access for the array
        for (int i = 1; i <= (currentLocation.connections).size(); i++){
            System.out.println(i + ":" + currentLocation.connections.get(i-1).getName()); //man
        }
        action = getSelection();
        moves -= currentLocation.getNumEnemies();
        currentLocation = currentLocation.connections.get(action - 1);
    }
    public int getSelection()
    {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    public Location getCurrentLocation(){
        return currentLocation;
    }

    public Item getCurrentItem(){
        return currentItem;
    }
    public void setCurrentLocation(Location l){
        currentLocation = l;
    }
    public void setCurrentPowerUp(Item p){
        currentItem = p;
    }

}