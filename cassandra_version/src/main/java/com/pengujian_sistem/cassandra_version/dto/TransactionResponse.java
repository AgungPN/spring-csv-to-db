package com.pengujian_sistem.cassandra_version.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Agung Prasetyo Nugroho <agungpn33@gmail.com>
 */
@Data
@Builder
public class TransactionResponse {
    private UUID id;
    private String companyCode;
    private String stockPoint;
    private String transactionCode;
    private LocalDate transactionDate;
}
