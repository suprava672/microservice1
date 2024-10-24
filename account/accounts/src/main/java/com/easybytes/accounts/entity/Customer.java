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
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy="native")
    @Column(name="customerId")
    private Long customerId;
    @Column(name="name")
    private  String name;
    @Column(name="email")
    private String email;
    @Column(name="mobileNumber")
    private String mobileNumber;

}
