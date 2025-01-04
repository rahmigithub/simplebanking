package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        return new ResponseEntity<>(accountService.findAccount(accountNumber), HttpStatus.OK);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction transaction) throws InsufficientBalanceException, AccountNotFoundException {
        return new ResponseEntity<>(accountService.operateTransaction(accountNumber, transaction), HttpStatus.OK);
    }
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction transaction) throws InsufficientBalanceException, AccountNotFoundException {
        return new ResponseEntity<>(accountService.operateTransaction(accountNumber, transaction), HttpStatus.OK);
	}
    @PostMapping("/phoneBillPayment/{accountNumber}")
    public ResponseEntity<TransactionStatus> phoneBillPayment(@PathVariable String accountNumber, @RequestBody PhoneBillPaymentTransaction transaction) throws InsufficientBalanceException, AccountNotFoundException {
        return new ResponseEntity<>(accountService.operateTransaction(accountNumber, transaction), HttpStatus.OK);
    }

}
