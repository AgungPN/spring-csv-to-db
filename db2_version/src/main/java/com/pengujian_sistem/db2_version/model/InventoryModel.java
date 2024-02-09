package com.pengujian_sistem.db2_version.model;

import java.util.ArrayList;
import java.util.List;

public class InventoryModel {


    public static String sql = "INSERT INTO DB2INST1.LENS_FRAME_INVENTORY (CMPNYCD,STOCK_HANDLING_CUSTOMER_NUMBER,STOCK_POINT,SLIP_NUMBER,LINE_NUMBER,ITEM_TYPE,F_LENS_RL_TYPE,F_LENS_LENS_CODE,F_LENS_COLOR_COAT_CODE,F_LENS_NAME,F_LENS_COLOR,F_LENS_COAT,F_LENS_CYLINDER_TYPE,F_LENS_SPHERE,F_LENS_CYLINDER,F_LENS_AXIS,F_LENS_ADDITION,F_LENS_DIAMETER,F_LENS_UNIVERSAL_PRODUCT_NAME,S_LENS_RL_TYPE,S_LENS_CODE,S_LENS_COLOR_COAT_CODE,S_LENS_NAME,S_LENS_COLOR,S_LENS_MAKER,S_LENS_NOMINAL_BASE_CURVE,S_LENS_DIAMETER,S_LENS_THICKNESS_TYPE,S_LENS_ADDITION,S_LENS_UNIVERSAL_PRODUCT_NAME,FRAME_CODE,FRAME_MAKER,FRAME_NAME,FRAME_EYE_SIZE,FRAME_DBL,FRAME_COLOR,FRAME_PARTS_TYPE,INSTRUMENT_CODE,INSTRUMENT_NAME,INSTRUMENT_PARTS_NUMBER,STOCK_IO_QUANTITY,RECEIVE_NUMBER,TRANSACTION_CODE,SUB_TRANSACTION_CODE,TRANSACTION_DATE,TRANSACTION_TIME) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static String insertQuery = "INSERT INTO LENS_FRAME_INVENTORY (" +
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

    private List<String> inventoryValues = new ArrayList<>();

    public void addTransactionValue(String value) {
        inventoryValues.add(value);
    }
}