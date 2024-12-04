/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;

/**
 *
 * @author herna
 */
public class Player extends LabyrinthCharacter{
    static private int MAX_WEAPONS = 2;
    static private int MAX_SHIELDS = 3;
    static private int INITIAL_HEALTH = 10;
    static private int HITS2LOSE = 3;

    private int consecutivehits;
    private char number;

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    public Player(char number,float intelligence,float strenght){
        super("Player # " + number,intelligence,strenght,INITIAL_HEALTH);
        this.number = number;
        this.consecutivehits=0;
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
    }
    public Player(Player other){
        super(other.getName(),other.getIntelligence(), other.getStrength(), other.getHealth());
    }
    public void resurrect(){
        weapons.clear();
        shields.clear();    
        setHealth(INITIAL_HEALTH);
        this.consecutivehits = 0; 
    }
    
     public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = false;
        
        for (Directions d: validMoves){
            if (d == direction){
                contained = true;
                break;
            }
        }
        if (size > 0 && !contained){
            return validMoves.get(0);
        } else {
            return direction;
        }
    }
    public char GetNumber(){
        return number;
    }
    @Override
    public float attack(){
       return getStrength() + sumWeapons();

    }
    @Override
    public boolean defend(float recivedAttack){
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
        setHealth(getHealth()+extrahealth);
        
    }
        

    @Override
    public String toString(){
        String msg= "Player " + super.toString(); 
        if(!weapons.isEmpty()){
            msg+="\nWeapons: " + weapons.toString()+" \nSum Weapons: " + sumWeapons();
        }
        if(!shields.isEmpty()){
            msg+="\nShields: " + shields.toString()+ " Sum Shields: " + sumShields();;
        }
        
        return msg;
              
    }
    
    private void ReciveWeapon(Weapon w){
        for (int i = 0; i < weapons.size(); i++){
            Weapon wi = weapons.get(i);
            boolean discard = wi.discard();
            if (discard){
                weapons.remove(i);
                i--;
            }
        }
        int size = weapons.size();
        if (size < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    private void ReciveShield(Shield s){
       for (int i = 0; i < shields.size(); i++){
            Shield si = shields.get(i);
            boolean discard = si.discard();
            if (discard){
                shields.remove(i);
                i--;
            }
        }
        int size = shields.size();
        if (size < MAX_SHIELDS){
            shields.add(s);
        }
    }
    
    private Weapon NewWeapon(){
        
        Weapon e = new Weapon (Dice.weaponPower(),Dice.usesLeft());
        return e;
    }
    
    private Shield NewShield(){
        Shield e = new Shield (Dice.shieldPower(),Dice.usesLeft());
        return e;
    }
    
    protected float sumWeapons(){
        float sumatorio = 0.0f;
        
        for(Weapon w: this.weapons){
            sumatorio += w.attack();
        }
        return sumatorio;
    }
    
    protected float sumShields(){
         float sumatorio = 0.0f;
        
        for(Shield s: this.shields){
            sumatorio += s.protect();
        }
        return sumatorio;
    }
    
    protected float DefensiveEnergy(){
        return getIntelligence() + sumShields();
    }
    
    private boolean ManageHit(float ReciveAttack){
         float defense = DefensiveEnergy();
        boolean lose;
        if (defense < ReciveAttack){
            gotWounded();
            IncConsecutiveHits();
        } else {
            resetHits();
        }
        if ((this.consecutivehits == HITS2LOSE) || dead()){
            resetHits();
            lose = true;
        } else{
            lose = false;
        }
        return lose;
    }
    
    private void resetHits(){
        this.consecutivehits=0;
    }
    
    private void IncConsecutiveHits(){
        this.consecutivehits++;
    }
}
