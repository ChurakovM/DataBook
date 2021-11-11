package com.example.authenticationservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@Table(name="endusers")
public class UserModel {

    @Column(nullable = false, length = 50, unique = true, name = "USER_NAME")
    private String userName;

    @Column(nullable = false, length = 70, unique = true)
    private String email;

    @Column(nullable = false, name = "ENCRYPTED_PASSWORD")
    private String encryptedPassword;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
