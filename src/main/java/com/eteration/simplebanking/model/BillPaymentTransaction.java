package com.eteration.simplebanking.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "bill_payment_transaction")
public abstract class BillPaymentTransaction extends Transaction {
    private String payee;

    BillPaymentTransaction(Double amount, String payee) {
        super(amount);
        this.payee = payee;
    }

    public boolean isDeposit(){
        return false;
    }

}
