package com.pengujian_sistem.db2_version.controller;

import com.pengujian_sistem.db2_version.service.ImportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/db2")
public class DB2Controller {
    private ImportService importService;

//    public List<TransactionDTO> getTransactions() {
//        return importService.getTransactions();
//    }

//    public List<InventoryDTO> getInventories() {
//
//    }

    @PostMapping("/store")
    public void store() {
        try {
            importService.store();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
