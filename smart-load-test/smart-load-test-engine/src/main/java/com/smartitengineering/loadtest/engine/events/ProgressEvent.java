/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.loadtest.engine.events;

import com.smartitengineering.loadtest.engine.persistence.PersistenceEngine;

/**
 *
 * @author imyousuf
 */
public class ProgressEvent
    extends AbstractStateChangeEvent<PersistenceEngine, Double, Double> {

    /**
     * Constructor of the event representing load test engine state change
     * @param source Load test engine of which state has changed
     * @param oldValue Old Old progress value
     * @param newValue New Old progress value
     */
    public ProgressEvent(PersistenceEngine source,
                         Double oldValue,
                         Double newValue) {
        super(source, oldValue, newValue);
    }
}
