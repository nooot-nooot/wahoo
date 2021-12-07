public class Item {
    String name;
    int numRolls, damage, numHit;

    public static int getRandomInteger(int lower, int upper) {
        int multiplier = upper - (lower - 1);
        return (int) (Math.floor(Math.random() * multiplier)) + lower;
    }

    public String getName(){
        return name;
    }
    public int getDamage(){
        return damage;
    }
    public Item(String varName, int varNumRolls, int varNumHit, int varDamage){
        name = varName;
        numRolls = varNumRolls;
        damage = varDamage;
        numHit = varNumHit;
    }
    public int rollDice(){
        return getRandomInteger(1,6);
    }
    public int checkHits(){
        int totalDamage = 0;
        for (int i = 0; i < numRolls; i++){
            if (rollDice() == numHit){
                totalDamage++;
            }
            else{
                ;
            }
        }
        return totalDamage;

    }
}