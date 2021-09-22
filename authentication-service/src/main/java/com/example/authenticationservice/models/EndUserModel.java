package com.example.authenticationservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="endusers")
public class EndUserModel {

    @Column(nullable = false, length = 50, unique = true)
    private String userName;

    @Column(nullable = false, length = 70, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String encryptedPassword;

    @Id
    @GeneratedValue
    private long id;
}
