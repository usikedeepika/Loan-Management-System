package com.loan.loan_service.repository;

import com.loan.loan_service.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {
//    List<Loan> findByLoanId(int loanId);
//    List<Loan> findByCustomerId(int customerId);
}
