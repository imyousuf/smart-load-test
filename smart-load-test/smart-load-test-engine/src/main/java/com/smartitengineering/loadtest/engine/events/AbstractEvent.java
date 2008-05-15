/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.loadtest.engine.events;

/**
 *
 * @author imyousuf
 */
public class AbstractEvent<Source> {

    /**
     * Source of the event
     */
    private Source source;
    
    /**
     * Construct the event with the given source
     * @param source Source of the event
     */
    public AbstractEvent(Source source) {
        this.source = source;
    }

    /**
     * Returns the source of the event provided during construction
     * @return Source of the event
     */
    public Source getSource() {
        return source;
    }
    
}
