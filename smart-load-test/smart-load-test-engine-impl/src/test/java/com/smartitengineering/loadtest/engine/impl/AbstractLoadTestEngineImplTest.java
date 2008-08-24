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
import com.smartitengineering.loadtest.engine.LoadTestEngine.State;
import com.smartitengineering.loadtest.engine.UnitTestInstance;
import com.smartitengineering.loadtest.engine.events.LoadTestEngineStateChangeListener;
import com.smartitengineering.loadtest.engine.events.LoadTestEngineStateChangedEvent;
import com.smartitengineering.loadtest.engine.events.TestCaseStateChangedEvent;
import com.smartitengineering.loadtest.engine.events.TestCaseTransitionListener;
import com.smartitengineering.loadtest.engine.impl.management.AbstracltTestCaseBatchCreator;
import com.smartitengineering.loadtest.engine.impl.management.DefaultTestCaseBatchCreator;
import com.smartitengineering.loadtest.engine.impl.management.DefaultTestCaseThreadManager;
import com.smartitengineering.loadtest.engine.impl.management.ManagementFactory;
import com.smartitengineering.loadtest.engine.management.TestCaseBatchCreator;
import com.smartitengineering.loadtest.engine.management.TestCaseBatchCreator.Batch;
import com.smartitengineering.loadtest.engine.management.TestCaseThreadManager;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author imyousuf
 */
