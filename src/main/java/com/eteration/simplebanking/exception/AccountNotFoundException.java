package com.eteration.simplebanking.exception;

public class AccountNotFoundException extends Exception {

    private String message;
    public AccountNotFoundException(String accountNumber) {
        this.message = "Account " + accountNumber + " not found";
    }
}
