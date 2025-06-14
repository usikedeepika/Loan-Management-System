package com.customer.service;

import com.customer.entity.Customer;
import com.customer.entity.Loan;
import com.customer.entity.Transaction;
import com.customer.exception.CustomerAlreadyRegisteredException;
import com.customer.exception.CustomerNotFoundException;
import com.customer.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String applyLoan(Loan loan) {
        int customerId = loan.getCustomerId();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        Loan loan1 = restTemplate.postForObject("http://localhost:8082/loan/add",loan,Loan.class);
        if(loan1!=null)
            return "Loan Applied Successfully";
        return "Loan Application failed";
    }

    @Override
    public String makeTransaction(Transaction transaction) {
        int customerId = transaction.getCustomerId();
        int loanId = transaction.getLoanId();
        Loan loan = restTemplate.getForObject("http://localhost:8082/loan/"+loanId, Loan.class);
        if(customerId==loan.getCustomerId()){
            Transaction transaction1 = restTemplate.postForObject("http://localhost:8083/transaction/add",transaction,Transaction.class);
            double amount = loan.getLoanAmt()-transaction.getTransAmt();
            loan.setLoanAmt(amount);
            restTemplate.put("http://localhost:8082/loan/update",loan,Loan.class);
            return "Transaction successful";
        }
        return "Transaction failed";
    }

    @Override
    public Integer doLogin(String email, String password) {
        Integer customerId = null;
        customerId = customerRepository.findCustomerByEmailAndPassword(email, password);
        if(customerId!=null){
            logger.info("Customer: " + customerId + " Logged In Successfully");
            return customerId;
        }
        else{
            throw new CustomerNotFoundException("Customer Not Found: " + customerId);
        }
    }



    @Override
    public Customer addCustomer(Customer c) {
        Customer customer = customerRepository.checkCustomer(c.getEmail(), c.getAdhaar(), c.getPan(), c.getPhone());
        if (customer != null) {
            throw new CustomerAlreadyRegisteredException("Customer Already Registered: " + customer.getId());
        }
        return customerRepository.save(c);
    }

    @Override
    public Customer updateCustomer(Customer c) {
        Customer customer = customerRepository.findById(c.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + c.getId()));
        BeanUtils.copyProperties(c, customer);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if(customers.isEmpty())
            throw new CustomerNotFoundException("No customers found");
        return customers;
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        logger.info("Customer Found: " + customerId);
        return customer;
    }

    @Override
    public boolean checkLoanEligibility(int id,int loanId){
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + id));
        Loan loan = restTemplate.getForObject("http://localhost:8082/loan/"+loanId, Loan.class);
        logger.info("{}");
        double l = loan.getLoanAmt();
        final double min_salary = 1000;
        final double max_loan_amount = 60*(0.6*c.getSalary());
        return (l<=max_loan_amount)&&(c.getSalary()>=min_salary);
    }



}