package com.loan.transaction_service.repository;

import com.loan.transaction_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByLoanId(int loanId);
    List<Transaction> findByCustomerId(int customerId);
}
