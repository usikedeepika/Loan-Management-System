The Loan Application Management System is a microservices-based web application designed to streamline loan-related processes such as customer registration, loan application, eligibility verification, and payments—all without relying on traditional paper-based method

Features


Customer microservices handles customer related data 
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

