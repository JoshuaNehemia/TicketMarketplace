package DAO.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Joshu
 */
public class DatabaseConnection {

    //FIELD
    //// DATABASE DATA
    private final String URL = "jdbc:mysql://localhost:3306/TicketMarketplace";
    private final String USER = "root";
    private final String PASSWORD = "";
    //// DATABASE CONNECTION
    private static Connection conn;
    private ResultSet result;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    //CONSTRUCTOR
    public DatabaseConnection() throws Exception {
        DatabaseConnection.conn = this.ConnectToDatabase();
    }

    //GETTER AND SETTER
    public static Connection getConnection() {
        return DatabaseConnection.conn;
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    //FUNCTION
    private Connection ConnectToDatabase() throws Exception {
        if (DatabaseConnection.conn == null) {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("SUCCESFULLY CONNECTED TO DATABASE: " + this.URL);
            return DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        } else {
            return DatabaseConnection.conn;
        }
    }

    //FUNCTION
    private int DoDML() throws Exception {
        int rowsAffected = this.getPreparedStatement().executeUpdate();
        this.getPreparedStatement().close();
        return rowsAffected;
    }
    
    public int Create() throws Exception {
        int rowsAffected = this.DoDML();
        if (rowsAffected > 0) {
            System.out.println("SUCCESFUL TO CREATE DATA IN DATABASE:\n" +"ROWS AFFECTED: "+ rowsAffected +"\nQUERY: " + this.getPreparedStatement());
        } else {
            System.out.println("FAILED TO CREATE DATA IN DATABASE:\n" +"ROWS AFFECTED: "+ rowsAffected +"\nQUERY: " + this.getPreparedStatement());
        }
        return rowsAffected;
    }

    public void Read() throws Exception {
        this.setResult(this.getStatement().executeQuery(String.valueOf(this.getPreparedStatement())));
        this.getStatement().close();
        System.out.println("SUCCESFUL TO RETRIEVE DATA FROM DATABASE:\n" + this.getPreparedStatement());
    }

    public int Update() throws Exception {
        int rowsAffected = this.DoDML();
        if (rowsAffected > 0) {
            System.out.println("SUCCESFUL TO UPDATE DATA IN DATABASE:\n" +"ROWS AFFECTED: "+ rowsAffected +"\nQUERY: " + this.getPreparedStatement());
        } else {
            System.out.println("FAILED TO UPDATE DATA IN DATABASE:\n" +"ROWS AFFECTED: "+ rowsAffected +"\nQUERY: " + this.getPreparedStatement());
        }
        return rowsAffected;
    }

    public int Delete() throws Exception {
        int rowsAffected = this.DoDML();
        if (rowsAffected > 0) {
            System.out.println("SUCCESFUL TO DELETE DATA IN DATABASE:\n" +"ROWS AFFECTED: "+ rowsAffected +"\nQUERY: " + this.getPreparedStatement());
        } else {
            System.out.println("FAILED TO DELETE DATA IN DATABASE:\n" +"ROWS AFFECTED: "+ rowsAffected +"\nQUERY: " + this.getPreparedStatement());
        }
        return rowsAffected;
    }
    
}
