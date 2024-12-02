/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author herna
 */
public class LabyrinthCharacter {
    
    private String name;
    private float intelligence, strength, health;
    private int row, col;
    
    
    public LabyrinthCharacter(String name, float intelligence, float strength, float health){
       this.name = name;
       this.intelligence = intelligence;
       this.strength = strength;
       this.health = health;
    }
    public LabyrinthCharacter(LabyrinthCharacter other){
        this.name = other.name;
        this.intelligence = other.intelligence;
        this.strength = other.strength;
        this.health = other.health;
        this.row = other.row;
        this.col = other.col;
    }
    public Boolean dead (){
        return this.health <= 0;
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    protected float getIntelligence(){
        return this.intelligence;
    }
    protected float getStrength(){
        return this.strength;
    }
    protected float getHealth(){
        return this.health;
    }
    protected String getName(){
        return this.name;
    }
    protected void setHealth(float health){
        this.health = health;
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
    public String toString() {
           return "[Name: " + getName() + 
                  ", Intelligence: " + getIntelligence() + 
                  ", Strength: " + getStrength() + 
                  ", Health: " + getHealth() + 
                  ", Row: " + getRow() + 
                  ", Col: " + getCol() + "]";
    }
    protected void gotWounded(){
        this.health--;
    }
    public float attack(){
        return Dice.intensity(this.strength);
    }
       
    public boolean defend(float attack){
        boolean isDead=dead();
        if(!isDead){
            float defensiveEnergy = Dice.intensity(this.intelligence);
            if(defensiveEnergy < attack){
                gotWounded();
                isDead=dead();
            }
        }    
        return isDead;
    }
}

