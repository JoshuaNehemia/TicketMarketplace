/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Communication;

import java.util.ArrayList;

/**
 *
 * @author joshu
 */


/**
 * Utility class for handling client-server communication by formatting and parsing messages.
 */
public static class Communication {
    
    // FUNCTION ----------------------------------------------------------------
    /**
     * Splits a received communication string into an array of commands based on the specified divider characters.
     *
     * @param communication The received string from communication between client-server architecture.
     * @param dividers The characters that divide the communication command and data (e.g., ';', '~', '-').
     * @return An array of commands extracted from the communication string.
     */
    public static final String[] TranslateToListOfCommand(String communication,String dividers)
    {
        return communication.split(dividers);
    }
   
     /**
     * Converts a task and its associated data into a formatted communication string.
     *
     * @param task The task command to be included in the communication.
     * @param data The array of data elements related to the task.
     * @param dividers The characters used to separate different parts of the communication.
     * @return A formatted communication string containing the task and data.
     */
    public static final String TranslateToCommunication(String task, String[] data, String dividers)
    {
        String communication =("" + dividers + task + dividers + "DATASTART" + dividers);
        for( String d : data)
        {
            communication += (d + dividers);
        }
        communication += "DATAEND";
        return communication;
    }
   
     /**
     * Extracts the data portion from a formatted communication string.
     *
     * @param communication The received communication string containing task and data.
     * @param dividers The characters used to separate different parts of the communication.
     * @return An array of extracted data elements.
     */
    public static final String[] GetDataFromCommunication(String communication, String dividers)
    {
        return GetDataFromCommand(TranslateToListOfCommand(communication,dividers));
    }
    
    /**
     * Extracts the data portion from a list of parsed communication commands.
     *
     * @param ListOfCommand The list of commands obtained from communication parsing.
     * @return An array containing only the extracted data elements.
     */
    public static final String[] GetDataFromCommand(String[] ListOfCommand)
    {
        String[] result;
        ArrayList<String> buffer = new ArrayList<>();
        int start = 0;
        int end = (ListOfCommand.length-1);
        
        int counter =0;
        for( String d : ListOfCommand)
        {
            if("DATASTART".equals(d))
            {
                start = counter;
                break;
            }
        }
        
        result = new String[(start-end-1)];
        
        for(int i=0;i<(start-end-1);i++)
        {
            buffer.add(ListOfCommand[start + i]);
        }
        result = buffer.toArray(result);
        
        return result;
    }
}
