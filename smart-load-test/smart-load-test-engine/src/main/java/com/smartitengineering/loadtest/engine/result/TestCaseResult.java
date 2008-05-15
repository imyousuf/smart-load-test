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
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public class TestCaseResult
    extends PersistentDTO<TestCaseResult> {

    
    private String name;
    private String instanceFactoryClassName;
    private String stepDelayConfiguration;
    private int stepSize;
    private int stepCount;
    private Set<TestProperty> testProperties;
    private Set<TestCaseInstanceResult> testCaseInstanceResults;
    private Set<KeyedInformation> otherInfomations;

    public boolean isValid(TestCaseResult object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getInstanceFactoryClassName() {
        return instanceFactoryClassName;
    }

    public void setInstanceFactoryClassName(String instanceFactoryClassName) {
        this.instanceFactoryClassName = instanceFactoryClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TestProperty> getProperties() {
        return testProperties;
    }

    public void setProperties(Set<TestProperty> properties) {
        this.testProperties = properties;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public String getStepDelayConfiguration() {
        return stepDelayConfiguration;
    }

    public void setStepDelayConfiguration(String stepDelayConfiguration) {
        this.stepDelayConfiguration = stepDelayConfiguration;
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public Set<TestCaseInstanceResult> getTestCaseInstanceResults() {
        return testCaseInstanceResults;
    }

    public void setTestCaseInstanceResults(
        Set<TestCaseInstanceResult> testCaseInstanceResults) {
        this.testCaseInstanceResults = testCaseInstanceResults;
    }

    public Set<TestProperty> getTestProperties() {
        return testProperties;
    }

    public void setTestProperties(Set<TestProperty> testProperties) {
        this.testProperties = testProperties;
    }

    public Set<KeyedInformation> getOtherInfomations() {
        return otherInfomations;
    }

    public void setOtherInfomations(Set<KeyedInformation> otherInfomations) {
        this.otherInfomations = otherInfomations;
    }
}
