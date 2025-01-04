package com.eteration.simplebanking.model;


import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @JsonIgnore
    private UUID id;

    private String owner;
    private String accountNumber;
    private Double balance;
    private Timestamp createDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        balance = 0.0;
        transactions = new ArrayList<>();
        createDate = new Timestamp(System.currentTimeMillis());
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        if(transaction == null)
            return;

        if(transaction.getAmount() == null)
            return;

        double amount = transaction.getAmount();
        if(transaction.isDeposit())
            deposit(amount);
        else
            withdraw(amount);

        transactions.add(transaction);
    }
    public void deposit(Double amount) {
        if(amount == null)
            return;

        balance += amount;
    }
    public void withdraw(Double amount) throws InsufficientBalanceException {
        if(amount == null)
            return;

        if(balance < amount)
            throw new InsufficientBalanceException();

        balance -= amount;
    }

}
