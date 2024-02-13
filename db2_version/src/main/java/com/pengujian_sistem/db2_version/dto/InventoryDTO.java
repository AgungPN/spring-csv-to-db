package com.pengujian_sistem.db2_version.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDTO {
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
    private double fLensSphere;
    private double fLensCylinder;
    private String fLensAxis;
    private String fLensAddition;
    private int fLensDiameter;
    private String fLensUniversalProductName;
    private String sLensRlType;
    private String sLensCode;
    private String sLensColorCoatCode;
    private String sLensName;
    private String sLensColor;
    private String sLensMaker;
    private double sLensNominalBaseCurve;
    private int sLensDiameter;
    private String sLensThicknessType;
    private String sLensAddition;
    private String sLensUniversalProductName;
    private String frameCode;
    private String frameMaker;
    private String frameName;
    private int frameEyeSize;
    private int frameDbl;
    private String frameColor;
    private String framePartsType;
    private String instrumentCode;
    private String instrumentName;
    private String instrumentPartsNumber;
    private int stockIoQuantity;
    private int receiveNumber;
    private String transactionCode;
    private String subTransactionCode;
    private Integer transactionDate;
    private Integer transactionTime;
}
