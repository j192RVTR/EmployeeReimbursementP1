# Employee Reimbursement P1

## Project Description
The Employee Reimbursement System is designed to manage the process of reimbursing employees for expenses incurred while on company time. 
All employees in the company can login (or reset password if forgotten, prompting a password reset email) and submit requests for reimbursement and view their past tickets and pending requests. 
Finance managers can log in and view all reimbursement requests and history for all employees in the company. 
Finance managers are authorized to approve and deny requests for expense reimbursement.reimbursement requests. 
Employees can optionally upload an image of his/her receipt as part of the reimbursement request, which managers may view as part of the reimbursement. 
Upon reimbursement rquest resolution, an employee will receive an email containing information the status of their reimbursement. 
New Employees can be registered by managers and will receive an email containing their credentials.

## Technologies Used

* Java 11+
* Hibernate
* Tomcat 10
* MySQL
* Java Servlets
* JS / HTML / CSS / JSPs
* XML

## Features

List of features ready
* Add Employee as Manager
* Employee Login
* Manager Login
* Reset Password with Email
* Add Reimbursement Request as Employee
* View Your Reimbursement Requests as Employee
* View Profile
* Update Profile
* Logout
* Search Reimbursements by Employee Name / Reimbursement Status as Manager
* Approve / Deny Reimbursements
* Send Email on Reimbursement Status Change to Employee
* Send Email with Credentials to Added Employee

To-do list:
* Code Refactoring / Organization
* Standardize look of sent emails to match Project

## Getting Started
   
git clone https://github.com/j192RVTR/EmployeeReimbursementP1.git

### Requires

* Tomcat Server
* Preconfigured MySQL database
* Hibernate Configuration to connect to mySQL Database 
* credentials.properties file containing credentials for testrevap1@gmail.com

## Usage

Once Tomcat Server is Started, usage should be fairly straightforward.

LOGIN

![Screenshot (37)](https://user-images.githubusercontent.com/98780372/160198855-9b78d0ca-8e90-472d-a9c4-de828b8f7bda.png)

View REIMBURSEMENTS (AS MANAGER)

![Screenshot (35)](https://user-images.githubusercontent.com/98780372/160198911-c0670827-bc10-4fb4-881c-dc8e8e29f96f.png)

RESET PASSWORD

![Screenshot (38)](https://user-images.githubusercontent.com/98780372/160198998-8045ee59-8576-4980-8ff2-9188563d704a.png)

PASSWORD RESET EMAIL

![Screenshot (39)](https://user-images.githubusercontent.com/98780372/160199015-9839d54d-b82c-49a1-8b4a-ed245efab3f2.png)

## Contributors

This was a Solo Project.
