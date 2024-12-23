/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author herna
 */
public class ShieldCardDeck extends CardDeck<Shield> {
    @Override
    public void addCards() {
        float protection = Dice.shieldPower(); 
        int uses = Dice.usesLeft(); 
        Shield shield = new Shield(protection,uses);
        addCard(shield);  
    }
}
