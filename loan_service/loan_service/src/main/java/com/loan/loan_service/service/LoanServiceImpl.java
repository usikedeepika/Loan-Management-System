package com.loan.loan_service.service;

import com.loan.loan_service.entity.Customer;
import com.loan.loan_service.entity.Loan;
import com.loan.loan_service.exception.LoanNotFoundException;
import com.loan.loan_service.repository.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.apache.log4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.geom.RectangularShape;
import java.beans.Customizer;
import java.util.*;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = Logger.getLogger(getClass());
    @Override
    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoansByCustomerId(int custId) {
        List<Loan> sol = new ArrayList<>();
        List<Loan> loans = getLoans();
        for(int i=0;i<loans.size();i++){
            int customerId = loans.get(i).getCustomerId();
            if(customerId==custId){
                sol.add(loans.get(i));
            }
        }
        return sol;
    }

    @Override
    public Loan getLoanByLoanId(int loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
        return loan;
    }

    @Override
    public void forCloseLoan(int loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
        loanRepository.delete(loan);

    }

    @Override
    public List<Loan> getLoans() {
        List<Loan> loans = loanRepository.findAll();
        if(loans.isEmpty())
            throw new LoanNotFoundException("No loans");
        return loans;
    }
    @Override
    public Loan updateLoan(Loan l) {
        Loan loan = loanRepository.findById(l.getLoanId())
                .orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + l.getLoanId()));
        BeanUtils.copyProperties(l, loan);
        return loanRepository.save(loan);
    }

    @Override
    public int getStatus(int loanId) {
        Loan l = loanRepository.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
        String url = "http://localhost:8081/customer/checkEligibility/"+l.getCustomerId()+"/"+loanId;
        int response = restTemplate.getForObject(url,Integer.class);
        return response;
    }
    public double getLoanEmi(int loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));

        int interest=loan.getInterest();
        double loanAmt=loan.getLoanAmt();
        int duration=loan.getDuration();
//        double d=interest*duration*Math.pow((1+interest),duration)/(Math.pow((1+interest),duration)-1);
       double emi;

        interest = interest/ (12 * 100); // one month interest
        duration = duration * 12; // one month period
        emi = (loanAmt * interest * Math.pow(1 + interest, duration)) / (Math.pow(1 + interest, duration) - 1);

        return (emi);
    }
}
