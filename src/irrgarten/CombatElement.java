/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package irrgarten;

public abstract class CombatElement {
    private float effect;
    private int uses;
    
    public CombatElement(float effect, int uses){
        this.effect = effect;
        this.uses  = uses;
    }
    protected float produceEffect(){
        if (this.uses > 0){
            this.uses--;
            return this.effect;
        } else{
            return 0.0f;
        }
    }
    public Boolean discard(){
        return Dice.discardElement(this.uses);
    }
    @Override
    public String toString(){
        return "Effect: " + this.effect + ", Uses: " + this.uses;
    }
}