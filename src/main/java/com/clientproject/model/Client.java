package com.clientproject.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "FIRST_NAME")
    @NonNull
    private String firstName;

    @Column(name = "LAST_NAME")
    @NonNull
    private String lastName;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "ID_NUMBER")
    @NonNull
    private String idNumber;

    @Column(name = "PHYSICAL_ADDRESS")
    private String physicalAddress;

    public Client() {
    }

    public Client(String firstName, String lastName, String mobileNumber, String idNumber, String physicalAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.idNumber = idNumber;
        this.physicalAddress = physicalAddress;
    }
}
