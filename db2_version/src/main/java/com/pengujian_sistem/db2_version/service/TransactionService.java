package com.pengujian_sistem.db2_version.service;

import com.pengujian_sistem.db2_version.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    public final static String INSERT_QUERY = "INSERT INTO DB2ADMIN.TRANSACTION (Completed,CMPNYCD, STOCK_HANDLING_CUSTOMER_NUMBER, STOCK_POINT, SLIP_NUMBER, " +
            "TRANSACTION_CODE, SUB_TRANSACTION_CODE, TRANSACTION_DATE, TRANSACTION_TIME, PURCHASE_ORDER_NUMBER, SHIPMENT_NUMBER, " +
            "SUPPLIER_COMPANY_CODE, SUPPLIER_NUMBER, TOTDETLINE, INTRANSIT_STOCK_POINT, RECEIVE_NUMBER, RX_ARRANGEMENT_NUMBER, " +
            "ORIGINAL_STOCK_POINT) " +
            "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public List<TransactionDTO> getList() {
        return null;
    }
}
