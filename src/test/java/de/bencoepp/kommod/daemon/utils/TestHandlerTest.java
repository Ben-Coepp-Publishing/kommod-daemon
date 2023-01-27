package de.bencoepp.kommod.daemon.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This Test class tests the TestHandler for all methods and situations. Please note that this just tests
 * if the methods can run and are working.
 * */
class TestHandlerTest {

    /**
     * Test if possible to get all tests
     * true if the list is not empty
     * false if exception or list is empty
     */
    @Test
    void getAllTests() {
        try {
            List<de.bencoepp.entity.test.Test> tests = TestHandler.getAllTests();
            assertTrue(!tests.isEmpty());
        } catch (IOException e) {
            assertFalse(true,e.getMessage());
        }
    }

    @Test
    void getTest() {
        try {
            de.bencoepp.entity.test.Test test = TestHandler.getTest("base_network.yaml");
            if(test != null){
                assertTrue(true);
            }else{
                assertTrue(false);
            }
        } catch (IOException e) {
            assertFalse(true,e.getMessage());
        }
    }
}