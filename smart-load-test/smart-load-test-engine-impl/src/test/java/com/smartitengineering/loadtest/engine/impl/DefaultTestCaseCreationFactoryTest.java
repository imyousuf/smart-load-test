/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.loadtest.engine.impl;

import com.smartitengineering.loadtest.engine.TestCaseCreationFactory;
import com.smartitengineering.loadtest.engine.TestCaseFactoryRegister;
import java.util.Properties;
import junit.framework.TestCase;

/**
 *
 * @author imyousuf
 */
public class DefaultTestCaseCreationFactoryTest
    extends TestCase {

    public DefaultTestCaseCreationFactoryTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp()
        throws Exception {
        super.setUp();
    }

    public void testFactoryCreation() {
        assertEquals(false, TestCaseFactoryRegister.hasFactory(
            TestCaseFactoryRegister.DEFAULT_FACTORY));
        TestCaseCreationFactory defaultFactory = DefaultTestCaseCreationFactory.
            getInstance();
        assertEquals(true, TestCaseFactoryRegister.hasFactory(
            TestCaseFactoryRegister.DEFAULT_FACTORY));
        assertEquals(defaultFactory, TestCaseFactoryRegister.getTestCaseFactory(
            TestCaseFactoryRegister.DEFAULT_FACTORY));
    }

    public void testTestCaseCreation() {
        DefaultTestCaseCreationFactory.getInstance();
        Properties properties = new Properties();
        properties.put(DefaultTestCaseCreationFactory.CLASS_NAME_PROPS,
            DummyTestCase.class.getName());
        com.smartitengineering.loadtest.engine.TestCase testCase
            = TestCaseFactoryRegister
            .getTestCaseFactory(TestCaseFactoryRegister.DEFAULT_FACTORY)
            .getTestCase(properties);
        assertEquals(true, testCase != null);
        assertEquals(true, testCase.getClass().equals(DummyTestCase.class));
        properties.put(DefaultTestCaseCreationFactory.CLASS_NAME_PROPS, "av");
        try {
            testCase = TestCaseFactoryRegister.getTestCaseFactory(
                TestCaseFactoryRegister.DEFAULT_FACTORY).getTestCase(properties);
            assertEquals(true, testCase == null);
        }
        catch(IllegalArgumentException ex) {
        }
    }
}
