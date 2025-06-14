package com.loan.loan_service.service;

import com.loan.loan_service.entity.Loan;

import java.util.List;

public interface LoanService {
    public Loan addLoan(Loan loan);

//    public Loan applyLoan(Loan loan);

    public List<Loan> getLoansByCustomerId(int custId);
    public Loan getLoanByLoanId(int loanId);

    public void forCloseLoan(int loanId);
    public List<Loan> getLoans();

    public Loan updateLoan(Loan l);
    public int getStatus(int loanId);

    double getLoanEmi(int loanId);
}
