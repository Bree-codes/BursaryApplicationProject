package com.bree.springproject.onlinebursaryapplication.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import java.util.List;

@Entity
@Table()
@Data
@Component
public class  UserRegistrationTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column()
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column()
    private Boolean status = false;


}

