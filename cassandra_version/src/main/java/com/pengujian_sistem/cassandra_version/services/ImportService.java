package com.pengujian_sistem.cassandra_version.services;

import com.pengujian_sistem.cassandra_version.dto.InventoryDTO;
import com.pengujian_sistem.cassandra_version.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Getter
public class ImportService {
    private TransactionService transactionService;
    private InventoryService inventoryService;

    /**
     * Store data from a file CSV to database
     */
    public void storeFileToDB(List<String> contents) {
        List<InventoryDTO> inventoryDTOS = new ArrayList<>();
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        contentCsvToRows(inventoryDTOS, transactionDTOs, contents);

        transactionService.insertWithChunkList(transactionDTOs, 100);
        inventoryService.insertWithChunkList(inventoryDTOS, 50);
    }

    /**
     * Store all data from pending CSV to database
     */
    public void pendingCsvToDB() throws IOException {
        String pathPending = "C:\\Users\\ThinkPad T480s\\Downloads\\tr_pengujian_sistem\\db2_version\\assets\\pending\\";

        File folder = new File(pathPending);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;

        Stream.of(listOfFiles)
                .parallel() // Process files in parallel
                .forEach(file -> {
                    Long startTime = System.currentTimeMillis();

                    List<InventoryDTO> inventoryDTOS = new ArrayList<>();
                    List<TransactionDTO> transactionDTOs = new ArrayList<>();

                    try {
                        List<String> contents = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                        contentCsvToRows(inventoryDTOS, transactionDTOs, contents);

                        transactionService.insertWithChunkList(transactionDTOs, 100);
                        inventoryService.insertWithChunkList(inventoryDTOS, 30);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Long endTime = System.currentTimeMillis();
                    System.out.println("END TIME DB2: " + (endTime - startTime) + " milliseconds");
                });
        //
//        for (File file : listOfFiles) {
//            List<InventoryDTO> inventoryDTOS = new ArrayList<>();
//            List<TransactionDTO> transactionDTOs = new ArrayList<>();
//
//            List<String> contents = Files.readAllLines(Paths.get(file.getAbsolutePath()));
//            contentCsvToRows(inventoryDTOS, transactionDTOs, contents);
////            transactionService.insertWithChunkList(transactionDTOs, 100);
////            inventoryService.insertWithChunkList(inventoryDTOS, 30);
//
//            transactionService.insertTransactions(transactionDTOs);
//            inventoryService.insertMany(inventoryDTOS);
//
//            Long endTime = System.currentTimeMillis();
//            System.out.println("END TIME DB2: " + (endTime - startTime) + " milliseconds");
//        }

    }

    /**
     * Read CSV file and convert to rows object
     */
    private void contentCsvToRows(List<InventoryDTO> invaentoryDTOs, List<TransactionDTO> transactionDTOs, List<String> contents) {
        String contentFields = contents.get(0);
        if (isFileDataTransaction(contentFields)) {
            for (int i = 1; i < contents.size(); i++) {
                transactionDTOs.add(transactionService.buildDTO(contents.get(i)));
            }
        } else {
            for (int i = 1; i < contents.size(); i++) {
                invaentoryDTOs.add(inventoryService.buildDTO(contents.get(i)));
            }
        }
    }

    /**
     * check is CSV file contains data transaction
     */
    public boolean isFileDataTransaction(String dataFileds) {
        return dataFileds.equalsIgnoreCase("Completed,CMPNYCD,STOCK_HANDLING_CUSTOMER_NUMBER,STOCK_POINT,SLIP_NUMBER,TRANSACTION_CODE,SUB_TRANSACTION_CODE,TRANSACTION_DATE,TRANSACTION_TIME,PURCHASE_ORDER_NUMBER,SHIPMENT_NUMBER,SUPPLIER_COMPANY_CODE,SUPPLIER_NUMBER,TOTDETLINE,INTRANSIT_STOCK_POINT,RECEIVE_NUMBER,RX_ARRANGEMENT_NUMBER,ORGINAL_STOCK_POINT");
    }
}
