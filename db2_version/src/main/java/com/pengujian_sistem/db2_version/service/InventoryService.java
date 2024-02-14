package com.pengujian_sistem.db2_version.service;

import com.pengujian_sistem.db2_version.helper.Helpers;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.StringTokenizer;

@Service
public class InventoryService {
    public final static String INSERT_QUERY = "INSERT INTO LENS_FRAME_INVENTORY (CMPNYCD,STOCK_HANDLING_CUSTOMER_NUMBER,STOCK_POINT,SLIP_NUMBER,LINE_NUMBER,ITEM_TYPE,F_LENS_RL_TYPE,F_LENS_LENS_CODE,F_LENS_COLOR_COAT_CODE,F_LENS_NAME,F_LENS_COLOR,F_LENS_COAT,F_LENS_CYLINDER_TYPE,F_LENS_SPHERE,F_LENS_CYLINDER,F_LENS_AXIS,F_LENS_ADDITION,F_LENS_DIAMETER,F_LENS_UNIVERSAL_PRODUCT_NAME,S_LENS_RL_TYPE,S_LENS_CODE,S_LENS_COLOR_COAT_CODE,S_LENS_NAME,S_LENS_COLOR,S_LENS_MAKER,S_LENS_NOMINAL_BASE_CURVE,S_LENS_DIAMETER,S_LENS_THICKNESS_TYPE,S_LENS_ADDITION,S_LENS_UNIVERSAL_PRODUCT_NAME,FRAME_CODE,FRAME_MAKER,FRAME_NAME,FRAME_EYE_SIZE,FRAME_DBL,FRAME_COLOR,FRAME_PARTS_TYPE,INSTRUMENT_CODE,INSTRUMENT_NAME,INSTRUMENT_PARTS_NUMBER,STOCK_IO_QUANTITY,RECEIVE_NUMBER,TRANSACTION_CODE,SUB_TRANSACTION_CODE,TRANSACTION_DATE,TRANSACTION_TIME) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * add inventory data to rows
     */
    public Object[] addToRows(String content) throws ParseException {
        StringTokenizer tkn = new StringTokenizer(content, ",");
        return new Object[]{
                tkn.nextToken(), // CMPNYCD
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
                Integer.parseInt(tkn.nextToken()), // RECEIVE_NUMBER
        };
    }
}
