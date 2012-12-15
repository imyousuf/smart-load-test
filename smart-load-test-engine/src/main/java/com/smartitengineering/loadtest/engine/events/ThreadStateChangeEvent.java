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
 * Event to listen to threads state change. Basically it will be used by
 * ThreadStateChangeListener
 * @author imyousuf
 */
public class ThreadStateChangeEvent
    extends AbstractStateChangeEvent<Thread, Thread.State, Thread.State> {

    /**
     * Create a new Thread sate change event with source, old and current state
     * @param source The thread thats state has changed
     * @param oldState Old state of the thread
     * @param newState New/current state of the thread
     */
    public ThreadStateChangeEvent(Thread source,
                                  Thread.State oldState,
                                  Thread.State newState) {
        super(source, oldState, newState);
    }
}
