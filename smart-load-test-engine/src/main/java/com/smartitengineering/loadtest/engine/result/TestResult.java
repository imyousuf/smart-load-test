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
package com.smartitengineering.loadtest.engine.result;

import com.smartitengineering.domain.AbstractPersistentDTO;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author imyousuf
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TestResult extends AbstractPersistentDTO<TestResult>{

    private String testName;
    private Date startDateTime;
    private Date endDateTime;
    private Set<TestCaseResult> testCaseRunResults;
    private Set<KeyedInformation> otherInfomations;
    private long longId;

    public boolean isValid() {
        if (startDateTime == null || endDateTime == null || testName == null) {
            return false;
        }
        if (testCaseRunResults == null || testCaseRunResults.size() < 1) {
            return false;
        }
        for (TestCaseResult testCaseResult : testCaseRunResults) {
            if (!testCaseResult.isValid()) {
                return false;
            }
        }
        if (otherInfomations != null) {
            for (KeyedInformation keyedInformation : otherInfomations) {
                if (!keyedInformation.isValid()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Object clone() {
        TestResult testResult = new TestResult();
        super.clone(testResult);
        testResult.startDateTime =
            startDateTime != null ? new Date(startDateTime.getTime()) : null;
        testResult.endDateTime =
            endDateTime != null ? new Date(endDateTime.getTime()) : null;
        if (testCaseRunResults != null) {
            testResult.testCaseRunResults = new HashSet<TestCaseResult>();
            for (TestCaseResult caseResult : testCaseRunResults) {
                testResult.testCaseRunResults.add((TestCaseResult) caseResult.
                    clone());
            }
        }
        if (otherInfomations != null) {
            testResult.otherInfomations = new HashSet<KeyedInformation>();
            for (KeyedInformation keyInfo : otherInfomations) {
                testResult.otherInfomations.add(
                    (KeyedInformation) keyInfo.clone());
            }
        }
        testResult.setTestName(testName);
        return testResult;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof TestResult) {
            TestResult resultObj = (TestResult) obj;
            if (testName != null && resultObj.testName != null && testName.
                equals(resultObj.testName) && startDateTime != null &&
                resultObj.startDateTime != null && startDateTime.equals(
                resultObj.startDateTime) && endDateTime != null &&
                resultObj.endDateTime != null && endDateTime.equals(
                resultObj.endDateTime)) {
                return true;
            }
        }
        return false;
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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public long getLongId() {
        return longId;
    }

    public void setLongId(long longId) {
        this.longId = longId;
    }
}
