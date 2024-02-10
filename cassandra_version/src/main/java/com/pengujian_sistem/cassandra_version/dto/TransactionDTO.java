package com.pengujian_sistem.cassandra_version.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class TransactionDTO {
    private UUID id;
    private String completed;
    private String cmpnycd;
    private String stockHandlingCustomerNumber;
    private String stockPoint;
    private String slipNumber;
    private String transactionCode;
    private String subTransactionCode;
    private LocalDate transactionDate;
    private int transactionTime;
    private String purchaseOrderNumber;
    private String shipmentNumber;
    private String supplierCompanyCode;
    private String supplierNumber;
    private String totDetLine;
    private String inTransitStockPoint;
    private String receiveNumber;
    private String rxArrangementNumber;
    private String originalStockPoint;
}
