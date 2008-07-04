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

import com.smartitengineering.loadtest.engine.TestCase;
import com.smartitengineering.loadtest.engine.events.ThreadStateChangeListener;

/**
 * Manager API for managing the threads. Basically load test engine creates the
 * threads and then hands them over to the manager and it will decide how and
 * when to terminate them and then notify all interested parties about its
 * action.
 * @author imyousuf
 */
public interface TestCaseThreadManager {
    
    /**
     * Once a thread is created its management is handed over to the manager so
     * that it can monitor the thread and once it finishes or is terminated it
     * can notify all interesting parties that 'thread is done'.
     * 
     * @param thread The thread that is created from the testCase
     * @param testCase The testCase that the thread will be running
     */
    public void manageThread(Thread thread, TestCase testCase);

    /**
     * Manager will use some sort of policy to determine whether to terminate
     * the thread or wait longer and also how long to wait, setting this policy
     * makes the thread manager aware of what to use.
     * 
     * @param threadPolicy The thread policy for the manager to use
     */
    public void setThreadPolicy(TestCaseThreadPolicy threadPolicy);

    /**
     * Unset the current thread policy and set null, i.e. no policy, instead
     */
    public void unsetTestCaseThreadPolicy();

    /**
     * Return the current thread policy for this manager, which is used to
     * determine whether to continue a thread or terminate it.
     * 
     * @return The current thread policy.
     */
    public TestCaseThreadPolicy getThreadPolicy();

    /**
     * Add listener to be notified that a thread has been stopped/terminated. It
     * is to be noted that listeners will be notified if and only if the manager
     * terminates the thread else it wont. So in order to know whether a test
     * case is finished/stopped or not TestCases's listener should be used.<p/>
     * If the listener to be added as an observer is null then it will be
     * ignored.
     * 
     * @param changeListener Listener waiting for thread to be stopped by manager
     */
    public void addThreadStateChangeListener(ThreadStateChangeListener changeListener);

    /**
     * Remove listener from observers list if it exists. If listener to remove
     * is NULL in that case it is ignored.
     * 
     * @param changeListener The listener to remove
     */
    public void removeThreadStateChangeListener(ThreadStateChangeListener changeListener);
}
