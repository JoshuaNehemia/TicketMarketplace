/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.Format;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author joshu
 */
public class Default {
    public static DateTimeFormatter getDateTimeFormatter(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
    public static DateTimeFormatter getDateFormatter(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
}
