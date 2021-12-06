public class PowerUp{
    private String name;
    public PowerUp(String n){
        name = n;

    }
    public String getName(){
        return name;
    }
    public void use(){
        if (name.equals("oneUp")){
            //mario.addHealth();
        }
        else if (name.equals("superStar")){
            //mario.nexthitnocount(); idk
        }
        else if (name.equals("superMushroom")){
            //mario.addAction();
        }
        else if (name.equals("bulletBill")){
            //mario.move(); idk
        }
        else if (name.equals("bobomb")){
            //mario.clearscreen(); idk
        }

    }
}