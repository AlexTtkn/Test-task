package com.example.testtask.controller;


import com.example.testtask.dto.ErrorDTO;
import com.example.testtask.dto.WalletDTO;
import com.example.testtask.servise.WalletService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService service;

    @PostMapping("/wallet")
    public ResponseEntity<?> update(@Valid @RequestBody WalletDTO wallet) {
        try {
            service.update(wallet);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Error"));
        }
    }

    @GetMapping("/wallets/{id}")
    public ResponseEntity<?> findById(@NotNull @PathVariable UUID id) {
        var wallet = service.findById(id);
        if (wallet.isPresent()) {
            return ResponseEntity.ok(wallet.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
