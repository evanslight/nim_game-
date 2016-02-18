/*
 * Name        : Hao Duan
 * ID          : 548771
 * File        : ErrorComException.java
 * Description : This Errorcomexception is to present
 *               the error caused by wrong command.
 */
public class ErrorComException extends Exception
{
    public ErrorComException(String x)
    {
        super("'"+x+"'"+" is not a valid command.");
    }


}

