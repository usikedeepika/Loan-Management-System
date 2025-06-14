package com.loan.loan_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {
    private int id;
    private String fname;
    private String lname;
    private String gender;
    private long phone;
    private String email;
    private String password;
    private String confirmPassword;
    private float salary;
    private long adhaar;
    private String pan;
    private double walletAmt;
//    private List<Loan> loans = new ArrayList<>();
}

