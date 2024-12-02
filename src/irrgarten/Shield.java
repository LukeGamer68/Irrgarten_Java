/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
/**
 *
 * @author herna
 */
public class Shield extends CombatElement{
    
    private float protection;
    private int uses;
    
     public Shield(float protection, int uses){
        super(protection,uses);
        this.protection = protection;
        this.uses = uses;
    }
    
    public float protect(){
        return produceEffect();
    }
    
    @Override
    public String toString(){
        return "S[Protection: "+ protection + ", uses: " + uses + "]";
    }
}
