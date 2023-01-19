package de.bencoepp.honnet.daemon.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.bencoepp.entity.test.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestHandler {

    public static ArrayList<Test> getAllTests() throws IOException {
        ArrayList<Test> tests = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        File dir = new File("tests");
        File[] files = dir.listFiles();
        for (File file : files){
            Test test = mapper.readValue(new File(file.getCanonicalPath()), Test.class);
            tests.add(test);
        }
        return tests;
    }
}
