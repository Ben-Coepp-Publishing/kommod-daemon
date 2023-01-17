package de.bencoepp.honnet.daemon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping("/")
    public ResponseEntity<Integer> getHealth(){
        return ResponseEntity.ok(200);
    }
}
