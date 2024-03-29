package com.pengujian_sistem.db2_version.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;

/**
 * @author Agung Prasetyo Nugroho <agungpn33@gmail.com>
 */
@Data
@Builder
public class TransactionResponse {
    private int id;
    private String companyCode;
    private String stockPoint;
    private String transactionCode;
    private Date transactionDate;
}
