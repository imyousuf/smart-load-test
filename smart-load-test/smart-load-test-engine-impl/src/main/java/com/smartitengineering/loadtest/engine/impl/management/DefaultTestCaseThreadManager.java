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
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author imyousuf
 */
public class DefaultTestCaseThreadManager
    extends AbstractTestCaseThreadManager {

    private Map<Long, Map.Entry<Thread, TestCase>> threads;
    private int pollInterval;
    protected Timer timer;
    protected ThreadMonitor threadMonitor;

    public DefaultTestCaseThreadManager() {
        super();
        threads = new HashMap<Long, Map.Entry<Thread, TestCase>>();
        pollInterval = 60 * 1000;
    }

    public void startManager() {
        timer = new Timer();
        threadMonitor = new ThreadMonitor();
        timer.schedule(threadMonitor, getPollInterval(), getPollInterval());
    }

    public void manageThread(Thread thread,
                             TestCase testCase) {
        AbstractMap.SimpleEntry<Thread, TestCase> entry =
            new AbstractMap.SimpleEntry<Thread, TestCase>(thread, testCase);
        threads.put(System.currentTimeMillis(), entry);
    }

    public int getPollInterval() {
        return pollInterval;
    }

    public void setPollInterval(int pollInterval) {
        this.pollInterval = pollInterval;
    }

    protected class ThreadMonitor
        extends TimerTask {

        @Override
        public void run() {
            Set<Long> entryTimes = threads.keySet();
            final ArrayList<Long> entryTimeList =
                new ArrayList<Long>(entryTimes);
            Collections.sort(entryTimeList);
            final long currentTime = System.currentTimeMillis();
            int index = Collections.<Long>binarySearch(entryTimeList,
                currentTime);
            if (index < 0) {
                //Check the javadoc for reasoning for this line
                index = (index + 1) * -1;
            }
            List<Long> readyToCheckThreads = entryTimeList.subList(0, index + 1);
            for (Long timeStamp : readyToCheckThreads) {
                if (timeStamp.longValue() <= currentTime) {
                    Map.Entry<Thread, TestCase> entry = threads.get(timeStamp);
                    final boolean shouldTestCaseBeStopped =
                        getThreadPolicy().shouldTestCaseBeStopped(entry.getKey(),
                        entry.getValue());
                    boolean stopped = false;
                    Thread.State threadState = entry.getKey().getState();
                    if (shouldTestCaseBeStopped) {
                        if (entry.getValue().isStoppable()) {
                            entry.getValue().stopTest();
                            stopped = true;
                        }
                        else if (entry.getValue().isInterruptable()) {
                            try {
                                entry.getKey().interrupt();
                                stopped = true;
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    threads.remove(timeStamp);
                    final int nextCheckDuration =
                        getThreadPolicy().getNextCheckDuration(entry.getKey(),
                        entry.getValue());
                    if (nextCheckDuration > 0) {
                        threads.put(currentTime + nextCheckDuration, entry);
                    }
                    if ((!shouldTestCaseBeStopped || !stopped) &&
                        nextCheckDuration <= 0) {
                        handleMonitorDeadlockCondition(entry.getKey(), entry.
                            getValue());
                    }
                    else if (stopped) {
                        fireThreadStoppedEvent(entry.getKey(), threadState,
                            entry.getKey().getState());
                    }
                }
            }
        }
    }

    /**
     * When there is no valid policy of monitoring a thread further and thread
     * is not either interruptable or stoppable this operation is called. It
     * should define how to handle such threads in such drastic times.<p/>
     * This implementation will first try to interrupt the running thread and if
     * that does not succeed then it will try to stop it despite stop being
     * deprecated.
     * @param thread The thread that is no longer monitorable
     * @param testCase The test case being executed
     */
    protected void handleMonitorDeadlockCondition(final Thread thread,
                                                  final TestCase testCase) {
        try {
            Thread.State threadState = thread.getState();
            thread.interrupt();
            if (!(testCase.getState().equals(TestCase.State.STOPPED) 
                || testCase.getState().equals(TestCase.State.FINISHED))) {
                thread.stop();
            }
            fireThreadStoppedEvent(thread, threadState, thread.getState());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
