/*
 * Name        : Hao Duan
 * ID          : 548771
 * File        : CountStoneException.java
 * Description : This CountStoneException is to verify the
 *               input stone number.
 */
public class CountStoneException extends Exception
{
    public CountStoneException(int x)
    {
        super("Invalid move. You must remove between 1 and " 
                    + x + " stones.");
    }

    public CountStoneException(String message)
    {
        super(message);
    }

}
