/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import irrgarten.Directions;
import irrgarten.Dice;
import irrgarten.Orientation;
import irrgarten.Monster;
import irrgarten.Player;
import java.util.ArrayList;


/**
 *
 * @author herna
 */
public class Labyrinth {
    static private final char BLOCK_CHAR= 'X';
    static private final char EMPTY_CHAR ='-';
    static private final char MONSTER_CHAR='M';
    static private final char COMBAT_CHAR='C';
    static private final char EXIT_CHAR='E';
    static private final int ROW=0;
    static private int COL=1;
    //+++++++++++++++++++++//
    private int nRows;
    private int nCols;
    
    private int exitRow;
    private int exitCol;
    
    private Monster[][] monsters;
    private char[][] labyrinth;
    private Player[][] players ;
    
    
    
    public Labyrinth(int ncols, int nrows, int exitRow, int exitCol){
        if(ncols > 0){
            this.nCols=ncols;
        }else{
            this.nCols=-1;
        }
        if(nrows > 0){
            this.nRows=nrows;
        }else{
            this.nRows=-1;
        }
        if(exitRow >= 0){
            this.exitRow=exitRow;
        }else{
            this.exitRow=-1;
        }
        if(exitCol >= 0){
            this.exitCol=exitCol;
        }else{
            this.exitCol=1;
        }
        
        this.monsters=new Monster[this.nRows][this.nCols];
        this.players=new Player[this.nRows][this.nCols];
        this.labyrinth=new char[this.nRows][this.nCols];
        
        for(int i=0;i< this.nRows;i++){
            for(int j=0;j<this.nCols;j++){
                this.monsters[i][j]= null;
                this.players[i][j]=null;
                this.labyrinth[i][j]= Labyrinth.EMPTY_CHAR;
            }
        }
        this.labyrinth[this.exitRow][this.exitCol]=Labyrinth.EXIT_CHAR;
        
        
    }

    public void AddPlayer(char number){
        Player nuevo= new Player (number ,Dice.RandomIntelligence(),Dice.RandomStrength());
        
        boolean encontrado=false;
        
         for(int i=0;i< this.nRows && !encontrado;i++){
            for(int j=0;j<this.nCols && !encontrado;j++){
                if(labyrinth[i][j]==Labyrinth.EMPTY_CHAR )
                    labyrinth[i][j]=number;
                    encontrado=true;
                    players[i][j]=nuevo;
            }
         }
    }
    public void SpreadPlayers(Player players[]){
        for(int i=0; i< this.nRows;i++){
            for(int j=0;j< this.nCols;j++){
                ArrayList<Integer> pos = RandomEmptyPos();
                if(this.players[i][j] != null){
                    this.PutPlayer2d(-1, -1, pos.get(1), pos.get(2), this.players[i][j]);
                }
            }
        }
    }
    
    public boolean HaveAWinner(){
        return players[exitRow][exitCol] != null;
    }
    
    public String To_String(){
        String msg="";
        for(int i=0;i< this.nRows;i++){
            msg+="{";
            for(int j=0;j<this.nCols;j++){
                if(players[i][j]!=null && labyrinth[i][j] != Labyrinth.COMBAT_CHAR){
                    msg+= ' '+players[i][j].GetNumber()+' ';
                }else{
                    msg+=' '+labyrinth[i][j]+' ';
                }
            }
            msg+="}"+'\n';
        }
       return msg;
    }
    
    public void AddMonster(int row, int col, Monster monster){
        if(labyrinth[row][col]==Labyrinth.EMPTY_CHAR){
            monsters[row][col]=monster;
            monsters[row][col].SetPos(row, col);
        }
            
    }
    
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        
        ArrayList<Integer> newPos = this.dir2pos(oldRow, oldCol, direction);
        
