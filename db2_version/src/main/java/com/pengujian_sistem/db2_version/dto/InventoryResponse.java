package com.pengujian_sistem.db2_version.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;

/**
 * @author Agung Prasetyo Nugroho <agungpn33@gmail.com>
 */
@Data
@Builder
public class InventoryResponse {
    private int id;
    private String cmpnycd;
    private String slipNumber;
    private String fLensColor;
    private Date transactionDate;
}
