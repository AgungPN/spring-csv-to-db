package com.pengujian_sistem.cassandra_version.services;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.pengujian_sistem.cassandra_version.dto.InventoryDTO;
import com.pengujian_sistem.cassandra_version.dto.TransactionDTO;
import com.pengujian_sistem.cassandra_version.helpers.Helper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

@Service
@AllArgsConstructor
@Getter
public class ImportService {
    private TransactionService transactionService;
    private InventoryService inventoryService;

    public void store() throws IOException {
        String pathPending = "C:\\Users\\ThinkPad T480s\\Downloads\\tr_pengujian_sistem\\db2_version\\assets\\pending\\";

        File folder = new File(pathPending);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;

        List<InventoryDTO> invaentoryDTOs = new ArrayList<>();
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (File file : listOfFiles) {
            List<String> contents = Files.readAllLines(Paths.get(file.getAbsolutePath()));

            boolean isListOfFieldNames = true;
            boolean isTransaction = false;

            for (String content : contents) {
                if (isListOfFieldNames) {
                    isTransaction = isTransactionData(content);
                    isListOfFieldNames = false;
                    continue;
                }

                content = content.replaceAll("\"", "");
                StringTokenizer tkn = new StringTokenizer(content, ",");
                if (isTransaction) {
                    TransactionDTO build = TransactionDTO.builder()
                            .id(Uuids.random())
                            .completed(tkn.nextToken())
                            .cmpnycd(tkn.nextToken())
                            .stockHandlingCustomerNumber(tkn.nextToken())
                            .stockPoint(tkn.nextToken())
                            .slipNumber(tkn.nextToken())
                            .transactionCode(tkn.nextToken())
                            .subTransactionCode(tkn.nextToken())
                            .transactionDate(Helper.convertStringToLocalDate(tkn.nextToken()))
                            .transactionTime(Integer.parseInt(tkn.nextToken()))
                            .purchaseOrderNumber(tkn.nextToken())
                            .shipmentNumber(tkn.nextToken())
                            .supplierCompanyCode(tkn.nextToken())
                            .supplierNumber(tkn.nextToken())
                            .totDetLine(tkn.nextToken())
                            .inTransitStockPoint(tkn.nextToken())
                            .receiveNumber(tkn.nextToken())
                            .rxArrangementNumber(tkn.nextToken())
                            .originalStockPoint(tkn.nextToken())
                            .build();
                    transactionDTOs.add(build);
                } else {
                    InventoryDTO build = InventoryDTO.builder()
                            .id(Uuids.random())
                            .cmpnycd(tkn.nextToken())
                            .stockHandlingCustomerNumber(tkn.nextToken())
                            .stockPoint(tkn.nextToken())
                            .slipNumber(tkn.nextToken())
                            .lineNumber(tkn.nextToken())
                            .itemType(tkn.nextToken())
                            .lensRlType(tkn.nextToken())
                            .lensLensCode(tkn.nextToken())
                            .lensColorCoatCode(tkn.nextToken())
                            .lensName(tkn.nextToken())
                            .lensColor(tkn.nextToken())
                            .lensCoat(tkn.nextToken())
                            .lensCylinderType(tkn.nextToken())
                            .lensSphere(tkn.nextToken())
                            .lensCylinder(tkn.nextToken())
                            .lensAxis(tkn.nextToken())
                            .lensAddition(tkn.nextToken())
                            .lensDiameter(tkn.nextToken())
                            .lensUniversalProductName(tkn.nextToken())
                            .frameCode(tkn.nextToken())
                            .frameMaker(tkn.nextToken())
                            .frameName(tkn.nextToken())
                            .frameEyeSize(tkn.nextToken())
                            .frameDbl(tkn.nextToken())
                            .frameColor(tkn.nextToken())
                            .framePartsType(tkn.nextToken())
                            .instrumentCode(tkn.nextToken())
                            .instrumentName(tkn.nextToken())
                            .instrumentPartsNumber(tkn.nextToken())
                            .stockIoQuantity(tkn.nextToken())
                            .receiveNumber(tkn.nextToken())
                            .transactionDate(tkn.nextToken())
                            .transactionTime(tkn.nextToken())
                            .build();
                    invaentoryDTOs.add(build);
                }

            }
        }

        transactionService.insertWithChunkList(transactionDTOs, 100);
        inventoryService.insertWithChunkList(invaentoryDTOs, 50);
    }

    public boolean isTransactionData(String data) {
        return Objects.equals(data, "Completed,CMPNYCD,STOCK_HANDLING_CUSTOMER_NUMBER,STOCK_POINT,SLIP_NUMBER,TRANSACTION_CODE,SUB_TRANSACTION_CODE,TRANSACTION_DATE,TRANSACTION_TIME,PURCHASE_ORDER_NUMBER,SHIPMENT_NUMBER,SUPPLIER_COMPANY_CODE,SUPPLIER_NUMBER,TOTDETLINE,INTRANSIT_STOCK_POINT,RECEIVE_NUMBER,RX_ARRANGEMENT_NUMBER,ORGINAL_STOCK_POINT");
    }
}
