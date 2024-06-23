package com.saas.starter.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String status;

    @Column
    private String subscriptionPlan;

    @Column
    private String billingInfo;

    @Column
    private String contactPerson;

    @Column
    private String phoneNumber;

    @Column
    private String address;
}
