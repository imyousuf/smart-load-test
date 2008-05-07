/**
 *    This module represents an engine for the load testing framework
 * 
 *    Copyright (C) 2008  Imran M Yousuf (imran@smartitengineering.com)
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License along
 *    with this program; if not, write to the Free Software Foundation, Inc.,
 *    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.smartitengineering.loadtest.engine;

import com.smartitengineering.loadtest.engine.events.LoadTestEngineStateChangeListener;
import com.smartitengineering.loadtest.engine.events.TestCaseTransitionListener;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public interface LoadTestEngine {

    public void init(Set<UnitTestInstance> testInstances);

    public LoadTestEngine.State getState();
    
    public void start();
    
    public Date getStartTime();
    
    public Date getEndTime();
    
    public long getDuration();
    
    public TestResult getTestResult();
    
    public void setTestCaseThreadPolicy(TestCaseThreadPolicy policy);
    
    public TestCaseThreadPolicy getTestCaseThreadPolicy();

    public void addLoadTestEngineStateChangeListener(
        LoadTestEngineStateChangeListener listener);
    
    public void addTestCaseTransitionListener(TestCaseTransitionListener listener);
    
    public void removeTestCaseTransitionListener(TestCaseTransitionListener listener);

    public void removeLoadTestEngineStateChangeListener(
        LoadTestEngineStateChangeListener listener);

    public enum State {

        CREATED(1),
        INITIALIZED(2),
        STARTED(3),
        FINISHED(4);
        private int stateStep;

        State(int stateStep) {
            this.stateStep = stateStep;
        }

        public int getStateStep() {
            return stateStep;
        }
    }
}
