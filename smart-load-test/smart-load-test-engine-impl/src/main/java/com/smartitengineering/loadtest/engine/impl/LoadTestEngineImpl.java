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

import com.smartitengineering.loadtest.engine.TestCase;
import com.smartitengineering.loadtest.engine.UnitTestInstance;
import com.smartitengineering.loadtest.engine.events.BatchEvent;
import com.smartitengineering.loadtest.engine.events.TestCaseBatchListener;
import com.smartitengineering.loadtest.engine.management.TestCaseBatchCreator;
import com.smartitengineering.loadtest.engine.management.TestCaseBatchCreator.Batch;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Default implementation of the LoadTestEngine
 *
 */
public class LoadTestEngineImpl
    extends AbstractLoadTestEngineImpl {

    protected EngineBatchListener engineBatchListener;
    private Map<TestCaseBatchCreator, UnitTestInstance> creators;
    private Semaphore semaphore;

    @Override
    protected void initializeBeforeCreatedState() {
        setTestInstances(new HashSet<UnitTestInstance>());
        creators = new HashMap<TestCaseBatchCreator, UnitTestInstance>();
    }

    public void init(String testName,
                     Set<UnitTestInstance> testInstances,
                     Properties initProperties)
        throws IllegalArgumentException,
               IllegalStateException {
        if (getState().getStateStep() != State.CREATED.getStateStep()) {
            throw new IllegalStateException();
        }
        if (testName == null || testName.length() <= 0) {
            throw new IllegalArgumentException();
        }
        if (testInstances == null || testInstances.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (initProperties != null && !initProperties.isEmpty()) {
            if (initProperties.containsKey(PROPS_PERMIT_KEY)) {
                try {
                    setPermits(Integer.parseInt(initProperties.getProperty(
                        PROPS_PERMIT_KEY)));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    setPermits(-1);
                }
            }
        }

        engineBatchListener = new EngineBatchListener();
        semaphore = new Semaphore(getPermits());
        setTestName(testName);
        getTestInstances().addAll(testInstances);
        for (UnitTestInstance instance : getTestInstances()) {
            try {
                TestCaseBatchCreator creator = getTestCaseBatchCreatorInstance();
                creator.init(instance);
                creator.addBatchCreatorListener(engineBatchListener);
                creators.put(creator, instance);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        setState(State.INITIALIZED);
    }

    public void start()
        throws IllegalStateException {
        if (getState().getStateStep() != State.INITIALIZED.getStateStep()) {
            throw new IllegalStateException();
        }
        getTestCaseThreadManager().startManager();
        for (Map.Entry<TestCaseBatchCreator, UnitTestInstance> creator : creators.
            entrySet()) {
            creator.getKey().start();
        }
        //TODO implement other steps
        setState(State.STARTED);
    }

    public TestResult getTestResult()
        throws IllegalStateException {
        if (getState().getStateStep() < State.FINISHED.getStateStep()) {
            throw new IllegalStateException();
        }
        //TODO implement test result
        return null;
    }

    @Override
    protected void rollBackToCreatedState() {
        getTestInstances().clear();
        setTestName(null);
        semaphore = null;
        engineBatchListener = null;
    }

    protected class EngineBatchListener
        implements TestCaseBatchListener {

        public void batchAvailable(BatchEvent event) {
            boolean acquired = false;
            try {
                semaphore.acquire();
                acquired = true;
                Batch batch;
                Map.Entry<ThreadGroup, Map<Thread, TestCase>> batchThreads;
                batch = event.getBatch();
                if (batch != null) {
                    batchThreads = batch.getBatch();
                    for (Map.Entry<Thread, TestCase> thread : batchThreads.
                        getValue().entrySet()) {
                        //TODO add test case listeners to monitor the test cases
                        thread.getKey().start();
                        getTestCaseThreadManager().manageThread(thread.getKey(),
                            thread.getValue());
                    }
                    batch.setBatchStarted();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                if (acquired) {
                    semaphore.release();
                    acquired = false;
                }
            }
        }

        public void batchCreationEnded(BatchEvent event) {
        }
    }
}