public class AbstractLoadTestEngineImplTest
    extends TestCase {

    public AbstractLoadTestEngineImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp()
        throws Exception {
        super.setUp();
    }
    private boolean flag;

    /**
     * Test of getState method, of class AbstractLoadTestEngineImpl.
     */
    public void testGetState() {
        System.out.println("getState");
        final DummyLoadTestEngineImpl instance =
            new DummyLoadTestEngineImpl();
        assertEquals(LoadTestEngine.State.CREATED, instance.getState());
        flag = false;
        LoadTestEngineStateChangeListener listener = addDefaultStateChangeListener(
            instance,
            LoadTestEngine.State.CREATED,
            LoadTestEngine.State.INITIALIZED);
        instance.addLoadTestEngineStateChangeListener(new LoadTestEngineStateChangeListener() {

            public void stateChanged(
                LoadTestEngineStateChangedEvent stateChangeEvent) {
                flag = true;
            }
        });
        performDefaultInit(instance);
        assertEquals(Boolean.TRUE.booleanValue(), flag);
        assertEquals(LoadTestEngine.State.INITIALIZED, instance.getState());
        instance.removeLoadTestEngineStateChangeListener(listener);

        flag = false;
        listener = addDefaultStateChangeListener(
            instance,
            LoadTestEngine.State.INITIALIZED,
            LoadTestEngine.State.STARTED);
        instance.start();
        assertEquals(Boolean.TRUE.booleanValue(), flag);
        assertEquals(LoadTestEngine.State.STARTED, instance.getState());
        instance.removeLoadTestEngineStateChangeListener(listener);

        flag = false;
        listener = addDefaultStateChangeListener(
            instance,
            LoadTestEngine.State.STARTED,
            LoadTestEngine.State.FINISHED);
        instance.endTest();
        assertEquals(Boolean.TRUE.booleanValue(), flag);
        assertEquals(LoadTestEngine.State.FINISHED, instance.getState());
        instance.removeLoadTestEngineStateChangeListener(listener);
    }

    /**
     * Test of reinstantiateCreatedState method, of class AbstractLoadTestEngineImpl.
     */
    public void testReinstantiateCreatedState() {
        System.out.println("reinstantiateCreatedState");
        AbstractLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        performDefaultInit(instance);
        instance.reinstantiateCreatedState();
        assertEquals(LoadTestEngine.State.CREATED, instance.getState());
    }

    /**
     * Test of getStartTime method, of class AbstractLoadTestEngineImpl.
     */
    public void testGetStartTime() {
        System.out.println("getStartTime");
        DummyLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        try {
            instance.getStartTime();
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
        performDefaultInit(instance);
        try {
            instance.getStartTime();
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
        instance.start();
        Date startTime = null;
        try {
            startTime = instance.getStartTime();
            assertNotNull(startTime);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
        instance.endTest();
        try {
            Date startTimeAgain = instance.getStartTime();
            assertNotNull(startTimeAgain);
            assertEquals(startTime, startTimeAgain);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    /**
     * Test of getEndTime method, of class AbstractLoadTestEngineImpl.
     */
    public void testGetEndTime() {
        System.out.println("getEndTime");
        DummyLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        try {
            instance.getEndTime();
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
        performDefaultInit(instance);
        try {
            instance.getEndTime();
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
        instance.start();
        try {
            instance.getEndTime();
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
        instance.endTest();
        try {
            Date endTime = instance.getEndTime();
            assertNotNull(endTime);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }

    }

    /**
     * Test of getDuration method, of class AbstractLoadTestEngineImpl.
     */
    public void testGetDuration() {
        System.out.println("getDuration");
        DummyLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        try {
            instance.getDuration();
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
        performDefaultInit(instance);
        try {
            instance.getDuration();
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
        instance.start();
        try {
            instance.getDuration();
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
        instance.endTest();
        try {
            Long duration = new Long(instance.getDuration());
            assertNotNull(duration);
            assertEquals(duration, new Long(instance.getEndTime().getTime() - instance.getStartTime().
                getTime()));

        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    /**
     * Test of addLoadTestEngineStateChangeListener method, of class AbstractLoadTestEngineImpl.
     */
    public void testAddRemoveLoadTestEngineStateChangeListener() {
        System.out.println("add/removeLoadTestEngineStateChangeListener");
        final DummyLoadTestEngineImpl instance =
            new DummyLoadTestEngineImpl();

        instance.addLoadTestEngineStateChangeListener(null);
        assertEquals(0, instance.getEngineStateListeners().size());
        
        flag = false;
        LoadTestEngineStateChangeListener listener = addDefaultStateChangeListener(
            instance,
            LoadTestEngine.State.CREATED,
            LoadTestEngine.State.INITIALIZED);
        
        instance.removeLoadTestEngineStateChangeListener(null);
        assertEquals(1, instance.getEngineStateListeners().size());
        
        instance.addLoadTestEngineStateChangeListener(new LoadTestEngineStateChangeListener() {

            public void stateChanged(
                LoadTestEngineStateChangedEvent stateChangeEvent) {
                flag = true;
            }
        });
        performDefaultInit(instance);
        assertEquals(Boolean.TRUE.booleanValue(), flag);
        instance.removeLoadTestEngineStateChangeListener(listener);

        flag = false;
        listener = addDefaultStateChangeListener(
            instance,
            LoadTestEngine.State.INITIALIZED,
            LoadTestEngine.State.STARTED);
        instance.start();
        assertEquals(Boolean.TRUE.booleanValue(), flag);
        instance.removeLoadTestEngineStateChangeListener(listener);

        flag = false;
        listener = addDefaultStateChangeListener(
            instance,
            LoadTestEngine.State.STARTED,
            LoadTestEngine.State.FINISHED);
        instance.endTest();
        assertEquals(Boolean.TRUE.booleanValue(), flag);
        instance.removeLoadTestEngineStateChangeListener(listener);

    }

    /**
     * Test of addTestCaseTransitionListener method, of class AbstractLoadTestEngineImpl.
     */
    public void testAddRemoveTestCaseTransitionListener() {
        System.out.println("addTestCaseTransitionListener");
        TestCaseTransitionListener listener;
        AbstractLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        listener = null;
        instance.addTestCaseTransitionListener(listener);
        assertEquals(0, instance.getTestCaseTransitionListeners().size());

        listener = new TestCaseTransitionListener() {

            public void testCaseInitialized(TestCaseStateChangedEvent event) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void testCaseStarted(TestCaseStateChangedEvent event) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void testCaseFinished(TestCaseStateChangedEvent event) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void testCaseStopped(TestCaseStateChangedEvent event) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        instance.addTestCaseTransitionListener(listener);
        assertEquals(1, instance.getTestCaseTransitionListeners().size());

        instance.removeTestCaseTransitionListener(null);
        assertEquals(1, instance.getTestCaseTransitionListeners().size());

        instance.removeTestCaseTransitionListener(listener);
        assertEquals(0, instance.getTestCaseTransitionListeners().size());
    }

    /**
     * Test of setTestCaseBatchCreator method, of class AbstractLoadTestEngineImpl.
     */
    public void testSetTestCaseBatchCreator_Class() {
        System.out.println("setTestCaseBatchCreator");
        Class<? extends TestCaseBatchCreator> batchCreator = null;
        AbstractLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        instance.setTestCaseBatchCreator(batchCreator);
        assertNotNull(instance.getTestCaseBatchCreator());
        assertEquals(ManagementFactory.getDefaultBatchCreatorClass(),
            instance.getTestCaseBatchCreator());
        batchCreator = DefaultTestCaseBatchCreator.class;
        instance.setTestCaseBatchCreator(batchCreator);
        assertNotNull(instance.getTestCaseBatchCreator());
        Class<? extends TestCaseBatchCreator> batchCreator2 = null;
        instance.setTestCaseBatchCreator(batchCreator2);
        assertNotNull(instance.getTestCaseBatchCreator());
        assertEquals(batchCreator, instance.getTestCaseBatchCreator());
        performDefaultInit(instance);
        try {
            instance.setTestCaseBatchCreator(batchCreator2);
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Test of setTestCaseBatchCreator method, of class AbstractLoadTestEngineImpl.
     */
    public void testSetTestCaseBatchCreator_String() {
        System.out.println("setTestCaseBatchCreator");
        String batchCreator = null;
        AbstractLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        instance.setTestCaseBatchCreator(batchCreator);
        batchCreator = AbstractLoadTestEngineImpl.class.getName();
        try {
            instance.setTestCaseBatchCreator(batchCreator);
            fail();
        }
        catch (IllegalArgumentException exception) {
        }
        catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
        String batchCreator2 = DefaultTestCaseBatchCreator.class.getName();
        instance.setTestCaseBatchCreator(batchCreator2);
        batchCreator2 = DummyTestCaseBatchCreator.class.getName();
        instance.setTestCaseBatchCreator(batchCreator2);
        performDefaultInit(instance);
        try {
            instance.setTestCaseBatchCreator(batchCreator2);
            fail();
        }
        catch (IllegalStateException exception) {
        }
        catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Test of getTestCaseBatchCreator method, of class AbstractLoadTestEngineImpl.
     */
    public void testGetTestCaseBatchCreator() {
        System.out.println("getTestCaseBatchCreator");
        AbstractLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        assertEquals(DefaultTestCaseBatchCreator.class, instance.
            getTestCaseBatchCreator());
        String batchCreator2 = DummyTestCaseBatchCreator.class.getName();
        instance.setTestCaseBatchCreator(batchCreator2);
        assertEquals(DummyTestCaseBatchCreator.class, instance.
            getTestCaseBatchCreator());
        instance.setTestCaseBatchCreator((String) null);
        assertEquals(DummyTestCaseBatchCreator.class, instance.
            getTestCaseBatchCreator());
        instance.setTestCaseBatchCreator(DefaultTestCaseBatchCreator.class);
        assertEquals(DefaultTestCaseBatchCreator.class, instance.
            getTestCaseBatchCreator());
    }

    /**
     * Test of setTestCaseThreadManager and setTestCaseThreadManager methods, of
     * class AbstractLoadTestEngineImpl.
     */
    public void testGetSetTestCaseThreadManager() {
        System.out.println("setTestCaseThreadManager");
        TestCaseThreadManager threadManager = new DefaultTestCaseThreadManager();
        AbstractLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        instance.setTestCaseThreadManager(null);
        assertEquals(DefaultTestCaseThreadManager.class, instance.
            getTestCaseThreadManager().getClass());
        instance.setTestCaseThreadManager(threadManager);
        assertEquals(threadManager, instance.getTestCaseThreadManager());
    }

    /**
     * Test of getPermits and setPermits methods, of class AbstractLoadTestEngineImpl.
     */
    public void testGetSetPermits() {
        System.out.println("getPermits");
        AbstractLoadTestEngineImpl instance = new DummyLoadTestEngineImpl();
        assertEquals(1, instance.getPermits());
        int permits = 20;
        instance.setPermits(permits);
        assertEquals(permits, instance.getPermits());
        permits = -100;
        instance.setPermits(permits);
        assertEquals(1, instance.getPermits());
        permits = 0;
        instance.setPermits(permits);
        assertEquals(1, instance.getPermits());
        permits = -1;
        instance.setPermits(permits);
        assertEquals(1, instance.getPermits());
        permits = 1;
        instance.setPermits(permits);
        assertEquals(permits, instance.getPermits());
        permits = 2;
        instance.setPermits(permits);
        assertEquals(permits, instance.getPermits());
    }

    protected static HashSet<UnitTestInstance> getDefaultTestInstances() {
        HashSet<UnitTestInstance> testInstances =
            new HashSet<UnitTestInstance>();
        UnitTestInstance testInstance =
            new UnitTestInstance();
        testInstance.setDelayTimeProviderClassName(UniformDelayProvider.class.
            getName());
        testInstance.setIncrementSizeProviderClassName(UniformStepSizeProvider.class.
            getName());
        testInstance.setInstanceFactoryClassName(DefaultTestCaseCreationFactory.class.
            getName());
        testInstance.setName("Test Instanec - 1");
        Properties testProperties = new Properties();
        testProperties.setProperty(
            DefaultTestCaseCreationFactory.CLASS_NAME_PROPS,
            DummyTestCase.class.getName());
        testProperties.setProperty(UniformDelayProvider.DELAY_PROPS,
            Integer.toString(1000));
        testProperties.setProperty(UniformDelayProvider.STEP_COUNT_PROPS,
            Integer.toString(5));
        testProperties.setProperty(UniformStepSizeProvider.UNIT_STEP_SIZE_PROPS,
            Integer.toString(7));
        testInstance.setProperties(testProperties);
        testInstances.add(testInstance);
        return testInstances;
    }

    protected static LoadTestEngineStateChangeListener addDefaultStateChangeListener(
        final AbstractLoadTestEngineImpl instance,
        final State expectedOldState,
        final State expectedNewState) {
        LoadTestEngineStateChangeListener listener = new LoadTestEngineStateChangeListener() {

            public void stateChanged(
                LoadTestEngineStateChangedEvent stateChangeEvent) {
                assertEquals(instance, stateChangeEvent.getSource());
                assertEquals(expectedOldState, stateChangeEvent.getOldValue());
                assertEquals(expectedNewState, stateChangeEvent.getNewValue());
            }
        };
        instance.addLoadTestEngineStateChangeListener(listener);
        return listener;
    }

    protected static void performDefaultInit(
        final AbstractLoadTestEngineImpl instance)
        throws IllegalStateException,
               IllegalArgumentException {
        HashSet<UnitTestInstance> testInstances = getDefaultTestInstances();
        instance.init("Test-1", testInstances, new Properties());
    }

    private static class DummyLoadTestEngineImpl
        extends AbstractLoadTestEngineImpl {

        @Override
        protected void initializeBeforeCreatedState() {
        }

        @Override
        protected void rollBackToCreatedState() {
        }

        public void init(String testName,
                         Set<UnitTestInstance> testInstances,
                         Properties initProperties)
            throws IllegalArgumentException,
                   IllegalStateException {
            super.setState(LoadTestEngine.State.INITIALIZED);
        }

        public void start()
            throws IllegalStateException {
            super.setState(LoadTestEngine.State.STARTED);
        }

        public void endTest() {
            super.setState(LoadTestEngine.State.FINISHED);
        }

        public TestResult getTestResult()
            throws IllegalStateException {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public static class DummyTestCaseBatchCreator
        extends AbstracltTestCaseBatchCreator {

        public void start()
            throws IllegalStateException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Batch getNextBatch()
            throws IllegalStateException {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
