/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Random;
/**
 *
 * @author herna
 */
public class Dice{
    static private float MAX_INTELLIGENCE = 10.0f;
    static private int MAX_USES =5;
    static private float MAX_STRENGTH = 10.0f;
    static private float RESURRECT_PROB = 0.3f;
    static private int WEAPONS_REWARD = 2;
    static private int SHIELDS_REWARD = 3;
    static private int HEALTH_REWARD = 5;
    static private int MAX_ATTACK = 3;
    static private int MAX_SHIELD = 2;

    static private Random generator = new Random();
    
    static public float RandomIntelligence(){
        
        return generator.nextFloat() * Dice.MAX_INTELLIGENCE;
    }
    static public float RandomStrength(){
        
        return generator.nextFloat() * Dice.MAX_STRENGTH;
    }
    static public int usesLeft(){
        return generator.nextInt(Dice.MAX_USES +1);
    }
    
    static public int randomPos(int max){
      return generator.nextInt(max);
    }
    static public int whoStarts(int nplayers){
       return generator.nextInt(nplayers);
    }
    static public boolean resurrectPlayer(){
      if (generator.nextFloat() < RESURRECT_PROB)
        return true;
      else
        return false;
    }
    static public int shieldsReward(){
       return generator.nextInt(SHIELDS_REWARD+1);
    }
    static public int weaponsReward(){
       return generator.nextInt(WEAPONS_REWARD+1);
    }
    static public int healthReward(){
      return generator.nextInt(HEALTH_REWARD+1);
    }
    static public int weaponPower(){
      return generator.nextInt(MAX_ATTACK+1);
    }
    static public int shieldPower(){
      return generator.nextInt(MAX_SHIELD+1);
    }
    static public float intensity(float competence){
      return generator.nextFloat(competence+1);
    }
    static public boolean discardElement(float usesLeft){
      if (usesLeft/MAX_USES > 0.3)
        return false;
      else
        return true;
    }
}
