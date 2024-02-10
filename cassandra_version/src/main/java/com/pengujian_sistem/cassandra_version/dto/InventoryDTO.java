package com.pengujian_sistem.cassandra_version.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
@Builder
public class InventoryDTO {
    private UUID id;
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
    private String  stockIoQuantity;
    private String  receiveNumber;
    private String  transactionDate;
    private String  transactionTime;
}
