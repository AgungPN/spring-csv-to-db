package com.pengujian_sistem.cassandra_version.services;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.pengujian_sistem.cassandra_version.dto.TransactionDTO;
import com.pengujian_sistem.cassandra_version.dto.TransactionResponse;
import com.pengujian_sistem.cassandra_version.helpers.Helpers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@AllArgsConstructor
public class TransactionService {
    private CqlSession cqlSession;

    /**
     * Get all transactions from the database.
     */
    public List<TransactionResponse> getList() {
        List<TransactionResponse> transactionDTOS = new ArrayList<>();
        ResultSet resultSet = cqlSession.execute("select id, cmpnycd as company_code, stock_point, transaction_code, transaction_date from transactions;");
        for (Row row : resultSet) {
            TransactionResponse transactionDTO = TransactionResponse.builder()
                    .id(row.getUuid("id"))
                    .companyCode(row.getString("company_code"))
                    .stockPoint(row.getString("stock_point"))
                    .transactionCode(row.getString("transaction_code"))
                    .transactionDate(row.getLocalDate("transaction_date"))
                    .build();
            transactionDTOS.add(transactionDTO);
        }
        return transactionDTOS;
    }

    /**
     * Insert a list of transactions into the database.
     */
    public void insertTransactions(List<TransactionDTO> transactions) {
        BoundStatement[] boundStatements = new BoundStatement[transactions.size()];

        for (int i = 0; i < transactions.size(); i++) {
            BoundStatement boundStatement = prepareInsert(transactions.get(i));
            boundStatements[i] = boundStatement;
        }

        BatchStatement batchStatement = BatchStatement.newInstance(BatchType.LOGGED, boundStatements);
        cqlSession.execute(batchStatement);
    }

    /**
     * Prepare a bound statement for inserting a transaction into the database.
     */
    private BoundStatement prepareInsert(TransactionDTO transaction) {
        String insertCQL = "INSERT INTO transactions (" +
                "    id," +
                "    completed," +
                "    cmpnycd," +
                "    stock_handling_customer_number," +
                "    stock_point," +
                "    slip_number," +
                "    transaction_code," +
                "    sub_transaction_code," +
                "    transaction_date," +
                "    transaction_time," +
                "    purchase_order_number," +
                "    shipment_number," +
                "    supplier_company_code," +
                "    supplier_number," +
                "    totdetline," +
                "    intransit_stock_point," +
                "    receive_number," +
                "    rx_arrangement_number," +
                "    original_stock_point" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = cqlSession.prepare(insertCQL);
        return preparedStatement.bind(
                transaction.getId(),
                transaction.getCompleted(),
                transaction.getCmpnycd(),
                transaction.getStockHandlingCustomerNumber(),
                transaction.getStockPoint(),
                transaction.getSlipNumber(),
                transaction.getTransactionCode(),
                transaction.getSubTransactionCode(),
                transaction.getTransactionDate(),
                transaction.getTransactionTime(),
                transaction.getPurchaseOrderNumber(),
                transaction.getShipmentNumber(),
                transaction.getSupplierCompanyCode(),
                transaction.getSupplierNumber(),
                transaction.getTotDetLine(),
                transaction.getInTransitStockPoint(),
                transaction.getReceiveNumber(),
                transaction.getRxArrangementNumber(),
                transaction.getOriginalStockPoint()
        );
    }

    /**
     * Inserts a list of transactions into the database in chunks to prevent large batch failures.
     */
    public void insertWithChunkList(List<TransactionDTO> transactions, int chunkSize) {
        List<List<TransactionDTO>> chunks = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i += chunkSize) {
            chunks.add(transactions.subList(i, Math.min(i + chunkSize, transactions.size())));
        }

        chunks.forEach(this::insertTransactions);
    }

    /**
     * Convert CSV content to TransactionDTO
     */
    public TransactionDTO buildDTO(String content) {
        Long startTime = System.currentTimeMillis();

        StringTokenizer tkn = new StringTokenizer(content, ",");
        return TransactionDTO.builder()
                .id(Uuids.random())
                .completed(tkn.nextToken())
                .cmpnycd(tkn.nextToken())
                .stockHandlingCustomerNumber(tkn.nextToken())
                .stockPoint(tkn.nextToken())
                .slipNumber(tkn.nextToken())
                .transactionCode(tkn.nextToken())
                .subTransactionCode(tkn.nextToken())
                .transactionDate(Helpers.convertStringToLocalDate(tkn.nextToken()))
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
    }
}
