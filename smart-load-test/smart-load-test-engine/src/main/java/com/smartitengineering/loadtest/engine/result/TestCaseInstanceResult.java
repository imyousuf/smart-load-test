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
package com.smartitengineering.loadtest.engine.result;

import com.smartitengineering.domain.PersistentDTO;
import com.smartitengineering.loadtest.engine.TestCase;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public class TestCaseInstanceResult
    extends PersistentDTO<TestCaseInstanceResult> {

    private TestCaseResult testCaseResult;
    private Date startTime;
    private Date endTime;
    private int instanceNumber;
    private String endState;
    private Set<KeyedInformation> otherInfomations;

    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected String getEndState() {
        return endState;
    }

    protected void setEndState(String endState) {
        this.endState = endState;
    }

    public TestCase.State getEndTestCaseState() {
        return TestCase.State.valueOf(getEndState());
    }

    public void setEndTestCaseState(TestCase.State testCaseState) {
        if (testCaseState != null) {
            setEndState(testCaseState.name());
        }
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(int instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public TestCaseResult getTestCaseResult() {
        return testCaseResult;
    }

    public void setTestCaseResult(TestCaseResult testCaseResult) {
        this.testCaseResult = testCaseResult;
    }

    public Set<KeyedInformation> getOtherInfomations() {
        return otherInfomations;
    }

    public void setOtherInfomations(Set<KeyedInformation> otherInfomations) {
        this.otherInfomations = otherInfomations;
    }
}
