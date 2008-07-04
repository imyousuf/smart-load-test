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

import com.smartitengineering.loadtest.engine.events.ThreadStateChangeListener;
import com.smartitengineering.loadtest.engine.management.TestCaseThreadManager;
import com.smartitengineering.loadtest.engine.management.TestCaseThreadPolicy;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public abstract class AbstractTestCaseThreadManager
    implements TestCaseThreadManager {

    protected TestCaseThreadPolicy threadPolicy;
    protected Set<ThreadStateChangeListener> listeners;

    protected AbstractTestCaseThreadManager() {
        listeners = new HashSet<ThreadStateChangeListener>();
    }

    public void setThreadPolicy(TestCaseThreadPolicy threadPolicy) {
        this.threadPolicy = threadPolicy;
    }

    public void unsetTestCaseThreadPolicy() {
        this.threadPolicy = null;
    }

    public TestCaseThreadPolicy getThreadPolicy() {
        if (threadPolicy == null) {
            final TimeoutThreadPolicy timeoutThreadPolicy =
                new TimeoutThreadPolicy();
            timeoutThreadPolicy.setTimeoutPeriod(5 * 60 * 1000);
            timeoutThreadPolicy.setTestCaseStoppableStatusDisabled(false);
            this.threadPolicy = timeoutThreadPolicy;
            return timeoutThreadPolicy;
        }
        return this.threadPolicy;
    }

    public void addThreadStateChangeListener(
        ThreadStateChangeListener changeListener) {
        if (changeListener != null) {
            listeners.add(changeListener);
        }
    }

    public void removeThreadStateChangeListener(
        ThreadStateChangeListener changeListener) {
        if (changeListener != null) {
            listeners.remove(changeListener);
        }
    }
}
