package com.pengujian_sistem.db2_version.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private String cmpnycd;
    private String stockHandlingCustomerNumber;
    private String stockPoint;
    private String slipNumber;
    private String transactionCode;
    private String subTransactionCode;
    private Integer transactionDate;
    private Integer transactionTime;
    private String purchaseOrderNumber;
    private String shipmentNumber;
    private String supplierCompanyCode;
    private String supplierNumber;
    private Integer totdetline;
    private String intransitStockPoint;
    private Integer receiveNumber;
    private Integer rxArrangementNumber;
    private String originalStockPoint;
}
