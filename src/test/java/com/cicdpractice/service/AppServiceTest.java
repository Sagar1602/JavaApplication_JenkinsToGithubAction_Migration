package com.cicdpractice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppServiceTest {

    private AppService appService;

    @BeforeEach
    public void setUp() {
        appService = new AppService();
    }

    @Test
    public void testGetApplicationInfo() {
        String info = appService.getApplicationInfo();
        assertNotNull(info);
        assertTrue(info.contains("CICD"));
    }

    @Test
    public void testValidateInputWithValidData() {
        assertTrue(appService.validateInput("hello"));
    }

    @Test
    public void testValidateInputWithInvalidData() {
        assertFalse(appService.validateInput("ab"));
        assertFalse(appService.validateInput(""));
        assertFalse(appService.validateInput(null));
    }

    @Test
    public void testProcessData() {
        String result = appService.processData("test");
        assertEquals("Processed: TEST", result);
    }

    @Test
    public void testProcessDataWithInvalidInput() {
        String result = appService.processData("ab");
        assertEquals("Invalid input", result);
    }

    @Test
    public void testGetHealthCheck() {
        String health = appService.getHealthCheck();
        assertNotNull(health);
        assertTrue(health.contains("healthy"));
    }
}
