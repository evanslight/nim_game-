/*
 * Name        : Hao Duan
 * ID          : 548771
 * File        : NimGame.java
 * Description : This is to run through the game procedures
 *               and judge stones removed are right and players
 *               are in the arraylist 
 */
import java.util.ArrayList;

public class NimGame
{
    protected ArrayList<NimPlayer> player;
    protected String winnerFami; 
    protected String winnerGive;   
    private int initialStones;
    private int upperbound;
    protected String player1;
    protected String player2; 
   
    /* Constructor */
    
    public NimGame()
    {
        initialStones = 0;
        upperbound = 0;
        player1 = null;
        player2  = null;
    }

    public NimGame
    (int is, int ub, String p1, String p2, ArrayList<NimPlayer> player)
    {
        this.initialStones= is;
        this.upperbound=ub;
        this.player1=p1;
        this.player2=p2;
        this.player = player;
    }

    //start game
    public void startGame(NimSystem order)
    {
        boolean flag0 = false, flag1 = false;
        int x=0,y=0;
        int numremo;

        if(truePlayer(player1, player2) == false)
             System.out.println("One of the players does not exist.");

        while(truePlayer(player1, player2))
        {
            System.out.println("");
            System.out.println("Initial stone count: " + initialStones);
            System.out.println("Maximum stone removal: " + upperbound);
            
            //this is to get the location of player1 and player2 
            //in arraylist and output the information of them and
            //reduce loop times by using flag0 and flag1 
            //to stop this loop;
            for(int i=0; i < player.size(); i++)
            {
                
                if(player1.equals(player.get(i).getUserName()))
                {
                    x = i;
                    flag0 = true;
                }
                if(player2.equals(player.get(i).getUserName()))
                {
                    y = i;
                    flag1 = true;
                }
                if(flag0 && flag1)
                {
                    System.out.println
                        ("Player 1: " +player.get(x).getGiveName()
                         + " " + player.get(x).getFamiName());
                    System.out.println("Player 2: " 
                            +player.get(y).getGiveName()+ " " 
                            + player.get(y).getFamiName());
                    break;
                }
            }
            System.out.println("");           
            
            //start the real game now
            System.out.print(initialStones +" stones left:"); 
            for(int numleft = initialStones; numleft>0; numleft--)
			    System.out.print(" *"); 
		    System.out.println("");

            for(int numleft = initialStones, i = 0; numleft>0; i++) 
		    {
                //this "if" sentence is to distinguish the two players
                //and then judge the winner and 
                //update corresponding data
                if(i % 2 == 0)
                {
			        System.out.println(player.get(x).getGiveName() 
                            + "'s turn - remove how many?");
                    numremo = 
                        player.get(x).getRemoStone(order, numleft, upperbound);
                    if(numremo == numleft)
                    {
                        winnerFami = player.get(y).getFamiName();
                        winnerGive = player.get(y).getGiveName();
                        player.get(y).updateWinCount();
                    }
                }
                else
                {
                    System.out.println(player.get(y).getGiveName() 
                            + "'s turn - remove how many?");
                    numremo = 
                        player.get(y).getRemoStone(order, numleft, upperbound);
                    if(numremo == numleft)
                    {
                        winnerFami = player.get(x).getFamiName();
                        winnerGive = player.get(x).getGiveName();
                        player.get(x).updateWinCount();
                    }       
                }
                
                //this is to insure the valid number of stones removed
                if(trueRemoStones(numremo, numleft, upperbound) == true)
                {
                    numleft = numleft -numremo;
                }
                else i--;
           
                if (numleft !=0)
                {
                    System.out.println("");
                    System.out.print(numleft +" stones left:");
                    //This loop aims at displaying the number of 
                    //stones left by presenting stars.
                    for(int j=numleft; j>0; j--) 
                        System.out.print(" *"); 
                    System.out.println("");
                }
                else
                {   
                    player.get(x).updateGameCount();
                    player.get(y).updateGameCount();
                    System.out.println("");
                    System.out.println("Game Over");
                    System.out.println
                        (winnerGive + " "+winnerFami+ " wins!");
                    break;
                }
            } 
            break;
        }
        
    }

    //this is to judge that both player1 and player2 are in arraylist 
    private boolean truePlayer(String gamer0, String gamer1)
    {
        boolean haveGamer1 = false, haveGamer2 = false;
        for(NimPlayer element:player)
        {
            if(gamer0.equals(element.getUserName()))
                haveGamer1 = true;
        }
        for(NimPlayer element:player)
        {
            if(gamer1.equals(element.getUserName()))
                haveGamer2 = true;
        }
        if(haveGamer1 && haveGamer2)
            return true;
        else return false;
    }
    
    //this is to rule the number of remostone
    private boolean trueRemoStones
        (int remoStone, int numleft, int upperbound)
    {
        try
        {
       if(remoStone > numleft || remoStone > upperbound || remoStone < 1)
       {
           if(upperbound > numleft)
           {
               throw new CountStoneException
                   (numleft);
           }
           else if(upperbound <= numleft)
           {
               throw new CountStoneException
                   (upperbound);
           }
       }
       else
           return true;
        }
        catch(Exception e)
        {
            System.out.println("");
            String message = e.getMessage();
            System.out.println(message);
        }    
        return false;
    }
}
