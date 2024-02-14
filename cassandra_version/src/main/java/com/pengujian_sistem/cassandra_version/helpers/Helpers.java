package com.pengujian_sistem.cassandra_version.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helpers {
    /**
     * Convert string to local date
     * We need this to use LocalDate in Cassandra
     */
    public static LocalDate convertStringToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(dateString, formatter);
    }
}
