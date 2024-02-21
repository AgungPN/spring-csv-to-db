package com.pengujian_sistem.db2_version.controller;

import com.pengujian_sistem.db2_version.dto.InventoryDTO;
import com.pengujian_sistem.db2_version.dto.InventoryResponse;
import com.pengujian_sistem.db2_version.dto.TransactionDTO;
import com.pengujian_sistem.db2_version.dto.TransactionResponse;
import com.pengujian_sistem.db2_version.service.ImportService;
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
@RequestMapping("/api/db2")
public class DB2Controller {
    private ImportService importService;

    @GetMapping("/transactions")
    public List<TransactionResponse> getTransactions() {
        return importService.getListTransactions();
    }

    @GetMapping("/inventories")
    public List<InventoryResponse> getInventories() {
        return importService.getListInventories();
    }

    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestParam("file") MultipartFile file) throws ParseException, IOException {
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
    public void pendingCsvToDb() {
        try {
            importService.pendingCsvToDb();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
