package com.example.testtask.controller;

import com.example.testtask.dto.WalletDTO;
import com.example.testtask.model.Wallet;
import com.example.testtask.repository.WalletRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testWalletOperation() throws Exception {
        Wallet walletInRepo = new Wallet();
        walletInRepo.setId(UUID.randomUUID());
        walletInRepo.setAmount(1000L);

        walletRepository.save(walletInRepo);

        WalletDTO wallet = new WalletDTO();
        wallet.setAmount(1000L);

        var request = post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(walletInRepo));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("UUID").isEqualTo(walletInRepo.getId()),
                v -> v.node("amount").isEqualTo(walletInRepo.getAmount() + wallet.getAmount())

        );
    }

    @Test
    public void testFindById() throws Exception {
        WalletDTO wallet = new WalletDTO();
        wallet.setAmount(1000L);


        var request = get("/api/v1/wallets/{id}", wallet.getWalletId());

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("UUID").isEqualTo(wallet.getWalletId()),
                v -> v.node("amount").isEqualTo(wallet.getAmount())
        );
    }
}
