/*
 * Name        : Hao Duan
 * ID          : 548771
 * File        : HumanPlayer.java
 * Description : This HumanPlayer is for storing human players.
 */
import java.util.StringTokenizer;

class HumanPlayer extends NimPlayer
{
    private int remoStone;
    private int numleft;
    private int index;
    private int numremo;
    private String lastMove;
    private int pointer;

    //constructor
    public HumanPlayer()
    {
        super();
    }

    public HumanPlayer(String un, String fn, String gn)
    {
        super(un, fn, gn);
    }

    //accessor
    public int getIndex()
    {
        return index;
    }

    public String getLastMove()
    {
        return lastMove;
    }

    public int getNumleft()
    {
        return numleft;
    }

    //to insure the l is as before in NimadvGame when error occurs
    public int getPointer()
    {
        return pointer;
    }

    public int getAdvStone(NimSystem order,boolean[] availiable)
    {
        pointer = 0;
        numleft = 0;
        for(int i=0; i<availiable.length; i++)
        {
            if(availiable[i] == true)
                numleft++;
        }
       
        //get the essential argument 
        lastMove = order.getRemoStoneAdv();
        int x;
        StringTokenizer agent = new StringTokenizer(lastMove);
        x = Integer.parseInt(agent.nextToken());
        index = x-1;
        numremo = Integer.parseInt(agent.nextToken());
        
        try
        {
            if (numremo > numleft)
                throw new Exception("Invalid move.");
            else if (numremo < 1 || numremo > 2)
                throw new Exception("Invalid move.");
            else if ((index+1) > availiable.length || index < 0)
                throw new Exception("Invalid move.");
            else if(numremo == 1 && (availiable[index] == false))
            {
                throw new Exception("Invalid move.");
            }
            else if(numremo ==2 && ( (index >= availiable.length) 
                    || (availiable[index] == false)
                    || (availiable[index+1] == false)))
                throw new Exception("Invalid move.");
            else
            {
                //move stones
                if (numremo == 1)
                {
                    availiable[index] = false;
                    this.numleft = numleft - 1;
                }
                else if(numremo == 2)
                {
                    availiable[index] = false;
                    availiable[index+1] = false;
                    this.numleft =numleft - 2;
                }              
            }
        }
        catch(Exception e)
        {
            System.out.println("");
            System.out.println(e.getMessage());
            pointer = pointer - 1;
        }
  
        return numremo;
    }

    public String advancedMove(boolean[] available, String lastMove)
    {
        return lastMove;
    }

}

