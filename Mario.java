import java.util.Scanner;

public class Mario{
    Item currentItem = new Item("redShell", 1, 6, 1);
    PowerUp currentPowerUp = new PowerUp("null");
    Location currentLocation;
    int action,numWounds;
    public Mario(){

    }
    public void wound(){
        numWounds++;
    }
    public void move(){
        currentLocation.printConnections();
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
    public PowerUp getCurrentPowerUp(){
        return currentPowerUp;
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