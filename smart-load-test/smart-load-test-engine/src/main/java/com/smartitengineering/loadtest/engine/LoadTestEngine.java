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

import com.smartitengineering.loadtest.engine.events.LoadTestEngineStateChangeListener;
import com.smartitengineering.loadtest.engine.events.TestCaseTransitionListener;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public interface LoadTestEngine {

    /**
     * Initializes the test engine with the test instances. Once initialized its
     * ready to be started. Please note that to invoke start, init must be
     * invoked earlier.
     * 
     * @param testInstances A collection of instances that this engine will
     *                      execute
     * @param initProperties Properties to be used initialization
     */
    public void init(Set<UnitTestInstance> testInstances,
                     Properties initProperties);

    /**
     * Returns the current state of the engine.
     * 
     * @return Current state of the engine
     */
    public LoadTestEngine.State getState();

    /**
     * This method will set the status ot CREATED and ensure that the engine can
     * be reused for another test. It will ensure that the same engine can be
     * used to either repeast the test or perform new set of tests.
     */
    public void reinstanteCreatedState();

    /**
     * Starts the engine; that is it starts executing the test instances as they
     * are supposed to be configured. Once started the engine should be in
     * STARTED state and it can not be stopped. Depending on the thread policy
     * supplied to the engine, one can decide to terminate threads and suspend
     * the test instance.
     * 
     * @throws java.lang.IllegalStateException If not in INITIALIZED state
     */
    public void start()
        throws IllegalStateException;

    /**
     * Datetime stamp of commencing the tests.
     * 
     * @return Start time
     */
    public Date getStartTime();

    /**
     * Datetime stamp of the ending of the tests.
     * 
     * @return End time
     */
    public Date getEndTime();

    /**
     * Returns the duration of the test once it is finished.
     * 
     * @return Duration of the testing
     * @throws java.lang.IllegalStateException If the engine state is not
     *                                          FINISHED
     */
    public long getDuration()
        throws IllegalStateException;

    /**
     * Returns the result of the test executed by the test engine
     * 
     * @return The result representing the test executed by the engine
     * @throws java.lang.IllegalStateException If the engine state is not
     *                                          FINISHED
     */
    public TestResult getTestResult()
        throws IllegalStateException;

    /**
     * Sets the thread policy for this engine. The policy will decide whether
     * the thread should be killed or not. It will be used by the egnine's
     * thread monitor. Please not that if policy is null the previous policy
     * will not be overwritten.
     * 
     * @param policy Policy for the thread-killing, i.e. when to terminate
     */
    public void setTestCaseThreadPolicy(TestCaseThreadPolicy policy);

    /**
     * Returns the current thread policy for the engine.
     * 
     * @return Current policy, returns null if no policy is available
     */
    public TestCaseThreadPolicy getTestCaseThreadPolicy();

    /**
     * Unset the current thread policy and set null, i.e. no policy, instead
     */
    public void unsetTestCaseThreadPolicy();

    /**
     * Adds state change listener if listener is not null
     * 
     * @param listener Listener to add
     */
    public void addLoadTestEngineStateChangeListener(
        LoadTestEngineStateChangeListener listener);

    /**
     * Remmoves the state change listener iff it exists
     * 
     * @param listener Listener to remove
     */
    public void removeLoadTestEngineStateChangeListener(
        LoadTestEngineStateChangeListener listener);

    /**
     * Adds test cases' state transition listener, which will invoke on
     * particular states of the test cases'.
     * 
     * @param listener Listener to add
     */
    public void addTestCaseTransitionListener(
        TestCaseTransitionListener listener);

    /**
     * Removes the state transition listener iff it exists
     * 
     * @param listener Listener to remove
     */
    public void removeTestCaseTransitionListener(
        TestCaseTransitionListener listener);

    /**
     * Enum for representing the state of this load test engine
     */
    public enum State {

        /**
         * Representing the created state
         */
        CREATED(1),
        /**
         * Representing the initialized state after the init method is invoked
         */
        INITIALIZED(2),
        /**
         * Representing the started state once start is invoked
         */
        STARTED(3),
        /**
         * Representing the finished state once the engine is done
         */
        FINISHED(4);
        /**
         * At what step of the life-cycle is the current state located
         */
        private int stateStep;

        State(int stateStep) {
            this.stateStep = stateStep;
        }

        /**
         * Get the state's step level
         * @return The step level
         */
        public int getStateStep() {
            return stateStep;
        }
    }
}
