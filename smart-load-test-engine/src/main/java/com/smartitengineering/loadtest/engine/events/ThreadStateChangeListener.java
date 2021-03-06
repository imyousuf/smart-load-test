/**
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
 * Listener to be used to communicate between observee and the observed.
 * @author imyousuf
 */
public interface ThreadStateChangeListener {
    
    /**
     * It method should be invoked for the observee so that they can appropriate
     * action when a thread state changes.
     * 
     * @param changeEvent Event representing the thread state change
     */
    public void stateChanged(ThreadStateChangeEvent changeEvent);
}
