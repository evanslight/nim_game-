/*
 * Name        : Hao Duan
 * ID          : 548771
 * File        : NimAIPlayer.java
 * Description : This NimAIPlayer is to store the AI players
 *               and use the best method to win the game.
 */
import java.util.StringTokenizer;

class NimAIPlayer extends NimPlayer implements Testable
{
    private int remoStone;
    private int numleft;
    private String lastmove;
    private int index;
    private int number;
    private int pointer;

    //constructor
    public NimAIPlayer()
    {
        super();
    }
    public NimAIPlayer(String un, String fn, String gn)
    {
        super(un, fn, gn);
    }

    //accessor
    public int getIndex()
    {
        return index;
    }

    public int getAdvStone(NimSystem order, boolean[] available)
    {
        return number;
    }

    public String getLastMove()
    {
        return lastmove;
    }

    public int getNumleft()
    {
        return numleft;
    }

    public int getPointer()
    {
        pointer = 0;
        return pointer;
    }

    //for normal ai game
    public int getRemoStone(NimSystem order, int numleft, int upperbound)
    {
    
        for(int i=1 ; i <= upperbound ; i++)
        {
          
            if((numleft-i-1)%(upperbound+1) == 0)
            {
                remoStone = i;
                return i;
            }
            else
                remoStone = 1;
        }
        return remoStone;
    }

    //for advanced game
    public String advancedMove(boolean[] available, String lastMove)
    {
        numleft = 0; 

        //get number of stones left
        for(int i=0; i<available.length; i++)
        {
            if(available[i] == true)
                numleft++;
        }

        //the algorithm for win
        if(lastMove == null || lastMove.equals(""))
        {
            int medium = available.length/2;
            if(available.length % 2 == 1)
            {
                lastmove = String.format("%d %d",medium+1, 1);
                available[medium] = false;
                this.numleft = numleft -1;
                return lastmove;
            }
            else
            {
                lastmove = String.format("%d %d", medium, 2);
                available[medium-1] = false;
                available[medium] = false;
                this.numleft = numleft -2;
                return lastmove;
            }
        }
        else
        {

            //remo 2 stone
            for(int i=0; i<available.length-1; i++)
            {
                boolean[] exp = new boolean[available.length];
                for(int j=0; j<available.length; j++)
                    exp[j] = available[j];
                if(exp[i]&&exp[i+1])
                {
                    exp[i] = false;
                    exp[i+1] = false;
                
                    if(getHeapXOR(exp))
                    {
                        available[i] = false;
                        available[i+1] = false;
                        lastmove = String.format("%d 2", i+1);
                        index = i;
                        number = 2;
                        this.numleft = numleft - 2;
                        return this.lastmove;
                    }
                    exp[i] = true;
                    exp[i+1] = true;
                }

            }
    
            //remove 1 stone
            for(int i=0; i< available.length; i++)
            {
                boolean[] exp = new boolean[available.length];
                for(int j=0; j<available.length; j++)
                    exp[j] = available[j];
                if(exp[i])
                {
                    exp[i] = false;
                    if(getHeapXOR(exp))
                    {
                        available[i] = false; 
                        lastmove =  String.format("%d 1", i+1);
                        index = i;
                        number = 1;
                        this.numleft = numleft - 1;
                        return lastmove;
                    }
                        exp[i] = true;
                }
            }
            //fail to find win
            for(int i=0; i< available.length; i++)
            {
                if(available[i])
                {
                    available[i] =false;
                    lastmove = String.format("%d 1", i+1);
                    index = i;
                    number = 1;
                    this.numleft = numleft - 1;
                    return lastmove;
                }
            }
            return "";
        }
        
    }

    //use XOR to get the balance value or unbalance value
    public boolean getHeapXOR(boolean[] available)
    {
        int startHeap = 0;
        int pileNumber = 0;

        for(int i=0; i<available.length; i++)
        {
            if(available[i])
                pileNumber++;
            if(available[i] == false || i== available.length-1)
            {
                startHeap ^= pileNumber;
                pileNumber = 0;
            }
        }
        if(startHeap == 0)
            return true;
        else
            return false;
    }
    
}
