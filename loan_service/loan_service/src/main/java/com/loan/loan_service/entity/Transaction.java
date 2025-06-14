package com.loan.loan_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    private int transId;
    private Timestamp transTime;
    private String mssg;

    private double transAmt;
    private int loanId;
    private int customerId;
}
