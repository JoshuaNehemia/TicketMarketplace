# Database Connection

> **Source File Relative Path:**
> TicketMarketplace_Server\src\ticketmarketplace_server\Database\Database_Connection.java

This Java code is responsible for establishing a connection to a **MySQL database** using **JDBC (Java Database Connectivity)**. Let's break it down step by step:

### **1. Declaring Constants for Database Connection**
```java
private static final String URL = "jdbc:mysql://localhost:3306/projectjava1";
private static final String USER = "root"; 
private static final String PASSWORD = "";
```
- `URL`: Specifies the **JDBC connection string**, which includes:
  - `jdbc:mysql://` → The JDBC protocol for MySQL.
  - `localhost` → The MySQL server is running locally.
  - `3306` → The default port number for MySQL.
  - `projectjava1` → The name of the database being accessed.
- `USER`: The **username** to log into the database (`root` is the default user for MySQL).
- `PASSWORD`: The **password** for the MySQL database (here, it is empty).

---

### **2. Establishing a Database Connection**
```java
public static Connection getConnection() {
```
- This is a **static method**, meaning it can be called without creating an instance of the class.
- It **returns a `Connection` object**, which represents the connection to the database.

---

### **3. Loading the MySQL JDBC Driver**
```java
Class.forName("com.mysql.cj.jdbc.Driver");
```
- This **loads the MySQL JDBC Driver** (`com.mysql.cj.jdbc.Driver`), which is required to establish a connection.
- The `Class.forName()` method dynamically loads the driver at runtime.

> 🔹 *This step is required in older versions of Java. In newer JDBC versions (Java 6 and above), the driver is automatically registered, so this line is technically optional.*

---

### **4. Connecting to the Database**
```java
return DriverManager.getConnection(URL, USER, PASSWORD);
```
- The `DriverManager.getConnection()` method attempts to connect to the MySQL database using the provided `URL`, `USER`, and `PASSWORD`.
- If successful, it returns a `Connection` object.

---

### **5. Handling Exceptions**
```java
} catch (ClassNotFoundException | SQLException e) {
    e.printStackTrace();
    return null;
}
```
- If **`Class.forName()` fails**, a `ClassNotFoundException` is thrown (e.g., if the MySQL JDBC driver is missing).
- If **`DriverManager.getConnection()` fails**, a `SQLException` is thrown (e.g., incorrect credentials, database not running, or wrong URL).
- `e.printStackTrace();` prints error details in case of failure.
- If an error occurs, the method **returns `null`**, indicating the connection failed.

---

### **How to Use This Method?**
To establish a connection, you can call:
```java
Connection conn = getConnection();
if (conn != null) {
    System.out.println("Connected to the database!");
} else {
    System.out.println("Failed to connect to the database.");
}
```

---

### **Summary**
This method:
✔ Loads the MySQL JDBC driver.  
✔ Connects to the MySQL database (`projectjava1`).  
✔ Returns a `Connection` object if successful.  
✔ Prints an error and returns `null` if there is a failure.  
