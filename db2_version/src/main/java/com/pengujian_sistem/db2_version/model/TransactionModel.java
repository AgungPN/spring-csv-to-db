package com.pengujian_sistem.db2_version.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class TransactionModel {

    public static String sql = "INSERT INTO DB2ADMIN.TRANSACTION (Completed,CMPNYCD, STOCK_HANDLING_CUSTOMER_NUMBER, STOCK_POINT, SLIP_NUMBER, " +
            "TRANSACTION_CODE, SUB_TRANSACTION_CODE, TRANSACTION_DATE, TRANSACTION_TIME, PURCHASE_ORDER_NUMBER, SHIPMENT_NUMBER, " +
            "SUPPLIER_COMPANY_CODE, SUPPLIER_NUMBER, TOTDETLINE, INTRANSIT_STOCK_POINT, RECEIVE_NUMBER, RX_ARRANGEMENT_NUMBER, " +
            "ORIGINAL_STOCK_POINT) " +
            "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String sqlTesting = "INSERT INTO DB2INST1.TESTING (CMPNYCD) VALUES (?)";
    public static String insertQuery = "INSERT INTO TRANSACTION (" +
            "    CMPNYCD," +
            "    STOCK_HANDLING_CUSTOMER_NUMBER," +
            "    STOCK_POINT," +
            "    SLIP_NUMBER," +
            "    TRANSACTION_CODE," +
            "    SUB_TRANSACTION_CODE," +
            "    TRANSACTION_DATE," +
            "    TRANSACTION_TIME," +
            "    PURCHASE_ORDER_NUMBER," +
            "    SHIPMENT_NUMBER," +
            "    SUPPLIER_COMPANY_CODE," +
            "    SUPPLIER_NUMBER," +
            "    TOTDETLINE," +
            "    INTRANSIT_STOCK_POINT," +
            "    RECEIVE_NUMBER," +
            "    RX_ARRANGEMENT_NUMBER," +
            "    ORIGINAL_STOCK_POINT" +
            ") VALUES ";

    private List<String> transactionValues = new ArrayList<>();

    public void addTransactionValue(String value) {
        transactionValues.add(value);
    }
}
