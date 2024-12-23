/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author herna
 */
public class WeaponCardDeck extends CardDeck<Weapon> {
    @Override
    public void addCards() {
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft(); 
        Weapon weapon = new Weapon(power,uses);
        addCard(weapon); 
    }
}
