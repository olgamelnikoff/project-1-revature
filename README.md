# Expense Reimbursement System (ERS)
## Project Overview
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. 
All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. 
Finance managers can log in and view all reimbursement requests and past history for all employees in the company. 
Finance managers are authorized to approve and deny requests for expense reimbursement.



## Technologies Used
- Java
- JDBC
- Oracle SQL
- MariaDB
- Javalin
- JavaScript
- HTML
- CSS
- Bootstrap
- AJAX
- Log4J
- Selenium
- Maven
- Amazon Web Services
- REST
- Git
- Github

## List of Features Implemented:
- A user can log in if correct combination of username and password is used
- If the user role is Employee, then the following features are available: 
  * The user can see their past tickets
  * The user can submit a new request
- If the user role is Finance Manager, then the following features are available:
  * Continue as Employee and see their own tickets / submit a new request
  * See all the tickets of all the employees
  * Filter the tickest by status (Pending, Approved or Rejected)
  * Approve / Reject any pending request, except for their own
  
## To-do List:
- Log out functionality
- Registration functionality
- Forgot Password feature
- Sending an email with a temporary password upon registration
- Password encryption
- Add more security to the project, to prevent unauthorized access

## How to set up / get started using it
- git clone the project using the project URL
- update the URL, username and password for the Database connection in the following file: com.example.dao.DAOConnection.java
- start Main Driver as Java Application, it will start Javalin
- Go to start page - http://localhost:7001/html/login.html

## Usage of the project
1. On the start page, enter "spacexdragon" as username and "password" as password

![Alt text](https://github.com/olgamelnikoff/project-1-revature/blob/master/src/main/resources/frontend/screenshots/01_LoginPage.png "Optional title")

2. You will see the Employee screen:

![Alt text](https://github.com/olgamelnikoff/project-1-revature/blob/master/src/main/resources/frontend/screenshots/02_Employee_Dashboard.png "Optional title")

3. Hit "View Past Tickets" button:

![Alt text](https://github.com/olgamelnikoff/project-1-revature/blob/master/src/main/resources/frontend/screenshots/03_Past_Tickets.png "Optional title")

4. Go back and hit "Add Reimbursement Request" button, then fill out the form and hit "Submit Request":

![Alt text](https://github.com/olgamelnikoff/project-1-revature/blob/master/src/main/resources/frontend/screenshots/04_Submit_New_Request.png "Optional title")

5. You can reload the login page and enter "nectarine" as username and "password" as password. Then hit "View All Tickets" button:

![Alt text](https://github.com/olgamelnikoff/project-1-revature/blob/master/src/main/resources/frontend/screenshots/05_Finance_Manager_Dashboard.png "Optional title")

6. Then select "Pending" from the dropdown list:

![Alt text](https://github.com/olgamelnikoff/project-1-revature/blob/master/src/main/resources/frontend/screenshots/06_View_All_Tickets.png "Optional title")

7. It will direct you to the Pending Tickets page. You can then hit the "Approve" or "Reject" button.

![Alt text](https://github.com/olgamelnikoff/project-1-revature/blob/master/src/main/resources/frontend/screenshots/07_Pending_Tickets.png "Optional title")

## Contributors and License information
