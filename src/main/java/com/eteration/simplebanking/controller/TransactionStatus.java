package com.eteration.simplebanking.controller;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class TransactionStatus {
    private String status;
    private UUID approvalCode;
}
