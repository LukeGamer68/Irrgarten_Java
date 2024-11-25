/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import irrgarten.Dice;
/**
 *
 * @author herna
 */
public class Shield {
    
    private float protection;
    private int uses;
    
    public Shield(float protection,int uses){
        this.protection=protection;
        this.uses=uses;
    }
    
    public double protect(){
        if(uses>0){
            uses--;
            return protection;
        }else{
            return (0.0);
        }
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    @Override
    public String toString(){
        return "Proteccion es" + protection +" usos que quedan " +uses;
    }
}
