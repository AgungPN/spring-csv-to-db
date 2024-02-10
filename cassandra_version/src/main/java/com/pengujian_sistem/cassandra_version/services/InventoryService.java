package com.pengujian_sistem.cassandra_version.services;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.pengujian_sistem.cassandra_version.dto.InventoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {

    private CqlSession cqlSession;

    public void insertMany(List<InventoryDTO> inventories) {
        BoundStatement[] boundStatements = new BoundStatement[inventories.size()];

        for (int i = 0; i < inventories.size(); i++) {
            InventoryDTO iventory = inventories.get(i);
            BoundStatement boundStatement = add(iventory);
            boundStatements[i] = boundStatement;
        }

        BatchStatement batchStatement = BatchStatement.newInstance(BatchType.LOGGED, boundStatements);
        cqlSession.execute(batchStatement);
    }

    private BoundStatement add(InventoryDTO inventory) {
        String cql = "INSERT INTO lens_frame_inventory (id, cmpnycd, stock_handling_customer_number, stock_point, " +
                "slip_number, line_number, item_type, f_lens_rl_type, f_lens_lens_code, f_lens_color_coat_code, " +
                "f_lens_name, f_lens_color, f_lens_coat, f_lens_cylinder_type, f_lens_sphere, f_lens_cylinder, " +
                "f_lens_axis, f_lens_addition, f_lens_diameter, f_lens_universal_product_name, s_lens_rl_type, " +
                "s_lens_code, s_lens_color_coat_code, s_lens_name, s_lens_color, s_lens_maker, " +
                "s_lens_nominal_base_curve, s_lens_diameter, s_lens_thickness_type, s_lens_addition, " +
                "s_lens_universal_product_name, frame_code, frame_maker, frame_name, frame_eye_size, " +
                "frame_dbl, frame_color, frame_parts_type, instrument_code, instrument_name, " +
                "instrument_parts_number, stock_io_quantity, receive_number, transaction_code, " +
                "sub_transaction_code, transaction_date, transaction_time) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = cqlSession.prepare(cql);
        return preparedStatement.bind(
                inventory.getId(),
                inventory.getCmpnycd(),
                inventory.getStockHandlingCustomerNumber(),
                inventory.getStockPoint(),
                inventory.getSlipNumber(),
                inventory.getLineNumber(),
                inventory.getItemType(),
                inventory.getLensRlType(),
                inventory.getLensLensCode(),
                inventory.getLensColorCoatCode(),
                inventory.getLensName(),
                inventory.getLensColor(),
                inventory.getLensCoat(),
                inventory.getLensCylinderType(),
                inventory.getLensSphere(),
                inventory.getLensCylinder(),
                inventory.getLensAxis(),
                inventory.getLensAddition(),
                inventory.getLensDiameter(),
                inventory.getLensUniversalProductName(),
                inventory.getFrameCode(),
                inventory.getFrameMaker(),
                inventory.getFrameName(),
                inventory.getFrameEyeSize(),
                inventory.getFrameDbl(),
                inventory.getFrameColor(),
                inventory.getFramePartsType(),
                inventory.getInstrumentCode(),
                inventory.getInstrumentName(),
                inventory.getInstrumentPartsNumber(),
                inventory.getStockIoQuantity(),
                inventory.getReceiveNumber(),
                inventory.getTransactionDate(),
                inventory.getTransactionTime()
        );
    }

    public void insertWithChunkList(List<InventoryDTO> transactions, int chunkSize) {
        List<List<InventoryDTO>> chunks = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i += chunkSize) {
            chunks.add(transactions.subList(i, Math.min(i + chunkSize, transactions.size())));
        }
        System.out.println("TOTAL JUMLAH CHUNKS: " + chunks.size());

        for (List<InventoryDTO> chunk : chunks) {
            System.out.println("TOTAL JUMLAH EACHCHUNK: " + chunk.size());
            insertMany(chunk);
        }
    }
}
