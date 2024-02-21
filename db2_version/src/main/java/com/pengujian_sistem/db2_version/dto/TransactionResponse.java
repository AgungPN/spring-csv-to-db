package com.pengujian_sistem.db2_version.dto;

import lombok.Data;
import java.sql.Date;

/**
 * @author Agung Prasetyo Nugroho <agungpn33@gmail.com>
 */
@Data
public class TransactionResponse {
    private int id;
    private String cmpnycd;
    private String stockPoint;
    private String purchaseOrderNumber;
    private Date transactionDate;
}
