The Loan Application Management System is a microservices-based web application designed to streamline loan-related processes such as customer registration, loan application, eligibility verification, and payments—all without relying on traditional paper-based method

Features

This system allows customers to register, log in, and apply for loans while checking their eligibility and loan status.
Admins or services can create, update, and foreclose loans, and also calculate EMI dynamically.
Customers can make payments, and all transactions are recorded and retrieved by customer or loan ID.
It supports full CRUD operations and provides endpoints for tracking loan lifecycle and customer financial activity.


  📘 CustomerMicroservice – API Endpoints
1. POST /customer/add
Description: Create a new customer.

2. GET /customer/{id}
Description: Retrieve a customer by their ID.

3. GET /customer/all
Description: Get a list of all customers.

4. PUT /customer/update
Description: Update an existing customer's details.

5. POST /customer/apply
Description: Apply for a loan.

6. POST /customer/makePayment
Description: Make a loan payment (transaction).

7. POST /customer/login?email=...&password=...
Description: Customer login with email and password.

8. POST /customer/checkEligibility?id=...&loanId=...
Description: Check loan eligibility using query parameters.

9. GET /customer/checkEligibility/{id}/{loanId}
Description: Check loan eligibility using path variables.

📘 LoanMicroservice – API Endpoints
Base URL: /loan

1. POST /loan/add
Description: Create a new loan entry.

2. GET /loan/{id}
Description: Retrieve loan details by loan ID.

3. GET /loan/customer/{id}
Description: Get all loans associated with a specific customer ID.

4. GET /loan/all
Description: Retrieve a list of all loans.

5. PUT /loan/update
Description: Update loan details.

6. DELETE /loan/forclose/{loanId}
Description: Foreclose a loan by loan ID.

7. GET /loan/status/{loanId}
Description: Check the approval status of a loan.

8. GET /loan/emiCalculate/{loanId}
Description: Calculate and return the EMI amount for a given loan ID

📘 TransactionMicroservice – API Endpoints
Base URL: /transaction

1. POST /transaction/add
Description: Add a new transaction record.

2. GET /transaction/all
Description: Retrieve a list of all transactions.

3. GET /transaction/loans/{id}
Description: Get all transactions associated with a specific loan ID.

4. GET /transaction/customer/{id}
Description: Get all transactions associated with a specific customer ID.



