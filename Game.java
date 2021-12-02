public class Game {
    Map map = new Map();
    Mario mario = new Mario();
    public Game(){

        map.shuffleLocations();
        map.connectAll();

    }
    public void start(){
        mario.setCurrentLocation(map.getRandomLocation());

    }
}
