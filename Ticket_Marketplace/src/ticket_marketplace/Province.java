/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticket_marketplace;

/**
 *
 * @author Franly
 */
public class Province {
    private int id;
    private String name;

    public Province(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Province() {
        this.id = 0;
        this.name = "";
    }

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
