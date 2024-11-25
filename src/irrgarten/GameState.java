/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author herna
 */
public class GameState {
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    public GameState(String labyrinth,String players,String monsters,int currentPlayer,boolean winner,String log){
        this.labyrinth=labyrinth;
        this.players=players;
        this.monsters=monsters;
        this.currentPlayer=currentPlayer;
        this.winner=winner;
        this.log=log;
        
        
    }
    
    public String Get_labyrinth(){
        return(labyrinth);
    }
   
    public String Get_players(){
        return (players);
    }
    public String Get_monsters(){
        return(monsters);
    }
    public String Get_log(){
        return(log);
    }
    public int Get_CurrentPlayer(){
        return(currentPlayer);
    }
    public boolean Get_Winner(){
        return(winner);
    }
}
