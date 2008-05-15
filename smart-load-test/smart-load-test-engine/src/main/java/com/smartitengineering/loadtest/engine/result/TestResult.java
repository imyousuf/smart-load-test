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
import java.util.Date;
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public class TestResult
    extends PersistentDTO<TestResult> {

    private Date startDateTime;
    private Date endDateTime;
    private Set<TestCaseResult> testCaseRunResults;
    private Set<KeyedInformation> otherInfomations;

    public boolean isValid(TestResult object) {
        //Not implemented yet
        return true;
    }

    @Override
    public Object clone() {
        throw new UnsupportedOperationException("Clone not implemented yet!");
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Set<KeyedInformation> getOtherInfomations() {
        return otherInfomations;
    }

    public void setOtherInfomations(Set<KeyedInformation> otherInfomations) {
        this.otherInfomations = otherInfomations;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Set<TestCaseResult> getTestCaseRunResults() {
        return testCaseRunResults;
    }

    public void setTestCaseRunResults(Set<TestCaseResult> testCaseRunResults) {
        this.testCaseRunResults = testCaseRunResults;
    }
}
