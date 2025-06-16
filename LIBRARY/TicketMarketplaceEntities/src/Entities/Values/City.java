/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.Values;

/**
 *
 * @author joshu
 */
public class City {
    //FIELDS
    private int id;
    private String name;

    //CONSTRUCTOR
    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //GETTER AND SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
