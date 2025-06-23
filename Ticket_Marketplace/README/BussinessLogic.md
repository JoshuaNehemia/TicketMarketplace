# Bussiness Logic - Ticket Marketplace

1. Client Request
User (Client) Create Request -> Communication (Create communication message for consistent protocols) -> Server receiving request

2. Server Receiving Request from Client
Server receiving request -> Parsing communication to processable data -> sending data to process

3. Processing Data
Server sending data -> app logic -> send request to access database -> accessing database

4. Accessing Database
Accessing Database -> Requesting DAO (Data Access Object) to communicate with Database -> Connected to Database (DatabaseConnection - similar to MyModel)

5. Database Process
Connected to Database -> selecting function that required for business logic (Choosing command) -> DAO Runs the command

6. Data Access Object
DAO Runs the command -> Sending Query to Database -> Database receiving query

7. Data Manipulation in Database
Database receiving query -> Database running query -> Database sending response

8. Database Response
Database sending response -> DAO receving response -> DAO sending response to business logic

9. Server Response
DAO sending response to business logic -> Server receiving response -> Server doing business logic (if needed) -> Server sending response 

10. Client Response 
Server sending response - client receiving response -> client create request (depending on user)

## Diagram
```mermaid
sequenceDiagram
    participant User as User (Client)
    participant Communication as Communication
    participant Server as Server
    participant AppLogic as Application Logic
    participant DAO as Data Access Object (DAO)
    participant Database as Database

    Note over User,Database: Bussiness Logic
    User->>Communication: Create Request
    Communication->>Server: Send formatted message

    Note over Server: 2. Server Receiving Request
    Server->>Server: Parse communication to processable data
    Server->>AppLogic: Send data for processing

    Note over AppLogic: 3. Processing Data
    AppLogic->>DAO: Send database access request

    Note over DAO: 4. Accessing Database
    DAO->>Database: Establish connection (DatabaseConnection)

    Note over Database: 5. Database Process
    Database-->>DAO: Connection established
    DAO-->>AppLogic : Sending response (established connection)
    AppLogic->>AppLogic : Select required data transaction
    AppLogic->>DAO : Sending required data transaction

    Note over DAO: 6. Data Access Object
    DAO->>Database: Send query

    Note over Database: 7. Data Manipulation
    Database->>Database: Execute query
    Database->>DAO: Send response

    Note over DAO: 8. Database Response
    DAO->>AppLogic: Forward response

    Note over Server: 9. Server Response
    AppLogic->>AppLogic : Processing data
    AppLogic->>Server: Send processed response
    Server->>Communication: Format response
    Communication->>User: Deliver response

    Note over User: 10. Client Response
    User->>User: Process response and decide next action
```