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
package com.smartitengineering.loadtest.engine.impl;

import com.smartitengineering.loadtest.engine.events.TestCaseStateChangeListener;
import com.smartitengineering.loadtest.engine.events.TestCaseStateChangedEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author imyousuf
 */
public class AbstractTestCaseTest
    extends TestCase {

    public AbstractTestCaseTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp()
        throws Exception {
        super.setUp();
    }

    private com.smartitengineering.loadtest.engine.TestCase getTestCase(
        final int sleep) {
        return new AbstractTestCase() {

            @Override
            protected void extendRun()
                throws Exception {
                Thread.sleep(sleep);
            }

            public <InitParam> void initTestCase(InitParam... params) {
                setState(State.INITIALIZED);
            }
        };
    }

    public void testNotInitializedStart() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            2);
        try {
            testCase.run();
            fail("should not run it properly!");
        }
        catch (IllegalStateException exception) {
            //Should receive this
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }

    public void testStart() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            1);
        testCase.initTestCase((Object) null);
        try {
            testCase.run();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }

    public void testNotStartedGetStartTime() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            2);
        try {
            testCase.getStartTimeOfTest();
            fail("should not get start time properly!");
        }
        catch (IllegalStateException exception) {
            //Should receive this
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
        testCase.initTestCase((Object) null);
        try {
            testCase.getStartTimeOfTest();
            fail("should not get start time properly!");
        }
        catch (IllegalStateException exception) {
            //Should receive this
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    public void testGetStartTime() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            2);
        testCase.initTestCase((Object) null);
        Thread thread = new Thread(testCase);
        thread.start();
        try {
            while (testCase.getState().getStateStep() <
                com.smartitengineering.loadtest.engine.TestCase.State.STARTED.
                getStateStep()) {
                continue;
            }
            testCase.getStartTimeOfTest();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }

    public void testNotProperGetEndTime() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            1);
        try {
            testCase.getEndTimeOfTest();
            fail("should not get end time properly!");
        }
        catch (IllegalStateException exception) {
            //Should receive this
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
        testCase.initTestCase((Object) null);
        try {
            testCase.getEndTimeOfTest();
            fail("should not get end time properly!");
        }
        catch (IllegalStateException exception) {
            //Should receive this
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
        Thread thread = new Thread(testCase);
        thread.start();
        try {
            testCase.getEndTimeOfTest();
            fail("should not get end time properly!");
        }
        catch (IllegalStateException exception) {
            //Should receive this
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    public void testGetStopTime() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            2);
        testCase.initTestCase((Object) null);
        Thread thread = new Thread(testCase);
        thread.start();
        try {
            thread.join();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            testCase.getEndTimeOfTest();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }

    public void testImproperGetDuration() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            1);
        try {
            testCase.getTestDuration();
            fail("should not get duration properly!");
        }
        catch (IllegalStateException exception) {
            //Should receive this
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
        testCase.initTestCase((Object) null);
        try {
            testCase.getTestDuration();
            fail("should not get duration properly!");
        }
        catch (IllegalStateException exception) {
            //Should receive this
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
        Thread thread = new Thread(testCase);
        thread.start();
        try {
            testCase.getTestDuration();
            fail("should not get duration properly!");
        }
        catch (IllegalStateException exception) {
            //Should receive this
        }
        catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    public void testGetTestDuration() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            2);
        testCase.initTestCase((Object) null);
        Thread thread = new Thread(testCase);
        thread.start();
        try {
            thread.join();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            assertEquals(testCase.getEndTimeOfTest().getTime() - testCase.
                getStartTimeOfTest().getTime(), testCase.getTestDuration());
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }

    public void testGetStatus() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            2);
        assertEquals(
            com.smartitengineering.loadtest.engine.TestCase.State.CREATED,
            testCase.getState());
        testCase.initTestCase((Object) null);
        assertEquals(
            com.smartitengineering.loadtest.engine.TestCase.State.INITIALIZED,
            testCase.getState());
        Thread thread = new Thread(testCase);
        thread.start();
        while (testCase.getState().getStateStep() <
            com.smartitengineering.loadtest.engine.TestCase.State.STARTED.
            getStateStep()) {
            continue;
        }
        assertEquals(
            com.smartitengineering.loadtest.engine.TestCase.State.STARTED,
            testCase.getState());
        try {
            thread.join();
            assertEquals(
                com.smartitengineering.loadtest.engine.TestCase.State.FINISHED,
                testCase.getState());
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        testCase = getTestCase(2);
        assertEquals(
            com.smartitengineering.loadtest.engine.TestCase.State.CREATED,
            testCase.getState());
        testCase.initTestCase((Object) null);
        assertEquals(
            com.smartitengineering.loadtest.engine.TestCase.State.INITIALIZED,
            testCase.getState());
        thread = new Thread(testCase);
        thread.start();
        while (testCase.getState().getStateStep() <
            com.smartitengineering.loadtest.engine.TestCase.State.STARTED.
            getStateStep()) {
            continue;
        }
        assertEquals(
            com.smartitengineering.loadtest.engine.TestCase.State.STARTED,
            testCase.getState());
        try {
            thread.interrupt();
            while (testCase.getState().getStateStep() <
                com.smartitengineering.loadtest.engine.TestCase.State.FINISHED.
                getStateStep()) {
                continue;
            }
            assertEquals(
                com.smartitengineering.loadtest.engine.TestCase.State.STOPPED,
                testCase.getState());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            fail(ex.getMessage());
        }
    }

    public void testStopRelatedFlags() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            1);
        assertEquals(true, testCase.isInterruptable());
        assertEquals(false, testCase.isStoppable());
    }

    public void testStopTest() {
        com.smartitengineering.loadtest.engine.TestCase testCase = getTestCase(
            1);
        try {
            testCase.stopTest();
            fail("Should not run successfully!");
        }
        catch (UnsupportedOperationException exception) {
        }
        catch (Exception exception) {
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    private ArrayList<TestCaseStateChangedEvent> events;

    public void testListeners() {
        com.smartitengineering.loadtest.engine.TestCase testCase =
            getTestCase(1);
        testCase.addTestCaseStateChangeListener(new TestCaseStateChangeListener() {

            public void stateChanged(TestCaseStateChangedEvent stateChangeEvent) {
                addEvent(stateChangeEvent);
            }
        });
        testCase.initTestCase((Object) null);
        testCase.addTestCaseStateChangeListener(null);
        Thread thread = new Thread(testCase);
        thread.start();
        try {
            thread.join();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        checkEvents(testCase,
            com.smartitengineering.loadtest.engine.TestCase.State.FINISHED);
        testCase = getTestCase(1);
        testCase.addTestCaseStateChangeListener(new TestCaseStateChangeListener() {

            public void stateChanged(TestCaseStateChangedEvent stateChangeEvent) {
                addEvent(stateChangeEvent);
            }
        });
        testCase.initTestCase((Object) null);
        thread = new Thread(testCase);
        thread.start();
        try {
            thread.interrupt();
            thread.join();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        checkEvents(testCase,
            com.smartitengineering.loadtest.engine.TestCase.State.STOPPED);
        testCase =
            getTestCase(1);
        TestCaseStateChangeListener listener = new TestCaseStateChangeListener() {

            public void stateChanged(TestCaseStateChangedEvent stateChangeEvent) {
                addEvent(stateChangeEvent);
            }
        };
        testCase.addTestCaseStateChangeListener(listener);
        testCase.initTestCase((Object) null);
        testCase.removeTestCaseStateChangeListener(listener);
        testCase.removeTestCaseStateChangeListener(null);
        thread = new Thread(testCase);
        thread.start();
        try {
            thread.join();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if (events == null) {
            fail("Listener not added!");
        }
        if (events.size() != 1) {
            fail("Not enough events!");
        }
        int i = 0;
        for (TestCaseStateChangedEvent event : events) {
            assertEquals(testCase, event.getSource());
            if (i > 0) {
                fail("Not enough events!");
            }
            else if (i == 0) {
                assertEquals(
                    com.smartitengineering.loadtest.engine.TestCase.State.CREATED,
                    event.getOldValue());
                assertEquals(
                    com.smartitengineering.loadtest.engine.TestCase.State.INITIALIZED,
                    event.getNewValue());
            }
            i++;
        }
    }

    private void addEvent(TestCaseStateChangedEvent event) {
        if (events == null) {
            events = new ArrayList<TestCaseStateChangedEvent>();
        }
        if (event != null) {
            events.add(event);
        }
    }

    protected void checkEvents(
        com.smartitengineering.loadtest.engine.TestCase testCase,
        com.smartitengineering.loadtest.engine.TestCase.State lastState) {
        if (events == null) {
            fail("Listener not added!");
        }
        if (events.size() < 3) {
            fail("Not enough events!");
        }
        int i = 0;
        for (TestCaseStateChangedEvent event : events) {
            assertEquals(testCase, event.getSource());
            if (i == 0) {
                assertEquals(
                    com.smartitengineering.loadtest.engine.TestCase.State.CREATED,
                    event.getOldValue());
                assertEquals(
                    com.smartitengineering.loadtest.engine.TestCase.State.INITIALIZED,
                    event.getNewValue());
            }
            else if (i == 1) {
                assertEquals(
                    com.smartitengineering.loadtest.engine.TestCase.State.INITIALIZED,
                    event.getOldValue());
                assertEquals(
                    com.smartitengineering.loadtest.engine.TestCase.State.STARTED,
                    event.getNewValue());
            }
            else if (i == 2) {
                assertEquals(
                    com.smartitengineering.loadtest.engine.TestCase.State.STARTED,
                    event.getOldValue());
                assertEquals(
                    lastState, event.getNewValue());
            }
            i++;
        }
        events = null;
    }
}
