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
 *    his program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
