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
@Table(name = "phone_bill_payment_transaction")
public class PhoneBillPaymentTransaction extends BillPaymentTransaction {

    private String phoneNumber;

    PhoneBillPaymentTransaction(Double amount, String payee, String phoneNumber) {
        super(amount, payee);
        this.phoneNumber = phoneNumber;
    }
}
