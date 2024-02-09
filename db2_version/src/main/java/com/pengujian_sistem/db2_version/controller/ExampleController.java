package com.pengujian_sistem.db2_version.controller;

import com.pengujian_sistem.db2_version.jdbc.Things;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ExampleController {
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("test")
    public @ResponseBody ResponseEntity<String> example() {
        List<String> list = new ArrayList<>();
        list.add("Table data...");
        jdbcTemplate.query(
                "SELECT * FROM THINGS.THINGS", new Object[]{},
                (rs, rowNum) -> new Things(rs.getLong("id"), rs.getString("name"))
        ).forEach(thing -> list.add(thing.toString()));

        return new ResponseEntity<String>(list.toString(), HttpStatus.OK);
    }
}
