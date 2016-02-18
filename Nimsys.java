/*
 * Name        : Hao Duan
 * ID          : 548771
 * File        : Nimsys.java
 * Description : This NimSystem is for controlling and commands 
 *               processing and Nimsys is to run the Nimgame by
 *               commands.
 */
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;  
import java.util.Comparator;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class Nimsys
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to Nim"); 
        NimSystem order = new NimSystem();
        NimGame dealorder;
        NimadvGame advance;
        order.inputFile();

        
        //this loop is to recognize the command
        //and excute them
        while (true)
        {
            try
            {
            System.out.println("");
            System.out.print(">");    
            String neworder = order.getOrder();
            order.getCommandName(neworder);

            if(order.getCommand().equals("addplayer"))
                order.addPlayer();
            else if(order.getCommand().equals("addaiplayer"))
                order.addAIPlayer();
            else if(order.getCommand().equals("removeplayer"))
                order.remoPlayer();
            else if(order.getCommand().equals("editplayer"))               
                order.editPlayer();
            else if(order.getCommand().equals("resetstats"))
                order.resetstatsPlayer();
            else if(order.getCommand().equals("displayplayer"))            
                order.displayPlayer();
            else if(order.getCommand().equals("startgame"))
            {
                order.getStartArgument(); 
                dealorder = new NimGame
                    (order.getInitialStones(),
                     order.getUpperbound(), 
                     order.getplayer1Username(), 
                     order.getplayer2Username(), 
                     order.getPlayer());
                dealorder.startGame(order);
            }
            else if(order.getCommand().equals("startadvancedgame"))
            {
                order.getAdvArgument();
                advance = new NimadvGame
                    (order.getInitialStones(),
                     2, 
                     order.getplayer1Username(), 
                     order.getplayer2Username(), 
                     order.getPlayer());
                advance.startAdvGame(order);
                
            }
            else if(order.getCommand().equals("rankings"))
                order.rank();
            else if(order.getCommand().equals("exit"))
            {
                order.outputFile();
                System.out.println("");
                System.exit(0);
            }
            else if(order.getCommand().equals("error"))
            {}
            else
            {
                 throw new ErrorComException(order.getCommand());
            }
            }
            catch(ErrorComException e)
            {
                String message = e.getMessage();
                System.out.println(message);
            }
            catch(Exception e)
            {}
        }

    }
}  


class NimSystem
{
    private String order;
    private String command;
    private String argument;
    private String[] gamer;    
    private ArrayList<NimPlayer> player;
    private int initialStones;
    private int upperbound;
    private int remoStone;
    private String lastmove;
    Scanner input = new Scanner(System.in);
    
    /* Constructors */

    public NimSystem()
    {
        player = new ArrayList<NimPlayer>();
        order = null;
        command = null;
        argument = null;
        remoStone = 0;
    }
   
    /* Accessor */

    public String getOrder()
    {
        return input.nextLine();
    }

    public String getCommand()
    {
        return command;
    }

    public String getArgument()
    {
        return argument;
    }

    public String getRemoStoneAdv()
    {
        return input.nextLine();
    }
    public int getRemoStoneNimsys()
    {
        try
        {
        String x = input.nextLine();
        remoStone = Integer.parseInt(x);
        }
        catch(NumberFormatException e)
        {
            remoStone = 999;
        }
        return remoStone;
    }

    public String getAdvMove()
    {
       
        lastmove = input.nextLine();
        return lastmove;
    }

    public String getplayer1Username()
    {
        return gamer[0];
    }

    public String getplayer2Username()
    {
        return gamer[1];
    }

    public int getInitialStones()
    {
        return initialStones;
    }

    public int getUpperbound()
    {
        return upperbound;
    }

    public ArrayList<NimPlayer> getPlayer()
    {
        return player;
    }
    
    
    //get start game argument
    public void getStartArgument()
    {
        try
        {
            gamer = new String[2];
            StringTokenizer agent = new StringTokenizer(argument, ",");
            
            String number1 = agent.nextToken();
            String number2 = agent.nextToken();
            this.initialStones = Integer.parseInt(number1);
            this.upperbound =  Integer.parseInt(number2);

            for(int i=0; i<=1;i++)
                gamer[i] = agent.nextToken();
        }
        catch(NoSuchElementException e)
        {
            System.out.println
               ("Incorrect number of arguments supplied to command.");
        }        
    }

    //get argument for advanced game
    public void getAdvArgument()
    {
        try
        {
            gamer = new String[2];
            StringTokenizer agent = new StringTokenizer(argument, ","); 
            this.initialStones = Integer.parseInt(agent.nextToken());
            for(int i=0; i<=1;i++)
                gamer[i] = agent.nextToken();   
        }
        catch(NoSuchElementException e)
        {
            System.out.println
               ("Incorrect number of arguments supplied to command.");
        }     
    }

