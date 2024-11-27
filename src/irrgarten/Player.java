/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;

import irrgarten.Weapon;
import irrgarten.Shield;
import irrgarten.Directions;
import irrgarten.Dice;
/**
 *
 * @author herna
 */
public class Player {
    static private int MAX_WEAPONS = 2;
    static private int MAX_SHIELDS = 3;
    static private int INITIAL_HEALTH = 10;
    static private int HITS2LOSE = 3;
    //++++++++++++++++++++//
    private String name;
    private char number;
    //++++++++++++++++++++//
    private float integillence;
    private float health;
    private float strenght;
    //++++++++++++++++++++//
    private int row;
    private int col;
    //++++++++++++++++++++//
    private int consecutivehits; //esto cambialo al constructor
    //+++++++++++++++++++++//
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    public Player(char number,float intelligence,float strenght){
        this.consecutivehits = 0;
        
        this.number= number;
        this.name = "Player#" + number;
        this.integillence=intelligence;
        this.strenght=strenght;
        
        this.health= Player.INITIAL_HEALTH;
        this.row=-1;
        this.col=-1;
        
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
    }
    
    public void Resurect(){
        if (Dice.resurrectPlayer())
            health=INITIAL_HEALTH;
    }
    public int getCol(){
        return col;
    }
    
    public int getRow(){
        return row;
    }
            
    public char GetNumber(){
        return number;
    }
    
    private void SetPos(int row, int col){
        if(row<0)
            this.row=-1;
        else
            this.row=row;
        if(col<0)
            this.col=-1;
        else
            this.col=col;
    }
    
    public boolean Dead(){
         return (health <= 0);
    }
    
    public Directions move(Directions direction,ArrayList<Directions> validMoves){
        int size= validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if(size > 0 && !contained){
            Directions firtsElement = validMoves.get(0);
            return(firtsElement);
        }else{
            return(direction);
        }
    }
    
    public float Attack(){
        float ataque=0.0f;
        
        ataque = this.strenght + this.sumWeapons();
        
        return ataque;
    }
    
    public boolean Defend(float recivedAttack){
        return ManageHit(recivedAttack);
    }
    
    public void ReciveReward(){
        int Wreward = Dice.weaponsReward();
        int Sreward = Dice.shieldsReward();
        
        for(int i=0 ; i<Wreward;i++){
            Weapon wnew = new Weapon(Dice.weaponPower(),Dice.usesLeft());
            this.ReciveWeapon(wnew);
        }
        for(int j=0 ; j<Sreward;j++){
            Shield snew = new Shield(Dice.shieldPower(),Dice.usesLeft());
            this.ReciveShield(snew);
        }
        
        float extrahealth = Dice.healthReward();
        this.health +=extrahealth;
        
    }
        
    public String To_String(){
        String msg;
        
        msg = name + '\n' + "integillence " +this.integillence + '\n';
        msg += "health " + this.health + '\n' + "streght " + this.strenght+ '\n';
        msg += "Row " + this.row + " Col " +this.col+'\n';
        msg+="Weapons: "+'\n';
        for(Weapon w: this.weapons){
            msg+=this.weapons.toString()+'\n';
        }
        msg+="Shields: "+'\n';
        for(Shield s: this.shields){
            msg+=this.shields.toString()+'\n';
        }
        
        return msg;
    }
    
    private void ReciveWeapon(Weapon w){
        for(int i= this.weapons.size()-1;i>=0;i--){
            Weapon wi = this.weapons.get(i);
            
            boolean discard = wi.discard();
            if(discard){
                weapons.remove(wi);
            }
        }
        int size = weapons.size();
            
        if(size < Player.MAX_WEAPONS)
            weapons.add(w);
    }
    
    private void ReciveShield(Shield s){
        for(int i= this.shields.size()-1;i>=0;i--){
            Shield si = this.shields.get(i);
            
            boolean discard = si.discard();
            if(discard){
                weapons.remove(si);
            }
        }
        int size = shields.size();
            
        if(size < Player.MAX_SHIELDS)
            shields.add(s);
    }
    
    private Weapon NewWeapon(){
        
        Weapon e = new Weapon (Dice.weaponPower(),Dice.usesLeft());
        return e;
    }
    
    private Shield NewShield(){
        Shield e = new Shield (Dice.shieldPower(),Dice.usesLeft());
        return e;
    }
    
    private float sumWeapons(){
        float sumatorio = 0.0f;
        
        for(Weapon w: this.weapons){
            sumatorio += w.attack();
        }
        return sumatorio;
    }
    
    private float sumShields(){
         float sumatorio = 0.0f;
        
        for(Shield s: this.shields){
            sumatorio += s.protect();
        }
        return sumatorio;
    }
    
    private float DefensiveEnergy(){
        float sumaInt=0.0f;
        
        sumaInt= this.integillence + this.sumShields();
        
        return sumaInt;
    }
    
    private boolean ManageHit(float ReciveAttack){
        boolean lose=false;
        float defense = this.DefensiveEnergy();
        if(defense < ReciveAttack){
            this.GotWounded();
            this.IncConsecutiveHits();
        }else{
            this.ResetHits();
        }
        
        if(this.consecutivehits == Player.HITS2LOSE || this.Dead()){
            lose=true;
        }else{
            lose=false;
        }
        return lose;
    }
    
    private void ResetHits(){
        this.consecutivehits=0;
    }
    
    private void GotWounded(){
         if(this.health > 0)
            this.health--;
    }
    
    private void IncConsecutiveHits(){
        
    }
}
