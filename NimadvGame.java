/*
 * Name        : Hao Duan
 * ID          : 548771
 * File        : NimadvGame.java
 * Description : This NimadvGame is for controlling advanced game.
 */
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NimadvGame extends NimGame
{
    private String status;
    private String lastMove;
    private int numremo;
    private int initialStones;
    private int x,y;
    private int index;
    private String person;

    //Constructor
    public NimadvGame()
    {
        super();
        lastMove = null;
    }

    public NimadvGame
        (int is, int ub, String p1, String p2, ArrayList<NimPlayer> player)
    {
        super(is, ub, p1, p2, player);
    }


    //run advancedgame
    public void startAdvGame(NimSystem order)
    {
        initialStones = order.getInitialStones();
        player1 = order.getplayer1Username();
        player2 = order.getplayer2Username();
        System.out.println("");

        //give true to array availiable elemens
        boolean[] availiable = new boolean[initialStones];
        for (int i = 0; i < availiable.length; i++)
            availiable[i]=true;
        int number;
        int x=0, y=0;
        boolean flag0 = false, flag1 =false;
        int numleft;
        numleft = initialStones;
        

      if(truePlayer(player1, player2) == false)
             System.out.println("One of the players does not exist.");

        while(truePlayer(player1, player2))
        {
     
            System.out.println("Initial stone count: " + initialStones);
        
            System.out.print("Stones display:");

            for(int i=0; i<initialStones; i++)
            {
                if(availiable[i]==true)
                    status = "*";
                else
                    status = "x";
                System.out.printf
                (" <%d,%s>", (i+1), status);
            }
            System.out.println("");
               
            //Display players
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
                    System.out.println
                    ("Player 2: " +player.get(y).getGiveName()
                        + " " + player.get(y).getFamiName());
                    break;
                }
            }

            //begin the game
            while(numleft > 0)
            {
                System.out.println("");
                System.out.print(initialStones+" stones left:");
        
                for(int j=0; j<initialStones; j++)
                {
                    if(availiable[j]==true)
                        status = "*";
                    else
                        status = "x";
                    System.out.print
                    (" <" + (j+1) + ","+status+">");
                }
                System.out.println("");

                for(int l=0; numleft>0; l++)
                {
                    //player1 will be implemented here
                    if( l % 2 == 0)
                    {
                        System.out.println
                        (player.get(x).getGiveName()+
                         "'s turn - which to remove?");
                        player.get(x).advancedMove(availiable, lastMove);
                        numremo = player.get(x).getAdvStone
                        (order, availiable);
                        index = player.get(x).getIndex();
                        lastMove = player.get(x).getLastMove();
                        numleft = player.get(x).getNumleft();
                        l = l + player.get(x).getPointer();
                        if(numleft == 0)
                        {
                            winnerFami = player.get(x).getFamiName();
                            winnerGive = player.get(x).getGiveName();
                            player.get(x).updateWinCount();
                        }
                    }
                    else
                    {
                        //player2 will be implemented here
                        System.out.println
                        (player.get(y).getGiveName()+
                         "'s turn - which to remove?");
                    
                        player.get(y).advancedMove(availiable, lastMove);
                        numremo = player.get(y).getAdvStone
                            (order, availiable);
                        index = player.get(y).getIndex();
                        lastMove = player.get(y).getLastMove();
                        numleft = player.get(y).getNumleft();
                        l = l + player.get(y).getPointer();
                        if(numleft == 0)
                        {
                            winnerFami = player.get(y).getFamiName();
                            winnerGive = player.get(y).getGiveName();
                            player.get(y).updateWinCount();
                        }
                    
                    }
                    System.out.println("");

                    
                    if(numleft !=0)
                    {
                        //print the stones left
                        System.out.print(numleft + " stones left:");
                        for(int m=0; m<initialStones; m++)
                        {
                            if(availiable[m]==true)
                                status = "*";
                            else
                                status = "x";
                            System.out.print
                            (" <" + (m+1) + ","+status+">");
                        }
                        System.out.println("");
                    }
                    else
                    {   
                        player.get(x).updateGameCount();
                        player.get(y).updateGameCount();
                        System.out.println("Game Over");
                        System.out.println
                            (winnerGive + " "+winnerFami+ " wins!");
                        break;
                    }
                }
            
            }
            break;
        }              
           
    }

    //make sure player are in the list
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
}
