package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @JsonIgnore
    private UUID id;
    private Timestamp date;
    private Double amount;
    private UUID approvalCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    public Transaction(){
        this.date = new Timestamp(System.currentTimeMillis());
    }
    Transaction(Double amount){
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.date = new Timestamp(System.currentTimeMillis());
    }

    @JsonIgnore
    public boolean isDeposit(){
        return true;
    }
    public String getType(){
        return getClass().getSimpleName();
    }
}
