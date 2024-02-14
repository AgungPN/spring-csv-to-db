package com.pengujian_sistem.db2_version.service;

import com.pengujian_sistem.db2_version.helper.Helpers;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.StringTokenizer;

@Service
public class TransactionService {
    public final static String INSERT_QUERY = "INSERT INTO TRANSACTION (Completed,CMPNYCD, STOCK_HANDLING_CUSTOMER_NUMBER, STOCK_POINT, SLIP_NUMBER, " +
            "TRANSACTION_CODE, SUB_TRANSACTION_CODE, TRANSACTION_DATE, TRANSACTION_TIME, PURCHASE_ORDER_NUMBER, SHIPMENT_NUMBER, " +
            "SUPPLIER_COMPANY_CODE, SUPPLIER_NUMBER, TOTDETLINE, INTRANSIT_STOCK_POINT, RECEIVE_NUMBER, RX_ARRANGEMENT_NUMBER, " +
            "ORIGINAL_STOCK_POINT) " +
            "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public Object[] addToRows(String content) throws ParseException {
        StringTokenizer tkn = new StringTokenizer(content, ",");
        return new Object[]{
                tkn.nextToken(), // Completed
                tkn.nextToken(), // CMPNYCD
                tkn.nextToken(), // STOCK_HANDLING_CUSTOMER_NUMBER
                tkn.nextToken(), // STOCK_POINT
                tkn.nextToken(), // SLIP_NUMBER
                tkn.nextToken(), // TRANSACTION_CODE
                tkn.nextToken(), // SUB_TRANSACTION_CODE
                Helpers.convertStringToDate(tkn.nextToken()), // TRANSACTION_DATE
                Integer.parseInt(tkn.nextToken()), // TRANSACTION_TIME
                tkn.nextToken(), // PURCHASE_ORDER_NUMBER
                tkn.nextToken(), // SHIPMENT_NUMBER
                tkn.nextToken(), // SUPPLIER_COMPANY_CODE
                tkn.nextToken(), // SUPPLIER_NUMBER
                Integer.parseInt(tkn.nextToken()), // TOTDETLINE
                tkn.nextToken(), // INTRANSIT_STOCK_POINT
                Integer.parseInt(tkn.nextToken()), // RECEIVE_NUMBER
                tkn.nextToken(), // RX_ARRANGEMENT_NUMBER
                tkn.nextToken()  // ORIGINAL_STOCK_POINT
        };
    }
}
