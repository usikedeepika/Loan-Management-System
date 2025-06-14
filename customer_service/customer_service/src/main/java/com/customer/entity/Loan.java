package com.customer.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Loan {
    private int loanId;
    private double loanAmt;
    private String loanType;
    private int duration;
    private int customerId;
}
