package com.loan.loan_service.controller;


import com.loan.loan_service.entity.Loan;
import com.loan.loan_service.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("/add")
    public ResponseEntity<Loan> addLoan(@RequestBody Loan l){
        return new ResponseEntity<Loan>(loanService.addLoan(l),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanByLoanId(@PathVariable int id) {
        return new ResponseEntity<Loan>(loanService.getLoanByLoanId(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Loan>> getLoansByCustomerId(@PathVariable int id) {
        return new ResponseEntity<List<Loan>>(loanService.getLoansByCustomerId(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Loan>> getLoans() {
        return new ResponseEntity<List<Loan>>(loanService.getLoans(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Loan> updateCustomer(@RequestBody Loan l) {
        return new ResponseEntity<Loan>(loanService.updateLoan(l), HttpStatus.OK);
    }
    @DeleteMapping("/forclose/{loanId}")
    public ResponseEntity<String> forecloseLoan(@PathVariable int loanId) {
        loanService.forCloseLoan(loanId);
        return new ResponseEntity<String>("Loan Foreclosed with Loan Id: " + loanId, HttpStatus.OK);
    }
    @GetMapping("/status/{loanId}")
    public ResponseEntity<String> getStatus(@PathVariable int loanId){
        int status = loanService.getStatus(loanId);
        if(status==1){
            return ResponseEntity.ok("Loan with Loan Id: " + loanId+", is accepted");
        }
        else{
            return ResponseEntity.badRequest().body("Loan with Loan Id: " + loanId+", is rejected");
        }

    }
    @GetMapping("/emiCalculate/{loanId}")
    public double getLoanEmi(@PathVariable int loanId)
    {
        double l=loanService.getLoanEmi(loanId);
        return l;
    }

}
