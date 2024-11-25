
package irrgarten;

import java.util.Random;
import irrgarten.Dice;

/**
 * @author Hernan Gonzlaez
 */
public class Weapon {
    private float power;
    private int uses;
    static private Random generator = new Random();
    
    public Weapon(float power,int uses){
        this.power=power;
        this.uses=uses;
    }
    public float attack(){
        if(uses>0){
            uses--;
            return power;
        }else{
            return (0);
        }
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    @Override
    public String toString(){
        return "Poder es " + power +" usos que quedan " + uses;
    }
}
