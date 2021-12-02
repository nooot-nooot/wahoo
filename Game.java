public class Game {
    Map map = new Map();
    Mario mario = new Mario();
    public Game(){

        map.shuffleLocations();
        map.connectAll();


    }
    public void start(){
        mario.setCurrentLocation(map.getRandomLocation());
        System.out.println("You are at " + (mario.getCurrentLocation()).getName());
        (mario.getCurrentLocation()).printConnections();

    }
}
