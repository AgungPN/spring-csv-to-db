package com.pengujian_sistem.db2_version.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDTO {
    private Integer id;
    private String cmpnycd;
    private String stockHandlingCustomerNumber;
    private String stockPoint;
    private String slipNumber;
    private String lineNumber;
    private String itemType;
    private String lensRlType;
    private String lensLensCode;
    private String lensColorCoatCode;
    private String lensName;
    private String lensColor;
    private String lensCoat;
    private String lensCylinderType;
    private String lensSphere;
    private String lensCylinder;
    private String lensAxis;
    private String lensAddition;
    private String lensDiameter;
    private String lensUniversalProductName;
    private String frameCode;
    private String frameMaker;
    private String frameName;
    private String frameEyeSize;
    private String frameDbl;
    private String frameColor;
    private String framePartsType;
    private String instrumentCode;
    private String instrumentName;
    private String instrumentPartsNumber;
    private Integer stockIoQuantity;
    private Integer receiveNumber;
    private Date transactionDate;
    private Integer transactionTime;
}
