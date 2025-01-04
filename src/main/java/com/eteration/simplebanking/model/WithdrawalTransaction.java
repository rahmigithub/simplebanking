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
@Table(name = "withdrawal_transaction")
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(Double amount) {
        super(amount);
    }

    public boolean isDeposit(){
        return false;
    }

}
