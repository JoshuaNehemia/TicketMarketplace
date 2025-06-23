/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Protocol;

/**
 *
 * @author joshu
 */
public class InteractiveIO {
    private static String ANSI_RESET = "\u001B[0m";
    private static String ANSI_BLACK = "\u001B[30m";
    private static String ANSI_RED = "\u001B[31m";
    private static String ANSI_GREEN = "\u001B[32m";
    private static String ANSI_YELLOW = "\u001B[33m";
    private static String ANSI_BLUE = "\u001B[34m";
    private static String ANSI_PURPLE = "\u001B[35m";
    private static String ANSI_CYAN = "\u001B[36m";
    private static String ANSI_WHITE = "\u001B[37m";
    
    public static final String BlackMessage(String _message)
    {
        return ANSI_BLACK + _message + ANSI_RESET;
    }
    public static final String RedMessage(String _message)
    {
        return ANSI_RED + _message + ANSI_RESET;
    }
    public static final String GreenMessage(String _message)
    {
        return ANSI_GREEN + _message + ANSI_RESET;
    }
    public static final String YellowMessage(String _message)
    {
        return ANSI_YELLOW + _message + ANSI_RESET;
    }
    public static final String BlueMessage(String _message)
    {
        return ANSI_BLUE + _message + ANSI_RESET;
    }
    public static final String PurpleMessage(String _message)
    {
        return ANSI_PURPLE + _message + ANSI_RESET;
    }
    public static final String CyanMessage(String _message)
    {
        return ANSI_CYAN + _message + ANSI_RESET;
    }
    public static final String WhiteMessage(String _message)
    {
        return ANSI_WHITE + _message + ANSI_RESET;
    }
}
