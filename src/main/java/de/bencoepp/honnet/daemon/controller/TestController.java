package de.bencoepp.honnet.daemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.bencoepp.entity.test.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public ResponseEntity<ArrayList<Test>> getAllTests() throws IOException {
        ArrayList<Test> tests = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        File dir = new File("tests");
        File[] files = dir.listFiles();
        for (File file : files){
            Test test = mapper.readValue(new File(file.getCanonicalPath()), Test.class);
            tests.add(test);
        }
        return ResponseEntity.ok(tests);
    }

    @PostMapping("/run")
    public ResponseEntity<String> runTests(@RequestBody String command){
        System.out.println(command);
        return ResponseEntity.ok("");
    }
}
