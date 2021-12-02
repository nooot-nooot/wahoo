public class Mario{
    PowerUp currentPowerUp = new PowerUp("redShell", 1, 6, 1);
    Location currentLocation;
    public Mario(){

    }
    public Location getCurrentLocation(){
        return currentLocation;
    }
    public PowerUp getCurrentPowerUp(){
        return currentPowerUp;
    }
    public void setCurrentLocation(Location l){
        currentLocation = l;
    }
    public void setCurrentPowerUp(PowerUp p){
        currentPowerUp = p;
    }

}