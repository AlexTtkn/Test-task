package com.example.testtask.servise;

import com.example.testtask.OperationType;
import com.example.testtask.exception.NotEnoughMoneyException;
import com.example.testtask.exception.WalletNotFoundException;
import com.example.testtask.model.Wallet;
import com.example.testtask.dto.WalletDTO;
import com.example.testtask.repository.WalletRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;


    @Transactional
    public void update(WalletDTO wallet) {
        var walletInRepo = walletRepository.findById(wallet.getWalletId())
                .orElseThrow(WalletNotFoundException::new);

        if (wallet.getOperationType() == OperationType.DEPOSIT) {
            walletInRepo.setAmount(walletInRepo.getAmount() + wallet.getAmount());

        } else {
            if (walletInRepo.getAmount() < wallet.getAmount()) {
                throw new NotEnoughMoneyException();
            }
            walletInRepo.setAmount(walletInRepo.getAmount() - wallet.getAmount());
        }

        walletRepository.save(walletInRepo);

    }

    public Optional<Wallet> findById(UUID id) {
        return walletRepository.findById(id);

    }

}
