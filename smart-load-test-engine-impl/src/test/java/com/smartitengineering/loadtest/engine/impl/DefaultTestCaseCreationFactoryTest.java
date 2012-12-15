/**
 *    This module represents an engine IMPL for the load testing framework
 *    Copyright (C) 2008  Imran M Yousuf (imran@smartitengineering.com)
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
