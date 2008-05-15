/**
 * 
 *    This module represents an engine for the load testing framework
 *    Copyright (C) 2008  Imran M Yousuf (imran@smartitengineering.com)
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.smartitengineering.loadtest.engine.events;

/**
 *
 * @author imyousuf
 */
public class AbstractStateChangeEvent<Source, OldValue, NewValue> extends AbstractEvent<Source>{

    /**
     * Old value of the source's state that changed. It need not only be the
     * state but anything for that matter
     */
    private OldValue oldValue;
    /**
     * New value of the source that it has changed to
     */
    private NewValue newValue;

    /**
     * Constructor to construct the event with source, its old value and new
     * value
     * @param source Source of the event
     * @param oldValue Old value that prevailed before change
     * @param newValue New value after the change of state of the source 
     */
    protected AbstractStateChangeEvent(Source source,
                            OldValue oldValue,
                            NewValue newValue) {
        super(source);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * The new value of the source state provided during construction
     * @return The new value representing the state of the source
     */
    public NewValue getNewValue() {
        return newValue;
    }

    /**
     * The old of the source that was provided during the construction of the
     * event
     * @return The old value of the source representing the old source
     */
    public OldValue getOldValue() {
        return oldValue;
    }

}
