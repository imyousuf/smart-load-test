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

    public ProgressEvent(PersistenceEngine source,
                         Double oldValue,
                         Double newValue) {
        super(source, oldValue, newValue);
    }
}
