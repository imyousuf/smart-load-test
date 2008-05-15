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
 *    his program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.smartitengineering.loadtest.engine;

/**
 *
 * @author imyousuf
 */
public interface TestCaseThreadPolicy {

    /**
     * Check the thread and the test case for deciding whether the thread should
     * be stopped or not.
     * 
     * @param testCaseThread Thread executing the test case
     * @param testCase The test case being executed by the thread
     * @return true if it should be stopped by the client or else false
     */
    public boolean shouldTestCaseBeStopped(Thread testCaseThread,
                                           TestCase testCase);
    /**
     * If the client wants to know after how long more to check the thread then
     * this method can be used to find out a sleep duration for the thread-stop
     * monitor.
     * @return Duration in milisecond
     */
    public long getNextCheckDuration();
}
