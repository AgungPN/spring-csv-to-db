package com.pengujian_sistem.db2_version;

import com.pengujian_sistem.db2_version.dto.LensFrameInventoryDTO;
import com.pengujian_sistem.db2_version.dto.TransactionDTO;
import com.pengujian_sistem.db2_version.model.InventoryModel;
import com.pengujian_sistem.db2_version.model.TransactionModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class StoreTransactionController {
    private  JdbcTemplate jdbcTemplate;
    private String insertInventoryQuery = "INSERT INTO LENS_FRAME_INVENTORY (" +
            "    CMPNYCD," +
            "    STOCK_HANDLING_CUSTOMER_NUMBER," +
            "    STOCK_POINT," +
            "    SLIP_NUMBER," +
            "    LINE_NUMBER," +
            "    ITEM_TYPE," +
            "    F_LENS_RL_TYPE," +
            "    F_LENS_LENS_CODE," +
            "    F_LENS_COLOR_COAT_CODE," +
            "    F_LENS_NAME," +
            "    F_LENS_COLOR," +
            "    F_LENS_COAT," +
            "    F_LENS_CYLINDER_TYPE," +
            "    F_LENS_SPHERE," +
            "    F_LENS_CYLINDER," +
            "    F_LENS_AXIS," +
            "    F_LENS_ADDITION," +
            "    F_LENS_DIAMETER," +
            "    F_LENS_UNIVERSAL_PRODUCT_NAME," +
            "    S_LENS_RL_TYPE," +
            "    S_LENS_CODE," +
            "    S_LENS_COLOR_COAT_CODE," +
            "    S_LENS_NAME," +
            "    S_LENS_COLOR," +
            "    S_LENS_MAKER," +
            "    S_LENS_NOMINAL_BASE_CURVE," +
            "    S_LENS_DIAMETER," +
            "    S_LENS_THICKNESS_TYPE," +
            "    S_LENS_ADDITION," +
            "    S_LENS_UNIVERSAL_PRODUCT_NAME," +
            "    FRAME_CODE," +
            "    FRAME_MAKER," +
            "    FRAME_NAME," +
            "    FRAME_EYE_SIZE," +
            "    FRAME_DBL," +
            "    FRAME_COLOR," +
            "    FRAME_PARTS_TYPE," +
            "    INSTRUMENT_CODE," +
            "    INSTRUMENT_NAME," +
            "    INSTRUMENT_PARTS_NUMBER," +
            "    STOCK_IO_QUANTITY," +
            "    RECEIVE_NUMBER," +
            "    TRANSACTION_CODE," +
            "    SUB_TRANSACTION_CODE," +
            "    TRANSACTION_DATE," +
            "    TRANSACTION_TIME" +
            ") VALUES ";

    private String insertTranslationQuery = "INSERT INTO TRANSACTION (" +
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


    public void main(String[] args) throws IOException {
        String pathPending = "/home/linux/Documents/programing/java/spring/tr_pengujian_sistem/db2_version/assets/pending/";

        TransactionModel transactionModel = new TransactionModel();
        InventoryModel inventoryModel = new InventoryModel();

        List<String> transactionValues = new ArrayList<>();
        List<String> inventoryValues = new ArrayList<>();

//        try {
        File folder = new File(pathPending);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;

        for (File file : listOfFiles) {
            List<String> contents = Files.readAllLines(Paths.get(file.getAbsolutePath()));

            boolean isListOfFieldNames = true;
            boolean isDataTransaction = false;

            for (String content : contents) {
                if (isListOfFieldNames) {
                    isDataTransaction = isDataTransaction(content);
                    isListOfFieldNames = false;
                    continue;
                }

                StringTokenizer tkn = new StringTokenizer(content, ",");
                if (isDataTransaction) {
                    String transactionValue = "(" +
                            "CMPNYCD='" + tkn.nextToken() + "', " +
                            "STOCK_HANDLING_CUSTOMER_NUMBER='" + tkn.nextToken() + "', " +
                            "STOCK_POINT='" + tkn.nextToken() + "', " +
                            "SLIP_NUMBER='" + tkn.nextToken() + "', " +
                            "TRANSACTION_CODE='" + tkn.nextToken() + "', " +
                            "SUB_TRANSACTION_CODE='" + tkn.nextToken() + "', " +
                            "TRANSACTION_DATE='" + tkn.nextToken() + "', " + // Menambahkan tanda kutip pada nilai string
                            "TRANSACTION_TIME='" + tkn.nextToken() + "', " + // Menambahkan tanda kutip pada nilai string
                            "PURCHASE_ORDER_NUMBER='" + tkn.nextToken() + "', " +
                            "SHIPMENT_NUMBER='" + tkn.nextToken() + "', " +
                            "SUPPLIER_COMPANY_CODE='" + tkn.nextToken() + "', " +
                            "SUPPLIER_NUMBER='" + tkn.nextToken() + "', " +
                            "TOTDETLINE='" + tkn.nextToken() + "', " + // Menambahkan tanda kutip pada nilai string
                            "INTRANSIT_STOCK_POINT='" + tkn.nextToken() + "', " +
                            "RECEIVE_NUMBER='" + tkn.nextToken() + "', " + // Menambahkan tanda kutip pada nilai string
                            "RX_ARRANGEMENT_NUMBER=" + Integer.parseInt(tkn.nextToken()) + ", " +
                            "ORIGINAL_STOCK_POINT='" + tkn.nextToken() + "'" +
                            ")";
                    transactionValues.add(transactionValue);
                } else {
                    String inventoryValue = "(" +
                            "CMPNYCD='" + tkn.nextToken() + "', " +
                            "STOCK_HANDLING_CUSTOMER_NUMBER='" + tkn.nextToken() + "', " +
                            "STOCK_POINT='" + tkn.nextToken() + "', " +
                            "SLIP_NUMBER='" + tkn.nextToken() + "', " +
                            "LINE_NUMBER='" + tkn.nextToken() + "', " +
                            "ITEM_TYPE='" + tkn.nextToken() + "', " +
                            "F_LENS_RL_TYPE='" + tkn.nextToken() + "', " +
                            "F_LENS_LENS_CODE='" + tkn.nextToken() + "', " +
                            "F_LENS_COLOR_COAT_CODE='" + tkn.nextToken() + "', " +
                            "F_LENS_NAME='" + tkn.nextToken() + "', " +
                            "F_LENS_COLOR='" + tkn.nextToken() + "', " +
                            "F_LENS_COAT='" + tkn.nextToken() + "', " +
                            "F_LENS_CYLINDER_TYPE='" + tkn.nextToken() + "', " +
                            "F_LENS_SPHERE=" + tkn.nextToken() + ", " +
                            "F_LENS_CYLINDER=" + tkn.nextToken() + ", " +
                            "F_LENS_AXIS='" + tkn.nextToken() + "', " +
                            "F_LENS_ADDITION='" + tkn.nextToken() + "', " +
                            "F_LENS_DIAMETER=" + tkn.nextToken() + ", " +
                            "F_LENS_UNIVERSAL_PRODUCT_NAME='" + tkn.nextToken() + "', " +
                            "S_LENS_RL_TYPE='" + tkn.nextToken() + "', " +
                            "S_LENS_CODE='" + tkn.nextToken() + "', " +
                            "S_LENS_COLOR_COAT_CODE='" + tkn.nextToken() + "', " +
                            "S_LENS_NAME='" + tkn.nextToken() + "', " +
                            "S_LENS_COLOR='" + tkn.nextToken() + "', " +
                            "S_LENS_MAKER='" + tkn.nextToken() + "', " +
                            "S_LENS_NOMINAL_BASE_CURVE=" + tkn.nextToken() + ", " +
                            "S_LENS_DIAMETER=" + tkn.nextToken() + ", " +
                            "S_LENS_THICKNESS_TYPE='" + tkn.nextToken() + "', " +
                            "S_LENS_ADDITION='" + tkn.nextToken() + "', " +
                            "S_LENS_UNIVERSAL_PRODUCT_NAME='" + tkn.nextToken() + "', " +
                            "FRAME_CODE='" + tkn.nextToken() + "', " +
                            "FRAME_MAKER='" + tkn.nextToken() + "', " +
                            "FRAME_NAME='" + tkn.nextToken() + "', " +
                            "FRAME_EYE_SIZE=" + tkn.nextToken() + ", " +
                            "FRAME_DBL=" + tkn.nextToken() + ", " +
                            "FRAME_COLOR='" + tkn.nextToken() + "', " +
                            "FRAME_PARTS_TYPE='" + tkn.nextToken() + "', " +
                            "INSTRUMENT_CODE='" + tkn.nextToken() + "', " +
                            "INSTRUMENT_NAME='" + tkn.nextToken() + "', " +
                            "INSTRUMENT_PARTS_NUMBER='" + tkn.nextToken() + "', " +
                            "STOCK_IO_QUANTITY=" + Integer.parseInt(tkn.nextToken()) + ", " +
                            "RECEIVE_NUMBER=" + Integer.parseInt(tkn.nextToken()) + ", " +
                            "TRANSACTION_CODE='" + tkn.nextToken() + "', " +
                            "SUB_TRANSACTION_CODE='" + tkn.nextToken() + "', " +
                            "TRANSACTION_DATE=TO_DATE('" + tkn.nextToken() + "', 'YYYYMMDD'), " +
                            "TRANSACTION_TIME=TO_TIMESTAMP('" + tkn.nextToken() + "', 'HH24MISS')" +
                            ")";
                    inventoryValues.add(inventoryValue);
                }
            }
            // this.moveFileToCompleted(file);
        }

        testToInsert(inventoryValues, transactionValues);

//        } catch (Exception e) {
//            System.out.println(e.fillInStackTrace());
//        }
    }

    public boolean isDataTransaction(String data) {
        return Objects.equals(data, "Completed,CMPNYCD,STOCK_HANDLING_CUSTOMER_NUMBER,STOCK_POINT,SLIP_NUMBER,TRANSACTION_CODE,SUB_TRANSACTION_CODE,TRANSACTION_DATE,TRANSACTION_TIME,PURCHASE_ORDER_NUMBER,SHIPMENT_NUMBER,SUPPLIER_COMPANY_CODE,SUPPLIER_NUMBER,TOTDETLINE,INTRANSIT_STOCK_POINT,RECEIVE_NUMBER,RX_ARRANGEMENT_NUMBER,ORGINAL_STOCK_POINT");
    }

    public void testToInsert(List<String> inventories, List<String> transactions) {


        StringBuilder valueTransactionBuilder = new StringBuilder();
        StringBuilder valueInventoryBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            valueTransactionBuilder.append(transactions.get(i));
            valueInventoryBuilder.append(inventories.get(i));
            if (i != 9) {
                valueTransactionBuilder.append(", ");
                valueInventoryBuilder.append(", ");
            }
        }

        jdbcTemplate.update(insertInventoryQuery + inventories);
        jdbcTemplate.update(insertTranslationQuery + transactions);
    }
}
