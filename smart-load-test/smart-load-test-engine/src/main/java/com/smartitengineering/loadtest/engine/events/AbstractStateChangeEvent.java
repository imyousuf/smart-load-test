/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.loadtest.engine.events;

/**
 *
 * @author imyousuf
 */
public class AbstractStateChangeEvent<Source, OldValue, NewValue> extends AbstractEvent<Source>{

    private OldValue oldValue;
    private NewValue newValue;

    protected AbstractStateChangeEvent(Source source,
                            OldValue oldValue,
                            NewValue newValue) {
        super(source);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public NewValue getNewValue() {
        return newValue;
    }

    public OldValue getOldValue() {
        return oldValue;
    }

}
