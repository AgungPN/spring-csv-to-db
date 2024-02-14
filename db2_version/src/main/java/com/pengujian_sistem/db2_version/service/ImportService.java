package com.pengujian_sistem.db2_version.service;

import com.pengujian_sistem.db2_version.dto.InventoryDTO;
import com.pengujian_sistem.db2_version.helper.Helpers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

@Service
@AllArgsConstructor
@Getter
public class ImportService {
    private JdbcTemplate jdbcTemplate;
    private InventoryService inventoryService;
    private TransactionService transactionService;

    public void store() throws IOException, ParseException {
        String pathPending = "C:\\Users\\ThinkPad T480s\\Downloads\\tr_pengujian_sistem\\db2_version\\assets\\pending\\";

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
            int[] updateTransactionCounts = jdbcTemplate.batchUpdate(TransactionService.INSERT_QUERY, batchTransactionArgs);

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
    }

    private static void insertToRows(boolean isDataTransaction, List<Object[]> batchTransactionArgs, List<Object[]> batchInventoryArgs, String content) throws ParseException {
        StringTokenizer tkn = new StringTokenizer(content, ",");
        if (isDataTransaction) {
            Object[] args = new Object[]{tkn.nextToken(), // Completed
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

            Object[] args = new Object[]{tkn.nextToken(), // CMPNYCD
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
//                    new Time(Integer.parseInt(tkn.nextToken())), // TRANSACTION_TIME
                    Integer.parseInt(tkn.nextToken()), // RECEIVE_NUMBER
            };

            batchInventoryArgs.add(args);
        }
    }

    public boolean isDataTransaction(String data) {
        return Objects.equals(data, "Completed,CMPNYCD,STOCK_HANDLING_CUSTOMER_NUMBER,STOCK_POINT,SLIP_NUMBER,TRANSACTION_CODE,SUB_TRANSACTION_CODE,TRANSACTION_DATE,TRANSACTION_TIME,PURCHASE_ORDER_NUMBER,SHIPMENT_NUMBER,SUPPLIER_COMPANY_CODE,SUPPLIER_NUMBER,TOTDETLINE,INTRANSIT_STOCK_POINT,RECEIVE_NUMBER,RX_ARRANGEMENT_NUMBER,ORGINAL_STOCK_POINT");
    }

    public List<InventoryDTO> getListInventories() {
//        String selectQuery = "SELECT * FROM LENS_FRAME_INVENTORY";
//        return jdbcTemplate.query(selectQuery, (ResultSet rs, int rowNum) -> {
//            return InventoryDTO.builder()
//                    .id(rs.getInt("ID"))
//                    .cmpnycd(rs.getString("CMPNYCD"))
//                    .stockHandlingCustomerNumber(rs.getString("STOCK_HANDLING_CUSTOMER_NUMBER"))
//                    .stockPoint(rs.getString("STOCK_POINT"))
//                    .slipNumber(rs.getString("SLIP_NUMBER"))
//                    .lineNumber(rs.getString("LINE_NUMBER"))
//                    .itemType(rs.getString("ITEM_TYPE"))
//                    .lensRlType(rs.getString("F_LENS_RL_TYPE"))
//                    .lensLensCode(rs.getString("F_LENS_LENS_CODE"))
//                    .lensColorCoatCode(rs.getString("F_LENS_COLOR_COAT_CODE"))
//                    .lensName(rs.getString("F_LENS_NAME"))
//                    .lensColor(rs.getString("F_LENS_COLOR"))
//                    .lensCoat(rs.getString("F_LENS_COAT"))
//                    .lensCylinderType(rs.getString("F_LENS_CYLINDER_TYPE"))
//                    .lensSphere(rs.getString("F_LENS_SPHERE"))
//                    .lensCylinder(rs.getString("F_LENS_CYLINDER"))
//                    .lensAxis(rs.getString("F_LENS_AXIS"))
//                    .lensAddition(rs.getString("F_LENS_ADDITION"))
//                    .lensDiameter(rs.getString("F_LENS_DIAMETER"))
//                    .lensUniversalProductName(rs.getString("F_LENS_UNIVERSAL_PRODUCT_NAME"))
//                    .sLensRlType(rs.getString("S_LENS_RL_TYPE"))
//                    .sLensCode(rs.getString("S_LENS_CODE"))
//                    .sLensColorCoatCode(rs.getString("S_LENS_COLOR_COAT_CODE"))
//                    .sLensName(rs.getString("S_LENS_NAME"))
//                    .sLensColor(rs.getString("S_LENS_COLOR"))
//                    .sLensMaker(rs.getString("S_LENS_MAKER"))
//                    .sLensNominalBaseCurve(rs.getDouble("S_LENS_NOMINAL_BASE_CURVE"))
//                    .sLensDiameter(rs.getInt("S_LENS_DIAMETER"))
//                    .sLensThicknessType(rs.getString("S_LENS_THICKNESS_TYPE"))
//                    .sLensAddition(rs.getString("S_LENS_ADDITION"))
//                    .sLensUniversalProductName(rs.getString("S_LENS_UNIVERSAL_PRODUCT_NAME"))
//                    .frameCode(rs.getString("FRAME_CODE"))
//                    .frameMaker(rs.getString("FRAME_MAKER"))
//                    .frameName(rs.getString("FRAME_NAME"))
//                    .frameEyeSize(rs.getInt("FRAME_EYE_SIZE"))
//                    .frameDbl(rs.getInt("FRAME_DBL"))
//                    .frameColor(rs.getString("FRAME_COLOR"))
//                    .framePartsType(rs.getString("FRAME_PARTS_TYPE"))
//                    .instrumentCode(rs.getString("INSTRUMENT_CODE"))
//                    .instrumentName(rs.getString("INSTRUMENT_NAME"))
//                    .instrumentPartsNumber(rs.getString("INSTRUMENT_PARTS_NUMBER"))
//                    .stockIoQuantity(rs.getInt("STOCK_IO_QUANTITY"))
//                    .receiveNumber(rs.getInt("RECEIVE_NUMBER"))
//                    .transactionCode(rs.getString("TRANSACTION_CODE"))
//                    .subTransactionCode(rs.getString("SUB_TRANSACTION_CODE"))
//                    .transactionDate(rs.getDate("TRANSACTION_DATE"))
//                    .transactionTime(rs.getInt("TRANSACTION_TIME"))
//                    .build();
//        });
        return null;
    }
}
