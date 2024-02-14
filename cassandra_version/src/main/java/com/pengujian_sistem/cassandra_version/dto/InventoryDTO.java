package com.pengujian_sistem.cassandra_version.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
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
    private String fLensRlType;
    private String fLensLensCode;
    private String fLensColorCoatCode;
    private String fLensName;
    private String fLensColor;
    private String fLensCoat;
    private String fLensCylinderType;
    private String fLensSphere;
    private String fLensCylinder;
    private String fLensAxis;
    private String fLensAddition;
    private String fLensDiameter;
    private String fLensUniversalProductName;
    private String sLensRlType;
    private String sLensCode;
    private String sLensColorCoatCode;
    private String sLensName;
    private String sLensColor;
    private String sLensMaker;
    private String sLensNominalBaseCurve;
    private String sLensDiameter;
    private String sLensThicknessType;
    private String sLensAddition;
    private String sLensUniversalProductName;
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
    private String stockIoQuantity;
    private int receiveNumber;
    private int transactionCode;
    private String subTransactionCode;
    private LocalDate transactionDate;
    private int transactionTime;
}
