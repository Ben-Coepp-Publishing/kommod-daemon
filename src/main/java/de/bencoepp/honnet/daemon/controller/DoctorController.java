package de.bencoepp.honnet.daemon.controller;

import de.bencoepp.entity.Check;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/api/doctor")
public class DoctorController {

    @GetMapping("/all")
    public ResponseEntity<ArrayList<Check>> getAllChecks(){
        ArrayList<Check> checks = new ArrayList<>();
        checks.add(new Check("Some Title","Some description", "ls -la", true));
        return ResponseEntity.ok(checks);
    }
}
