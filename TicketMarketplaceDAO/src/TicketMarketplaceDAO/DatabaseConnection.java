package TicketMarketplaceDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import Communication.InteractiveIO;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Franly
 */
public abstract class DatabaseConnection {

    private final String URL = "jdbc:mysql://localhost:3306/TicketMarketplace";
    private final String USER = "root";
    private final String PASSWORD = "";

    private Statement statement;
    private ResultSet result;

    protected static Connection conn;

    public DatabaseConnection() throws Exception {
        this.conn = this._getConnection();
        this.setStatement(null);
        this.setResult(null);

    }

    //Getter and Setter
    public final Statement getStatement() {
        return statement;
    }

    public final void setStatement(Statement statement) throws Exception {
        if (statement != null) {
            this.statement = statement;
        } else {
            throw new Exception("Statement can't be null");
        }
    }

    public final ResultSet getResult() {
        return result;
    }

    public final void setResult(ResultSet result) throws Exception {
        if (result != null) {
            this.result = result;
        } else {
            throw new Exception("Result can't be null");
        }
    }

    public static Connection getConnection() {
        return DatabaseConnection.conn;
    }

    // Function
    public Connection _getConnection() throws Exception {
        if (DatabaseConnection.conn == null) {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println(InteractiveIO.GreenMessage("SUCCESFULLY CONNECTED TO DATABASE"));
            return DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        } else {
            return this.conn;
        }
    }

    //Main Function
    public void Create(String sqlQuery) throws Exception {
        PreparedStatement sql = (PreparedStatement) DatabaseConnection.getConnection().prepareStatement(sqlQuery);
        sql.executeUpdate();
        System.out.println(InteractiveIO.GreenMessage("SUCCESFUL TO CREATE DATA IN DATABASE:\n") + sqlQuery);
    }

    public ResultSet Read(String sqlQuery) throws Exception {
        this.setStatement((Statement) DatabaseConnection.getConnection().createStatement());
        this.setResult(this.getStatement().executeQuery(sqlQuery));
        System.out.println(InteractiveIO.GreenMessage("SUCCESFUL TO RETRIEVE DATA FROM DATABASE:\n") + sqlQuery);
        return this.result;
    }

    public void Update(String sqlQuery) throws Exception {
        PreparedStatement sql = (PreparedStatement) DatabaseConnection.getConnection().prepareStatement(sqlQuery);
        sql.executeUpdate();
        System.out.println(InteractiveIO.GreenMessage("SUCCESFUL TO UPDATE DATA IN DATABASE:\n") + sqlQuery);

    }

    public void Delete(String sqlQuery) throws Exception {
        PreparedStatement sql = (PreparedStatement) DatabaseConnection.getConnection().prepareStatement(sqlQuery);
        sql.executeUpdate();
        System.out.println(InteractiveIO.GreenMessage("SUCCESFUL TO DELETE DATA IN DATABASE:\n") + sqlQuery);
    }
}
