package com.eteration.simplebanking.services;


import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
    public TransactionStatus operateTransaction(String accountNumber, Transaction transaction) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        if(account == null)
        {
            throw new AccountNotFoundException(accountNumber);
        }

        UUID approvalCode = UUID.randomUUID();

        transaction.setAccount(account);
        transaction.setApprovalCode(approvalCode);

        account.post(transaction);

        accountRepository.save(account);

        return new TransactionStatus("OK", approvalCode);
    }

}
