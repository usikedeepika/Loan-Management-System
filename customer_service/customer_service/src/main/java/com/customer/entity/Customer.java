package com.customer.entity;

import lombok.*;
import org.hibernate.annotations.MetaValue;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
//    @Transient
//    private List<Loan> loans = new ArrayList<>();
}
