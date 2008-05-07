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

    private Source source;
    
    public AbstractEvent(Source source) {
        this.source = source;
    }

    public Source getSource() {
        return source;
    }
    
}
