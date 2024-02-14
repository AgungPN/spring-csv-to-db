package com.pengujian_sistem.db2_version.mapper;

import com.pengujian_sistem.db2_version.dto.TransactionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements RowMapper<TransactionDTO> {
    @Override
    public TransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TransactionDTO.builder()
                .id(rs.getInt("ID"))
                .cmpnycd(rs.getString("CMPNYCD"))
                .stockHandlingCustomerNumber(rs.getString("STOCK_HANDLING_CUSTOMER_NUMBER"))
                .stockPoint(rs.getString("STOCK_POINT"))
                .slipNumber(rs.getString("SLIP_NUMBER"))
                .transactionCode(rs.getString("TRANSACTION_CODE"))
                .subTransactionCode(rs.getString("SUB_TRANSACTION_CODE"))
                .transactionDate(rs.getDate("TRANSACTION_DATE"))
                .transactionTime(rs.getInt("TRANSACTION_TIME"))
                .purchaseOrderNumber(rs.getString("PURCHASE_ORDER_NUMBER"))
                .shipmentNumber(rs.getString("SHIPMENT_NUMBER"))
                .supplierCompanyCode(rs.getString("SUPPLIER_COMPANY_CODE"))
                .supplierNumber(rs.getString("SUPPLIER_NUMBER"))
                .totdetline(rs.getInt("TOTDETLINE"))
                .intransitStockPoint(rs.getString("INTRANSIT_STOCK_POINT"))
                .receiveNumber(rs.getInt("RECEIVE_NUMBER"))
                .rxArrangementNumber(rs.getString("RX_ARRANGEMENT_NUMBER"))
                .originalStockPoint(rs.getString("ORIGINAL_STOCK_POINT"))
                .build();

    }
}
