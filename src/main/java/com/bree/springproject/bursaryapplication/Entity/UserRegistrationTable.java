package com.bree.springproject.bursaryapplication.Entity;

import jakarta.persistence.*;

@Entity
@Table()
public class UserRegistrationTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

}
