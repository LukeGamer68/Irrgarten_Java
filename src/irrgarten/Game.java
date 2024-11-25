/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import irrgarten.Player;
import irrgarten.Monster;
import irrgarten.GameState;
import irrgarten.GameCharacter;
import irrgarten.Labyrinth;
import irrgarten.Directions;

import java.util.ArrayList;
/**
 *
 * @author herna
 */
public class Game {
    static private int MAX_ROUNDS = 10;
    private String log;
    //+++++++++++++++++++++++++++++++++//
    private int currentPlayerIndex;
    private Player currentPlayer;
    
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    
    private Labyrinth labyrinth;
    
    public Game(int nplayers){
        this.log="";
        this.players = new ArrayList<>(nplayers);
        this.monsters = new ArrayList<>();
        labyrinth =new Labyrinth(9,9,7,4);
    }
    
    public boolean finished(){
        return this.labyrinth.HaveAWinner();
    }
    
    public boolean nextStept(Directions preferredDirection){
        String log ="";
        boolean dead = this.currentPlayer.Dead();
        
        if(!dead){
           Directions direction = actualDirection(preferredDirection);
           
           if(direction != preferredDirection){
               this.logPlayerNoOrders();
           }
           
           Monster monster = this.labyrinth.putPlayer(direction, this.currentPlayer);
           
           if(monster == null){
               this.logNoMonster();
           }else{
               GameCharacter winner = combat(monster);
               
               ManageReward(winner);
           }
           
        }else{
            ManageResurrection();
        }
        
        boolean endGame = finished();
        
        if(!endGame){
            this.NextPlayer();
        }
        
        return endGame;
        
    }
    
    public GameState getGameState(){
        
        String mons="";
        String plays="";
        
        
        for(Monster m: this.monsters){
            mons += m.To_String();
        }
        for(Player p: this.players){
            plays += p.To_String();
        }
        String lab =this.labyrinth.To_String();
        GameState temp;
        temp= new GameState(lab,plays,mons,this.currentPlayerIndex,finished(),this.log);
        
        return temp;
        
    }
    
    private void configureLabyrinth(){
        this.labyrinth.AddBlock(Orientation.HORIZONTAL, 1, 2,2);
        this.labyrinth.AddBlock(Orientation.HORIZONTAL,1,6,1);
        this.labyrinth.AddBlock(Orientation.HORIZONTAL,2,5,1);
        this.labyrinth.AddBlock(Orientation.HORIZONTAL,2,4,2);
        this.labyrinth.AddBlock(Orientation.VERTICAL,4,1,2);
        this.labyrinth.AddBlock(Orientation.HORIZONTAL,3,6,3);
        this.labyrinth.AddBlock(Orientation.VERTICAL,5,3,2);
        this.labyrinth.AddBlock(Orientation.VERTICAL,6,8,2);
        this.labyrinth.AddBlock(Orientation.VERTICAL,7,4,4);
        this.labyrinth.AddBlock(Orientation.HORIZONTAL,5,5,2);
        this.labyrinth.AddBlock(Orientation.VERTICAL,7,6,2);
        
        
        
        Monster monster1 =new Monster("RascaVentanas",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(0,3, monster1);
        this.monsters.add(monster1);
        
        Monster monster2 =new Monster("LimpiaBaldes",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(1,1, monster2);
        this.monsters.add(monster2);
        
        Monster monster3 =new Monster("SacaTopos",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(1,5, monster3);
        this.monsters.add(monster3);
        
        Monster monster4 =new Monster("LameFarolas",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(4,2, monster4);
        this.monsters.add(monster4);
        
        Monster monster5 =new Monster("PinchaGlobos",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(4,5, monster5);
        this.monsters.add(monster5);
        
        Monster monster6 =new Monster("MasticaCojines",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(5,1, monster6);
        this.monsters.add(monster6);
        
        Monster monster7 =new Monster("LanzaCascaras",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(6,7, monster7);
        this.monsters.add(monster7);
        
        Monster monster8 =new Monster("GolpeaParedes",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(7,5, monster8);
        this.monsters.add(monster8);
        
        Monster monster9 =new Monster("AsaltaBuzones",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(8,3, monster9);
        this.monsters.add(monster9);
        
        Monster monster10 =new Monster("Patroclo",Dice.RandomIntelligence(),Dice.RandomStrength());
        this.labyrinth.AddMonster(8,7, monster10);
        this.monsters.add(monster10);
    }
    
    private void NextPlayer(){
        this.currentPlayerIndex = (this.currentPlayerIndex+1)%this.players.size();
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = this.currentPlayer.getRow();
        int currentCol = this.currentPlayer.getCol();
        
       ArrayList<Directions> ValidMoves = this.labyrinth.ValidMoves(currentRow, currentCol);
        
       Directions output = this.currentPlayer.move(preferredDirection, ValidMoves);
        
       return output;
    }
    
    private GameCharacter combat(Monster monster){
        int rounds =0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = this.currentPlayer.Attack();
        
        boolean lose = monster.Defend(playerAttack);
        
        while(!lose && rounds > Game.MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            
            float monsterAttack = monster.attack();
            
            lose = this.currentPlayer.Defend(monsterAttack);
            
            if(!lose){
                playerAttack = this.currentPlayer.Attack();
                
                winner = GameCharacter.PLAYER;
                
                lose = monster.Defend(playerAttack);
                
            }
            rounds++;
        }
        
        this.logRounds(rounds, Game.MAX_ROUNDS);
        
        return winner;
        
    }
    
    private void ManageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            this.currentPlayer.ReciveReward();
            this.logPlayerWon();
        }else{
            this.logMonsterWon();
        }
    }
    
    private void ManageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        
        if(resurrect){
            this.currentPlayer.Resurect();
            logResurrected();
        }else{
            logPlayerSkipTurn();
        }
    }
    
    private void logPlayerWon(){
        this.log+="Jugador "+this.currentPlayer+" ha ganado el combate"+'\n';
    }
    private void logMonsterWon(){
        this.log+="El monstruo ha ganado el combate"+'\n';
    }
    private void logResurrected(){
        this.log+="Jugador "+this.currentPlayer+" ha resucitado"+'\n';
    }
    private void logPlayerSkipTurn(){
        this.log+="Jugador "+this.currentPlayer+" no tiene turno por estar muerto"+'\n';
    }
    private void logPlayerNoOrders(){
        this.log+="Jugador "+this.currentPlayer+" no pudo seguir las ordenes"+'\n';
    }
    
     private void logNoMonster(){
         this.log+="Jugador "+this.currentPlayer+" se movio a una celda vacia o no pudo moverse"+'\n';
    }
    private void logRounds(int rounds,int max){
        this.log+="Se han producido "+rounds+" de "+ max + " de combate"+'\n';
    }
}
