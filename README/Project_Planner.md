# **Project Planner: Ticket Marketplace App**

### **Phase 1: Planning and Requirements Gathering**
**Tasks**:
- Define project scope and deliverables.  
- Gather and document requirements.  
- Conduct **data modeling** for the database schema (even though DBMS integration is later).   
- Design wireframes (UI Team).  
- Define the API endpoints and architecture for future DBMS integration (Backend Team).  

---

### **Phase 2: Design and Prototyping (Local System)**  \
**Tasks**:  
**UI Team**:  
1. Develop local prototypes:  
   - User login/registration pages.  
   - Event/ticket browsing and selection interface.  
   - Basic booking form for ticket purchase.  
   - ETC
2. Iterate on designs based on team feedback.  

**Backend Team**:  
1. Implement a local data storage solution (e.g., ArrayLists) as temporary placeholders for DBMS.  
2. Develop APIs that interact with local storage (CRUD for tickets, user authentication).  
3. Create a simple business logic layer for ticket reservations and validations.  

---

### **Phase 3: Development (Local System)**   
**Tasks**:  
**UI Team**:  
1. Code all necessary frontend components for the local system.  
2. Integrate frontend with APIs provided by the Backend Team.  
3. Test frontend functionality for the local phase.  

**Backend Team**:  
1. Code the API endpoints using Java.  
2. Implement and test features using local storage.  
3. Ensure backend functionality aligns with future DBMS integration plans.  

---

### **Phase 4: DBMS Integration and Distributed Programming** 
**Tasks**:  
**UI Team**:  
- Adjust frontend calls to the updated backend endpoints (for DBMS integration).  

**Backend Team**:  
1. Replace local storage with the distributed database
   - Migrate data from local storage (if needed).  
   - Optimize database queries for performance.  
2. Implement distributed components (e.g., replication, load balancing).  
3. Test and debug the distributed system.  

---

### **Phase 5: Integration and Testing (Final System)**  
**Timeframe**: 2 weeks  
**Tasks**:  
- Fully integrate frontend and backend (UI and Backend Teams).  
- Perform comprehensive testing:  
   - Unit testing.  
   - System testing.  
   - Stress testing for distributed system performance.  

---

### **Phase 6: Deployment and Presentation**  
**Timeframe**: 1 week  
**Tasks**:  
- Deploy the app in a simulated environment (if not live hosting).  
- Prepare a comprehensive presentation covering local and distributed phases.  
- Create documentation detailing the system's evolution from local to distributed.  
