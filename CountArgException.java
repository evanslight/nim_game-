/*
 * Name        : Hao Duan
 * ID          : 548771
 * File        : CountArgException.java
 * Description : This CountArgException is to check the input
 *               argument right or wrong.
 */
public class CountArgException extends Exception
{
    public CountArgException()
    {
        super("Incorrect number of arguments supplied to command.");
    }

    public CountArgException(String message)
    {
        super(message);
    }

}

