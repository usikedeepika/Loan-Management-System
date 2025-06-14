package com.customer.controller;

import com.customer.entity.Customer;
import com.customer.entity.Loan;
import com.customer.entity.Transaction;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) {
        return new ResponseEntity<Customer>(customerService.addCustomer(c), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        return new ResponseEntity<Customer>(customerService.getCustomerById(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<List<Customer>>(customerService.getCustomers(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) {
        return new ResponseEntity<Customer>(customerService.updateCustomer(c), HttpStatus.OK);
    }


    @PostMapping("/apply")
    public ResponseEntity<String> applyLoan(@RequestBody Loan loan) {
        return new ResponseEntity<String>(customerService.applyLoan(loan), HttpStatus.OK);
    }

    @PostMapping("/makePayment")
    public ResponseEntity<String> makePayment(@RequestBody Transaction transaction){
        return new ResponseEntity<>(customerService.makeTransaction(transaction),HttpStatus.OK);
    }

    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> doLogin(@RequestParam String email, @RequestParam String password) {
        return new ResponseEntity<Integer>(customerService.doLogin(email, password), HttpStatus.OK);
    }
    @PostMapping("/checkEligibility")
    public ResponseEntity<String> checkEligibility(@RequestParam int id, @RequestParam int loanId) {
//        return new ResponseEntity<Boolean>(customerService.checkLoanEligibility(id, loan), HttpStatus.OK);
        boolean isEligible = customerService.checkLoanEligibility(id,loanId);
        if(isEligible){
            return ResponseEntity.ok("Congratulations! You are eligible for the loan");
        }
        else{
            return ResponseEntity.badRequest().body("Sorry, You are not eligible for the loan");
        }
    }

    @GetMapping("/checkEligibility/{id}/{loanId}")
    public int checkLoanEligibility(@PathVariable int id, @PathVariable int loanId) {
//        return new ResponseEntity<Boolean>(customerService.checkLoanEligibility(id, loan), HttpStatus.OK);
        boolean isEligible = customerService.checkLoanEligibility(id,loanId);
        if(isEligible){
            return 1;
        }
        else{
            return 0;
        }
    }
}
