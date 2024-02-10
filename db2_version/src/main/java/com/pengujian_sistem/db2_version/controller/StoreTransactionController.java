package com.pengujian_sistem.db2_version.controller;

import com.pengujian_sistem.db2_version.dto.TransactionDTO;
import com.pengujian_sistem.db2_version.helper.Helpers;
import com.pengujian_sistem.db2_version.model.InventoryModel;
import com.pengujian_sistem.db2_version.model.TransactionModel;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.ParseException;
import java.util.*;

@RestController
@AllArgsConstructor
public class StoreTransactionController {
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/testing")
    public void coba() throws IOException {
        String pathPending = "C:\\Users\\ThinkPad T480s\\Downloads\\tr_pengujian_sistem\\db2_version\\assets\\pending\\";

        try {
            File folder = new File(pathPending);
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;

            for (File file : listOfFiles) {
                List<String> contents = Files.readAllLines(Paths.get(file.getAbsolutePath()));

                boolean isListOfFieldNames = true;
                boolean isDataTransaction = false;

                List<Object[]> batchTransactionArgs = new ArrayList<>();
                List<Object[]> batchInventoryArgs = new ArrayList<>();

                for (String content : contents) {
                    if (isListOfFieldNames) {
                        isDataTransaction = isDataTransaction(content);
                        isListOfFieldNames = false;
                        continue;
                    }

                    insertToRows(isDataTransaction, batchTransactionArgs, batchInventoryArgs, content);
                }
                int[] updateTransactionCounts = jdbcTemplate.batchUpdate(TransactionModel.sql, batchTransactionArgs);

                // Handle errors if necessary
                for (int count : updateTransactionCounts) {
                    if (count == 0) {
                        System.out.println("Transaction update failed");
                    }
                }

                int[] updateInventoryCounts = jdbcTemplate.batchUpdate(InventoryModel.sql, batchInventoryArgs);

                // Handle errors if necessary
                for (int count : updateInventoryCounts) {
                    if (count == 0) {
                        System.out.println("Inventory update failed");
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertToRows(boolean isDataTransaction, List<Object[]> batchTransactionArgs, List<Object[]> batchInventoryArgs, String content) throws ParseException {
        StringTokenizer tkn = new StringTokenizer(content, ",");
        if (isDataTransaction) {
            Object[] args = new Object[]{
                    tkn.nextToken(), // Completed
                    tkn.nextToken(), // CMPNYCD
                    tkn.nextToken(), // STOCK_HANDLING_CUSTOMER_NUMBER
                    tkn.nextToken(), // STOCK_POINT
                    tkn.nextToken(), // SLIP_NUMBER
                    tkn.nextToken(), // TRANSACTION_CODE
                    tkn.nextToken(), // SUB_TRANSACTION_CODE
                    tkn.nextToken(), // TRANSACTION_DATE
                    tkn.nextToken(), // TRANSACTION_TIME
                    tkn.nextToken(), // PURCHASE_ORDER_NUMBER
                    tkn.nextToken(), // SHIPMENT_NUMBER
                    tkn.nextToken(), // SUPPLIER_COMPANY_CODE
                    tkn.nextToken(), // SUPPLIER_NUMBER
                    tkn.nextToken(), // TOTDETLINE
                    tkn.nextToken(), // INTRANSIT_STOCK_POINT
                    tkn.nextToken(), // RECEIVE_NUMBER
                    tkn.nextToken(), // RX_ARRANGEMENT_NUMBER
                    tkn.nextToken()  // ORIGINAL_STOCK_POINT
            };
            batchTransactionArgs.add(args);
        } else {

            Object[] args = new Object[]{
                    tkn.nextToken(), // CMPNYCD
                    tkn.nextToken(), // STOCK_HANDLING_CUSTOMER_NUMBER
                    tkn.nextToken(), // STOCK_POINT
                    tkn.nextToken(), // SLIP_NUMBER
                    tkn.nextToken(), // LINE_NUMBER
                    tkn.nextToken(), // ITEM_TYPE
                    tkn.nextToken(), // F_LENS_RL_TYPE
                    tkn.nextToken(), // F_LENS_LENS_CODE
                    tkn.nextToken(), // F_LENS_COLOR_COAT_CODE
                    tkn.nextToken(), // F_LENS_NAME
                    tkn.nextToken(), // F_LENS_COLOR
                    tkn.nextToken(), // F_LENS_COAT
                    tkn.nextToken(), // F_LENS_CYLINDER_TYPE
                    tkn.nextToken(), // F_LENS_SPHERE
                    tkn.nextToken(), // F_LENS_CYLINDER
                    tkn.nextToken(), // F_LENS_AXIS
                    tkn.nextToken(), // F_LENS_ADDITION
                    tkn.nextToken(), // F_LENS_DIAMETER
                    tkn.nextToken(), // F_LENS_UNIVERSAL_PRODUCT_NAME
                    tkn.nextToken(), // S_LENS_RL_TYPE
                    tkn.nextToken(), // S_LENS_CODE
                    tkn.nextToken(), // S_LENS_COLOR_COAT_CODE
                    tkn.nextToken(), // S_LENS_NAME
                    tkn.nextToken(), // S_LENS_COLOR
                    tkn.nextToken(), // S_LENS_MAKER
                    tkn.nextToken(), // S_LENS_NOMINAL_BASE_CURVE
                    tkn.nextToken(), // S_LENS_DIAMETER
                    tkn.nextToken(), // S_LENS_THICKNESS_TYPE
                    tkn.nextToken(), // S_LENS_ADDITION
                    tkn.nextToken(), // S_LENS_UNIVERSAL_PRODUCT_NAME
                    tkn.nextToken(), // FRAME_CODE
                    tkn.nextToken(), // FRAME_MAKER
                    tkn.nextToken(), // FRAME_NAME
                    tkn.nextToken(), // FRAME_EYE_SIZE
                    tkn.nextToken(), // FRAME_DBL
                    tkn.nextToken(), // FRAME_COLOR
                    tkn.nextToken(), // FRAME_PARTS_TYPE
                    tkn.nextToken(), // INSTRUMENT_CODE
                    tkn.nextToken(), // INSTRUMENT_NAME
                    tkn.nextToken(), // INSTRUMENT_PARTS_NUMBER
                    Integer.parseInt(tkn.nextToken()), // STOCK_IO_QUANTITY
                    Integer.parseInt(tkn.nextToken()), // RECEIVE_NUMBER
                    tkn.nextToken(), // TRANSACTION_CODE
                    tkn.nextToken(), // SUB_TRANSACTION_CODE
                    Helpers.convertStringToDate(tkn.nextToken()),  // TRANSACTION_DATE
                    new Time(Integer.parseInt(tkn.nextToken())), // TRANSACTION_TIME
            };

            batchInventoryArgs.add(args);
        }
    }

    public boolean isDataTransaction(String data) {
        return Objects.equals(data, "Completed,CMPNYCD,STOCK_HANDLING_CUSTOMER_NUMBER,STOCK_POINT,SLIP_NUMBER,TRANSACTION_CODE,SUB_TRANSACTION_CODE,TRANSACTION_DATE,TRANSACTION_TIME,PURCHASE_ORDER_NUMBER,SHIPMENT_NUMBER,SUPPLIER_COMPANY_CODE,SUPPLIER_NUMBER,TOTDETLINE,INTRANSIT_STOCK_POINT,RECEIVE_NUMBER,RX_ARRANGEMENT_NUMBER,ORGINAL_STOCK_POINT");
    }
}