        Monster monster = this.PutPlayer2d(oldRow, oldCol, newPos.get(1),newPos.get(2), player);
        return monster;
    }
    
    public void AddBlock(Orientation orientation,int StarRow, int StarCol, int lenght ){
        
        int incRow,incCol;
        incRow=0;
        incCol=0;
        
        if(orientation == Orientation.VERTICAL){
            incRow++;
        }else{
            incCol++;
        }
        int row =StarRow,col = StarCol;
        
        while((this.PosOk(row, col)) && (this.EmptyPos(row, col)) && (lenght >0)){
            labyrinth[row][col] = Labyrinth.BLOCK_CHAR;
            row+=incRow;
            col+=incCol;
            lenght--;
        }
        
    }
    
    public  ArrayList<Directions> ValidMoves(int row,int col){
        ArrayList<Directions> output = new ArrayList<>();
        
        if(CanStepOn(row+1,col)){
            output.add(Directions.UP);
        }
        if(CanStepOn(row-1,col)){
            output.add(Directions.DOWN);
        }
        if(CanStepOn(row,col+1)){
            output.add(Directions.RIGHT);
        }
        if(CanStepOn(row,col-1)){
            output.add(Directions.LEFT);
        }
        
        return output;
    }
    
    private boolean PosOk(int row, int col){
        boolean ok;
        ok = row >= 0 && row < this.nRows && col >= 0 && col < this.nCols;;
        return ok;
    }
    
    private boolean EmptyPos(int row, int col){
        boolean empty = labyrinth[row][col]==Labyrinth.EMPTY_CHAR;
        return empty;
    }
    
    private boolean MonsterPos(int row, int col){
        boolean mos= labyrinth[row][col]==Labyrinth.MONSTER_CHAR;
        return mos;
    }
    
    private boolean ExitPos(int row, int col){
        boolean ext= labyrinth[row][col]==Labyrinth.EXIT_CHAR;
        return ext;
    }
    
    private boolean CombatPos(int row, int col){
        boolean com= labyrinth[row][col]==Labyrinth.COMBAT_CHAR;
        return com;
    }
    
    private boolean CanStepOn(int row, int col){
        return PosOk(row,col) && (EmptyPos(row,col) || ExitPos(row,col));
       
    }
    
    private void UpdateOldPost(int row, int col){
        if(PosOk(row,col))
            if(CombatPos(row,col))
                labyrinth[row][col]=Labyrinth.MONSTER_CHAR;
            else
                labyrinth[row][col]=Labyrinth.EMPTY_CHAR;
    }
    
    private ArrayList<Integer> dir2pos(int row, int col, Directions direction){
          ArrayList<Integer> direct = new ArrayList<>();
        
        switch (direction){
            case LEFT:
                direct.add(row);
                direct.add(col-1);
                break;
            case RIGHT:
                direct.add(row);
                direct.add(col+1);
                break;
            case UP:
                direct.add(row-1);
                direct.add(col);
                break;
            case DOWN:
                direct.add(row+1);
                direct.add(col);
                break;
            default:
                direct.add(row);
                direct.add(col);
                break;
                   
        }
            return direct;
    }
    
    private ArrayList<Integer> RandomEmptyPos(){
        ArrayList<Integer> pos = new ArrayList<>();
        pos.add(Dice.randomPos(this.nRows));
        pos.add(Dice.randomPos(this.nCols));
        while(!EmptyPos(pos.get(0),pos.get(1))){
            pos.add(Dice.randomPos(this.nRows));
            pos.add(Dice.randomPos(this.nCols));
        }
        return pos;
    }
    
    private Monster PutPlayer2d(int OldRow,int OldCol,int row, int col,Player player){
        Monster output =null;
        
        if(this.CanStepOn(row, col)){
            if(this.PosOk( OldRow,OldCol)){
                Player p = this.players[OldRow][OldCol];
                if(p == player){
                    UpdateOldPost(OldRow, OldCol);
                    this.players[OldRow][OldCol]=null;
                }
            }
            
            boolean monsterPos = MonsterPos(row, col);
            
            if(monsterPos){
                this.labyrinth[row][col] = Labyrinth.COMBAT_CHAR;
                output = monsters[row][col];
            }else{
                
                char number = player.GetNumber();
                this.labyrinth[row][col]= number;
                
            }
            
            
            this.players[row][col]=player;
            player.SetPos(row, col);
            
        }
        
        return output;
    }
        
}
