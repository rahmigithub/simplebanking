package com.eteration.simplebanking.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper=false)
@Entity
@Data
@NoArgsConstructor
@Table(name = "deposit_transaction")
public class DepositTransaction extends Transaction {
    public DepositTransaction(Double amount) {
        super(amount);
    }

}