    //get the command from the input words
    public void getCommandName(String order)
    {   
        try
        {    
            StringTokenizer agent = new StringTokenizer(order);
            
            //Get the command 
            command = agent.nextToken();
            if(agent.hasMoreTokens())
            {
                argument=agent.nextToken();
            }
            else 
            {
                argument = null;
                if(command.equals("addplayer"))
                {
                    command = "error";
                    throw new CountArgException();
                }
                else if(command.equals("editplayer"))
                {
                    command = "error";
                    throw new CountArgException();
                }
                else if(command.equals("startgame"))
                {
                    command = "error";
                    throw new CountArgException();
                }
                else if(command.equals("startadvancedgame"))
                {
                    command = "error";
                    throw new CountArgException();
                }
            }
        }
        catch(CountArgException e)
        {
            String message = e.getMessage();
             System.out.println
               (message);
        }
    }
    
    //add AI
    public void addAIPlayer()
    {
        try
        {
            StringTokenizer agent = new StringTokenizer(argument, ",");
            NimPlayer p = new NimAIPlayer
                (agent.nextToken(), agent.nextToken(),
                agent.nextToken());           

            boolean flag = true;
            for (NimPlayer element:player)
            {
                String e = element.getUserName();
                if(e.equals(p.getUserName()))
                {  
                    System.out.println("The player already exists.");  
                    flag = false;
                    break;
                }  
            }
            if (flag) 
                player.add(p);
        }
       catch(NoSuchElementException e)
       {
           System.out.println
               ("Incorrect number of arguments supplied to command.");
       }
    }
    //Get player and add each new player to the arraylist
    public void addPlayer()
    {
        try
        {
            StringTokenizer agent = new StringTokenizer(argument, ",");
            NimPlayer p = new HumanPlayer
                (agent.nextToken(), agent.nextToken(),
                agent.nextToken());           

            boolean flag = true;
            for (NimPlayer element:player)
            {
                String e = element.getUserName();
                if(e.equals(p.getUserName()))
                {  
                    System.out.println("The player already exists.");  
                    flag = false;
                    break;
                }  
            }
            if (flag) 
                player.add(p);
        }
       catch(NoSuchElementException e)
       {
           System.out.println
               ("Incorrect number of arguments supplied to command.");
       } 
        
    }
    
    //remove player and judge existance 
     public void remoPlayer()
    {
        if(null == argument)
        {
            System.out.println
            ("Are you sure you want to remove all players? (y/n)");
            String judge = input.nextLine();
            if(judge.toLowerCase().equals("y"))
                player.clear();
        }
        else
        {
            boolean flag = false;
            for (int i=0;i<player.size();i++)
            {
                String e = player.get(i).getUserName();
                if(e.equals(argument))
                {   
                    player.remove(i);
                    --i;
                    flag = true;
                }               
            }
            if(flag == false)
                System.out.println
                    ("The player does not exist.");  
        }       
    }

    //edit player
    public void editPlayer()
    {
        try
        {
            StringTokenizer agent = new StringTokenizer(argument, ",");
            NimPlayer p = new HumanPlayer
                (agent.nextToken(), agent.nextToken(), agent.nextToken());
            boolean flag = false;

            for (int i=0;i<player.size();i++)
            {
                String e = player.get(i).getUserName();
                if(e.equals(p.getUserName()))
                {   
                    p.setPlayer
                        (p.getUserName(), p.getFamiName(), p.getGiveName());
                    player.set(i,p);
                    flag = true;
                }               
        }
        if(flag == false)
            System.out.println("The player does not exist.");
        }
        catch(NoSuchElementException er)
        {
            System.out.println
               ("Incorrect number of arguments supplied to command.");
        } 
    }

    //reset statics of players
    public void resetstatsPlayer()
    {
        if(null == argument)
        {
            System.out.println
            ("Are you sure you want to reset all player statistics? (y/n)");
            String judge = input.nextLine();
            if(judge.toLowerCase().equals("y"))
            {   
                for (int i=0;i<player.size();i++)
                {   
                    player.get(i).resetWinCount();
                    player.get(i).resetGameCount();
                    player.get(i).resetWinRate();                    
                }               
            }
        }
        else
        {        
            boolean flag = false;
            for (int i=0;i<player.size();i++)
            {
                String e = player.get(i).getUserName();
                if(e.equals(argument))
                {   
                    player.get(i).resetWinCount();
                    player.get(i).resetGameCount();
                    player.get(i).resetWinRate();
                    flag = true;
                }               
            }
            if(flag == false)
                System.out.println("The player does not already exist.");

        }
    }

