/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/*
 *
 * @author herna
 */
public class Monster  extends LabyrinthCharacter{
    static private int INITIAL_HEALTH = 5;
   
    public Monster (String name, float intelligence, float strength){
        super(name,intelligence,strength,INITIAL_HEALTH);
    }
    
    @Override
    public String toString(){
        String msg = "Mosnter :"+'\n'+super.toString();
        return msg;
    }
 
}
