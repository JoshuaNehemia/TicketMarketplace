# Ticket Marketplace Server - Main Menu

## Function ConnectToDatabase()

```java
    private final static void ConnectToDatabase()
    {
        
        Connection conn = Database_Connection.getConnection();
        if (conn != null) {
            System.out.println("Koneksi berhasil!");
            try {
                // Query Testing
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users"); 

                while (rs.next()) {
                    System.out.println("User: " + rs.getString("usernames"));
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Koneksi gagal!");
        }
    }
```


This Java method, `ConnectToDatabase()`, is used to connect to a **MySQL database**, retrieve data from the `users` table, and display the usernames. Let's break it down step by step:

---

### **1. Method Declaration**
```java
public final void ConnectToDatabase()
```
- `private`: This method is **accessible** from anywhere.
- `final`: This method **cannot be overridden** by subclasses.
- `static`: This method **can be use** without objects.
- `void`: The method **does not return** any value.

---

### **2. Establishing a Database Connection**
```java
Connection conn = Database_Connection.getConnection();
```
- Calls the `getConnection()` method from the `Database_Connection` class (assumed to be the class containing your `getConnection()` method).
- `conn` holds the **database connection**.

---

### **3. Checking if the Connection is Successful**
```java
if (conn != null) {
    System.out.println("Koneksi berhasil!");
```
- If `conn` is **not null**, it prints `"Koneksi berhasil!"` (meaning *Connection successful!*).
- Otherwise, it prints `"Koneksi gagal!"` (*Connection failed!*).

---

### **4. Query Execution & Retrieving Data**
```java
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```
- `createStatement()`: Creates a `Statement` object to execute SQL queries.
- `executeQuery("SELECT * FROM users")`: Runs a **SQL query** to retrieve all records from the `users` table.
- `ResultSet rs`: Holds the result of the query.

---

### **5. Processing the Result Set**
```java
while (rs.next()) {
    System.out.println("User: " + rs.getString("usernames"));
}
```
- `rs.next()`: Moves to the **next row** in the result set.
- `rs.getString("usernames")`: Retrieves the value from the `"usernames"` column.
- **Prints each username** found in the database.

---

### **6. Closing Resources**
```java
rs.close();
stmt.close();
conn.close();
```
- Closes the `ResultSet`, `Statement`, and `Connection` to **prevent memory leaks**.

---

### **7. Handling SQL Exceptions**
```java
} catch (SQLException e) {
    e.printStackTrace();
}
```
- If an error occurs (e.g., incorrect table name, connection issues), it **prints the error details**.

---

### **Full Execution Flow**
1. **Tries to connect to the database** using `Database_Connection.getConnection()`.
2. If **successful**, executes `SELECT * FROM users`.
3. **Iterates through the result set**, printing usernames.
4. **Closes all database resources**.
5. If **connection fails**, prints `"Koneksi gagal!"`.

---

### **Example Output**
If the `users` table contains:
| id | usernames  |
|----|-----------|
| 1  | Alice     |
| 2  | Bob       |
| 3  | Charlie   |

The output will be:
```
Koneksi berhasil!
User: Alice
User: Bob
User: Charlie
```

---

### **Potential Issues**
❌ **Table doesn’t exist** → `SQLException: Table 'users' not found`  
❌ **Column name mismatch** (`usernames` vs. `username`) → `SQLException: Column 'usernames' not found`  
❌ **Connection issues** (e.g., MySQL not running) → `"Koneksi gagal!"`  