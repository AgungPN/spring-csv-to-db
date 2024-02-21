package com.pengujian_sistem.cassandra_version.controllers;

import com.pengujian_sistem.cassandra_version.dto.InventoryDTO;
import com.pengujian_sistem.cassandra_version.dto.InventoryResponse;
import com.pengujian_sistem.cassandra_version.dto.TransactionDTO;
import com.pengujian_sistem.cassandra_version.dto.TransactionResponse;
import com.pengujian_sistem.cassandra_version.services.ImportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cassandra")
public class CassandraController {
    private ImportService importService;

    @GetMapping("/transactions")
    public List<TransactionResponse> getTransactions() {
        return importService.getTransactionService().getList();
    }

    @GetMapping("/inventories")
    public List<InventoryResponse> getInventories() {
        return importService.getInventoryService().getList();
    }


    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestParam("file") MultipartFile file) throws IOException {
        if (!"text/csv".equalsIgnoreCase(file.getContentType())) {
            return ResponseEntity.badRequest().body(Map.of("message", "File must be in CSV format"));
        }

        List<String> contents = new BufferedReader(
                new InputStreamReader(file.getInputStream())
        ).lines().toList();
        importService.storeFileToDB(contents);
        return ResponseEntity.ok().body(Map.of("message", "File has been stored to database"));
    }

    @PostMapping("/pending-to-db")
    public void pendingCsvToDb() throws IOException {
        importService.pendingCsvToDB();
    }
}
