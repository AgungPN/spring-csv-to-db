package com.pengujian_sistem.cassandra_version.services;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.pengujian_sistem.cassandra_version.dto.InventoryDTO;
import com.pengujian_sistem.cassandra_version.dto.InventoryResponse;
import com.pengujian_sistem.cassandra_version.helpers.Helpers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@AllArgsConstructor
public class InventoryService {

    private CqlSession cqlSession;

    /**
     * Getting list of inventory
     */
    public List<InventoryResponse> getList() {
        List<InventoryResponse> inventoryDTOS = new ArrayList<>();
        ResultSet resultSet = cqlSession.execute("select id, cmpnycd as company_code, slip_number, transaction_date from lens_frame_inventory;");
        for (Row row : resultSet) {
            InventoryResponse inventoryDTO = InventoryResponse.builder()
                    .id(row.getUuid("id"))
                    .companyCode(row.getString("company_code"))
                    .slipNumber(row.getString("slip_number"))
                    .transactionDate(row.getLocalDate("transaction_date"))
                    .build();
            inventoryDTOS.add(inventoryDTO);
        }

        return inventoryDTOS;
    }

    /**
     * Insert many data of inventory
     */
    public void insertMany(List<InventoryDTO> inventories) {
        BoundStatement[] boundStatements = new BoundStatement[inventories.size()];

        for (int i = 0; i < inventories.size(); i++) {
            BoundStatement boundStatement = prepareInsert(inventories.get(i));
            boundStatements[i] = boundStatement;
        }

        BatchStatement batchStatement = BatchStatement.newInstance(BatchType.LOGGED, boundStatements);
        cqlSession.execute(batchStatement);
    }

    /**
     * Prepare insert statement
     */
    private BoundStatement prepareInsert(InventoryDTO inventory) {
        String insertCQL = "INSERT INTO lens_frame_inventory (id, cmpnycd, stock_handling_customer_number, stock_point, " +
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

        PreparedStatement preparedStatement = cqlSession.prepare(insertCQL);
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

    /**
     * Inserts a list of inventory transactions into the database in chunks to prevent large batch failures.
     */
    public void insertWithChunkList(List<InventoryDTO> transactions, int chunkSize) {
        List<List<InventoryDTO>> chunks = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i += chunkSize) {
            chunks.add(transactions.subList(i, Math.min(i + chunkSize, transactions.size())));
        }

        chunks.forEach(this::insertMany);
    }

    /**
     * Convert CSV content to InventoryDTO
     */
    public InventoryDTO buildDTO(String content) {
        StringTokenizer tkn = new StringTokenizer(content, ",");
        return InventoryDTO.builder()
                .id(Uuids.random())
                .cmpnycd(tkn.nextToken())
                .stockHandlingCustomerNumber(tkn.nextToken())
                .stockPoint(tkn.nextToken())
                .slipNumber(tkn.nextToken())
                .lineNumber(tkn.nextToken())
                .itemType(tkn.nextToken())
                .fLensRlType(tkn.nextToken())
                .fLensLensCode(tkn.nextToken())
                .fLensColorCoatCode(tkn.nextToken())
                .fLensName(tkn.nextToken())
                .fLensColor(tkn.nextToken())
                .fLensCoat(tkn.nextToken())
                .fLensCylinderType(tkn.nextToken())
                .fLensSphere(tkn.nextToken())
                .fLensCylinder(tkn.nextToken())
                .fLensAxis(tkn.nextToken())
                .fLensAddition(tkn.nextToken())
                .fLensDiameter(tkn.nextToken())
                .fLensUniversalProductName(tkn.nextToken())
                .sLensRlType(tkn.nextToken())
                .sLensCode(tkn.nextToken())
                .sLensColorCoatCode(tkn.nextToken())
                .sLensName(tkn.nextToken())
                .sLensColor(tkn.nextToken())
                .sLensMaker(tkn.nextToken())
                .sLensNominalBaseCurve(tkn.nextToken())
                .sLensDiameter(tkn.nextToken())
                .sLensThicknessType(tkn.nextToken())
                .sLensAddition(tkn.nextToken())
                .sLensUniversalProductName(tkn.nextToken())
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
                .receiveNumber(Integer.parseInt(tkn.nextToken()))
                .transactionCode(Integer.parseInt(tkn.nextToken()))
                .subTransactionCode(tkn.nextToken())
                .transactionDate(Helpers.convertStringToLocalDate(tkn.nextToken()))
                .transactionTime(Integer.parseInt(tkn.nextToken()))
                .build();
    }
}
