package com.loan.transaction_service.service;

import com.loan.transaction_service.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    List<Transaction> getTransactions();

    List<Transaction> getTransactionsByCustomerId(int customerId);

    List<Transaction> getTransactionsByLoanId(int loanId);
}
