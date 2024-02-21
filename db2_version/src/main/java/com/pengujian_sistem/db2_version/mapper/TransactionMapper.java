package com.pengujian_sistem.db2_version.mapper;

import com.pengujian_sistem.db2_version.dto.TransactionResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements RowMapper<TransactionResponse> {
    @Override
    public TransactionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TransactionResponse.builder()
                .id(rs.getInt("ID"))
                .companyCode(rs.getString("COMPANY_CODE"))
                .stockPoint(rs.getString("STOCK_POINT"))
                .transactionCode(rs.getString("TRANSACTION_CODE"))
                .transactionDate(rs.getDate("TRANSACTION_DATE"))
                .build();


    }
}
