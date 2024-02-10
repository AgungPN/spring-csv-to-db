package com.pengujian_sistem.cassandra_version.services;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.pengujian_sistem.cassandra_version.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private CqlSession cqlSession;

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
