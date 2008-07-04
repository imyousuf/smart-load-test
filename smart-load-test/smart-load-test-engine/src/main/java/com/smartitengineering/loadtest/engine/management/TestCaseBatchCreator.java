/**
 *    This module represents an engine for the load testing framework
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
package com.smartitengineering.loadtest.engine.management;

import com.smartitengineering.loadtest.engine.DelayProvider;
import com.smartitengineering.loadtest.engine.NextStepSizeProvider;
import com.smartitengineering.loadtest.engine.TestCase;
import com.smartitengineering.loadtest.engine.TestCaseCreationFactory;
import com.smartitengineering.loadtest.engine.UnitTestInstance;
import com.smartitengineering.loadtest.engine.events.TestCaseBatchListener;
import java.util.Map;

/**
 * A test case batch creator's purpose is to make test case batche available for
 * for test engine to work for them without the test engine having to generate
 * them and rather simply observing the batch creator. A test case has to have a
 * no args constructor and upon initializing the first operation on it should be
 * the init method. If init method is not called it could result in a NPE. It is
 * also safe to first check if batch is available or not before actually
 * retrieving the batch.
 *
 * @author imyousuf
 */
public interface TestCaseBatchCreator {

    /**
     * Initialie the batch creator with a test instance for which it is to
     * create batches. This should be the first operation after initializing the
     * batch creator. It is to be noted that unless initialization is successful
     * no other operation should be called and it can be safely assumed that if
     * no exception is thrown from init operation than initialization is
     * successful.
     * @param testInstance For which to create batches.
     * @throws IllegalArgumentException If testInstance is null or any
     *                                  information of it is improper.
     */
    public void init(UnitTestInstance testInstance)
        throws IllegalArgumentException;

    /**
     * Operation for starting batch creations. It has to be invoked in order to
     * notify the creator that from now on batches will be received by the
     * observers. Init must be called before invoking this operation.
     * 
     * @throws java.lang.IllegalStateException If init is not called prior to it
     *                                          or it is already started
     */
    public void start()
        throws IllegalStateException;

    /**
     * Return the test case creation factory for this batch creator.
     * @return Case creator for this batch creator
     * @throws java.lang.IllegalStateException If batch creator isn't intialized
     */
    public TestCaseCreationFactory getCreationFactory()
        throws IllegalStateException;

    /**
     * Returns the delay provider for this batch creator.
     * @return The delay provider
     * @throws java.lang.IllegalStateException If batch creator isn't intialized
     */
    public DelayProvider getDelayProvider()
        throws IllegalStateException;

    /**
     * Returns the configured next step size provider for this batch creator
     * @return The step size provider
     * @throws java.lang.IllegalStateException If batch creator isn't intialized
     */
    public NextStepSizeProvider getNextStepSizeProvider()
        throws IllegalStateException;

    /**
     * Informs whether the next batch is available as of now. If true then its
     * safe to invoke getNextBatch or else it may throw an Illegal Argument
     * Exception
     * @return True if next batch is available else false.
     */
    public boolean isNextBatchAvailable();

    /**
     * Return the next batches of Thread and test cases to start executing. It
     * is to be noted that once batch is return it is ready to be started. Also
     * to be noted that it is safer to invoke isNextBatchAvailable before trying
     * to retrieve the next batch
     * @return The batch to be started
     * @throws java.lang.IllegalStateException If init is not called and/or
     *                                         batch is not available yet
     */
    public Map<Thread, TestCase> getNextBatch()
        throws IllegalStateException;

    /**
     * Add observer to the creator so that it can notify them when a batch is
     * avaiable. If observer is null then it will be ignored
     * @param batchListener The overserver to add
     */
    public void addBatchCreatorListener(TestCaseBatchListener batchListener);

    /**
     * Remove the observer if and only if it exists and if null is provided then
     * it will be ignored.
     * @param batchListener The observer to remove
     */
    public void removeBatchCreatrorListener(TestCaseBatchListener batchListener);
}
