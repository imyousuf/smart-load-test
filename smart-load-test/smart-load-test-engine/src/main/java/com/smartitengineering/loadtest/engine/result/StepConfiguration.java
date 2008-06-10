/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
