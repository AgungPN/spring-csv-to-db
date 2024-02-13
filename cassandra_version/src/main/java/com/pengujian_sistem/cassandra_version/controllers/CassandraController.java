package com.pengujian_sistem.cassandra_version.controllers;

import com.pengujian_sistem.cassandra_version.dto.InventoryDTO;
import com.pengujian_sistem.cassandra_version.dto.TransactionDTO;
import com.pengujian_sistem.cassandra_version.services.ImportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cassandra")
public class CassandraController {
    private ImportService importService;

    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions() {
        return importService.getTransactionService().getList();
    }

    @GetMapping("/inventories")
    public List<InventoryDTO> getInventories() {
        return importService.getInventoryService().getList();
    }

    @PostMapping("/store")
    public void storeData() {
        try {
            importService.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
