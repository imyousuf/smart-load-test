/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.loadtest.engine.events;

/**
 *
 * @author imyousuf
 */
public interface AbstractStateChangeListener<StateChangedEvent extends AbstractStateChangeEvent> {
    public void stateChanged(StateChangedEvent stateChangeEvent);
}
