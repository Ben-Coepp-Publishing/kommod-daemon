package de.bencoepp.kommod.daemon.controller;

import de.bencoepp.entity.test.Test;
import de.bencoepp.kommod.daemon.utils.TestHandler;
import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

@Controller
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public ResponseEntity<ArrayList<Test>> getAllTests() throws IOException {
        ArrayList<Test> tests = TestHandler.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @PostMapping("/run")
    public ResponseEntity<String> runTests(@RequestBody String command) throws IOException {
        ArrayList<Test> tests = TestHandler.getAllTests();
        JSONObject object = new JSONObject(command);
        if(object.getInt("depth") == 4){
            //Here we will execute Tests based on their name
            JSONArray testArray = object.getJSONArray("tests");
            String[] selectedTest = testArray.toList().toArray(new String[0]);
            for (ListIterator<Test> iter = tests.listIterator(); iter.hasNext(); ) {
                Test element = iter.next();
                if(!Arrays.asList(selectedTest).contains(element.getTitle())){
                    iter.remove();
                }
            }
            System.out.println(tests);
        }else{
            for (ListIterator<Test> iter = tests.listIterator(); iter.hasNext(); ) {
                Test element = iter.next();
                if(element.getDepth() > object.getInt("depth")){
                    iter.remove();
                }
            }
            System.out.println(tests);
        }
        return ResponseEntity.ok("");
    }
}
