package com.pengujian_sistem.db2_version.mapper;

import com.pengujian_sistem.db2_version.dto.InventoryDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryMapper implements RowMapper<InventoryDTO> {
    @Override
    public InventoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return InventoryDTO.builder()
                .id(rs.getInt("ID"))
                .cmpnycd(rs.getString("CMPNYCD"))
                .stockHandlingCustomerNumber(rs.getString("STOCK_HANDLING_CUSTOMER_NUMBER"))
                .stockPoint(rs.getString("STOCK_POINT"))
                .slipNumber(rs.getString("SLIP_NUMBER"))
                .lineNumber(rs.getString("LINE_NUMBER"))
                .itemType(rs.getString("ITEM_TYPE"))
                .fLensRlType(rs.getString("F_LENS_RL_TYPE"))
                .fLensLensCode(rs.getString("F_LENS_LENS_CODE"))
                .fLensColorCoatCode(rs.getString("F_LENS_COLOR_COAT_CODE"))
                .fLensName(rs.getString("F_LENS_NAME"))
                .fLensColor(rs.getString("F_LENS_COLOR"))
                .fLensCoat(rs.getString("F_LENS_COAT"))
                .fLensCylinderType(rs.getString("F_LENS_CYLINDER_TYPE"))
                .fLensSphere(rs.getString("F_LENS_SPHERE"))
                .fLensCylinder(rs.getString("F_LENS_CYLINDER"))
                .fLensAxis(rs.getString("F_LENS_AXIS"))
                .fLensAddition(rs.getString("F_LENS_ADDITION"))
                .fLensDiameter(rs.getString("F_LENS_DIAMETER"))
                .fLensUniversalProductName(rs.getString("F_LENS_UNIVERSAL_PRODUCT_NAME"))
                .sLensRlType(rs.getString("S_LENS_RL_TYPE"))
                .sLensCode(rs.getString("S_LENS_CODE"))
                .sLensColorCoatCode(rs.getString("S_LENS_COLOR_COAT_CODE"))
                .sLensName(rs.getString("S_LENS_NAME"))
                .sLensColor(rs.getString("S_LENS_COLOR"))
                .sLensMaker(rs.getString("S_LENS_MAKER"))
                .sLensNominalBaseCurve(rs.getString("S_LENS_NOMINAL_BASE_CURVE"))
                .sLensDiameter(rs.getString("S_LENS_DIAMETER"))
                .sLensThicknessType(rs.getString("S_LENS_THICKNESS_TYPE"))
                .sLensAddition(rs.getString("S_LENS_ADDITION"))
                .sLensUniversalProductName(rs.getString("S_LENS_UNIVERSAL_PRODUCT_NAME"))
                .frameCode(rs.getString("FRAME_CODE"))
                .frameMaker(rs.getString("FRAME_MAKER"))
                .frameName(rs.getString("FRAME_NAME"))
                .frameEyeSize(rs.getString("FRAME_EYE_SIZE"))
                .frameDbl(rs.getString("FRAME_DBL"))
                .frameColor(rs.getString("FRAME_COLOR"))
                .framePartsType(rs.getString("FRAME_PARTS_TYPE"))
                .instrumentCode(rs.getString("INSTRUMENT_CODE"))
                .instrumentName(rs.getString("INSTRUMENT_NAME"))
                .instrumentPartsNumber(rs.getString("INSTRUMENT_PARTS_NUMBER"))
                .stockIoQuantity(rs.getString("STOCK_IO_QUANTITY"))
                .receiveNumber(rs.getInt("RECEIVE_NUMBER"))
                .transactionCode(rs.getInt("TRANSACTION_CODE"))
                .subTransactionCode(rs.getString("SUB_TRANSACTION_CODE"))
                .transactionDate(rs.getDate("TRANSACTION_DATE"))
                .transactionTime(rs.getInt("TRANSACTION_TIME"))
                .build();
    }
}
