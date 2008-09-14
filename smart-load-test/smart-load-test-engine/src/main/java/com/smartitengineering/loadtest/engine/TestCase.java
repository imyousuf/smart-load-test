/**
 * 
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
package com.smartitengineering.loadtest.engine;

import com.smartitengineering.loadtest.engine.events.TestCaseStateChangeListener;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author imyousuf
 */
public interface TestCase
    extends Runnable {

    /**
     * This returns the date and time the test was started in a thread
     * 
     * @return Time of starting test
     * @throws java.lang.IllegalStateException If state is not one of - STARTED,
     *                                          FINISHED or STOPPED
     */
    public Date getStartTimeOfTest()
        throws IllegalStateException;

    /**
     * This returns the date and time the test was stopped or finished.
     * 
     * @return Time of test ending
     * @throws java.lang.IllegalStateException If state is not one of - STOPPED
     *                                          or FINISHED
     */
    public Date getEndTimeOfTest()
        throws IllegalStateException;

    /**
     * Returns the duration of the test in miliseconds.
     * @return duration of the test
     * @throws java.lang.IllegalStateException If state is not one of - STOPPED
     *                                          or FINISHED
     */
    public long getTestDuration()
        throws IllegalStateException;

    /**
     * Throws exception if the thread is not stoppable. Stoppable will be 
     * determined using the isStoppable operation
     * 
     * @throws java.lang.UnsupportedOperationException If not stoppable
     */
    public void stopTest()
        throws UnsupportedOperationException;

    /**
     * Returns whether the test case is stoppable or not
     * @return true if the test case is stoppable
     */
    public boolean isStoppable();
    
    /**
     * Returns whether the method supports Thread.interrupt() or not. If
     * isStoppable false then one might decide on this result to determine
     * whether interrupt the thread or not.
     * 
     * @return True if the test case is ready for interruption else false;
     */
    public boolean isInterruptable();

    /**
     * Returns the current state of the test case
     * @return the current state
     */
    public TestCase.State getState();

    /**
     * This operation is invoked just before initializing/starting the thread.
     * It should be used to initialize the costly resources just before the
     * thread should start.
     * @param <InitParam> The type of param.
     * @param params Parameters used for initializing
     */
    public <InitParam extends Object> void initTestCase(InitParam... params);
    
    /**
     * If the test case wants to provide any extra info for the test case it can
     * provide it through this operations. Any operation responsible to creating
     * TestCaseResult HAS to to invoke this method once at least.
     * @return Extra result properties to be used by result processors. Might
     *          return null if nothing to return
     * @throws java.lang.IllegalStateException If not stopped or finished.
     */
    public Map<String, String> getTestCaseResultExtraInfo();

    public void addTestCaseStateChangeListener(
        TestCaseStateChangeListener changeListener);

    public void removeTestCaseStateChangeListener(
        TestCaseStateChangeListener changeListener);

    /**
     * Represents the state a test can attain
     */
    public enum State {

        /**
         * When the test case is initialized
         */
        CREATED(1),
        /**
         * After the initTestCase method invoked
         */
        INITIALIZED(2),
        /**
         * Once the this test case is started
         */
        STARTED(3),
        /**
         * If the test case is stopped
         */
        STOPPED(4),
        /**
         * If the test case finishes normally
         */
        FINISHED(4);

        /**
         * The step at which this state will be attained by the engine
         */
        private int stateStep;

        State(int stateStep) {
            this.stateStep = stateStep;
        }

        /**
         * Return the step of the state, that is which step in the lifecycle
         * this step is attained.
         * 
         * @return The step of this state
         */
        public int getStateStep() {
            return stateStep;
        }
    }
}
