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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Represents the step configuration that is the delay at a step and number of
 * threads introduced following this thread.
 * 
 * @author imyousuf
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StepConfiguration
    implements Cloneable {

    private Integer stepNumber;
    private Integer delayInMillis;
    private Integer stepSize;

    public Integer getDelayInMillis() {
        return delayInMillis;
    }

    public void setDelayInMillis(Integer delayInMillis) {
        this.delayInMillis = delayInMillis;
    }

    public Integer getStepSize() {
        return stepSize;
    }

    public void setStepSize(Integer stepSize) {
        this.stepSize = stepSize;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof StepConfiguration) {
            return ((StepConfiguration)obj).stepNumber.equals(stepNumber);
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return stepNumber;
    }
    
    @Override
    public Object clone() {
        StepConfiguration configuration = new StepConfiguration();
        configuration.setStepSize(stepSize);
        configuration.setDelayInMillis(delayInMillis);
        configuration.setStepNumber(stepNumber);
        return configuration;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }
}
