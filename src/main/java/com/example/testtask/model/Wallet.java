package com.example.testtask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "wallet")
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;

    @Column
    private Long amount;

}
