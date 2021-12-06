import java.util.Scanner;
import java.util.ArrayList;
public class Mario{
    Item currentItem = new Item("redShell", 1, 6, 1);
    ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
    Location currentLocation;
    int action, lives = 3, moves = 3;
    public Mario(){

    }

    public void printStats(){
        System.out.println("You have " + lives + " lives left");
        System.out.println("You have " + moves + " moves left");
        System.out.println("You have " + currentItem.getName() + " as your item");
        System.out.print("You have ");
        for (PowerUp p : powerUps){
            System.out.print(p.getName() + " ");
        }
        System.out.println("powerups in your inventory");
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
    public void move(){
        System.out.println("there are " + currentLocation.getNumEnemies() +" around. This move will cost you " + currentLocation.getNumEnemies() + " moves" );        currentLocation.printConnections();
        System.out.println("Would you still like to move? \n 1:yes \n 2:no");
        System.out.println("Where would you like to move?");
        //change to a getter instead of direct access for the array
        for (int i = 1; i <= (currentLocation.connections).size(); i++){
            System.out.println(i + ":" + currentLocation.connections.get(i-1).getName()); //man
        }
        action = getSelection();
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