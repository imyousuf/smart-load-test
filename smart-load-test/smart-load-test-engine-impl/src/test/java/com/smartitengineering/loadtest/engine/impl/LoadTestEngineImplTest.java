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

import com.smartitengineering.loadtest.engine.LoadTestEngine;
import com.smartitengineering.loadtest.engine.UnitTestInstance;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import junit.framework.TestCase;

/**
 *
 * @author imyousuf
 */
public class LoadTestEngineImplTest
    extends TestCase {

    public LoadTestEngineImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp()
        throws Exception {
        super.setUp();
    }

    public void testInit() {
        LoadTestEngine engine;
        //Following is the success scenario
        engine = new LoadTestEngineImpl();
        Properties properties = new Properties();
        //Allow 4 simultaneous batch creators be in action at a time
        properties.setProperty(LoadTestEngineImpl.PROPS_PERMIT_KEY, "4");
        HashSet<UnitTestInstance> hashSet = new HashSet();
        createDataSet1(hashSet);
        final String testName = "test-1";
        engine.init( testName, hashSet, properties);
        assertEquals(LoadTestEngine.State.INITIALIZED, engine.getState());
        engine.reinstantiateCreatedState();
        engine.init( testName, hashSet, null);
        assertEquals(LoadTestEngine.State.INITIALIZED, engine.getState());
        engine.reinstantiateCreatedState();
        engine.init( testName, hashSet, new Properties());
        assertEquals(LoadTestEngine.State.INITIALIZED, engine.getState());
        engine.reinstantiateCreatedState();
        final Properties invalidProps = new Properties();
        invalidProps.setProperty(LoadTestEngineImpl.PROPS_PERMIT_KEY, "4asd");
        engine.init( testName, hashSet, invalidProps);
        assertEquals(LoadTestEngine.State.INITIALIZED, engine.getState());
        
        //Following are the failure scenarios
        
        //Scenario-1 test case name is empty
        engine.reinstantiateCreatedState();
        try {
            engine.init(null, hashSet, properties);
            fail("Initialization should not succeed! - Invalid name");
        }
        catch (IllegalArgumentException exception) {
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
        try {
            engine.init(null, hashSet, properties);
            fail("Initialization should not succeed! - Invalid name");
        }
        catch (IllegalArgumentException exception) {
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
        
        //Scenario-2 test case name is null/empty
        engine.reinstantiateCreatedState();
        try {
            engine.init(testName, null, properties);
            fail("Initialization should not succeed! - Invalid test instance");
        }
        catch (IllegalArgumentException exception) {
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
        engine.reinstantiateCreatedState();
        try {
            engine.init(testName, Collections.<UnitTestInstance>emptySet(), properties);
            fail("Initialization should not succeed! - Invalid test instance");
        }
        catch (IllegalArgumentException exception) {
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
        
    }

    public void testStart() {
    }

    public void testGetTestResult() {
    }

    public void testAddTestCaseBatchListener() {
    }

    public void testRemoveTestCaseBatchListener() {
    }

    private void createDataSet1(HashSet<UnitTestInstance> hashSet) {
        UnitTestInstance instance 
            = createUnitTestInstance("instance-1", 100, 5, 3, 1000);
        hashSet.add(instance);
        instance = createUnitTestInstance("instance-2", 50, 6, 4, 1000);
        hashSet.add(instance);
        instance = createUnitTestInstance("instance-3", 200, 3, 5, 1000);
        hashSet.add(instance);
        instance = createUnitTestInstance("instance-4", 100, 3, 5, 500);
        hashSet.add(instance);
    }

    private UnitTestInstance createUnitTestInstance(final String testName,
                                                    final int sleepDuration, 
                                                    final int numOfBatches,
                                                    final int testCasesPerBatch,
                                                    final int batchInterval) {
        /*
        final int batchInterval = 1000;
        final int testCasesPerBatch = 3;
        final int numOfBatches = 5;
        final int sleepDuration = 100;
        */
        UnitTestInstance instance = new UnitTestInstance();
        Properties properties = new Properties();

        //Name the test case class
        properties.setProperty(DefaultTestCaseCreationFactory.CLASS_NAME_PROPS,
            DummyTestCase.class.getName());

        //Specify how long the test case would sleep
        properties.setProperty(DummyTestCase.SLEEP_TIME_PROP, Integer.toString(sleepDuration));

        //Create batches at 1s interval
        properties.setProperty(UniformDelayProvider.DELAY_PROPS,
            Integer.toString(batchInterval));
        
        //Create 5 batches
        properties.setProperty(UniformDelayProvider.STEP_COUNT_PROPS,
            Integer.toString(numOfBatches));
        
        //Create 3 test cases for each batch
        properties.setProperty(UniformStepSizeProvider.UNIT_STEP_SIZE_PROPS,
            Integer.toString(testCasesPerBatch));

        //Set the different class names
        instance.setInstanceFactoryClassName(DefaultTestCaseCreationFactory.class.
            getName());
        instance.setDelayTimeProviderClassName(UniformDelayProvider.class.
            getName());
        instance.setIncrementSizeProviderClassName(UniformStepSizeProvider.class.
            getName());
        instance.setProperties(properties);
        instance.setName(testName);
        return instance;
    }
}
