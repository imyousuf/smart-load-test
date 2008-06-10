/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.loadtest.engine.result;

/**
 * Represents the step configuration that is the delay at a step and number of
 * threads introduced following this thread.
 * 
 * @author imyousuf
 */
public class StepConfiguration
    implements Cloneable {

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
    public Object clone() {
        StepConfiguration configuration = new StepConfiguration();
        configuration.setStepSize(stepSize);
        configuration.setDelayInMillis(delayInMillis);
        return configuration;
    }
}
