package com.pengujian_sistem.db2_version.helper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Helpers {
    /**
     * Convert string to date
     */
    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsedDate = dateFormat.parse(dateString);
        return new Date(parsedDate.getTime());
    }
}
