package com.bree.springproject.onlinebursaryapplication.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table()
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
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

    private String role;

    @PrePersist
    private void setDefaultValue()
    {
        if(this.role == null)
        {
            this.role = "user";
        }
    }
}

