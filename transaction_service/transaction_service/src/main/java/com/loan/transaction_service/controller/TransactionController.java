package com.loan.transaction_service.controller;

import com.loan.transaction_service.entity.Transaction;
import com.loan.transaction_service.repository.TransactionRepository;
import com.loan.transaction_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
        return new ResponseEntity<Transaction>(transactionService.addTransaction(transaction), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getTransactions() {
        return new ResponseEntity<List<Transaction>>(transactionService.getTransactions(), HttpStatus.OK);
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByLoanId(@PathVariable int id) {
        return new ResponseEntity<List<Transaction>>(transactionService.getTransactionsByLoanId(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByCustomerId(@PathVariable int id) {
        return new ResponseEntity<List<Transaction>>(transactionService.getTransactionsByCustomerId(id), HttpStatus.OK);
    }

}
