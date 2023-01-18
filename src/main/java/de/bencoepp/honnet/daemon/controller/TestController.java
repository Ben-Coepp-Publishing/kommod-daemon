package de.bencoepp.honnet.daemon.controller;

import de.bencoepp.entity.test.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Array;
import java.util.ArrayList;

@Controller
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public ResponseEntity<ArrayList<Test>> getAllTests(){
        ArrayList<Test> tests = new ArrayList<>();
        return ResponseEntity.ok(tests);
    }
}
