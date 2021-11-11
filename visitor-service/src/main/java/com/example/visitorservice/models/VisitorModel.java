package com.example.visitorservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="visitors")
public class VisitorModel {

    // TODO how can I generate unique ID?, sequence doesn't work properly
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, length = 70)
    private String firstName;

    @Column(nullable = false, length = 70)
    private String lastName;

    @Column(nullable = false, length = 70, unique = true)
    private String email;

    @Column(nullable = false, length = 70, unique = true)
    private String phoneNumber;
}
