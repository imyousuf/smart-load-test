/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.loadtest.engine.events;

import com.smartitengineering.loadtest.engine.LoadTestEngine;

/**
 *
 * @author imyousuf
 */
public class LoadTestEngineStateChangedEvent
    extends AbstractStateChangeEvent<LoadTestEngine, LoadTestEngine.State, LoadTestEngine.State> {

    public LoadTestEngineStateChangedEvent(LoadTestEngine source,
                                           LoadTestEngine.State oldValue,
                                           LoadTestEngine.State newValue) {
        super(source, oldValue, newValue);
    }
}
