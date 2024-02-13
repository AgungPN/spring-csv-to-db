package com.pengujian_sistem.cassandra_version.services;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.pengujian_sistem.cassandra_version.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private CqlSession cqlSession;

    public List<TransactionDTO> getList() {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        ResultSet resultSet = cqlSession.execute("SELECT * FROM transactions");
        for (Row row : resultSet) {
            TransactionDTO transactionDTO = TransactionDTO.builder()
                    .id(row.getUuid("id"))
                    .completed(row.getString("completed"))
                    .cmpnycd(row.getString("cmpnycd"))
                    .stockHandlingCustomerNumber(row.getString("stock_handling_customer_number"))
                    .stockPoint(row.getString("stock_point"))
                    .slipNumber(row.getString("slip_number"))
                    .transactionCode(row.getString("transaction_code"))
                    .subTransactionCode(row.getString("sub_transaction_code"))
                    .transactionDate(row.getLocalDate("transaction_date"))
                    .transactionTime(row.getInt("transaction_time"))
                    .purchaseOrderNumber(row.getString("purchase_order_number"))
                    .shipmentNumber(row.getString("shipment_number"))
                    .supplierCompanyCode(row.getString("supplier_company_code"))
                    .supplierNumber(row.getString("supplier_number"))
                    .totDetLine(row.getString("totdetline"))
                    .inTransitStockPoint(row.getString("intransit_stock_point"))
                    .receiveNumber(row.getString("receive_number"))
                    .rxArrangementNumber(row.getString("rx_arrangement_number"))
                    .originalStockPoint(row.getString("original_stock_point"))
                    .build();
            transactionDTOS.add(transactionDTO);
        }
        return transactionDTOS;
    }

    public void insertTransactions(List<TransactionDTO> transactions) {
        BoundStatement[] boundStatements = new BoundStatement[transactions.size()];

        for (int i = 0; i < transactions.size(); i++) {
            TransactionDTO transactionDTO = transactions.get(i);
            BoundStatement boundStatement = addTransactions(transactionDTO);
            boundStatements[i] = boundStatement;
        }

        BatchStatement batchStatement = BatchStatement.newInstance(BatchType.UNLOGGED, boundStatements);
        cqlSession.execute(batchStatement);
    }

    private BoundStatement addTransactions(TransactionDTO transaction) {
        String cql = "INSERT INTO transactions (" +
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

        PreparedStatement preparedStatement = cqlSession.prepare(cql);
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

    public void insertWithChunkList(List<TransactionDTO> transactions, int chunkSize) {
        List<List<TransactionDTO>> chunks = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i += chunkSize) {
            chunks.add(transactions.subList(i, Math.min(i + chunkSize, transactions.size())));
        }

        for (List<TransactionDTO> chunk : chunks) {
            insertTransactions(chunk);
        }
    }
}
