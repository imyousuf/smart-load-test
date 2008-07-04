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

import com.smartitengineering.loadtest.engine.TestCase;
import com.smartitengineering.loadtest.engine.management.TestCaseBatchCreator;
import com.smartitengineering.loadtest.engine.management.TestCaseThreadManager;
import com.smartitengineering.loadtest.engine.management.TestCaseThreadPolicy;
import java.util.Map;

/**
 * A simple factory for creating default instances of different SPI
 * implementations.
 * @author imyousuf
 */
public final class ManagementFactory {

    private ManagementFactory() {
    }

    /**
     * Create a timeout based thread policy with default timeout set to 5
     * minutes.
     * @return Default thread policy.
     */
    public static TestCaseThreadPolicy getDefaultThreadPolicy() {
        final TimeoutThreadPolicy timeoutThreadPolicy =
            new TimeoutThreadPolicy();
        timeoutThreadPolicy.setTimeoutPeriod(5 * 60 * 1000);
        timeoutThreadPolicy.setTestCaseStoppableStatusDisabled(false);
        return timeoutThreadPolicy;
    }

    /**
     * Return an instance of default Batch implemenation for the for this SPI
     * implementation. Batch returned by this factory is intended to be used by
     * a test case batch creator to trigger batch event and return getBatch from
     * itself.
     * @param creator The creator that created the test cases' thread group.
     * @param batch The batch represented by its thread group, threads and test
     *              cases.
     * @return The batch that has the batch as its batch and creator as its
     *          creator. Creator will use it as its batch and trigger event.
     * @throws java.lang.IllegalArgumentException If creator or batch is null or
     *                                              creator does not have next
     *                                              batch available.
     */
    public static TestCaseBatchCreator.Batch getDefaultBatch(
        final TestCaseBatchCreator creator,
        final Map.Entry<ThreadGroup, Map<Thread, TestCase>> batch)
        throws IllegalArgumentException {
        if (creator == null || !creator.isNextBatchAvailable() || batch == null) {
            throw new IllegalArgumentException();
        }
        return new SimpleBatch(creator, batch);
    }

    /**
     * Create a default thread manager that monitors thread at a definit
     * interval. It check whether the thread is stoppable or interruptable or
     * not and based on that it takes step. If a thread expires the thread
     * policy then it is forcefully shutdown.
     * @param pollInterval The interval for checking thread's condition and its
     *                      relevant policy
     * @return The default text case thread manager configured with definite
     *          interval
     */
    public static TestCaseThreadManager getDefaultThreadManager(
        final int pollInterval) {
        DefaultTestCaseThreadManager manager =
            new DefaultTestCaseThreadManager();
        if (pollInterval > 0) {
            manager.setPollInterval(pollInterval);
        }
        manager.setThreadPolicy(getDefaultThreadPolicy());
        return manager;
    }

    /**
     * Return default batch creator that will create the necessary threads for a
     * given unit test instance provided during init-ialization of the thread.
     * @return The default batch creator.
     */
    public static TestCaseBatchCreator getDefaultBatchCreator() {
        DefaultTestCaseBatchCreator batchCreator =
            new DefaultTestCaseBatchCreator();
        return batchCreator;
    }

    /**
     * Return default batch creator class that might be used to initialize it.
     * @return The default batch creator class
     */
    public static Class<? extends TestCaseBatchCreator> getDefaultBatchCreatorClass() {
        return DefaultTestCaseBatchCreator.class;
    }
}