    //display players win count and game count
    public void displayPlayer()
    {
        if(null == argument)
        {
            //Sort the arraylist based on alphabet of username 
            Collections.sort(player, new Comparator<NimPlayer>()
            {
                public int compare(NimPlayer arg0, NimPlayer arg1)
                {
                    return arg0.getUserName().compareToIgnoreCase
                           (arg1.getUserName());                
                }
            });
            for(NimPlayer element:player)
                element.displayPlayerData();
        }
        else
        {        
            boolean flag = false;
            for (int i=0;i<player.size();i++)
            {
                String e = player.get(i).getUserName();
                if(e.equals(argument))
                {   
                    player.get(i).displayPlayerData();
                    flag = true;
                }               
            }
            if(flag == false)
                System.out.println
                ("The player does not already exist.");

        }

    }

    //present rank of top 10 players if not enough present all
    public void rank()
    {
        int rankCount = player.size(); 
        for(NimPlayer element:player)
        {
            element.compWinRate();
        }
        //order by win percentage but if win percentage is the same 
        //then compare username
        Collections.sort(player, new Comparator<NimPlayer>()
        {
            public int compare(NimPlayer arg0, NimPlayer arg1)
            {
                if(arg0.getWinRate() == arg1.getWinRate())
                    return arg0.getUserName().compareToIgnoreCase
                            (arg1.getUserName());
                else
                {                          
                    return (int) (arg1.getWinRate() - 
                                  arg0.getWinRate());
                }
                                                          
            }
        });
        
        //judge the number of players is below 10 or not
        //and present the essential information
        if(rankCount < 10)
        {   
            for(NimPlayer element:player)
            {
                String newpercent = String.format
                    ("%1$.0f%%",element.getWinRate());             
                System.out.printf
                    ("%-4s | %02d games | %s %s%n",
                    newpercent,
                    element.
                    getGameCount(),
                    element.getGiveName(),
                    element.getFamiName());
            }
        }
        else
        {
            rankCount = 0;
            for(NimPlayer element:player)
            {
                String newpercent = String.format
                    ("%1$.0f%%",element.getWinRate());             
                System.out.printf
                    ("%-4s | %02d games | %s %s%n",
                    newpercent,
                    element.
                    getGameCount(),
                    element.getGiveName(),
                    element.getFamiName());
                    rankCount++;
                    if(rankCount == 9)
                        break;
            }
        }
    }

    //input player to file
    public void inputFile()
    {
        Scanner inputStream = null;
        PrintWriter outputStream = null;
        try
        {
            inputStream = 
                new Scanner(new FileInputStream("players.dat"));
             
            while(inputStream.hasNextLine())
            {
                //input human to file marked by "huaman"
                if(inputStream.next().equals("Human"))
                {
                    NimPlayer p = new HumanPlayer
                    (inputStream.next(), inputStream.next(),inputStream.next());
                    p.setPlayer
                            (p.getUserName(), p.getFamiName(), p.getGiveName());
                    p.setWinCount(inputStream.nextInt());
                    p.setGameCount(inputStream.nextInt());
                    inputStream.nextLine();
                    player.add(p);
                }
                else
                {
                    //input AI to file marked by "AI"
                    NimPlayer p = new NimAIPlayer
                    (inputStream.next(), inputStream.next(),inputStream.next());
                    p.setPlayer
                            (p.getUserName(), p.getFamiName(), p.getGiveName());
                    p.setWinCount(inputStream.nextInt());
                    p.setGameCount(inputStream.nextInt());
                    inputStream.nextLine();
                    player.add(p);
                }
            }
            inputStream.close();
        }
        catch(FileNotFoundException e)
        {
            try
            {
                outputStream = 
                new PrintWriter(new FileOutputStream("players.dat"));
            }
            catch(FileNotFoundException err)
            {
                System.out.println("Problem opening files.");
            }

        }

    }
    
    //output player from file
    public void outputFile()
    {
        PrintWriter outputStream = null;
        try
        {
            outputStream = 
                new PrintWriter(new FileOutputStream("players.dat"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening files.");
        }
        for(int i = 0 ; i < player.size() ; i++ )
        {
            //output player into Nimaiplayer or Humanplayer
            if(player.get(i) instanceof NimAIPlayer)
                outputStream.print("AI ");
            else
                outputStream.print("Human ");
            outputStream.print(player.get(i).getUserName()+" ");
            outputStream.print(player.get(i).getFamiName()+" ");
            outputStream.print(player.get(i).getGiveName()+" ");
            outputStream.print(player.get(i).getWinCount()+" ");
            outputStream.println(player.get(i).getGameCount());
        }
        outputStream.close();
    }
}
