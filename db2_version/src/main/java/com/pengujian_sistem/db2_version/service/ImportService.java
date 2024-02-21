package com.pengujian_sistem.db2_version.service;

import com.pengujian_sistem.db2_version.dto.InventoryDTO;
import com.pengujian_sistem.db2_version.dto.TransactionDTO;
import com.pengujian_sistem.db2_version.mapper.InventoryMapper;
import com.pengujian_sistem.db2_version.mapper.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImportService {
    private JdbcTemplate jdbcTemplate;
    private InventoryService inventoryService;
    private TransactionService transactionService;

    public void storeFileToDB(List<String> contents) throws ParseException {
        List<Object[]> rowTransactions = new ArrayList<>();
        List<Object[]> rowInventories = new ArrayList<>();

        csvToRows(rowTransactions, rowInventories, contents);

        insertToDatabase(rowTransactions, rowInventories);
    }

    /**
     * Store data from CSV to database
     */
    public void pendingCsvToDb() throws IOException, ParseException {
        String pathPending = "C:\\Users\\ThinkPad T480s\\Downloads\\tr_pengujian_sistem\\db2_version\\assets\\pending\\";

        File folder = new File(pathPending);
        File[] listOfFiles = folder.listFiles();

        List<Object[]> rowTransactions = new ArrayList<>();
        List<Object[]> rowInventories = new ArrayList<>();

        for (File file : listOfFiles) {
            List<String> contents = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            csvToRows(rowTransactions, rowInventories, contents);
        }

        insertToDatabase(rowTransactions, rowInventories);
    }

    /**
     * Read CSV file and convert to rows object
     */
    private void csvToRows(
            List<Object[]> rowTransactions,
            List<Object[]> rowInventories,
            List<String> contents) throws ParseException {

        String contentFields = contents.get(0);
        if (isDataFileTransaction(contentFields)) {
            for (int i = 1; i < contents.size(); i++) {
                rowTransactions.add(transactionService.addToRows(contents.get(i)));
            }
        } else {
            for (int i = 1; i < contents.size(); i++) {
                rowInventories.add(inventoryService.addToRows(contents.get(i)));
            }
        }
    }

    /**
     * Insert data object to database
     */
    public void insertToDatabase(List<Object[]> batchTransactionArgs, List<Object[]> batchInventoryArgs) {
        int[] updateTransactionCounts = jdbcTemplate.batchUpdate(TransactionService.INSERT_QUERY, batchTransactionArgs);
        System.out.println("updateTransactionCounts: " + updateTransactionCounts.length);

        // Handle errors if necessary
        for (int count : updateTransactionCounts) {
            if (count == 0) {
                System.out.println("Transaction update failed");
            }
        }

        int[] updateInventoryCounts = jdbcTemplate.batchUpdate(InventoryService.INSERT_QUERY, batchInventoryArgs);

        // Handle errors if necessary
        for (int count : updateInventoryCounts) {
            if (count == 0) {
                System.out.println("Inventory update failed");
            }
        }
    }

    /**
     * check is CSV file contains data transaction
     */
    public boolean isDataFileTransaction(String dataFields) {
        return dataFields.equalsIgnoreCase("Completed,CMPNYCD,STOCK_HANDLING_CUSTOMER_NUMBER,STOCK_POINT,SLIP_NUMBER,TRANSACTION_CODE,SUB_TRANSACTION_CODE,TRANSACTION_DATE,TRANSACTION_TIME,PURCHASE_ORDER_NUMBER,SHIPMENT_NUMBER,SUPPLIER_COMPANY_CODE,SUPPLIER_NUMBER,TOTDETLINE,INTRANSIT_STOCK_POINT,RECEIVE_NUMBER,RX_ARRANGEMENT_NUMBER,ORGINAL_STOCK_POINT");
    }

    /**
     * Get list data of inventories
     */
    public List<InventoryDTO> getListInventories() {
        return jdbcTemplate.query(
                "SELECT ID, CMPNYCD as COMPANY_CODE, STOCK_POINT FROM LENS_FRAME_INVENTORY",
                new InventoryMapper()
        );
    }

    /**
     * get list data of transactions
     */
    public List<TransactionDTO> getListTransactions() {
        return jdbcTemplate.query(
                "SELECT ID,CMPNYCD, STOCK_POINT FROM TRANSACTION",
                new TransactionMapper()
        );
    }
}
