package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {
    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();


    public void updateAccountsByTransfer(Transfer transfer) {
        Account accountFrom = getAccountById(transfer.getAccountFrom());
        Account accountTo = getAccountById(transfer.getAccountTo());
        BigDecimal amount = transfer.getAmount();
            if (transfer.getTransferType() == 1) {
                accountFrom.setBalance((accountFrom.getBalance().add(amount)));
                accountTo.setBalance((accountTo.getBalance().subtract(amount)));
            }
            if (transfer.getTransferType() == 2) {
                // BigDecimal math, we alter account balances HERE and then update the API
                accountFrom.setBalance((accountFrom.getBalance().subtract(amount)));
                accountTo.setBalance((accountTo.getBalance().add(amount))); // IS NOT adding properly yet
            }
        try {
            restTemplate.put(API_BASE_URL + "accounts/" + accountFrom.getAccountId(), makeAccountEntity(accountFrom));
            restTemplate.put(API_BASE_URL + "accounts/" + accountTo.getAccountId(), makeAccountEntity(accountTo));
        } catch (RestClientResponseException e) {
            // This is blank for now, but I'd imagine we ought to reset the account balances if the puts fail
            System.out.println("If you're seeing this, an exception must have been thrown.");
        }
    }

    public Account getAccountById(int id) {
        Account account = null;

        try {
            account = restTemplate.getForObject(API_BASE_URL + "accounts/" + id, Account.class);
        } catch (RestClientResponseException e) {
            // :P
        }

        return account;
    }

    private HttpEntity<Account> makeAccountEntity(Account account) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(account, headers);
    }
}
