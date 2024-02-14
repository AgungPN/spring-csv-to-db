package com.pengujian_sistem.db2_version.helper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Helpers {
    /**
     * Convert string to date
     */
    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsedDate = dateFormat.parse(dateString);
        return new Date(parsedDate.getTime());
    }

    /**
     * Chunk list into smaller list
     */
    public static List<List<Object[]>> chunkList(List<Object[]> batchTransactionArgs, int chunkSize) {
        List<List<Object[]>> chunks = new ArrayList<>();
        for (int i = 0; i < batchTransactionArgs.size(); i += chunkSize) {
            chunks.add(batchTransactionArgs.subList(i, Math.min(i + i, batchTransactionArgs.size())));
        }

        return chunks;
    }
}
