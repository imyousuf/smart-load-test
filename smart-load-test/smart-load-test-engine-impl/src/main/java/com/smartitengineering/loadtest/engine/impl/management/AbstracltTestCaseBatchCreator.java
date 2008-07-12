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
package com.smartitengineering.loadtest.engine.impl.management;

import com.smartitengineering.loadtest.engine.DelayProvider;
import com.smartitengineering.loadtest.engine.NextStepSizeProvider;
import com.smartitengineering.loadtest.engine.TestCase;
import com.smartitengineering.loadtest.engine.TestCaseCreationFactory;
import com.smartitengineering.loadtest.engine.UnitTestInstance;
import com.smartitengineering.loadtest.engine.events.BatchEvent;
import com.smartitengineering.loadtest.engine.events.TestCaseBatchListener;
import com.smartitengineering.loadtest.engine.management.TestCaseBatchCreator;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public abstract class AbstracltTestCaseBatchCreator
    implements TestCaseBatchCreator {

    protected TestCaseCreationFactory testCaseCreationFactory;
    protected DelayProvider delayProvider;
    protected NextStepSizeProvider stepSizeProvider;
    protected boolean initialized;
    protected boolean nextBatchAvailable;
    private Set<TestCaseBatchListener> batchListeners;
    protected Properties initalProperties;
    protected String name;

    protected AbstracltTestCaseBatchCreator() {
        setNextBatchAvailable(false);
        setInitialized(false);
        batchListeners = new HashSet<TestCaseBatchListener>();
    }

    public void init(UnitTestInstance testInstance)
        throws IllegalArgumentException {
        if (testInstance == null) {
            setInitialized(false);
            throw new IllegalArgumentException();
        }
        try {
            testCaseCreationFactory = (TestCaseCreationFactory) Class.forName(
                testInstance.getInstanceFactoryClassName()).newInstance();
            delayProvider = (DelayProvider) Class.forName(testInstance.
                getDelayTimeProviderClassName()).newInstance();
            delayProvider.init(testInstance.getProperties());
            stepSizeProvider = (NextStepSizeProvider) Class.forName(
                testInstance.getIncrementSizeProviderClassName()).newInstance();
            stepSizeProvider.init(testInstance.getProperties());
            if (delayProvider.size() != stepSizeProvider.size()) {
                throw new IllegalArgumentException(
                    "Delay and step size provider size is not equal");
            }
            initalProperties = testInstance.getProperties();
            name = testInstance.getName();
            if (initalProperties == null || name == null || name.length() <= 0) {
                throw new IllegalArgumentException();
            }
        }
        catch (Exception ex) {
            setInitialized(false);
            throw new IllegalArgumentException(ex);
        }
        setInitialized(true);
    }

    public boolean isNextBatchAvailable() {
        return nextBatchAvailable;
    }

    public void addBatchCreatorListener(TestCaseBatchListener batchListener) {
        if (batchListener != null) {
            batchListeners.add(batchListener);
        }
    }

    public void removeBatchCreatrorListener(TestCaseBatchListener batchListener) {
        if (batchListener != null) {
            batchListeners.remove(batchListener);
        }
    }

    public DelayProvider getDelayProvider() {
        if (isInitialized()) {
            return delayProvider;
        }
        throw new IllegalStateException();
    }

    public TestCaseCreationFactory getCreationFactory()
        throws IllegalStateException {
        if (isInitialized()) {
            return testCaseCreationFactory;
        }
        throw new IllegalStateException();
    }

    public NextStepSizeProvider getNextStepSizeProvider()
        throws IllegalStateException {
        if (isInitialized()) {
            return stepSizeProvider;
        }
        throw new IllegalStateException();
    }

    /**
     * Gets the default batch implementation's instace from the SPI factory.
     * Prior to retrieving the batch it also sets true flag for next batch
     * availibility.
     * @param batch The thread group and its components.
     * @return The Batch representation of the current branch.
     * @throws java.lang.IllegalArgumentException If batch is NULL
     */
    protected Batch getBatch(
        final Map.Entry<ThreadGroup, Map<Thread, TestCase>> batch)
        throws IllegalArgumentException {
        if (batch == null) {
            throw new IllegalArgumentException();
        }
        setNextBatchAvailable(true);
        return ManagementFactory.getDefaultBatch(this, batch);
    }

    /**
     * Fire event for new batch available for the observers.
     */
    protected void fireBatchEvent() {
        if (isNextBatchAvailable()) {
            BatchEvent batchEvent = new BatchEvent(getNextBatch());
            fireBatchEvent(batchEvent);
        }
    }

    /**
     * Fire event for the batch event generated; one of its use cases is when
     * batch creation has finished
     * @param event The event to use to notify observers
     */
    protected void fireBatchEvent(BatchEvent event) {
        if (event != null) {
            for (TestCaseBatchListener listener : batchListeners) {
                listener.batchAvailable(event);
            }
        }
    }
    protected void setNextBatchAvailable(boolean nextBatchAvailable) {
        this.nextBatchAvailable = nextBatchAvailable;
    }

    protected NextStepSizeProvider getStepSizeProvider() {
        return stepSizeProvider;
    }

    protected TestCaseCreationFactory getTestCaseCreationFactory() {
        return testCaseCreationFactory;
    }

    protected boolean isInitialized() {
        return initialized;
    }

    protected void setInitialized(boolean initializationFailed) {
        this.initialized = initializationFailed;
    }

    protected Set<TestCaseBatchListener> getBatchListeners() {
        return batchListeners;
    }
}
