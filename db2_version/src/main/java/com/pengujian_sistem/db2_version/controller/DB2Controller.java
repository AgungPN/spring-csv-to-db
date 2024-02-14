package com.pengujian_sistem.db2_version.controller;

import com.pengujian_sistem.db2_version.dto.InventoryDTO;
import com.pengujian_sistem.db2_version.dto.TransactionDTO;
import com.pengujian_sistem.db2_version.service.ImportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/db2")
public class DB2Controller {
    private ImportService importService;

//    public List<TransactionDTO> getTransactions() {
//        return importService.getTransactionService().getList();
//    }

    @GetMapping("/inventories")
    public List<InventoryDTO> getInventories() {
        return importService.getListInventories();
    }

    @PostMapping("/store")
    public void store() {
        try {
            importService.store();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
