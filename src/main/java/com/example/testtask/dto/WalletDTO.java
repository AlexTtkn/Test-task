package com.example.testtask.dto;

import com.example.testtask.OperationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class WalletDTO {

    @NotNull
    private UUID walletId;

    @NotNull
    private OperationType operationType;

    @NotNull
    @Positive
    private Long amount;

}
