package com.easybytes.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {
    @Column(name="customerId")
    private Long customerId;
    @Column(name="accountNumber")
    @Id
    private Long accountNumber;
    @Column(name="accountType")
    private String accountType;
    @Column(name="branchAddress")
    private String branchAddress;
}
