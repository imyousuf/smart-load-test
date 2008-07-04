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
import com.smartitengineering.loadtest.engine.management.TestCaseThreadPolicy;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author imyousuf
 */
public class TimeoutThreadPolicy
    implements TestCaseThreadPolicy {

    protected Map<Thread, Integer> checkCountMap;
    private int timeoutPeriod;
    private boolean testCaseStoppableStatusDisabled;

    public TimeoutThreadPolicy() {
        checkCountMap = new WeakHashMap<Thread, Integer>();
    }

    public boolean shouldTestCaseBeStopped(Thread testCaseThread,
                                           TestCase testCase) {
        if (testCase.getState().equals(TestCase.State.STARTED)) {
            Date startDate = testCase.getStartTimeOfTest();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MILLISECOND, timeoutPeriod);
            if (calendar.getTime().after(new Date())) {
                return testCase.isStoppable() ||
                    isTestCaseStoppableStatusDisabled();
            }
        }
        Integer checkCount = checkCountMap.get(testCaseThread);
        if (checkCount == null) {
            checkCountMap.put(testCaseThread, 1);
        }
        else {
            checkCountMap.put(testCaseThread, (checkCount.intValue() + 1));
        }
        return false;
    }

    public int getNextCheckDuration(Thread testCaseThread,
                                    TestCase testCase) {
        if (checkCountMap.get(testCaseThread) != null) {
            return -1;
        }
        else {
            return timeoutPeriod;
        }
    }

    public int getTimeoutPeriod() {
        return timeoutPeriod;
    }

    public void setTimeoutPeriod(int timeoutPeriod) {
        this.timeoutPeriod = timeoutPeriod;
    }

    public boolean isTestCaseStoppableStatusDisabled() {
        return testCaseStoppableStatusDisabled;
    }

    public void setTestCaseStoppableStatusDisabled(
        boolean testCaseStoppableStatusDisabled) {
        this.testCaseStoppableStatusDisabled = testCaseStoppableStatusDisabled;
    }
}
