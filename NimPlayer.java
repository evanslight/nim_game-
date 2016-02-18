/*
 * Name        : Hao Duan
 * ID          : 548771
 * File        : NimPlayer.java
 * Description : This class is to store information of 
 *               Nim player, including name, game times
 *               and stones removed by each player etc.
 */

public abstract class NimPlayer
{
    private String famiName;
    private String giveName;
    private String userName;
    private int remoStone;
    private int winCount;
    private int gameCount;
    private float winRate;
   
    /* Constructors */

    public NimPlayer()
    {
        userName = null;
        famiName = null;
        giveName = null;
        winCount = 0;
        gameCount = 0;
        winRate = 0;
        remoStone = 0;
    } 

    public NimPlayer(String un, String fn, String gn)
    {
         userName = un;
         famiName = fn;
         giveName = gn; 
    }
   
    /* Accessors */
    
    public String getUserName()
    {
        return userName;
    }

    public String getFamiName()
    {
        return famiName;
    }

    public String getGiveName()
    {
        return giveName;
    }

    public int getWinCount()
    {
        return winCount;
    }
    
    public int getGameCount()
    {
        return gameCount;
    }
  
    public float getWinRate()
    {        
        return winRate;
    }

    public int getRemoStone(NimSystem order, int numleft, int upperbound)
    {
        this.remoStone = order.getRemoStoneNimsys();
        return this.remoStone;
    }

    /* Mutators */

    public void setPlayer(String un, String fn, String gn)
    {  
        this.famiName = fn;
        this.giveName = gn;
    }
    
    //count the win times
    public void updateWinCount()
    {
        winCount++;
    }

    //count the game turns
    public void updateGameCount()
    {
        gameCount++;
    }

    //caculate the win percentage
    public void compWinRate()
    {
        if(gameCount>0)
            winRate = ((float)winCount/(float)gameCount) * 100;
    }
    
    //reset winCount
    public void resetWinCount()
    {
        winCount = 0;
    }

    //reset game turns
    public void resetGameCount()
    {
        gameCount = 0;
    }

     //reset win percentage
    public void resetWinRate()
    {
        winRate = 0;
    }

    public int setWinCount(int wincount)
    {
        this.winCount = wincount;
        return winCount;
    }

    public int setGameCount(int gamecount)
    {
        this.gameCount = gamecount;
        return gameCount;
    }

    //remove number of stones
    public void remoStones(String rs)
    {
        remoStone = Integer.parseInt(rs);       
    }

    //Display statistics
	public void displayPlayerData()
	{
		System.out.printf("%s,%s,%s,%d games,%d wins%n",
                this.userName,
                this.giveName, 
                this.famiName,
                this.gameCount,
                this.winCount);
    }
    
    // for subclass
    public abstract int getAdvStone(NimSystem order,boolean[] available);

    public abstract String advancedMove(boolean[] available, String lastMove);

    public abstract int getIndex();

    public abstract String getLastMove();

    public abstract int getNumleft();

    public abstract int getPointer();
}
