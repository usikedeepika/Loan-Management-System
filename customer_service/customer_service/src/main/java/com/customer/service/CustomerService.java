package com.customer.service;

import com.customer.entity.Customer;
import com.customer.entity.Loan;
import com.customer.entity.Transaction;

import java.util.List;

public interface CustomerService {

    public String applyLoan(Loan loan);
    public String makeTransaction(Transaction transaction);
    public Integer doLogin(String email, String password);
    public Customer addCustomer(Customer c);
    public Customer updateCustomer(Customer c);
    public List<Customer> getCustomers();
    public Customer getCustomerById(int custId);

    public boolean checkLoanEligibility(int customerId,int loanId);
}
