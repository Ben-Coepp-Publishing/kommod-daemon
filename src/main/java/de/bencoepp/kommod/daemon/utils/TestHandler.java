package de.bencoepp.kommod.daemon.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.bencoepp.entity.test.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is responsible for all things consuming the handling of tests and there respected resources
 * please note that this class os just a handler.
 *
 * Most of the methods in this class are static so you can go ahead and call them from everywhere you want.
 */
public class TestHandler {

    /** This method will return a list of tests. To be exact it will return the all tests from the test
     * directory. Please only use if you want to get all of them. This method is quite resource intensive.
     * @return List<Test>>
     * @throws IOException
     */
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

    /** Use this method to get one specific test depending on the path of the test you want to get.
     * Use this if you want to get one specific test.
     * @param path
     * @return
     * @throws IOException
     */
    public static Test getTest(String path) throws IOException {
        File file = new File("tests/" + path);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, Test.class);
    }
}
