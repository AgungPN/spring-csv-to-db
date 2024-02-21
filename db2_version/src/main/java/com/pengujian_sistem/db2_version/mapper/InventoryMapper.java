package com.pengujian_sistem.db2_version.mapper;

import com.pengujian_sistem.db2_version.dto.InventoryDTO;
import com.pengujian_sistem.db2_version.dto.InventoryResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryMapper implements RowMapper<InventoryResponse> {
    @Override
    public InventoryResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return InventoryResponse.builder()
                .id(rs.getInt("ID"))
                .companyCode(rs.getString("COMPANY_CODE"))
                .slipNumber(rs.getString("SLIP_NUMBER"))
                .transactionDate(rs.getDate("TRANSACTION_DATE"))
                .build();
    }
}
