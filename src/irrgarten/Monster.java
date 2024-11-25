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
public class Monster {
    static private int INITIAL_HEALTH = 5;
    //++++++++++++++++++++//
    private String name;
    //++++++++++++++++++++//
    private float integillence;
    private float health;
    private float strenght;
    //++++++++++++++++++++//
    private int row;
    private int col;
    //++++++++++++++++++++//
    
    public Monster(String name,float intelligence,float strenght){
        this.name = name;
        this.integillence=intelligence;
        this.strenght=strenght;
        
        this.health= Monster.INITIAL_HEALTH;
        this.row=-1;
        this.col=-1;
    }
    
    public boolean Dead(){
        return (health <= 0);
    }
    
    public float attack(){
        return(Dice.intensity(strenght));
    }
    
    public boolean Defend(float recivedAttack){
        boolean isDead=Dead();
        if(!isDead){
            float defensiveEnergy = Dice.intensity(this.integillence);
            if(defensiveEnergy < recivedAttack){
                GotWounded();
                isDead=Dead();
            }
        }    
        return isDead;
    }
    
    public void SetPos(int row, int col){
        if(row<0)
            this.row=-1;
        else
            this.row=row;
        if(col<0)
            this.col=-1;
        else
            this.col=col;
    }
    
    public String To_String(){
        String msg;
        
        msg = this.name + '\n' + "integillence " +this.integillence + '\n';
        msg += "health " + this.health + '\n' + "streght " + this.strenght+ '\n';
        msg += "Row " + this.row + " Col " +this.col;
        
        return msg;
    }
    
    private void GotWounded(){
        if(this.health > 0)
            this.health--;
    }
}
