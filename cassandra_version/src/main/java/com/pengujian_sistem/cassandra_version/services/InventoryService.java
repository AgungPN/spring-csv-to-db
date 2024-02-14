package com.pengujian_sistem.cassandra_version.services;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.pengujian_sistem.cassandra_version.dto.InventoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {

    private CqlSession cqlSession;

    public List<InventoryDTO> getList() {
        List<InventoryDTO> inventoryDTOS = new ArrayList<>();
        ResultSet resultSet = cqlSession.execute("SELECT * FROM lens_frame_inventory");
        for (Row row : resultSet) {
            InventoryDTO inventoryDTO = InventoryDTO.builder()
                    .id(row.getUuid("id"))
                    .cmpnycd(row.getString("cmpnycd"))
                    .stockHandlingCustomerNumber(row.getString("stock_handling_customer_number"))
                    .stockPoint(row.getString("stock_point"))
                    .slipNumber(row.getString("slip_number"))
                    .lineNumber(row.getString("line_number"))
                    .itemType(row.getString("item_type"))
                    .fLensRlType(row.getString("f_lens_rl_type"))
                    .fLensLensCode(row.getString("f_lens_lens_code"))
                    .fLensColorCoatCode(row.getString("f_lens_color_coat_code"))
                    .fLensName(row.getString("f_lens_name"))
                    .fLensColor(row.getString("f_lens_color"))
                    .fLensCoat(row.getString("f_lens_coat"))
                    .fLensCylinderType(row.getString("f_lens_cylinder_type"))
                    .fLensSphere(row.getString("f_lens_sphere"))
                    .fLensCylinder(row.getString("f_lens_cylinder"))
                    .fLensAxis(row.getString("f_lens_axis"))
                    .fLensAddition(row.getString("f_lens_addition"))
                    .fLensDiameter(row.getString("f_lens_diameter"))
                    .fLensUniversalProductName(row.getString("f_lens_universal_product_name"))
                    .sLensRlType(row.getString("s_lens_rl_type"))
                    .sLensCode(row.getString("s_lens_code"))
                    .sLensColorCoatCode(row.getString("s_lens_color_coat_code"))
                    .sLensName(row.getString("s_lens_name"))
                    .sLensColor(row.getString("s_lens_color"))
                    .sLensMaker(row.getString("s_lens_maker"))
                    .sLensNominalBaseCurve(row.getString("s_lens_nominal_base_curve"))
                    .sLensDiameter(row.getString("s_lens_diameter"))
                    .sLensThicknessType(row.getString("s_lens_thickness_type"))
                    .sLensAddition(row.getString("s_lens_addition"))
                    .sLensUniversalProductName(row.getString("s_lens_universal_product_name"))
                    .frameCode(row.getString("frame_code"))
                    .frameMaker(row.getString("frame_maker"))
                    .frameName(row.getString("frame_name"))
                    .frameEyeSize(row.getString("frame_eye_size"))
                    .frameDbl(row.getString("frame_dbl"))
                    .frameColor(row.getString("frame_color"))
                    .framePartsType(row.getString("frame_parts_type"))
                    .instrumentCode(row.getString("instrument_code"))
                    .instrumentName(row.getString("instrument_name"))
                    .instrumentPartsNumber(row.getString("instrument_parts_number"))
                    .stockIoQuantity(row.getString("stock_io_quantity"))
                    .receiveNumber(row.getInt("receive_number"))
                    .transactionCode(row.getInt("transaction_code"))
                    .subTransactionCode(row.getString("sub_transaction_code"))
                    .transactionDate(row.getLocalDate("transaction_date"))
                    .transactionTime(row.getInt("transaction_time"))
                    .build();
            inventoryDTOS.add(inventoryDTO);
        }

        return inventoryDTOS;
    }

    public void insertMany(List<InventoryDTO> inventories) {
        BoundStatement[] boundStatements = new BoundStatement[inventories.size()];

        for (int i = 0; i < inventories.size(); i++) {
            InventoryDTO iventory = inventories.get(i);
            BoundStatement boundStatement = prepareInsert(iventory);
            boundStatements[i] = boundStatement;
        }

        BatchStatement batchStatement = BatchStatement.newInstance(BatchType.LOGGED, boundStatements);
        cqlSession.execute(batchStatement);
    }

    private BoundStatement prepareInsert(InventoryDTO inventory) {
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
                inventory.getFLensRlType(),
                inventory.getFLensLensCode(),
                inventory.getFLensColorCoatCode(),
                inventory.getFLensName(),
                inventory.getFLensColor(),
                inventory.getFLensCoat(),
                inventory.getFLensCylinderType(),
                inventory.getFLensSphere(),
                inventory.getFLensCylinder(),
                inventory.getFLensAxis(),
                inventory.getFLensAddition(),
                inventory.getFLensDiameter(),
                inventory.getFLensUniversalProductName(),
                inventory.getSLensRlType(),
                inventory.getSLensCode(),
                inventory.getSLensColorCoatCode(),
                inventory.getSLensName(),
                inventory.getSLensColor(),
                inventory.getSLensMaker(),
                inventory.getSLensNominalBaseCurve(),
                inventory.getSLensDiameter(),
                inventory.getSLensThicknessType(),
                inventory.getSLensAddition(),
                inventory.getSLensUniversalProductName(),
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
                inventory.getTransactionCode(),
                inventory.getSubTransactionCode(),
                inventory.getTransactionDate(),
                inventory.getTransactionTime()
        );
    }

    public void insertWithChunkList(List<InventoryDTO> transactions, int chunkSize) {
        List<List<InventoryDTO>> chunks = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i += chunkSize) {
            chunks.add(transactions.subList(i, Math.min(i + chunkSize, transactions.size())));
        }

        for (List<InventoryDTO> chunk : chunks) {
            insertMany(chunk);
        }
    }
}
