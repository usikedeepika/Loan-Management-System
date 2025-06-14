package com.loan.transaction_service.service;

import com.loan.transaction_service.entity.Transaction;
import com.loan.transaction_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class
TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByCustomerId(int customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Transaction> getTransactionsByLoanId(int loanId) {
        return transactionRepository.findByLoanId(loanId);
    }
}
