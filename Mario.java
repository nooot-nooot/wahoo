public class Mario{
    Item currentItem = new Item("redShell", 1, 6, 1);
    PowerUp currentPowerUp = new PowerUp("null");
    Location currentLocation;
    public Mario(){

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