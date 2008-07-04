/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.loadtest.engine.impl;

/**
 *
 * @author imyousuf
 */
public class DummyTestCase extends AbstractTestCase {
    
    private int sleepTime = 10;
    
    public DummyTestCase() {
    }

    public DummyTestCase(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    protected void extendRun()
        throws Exception {
        Thread.sleep(sleepTime);
    }

    public <InitParam> void initTestCase(InitParam... params) {
        setState(State.INITIALIZED);
    }

}
