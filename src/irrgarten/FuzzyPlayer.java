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
public class FuzzyPlayer extends Player{
     public FuzzyPlayer(Player other){
            super(other);
        }
        @Override
        public Directions move(Directions direction, ArrayList<Directions> validMoves){
            return Dice.nextStep(direction, validMoves, this.getIntelligence());
        }
        @Override
        public float attack(){
        return + Dice.intensity(this.getStrength());

        }
        @Override
        protected float DefensiveEnergy(){
        return sumShields() + Dice.intensity(this.getIntelligence());
        }
        @Override
        public String toString(){
            return "Fuzzy " + super.toString();
        }
}
