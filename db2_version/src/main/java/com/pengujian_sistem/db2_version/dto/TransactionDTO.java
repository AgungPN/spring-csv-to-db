package com.pengujian_sistem.db2_version.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private int id;
    private String cmpnycd;
    private String stockHandlingCustomerNumber;
    private String stockPoint;
    private String slipNumber;
    private String transactionCode;
    private String subTransactionCode;
    private Date transactionDate;
    private Integer transactionTime;
    private String purchaseOrderNumber;
    private String shipmentNumber;
    private String supplierCompanyCode;
    private String supplierNumber;
    private Integer totdetline;
    private String intransitStockPoint;
    private Integer receiveNumber;
    private String rxArrangementNumber;
    private String originalStockPoint;
}
