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
package com.smartitengineering.loadtest.engine.persistence;

import com.smartitengineering.loadtest.engine.LoadTestEngine;
import com.smartitengineering.loadtest.engine.events.PersistenceEngineStateChangeListener;
import com.smartitengineering.loadtest.engine.events.ProgressListener;

/**
 *
 * @author imyousuf
 */
public interface PersistenceEngine {
    
    /**
     * Initialize the persistence engine with the load test it is to configure
     * 
     * @param loadTestEngine The load test to persist
     */
    public void init(LoadTestEngine loadTestEngine);

    /**
     * Get the load test engine this persistence engine will persist.
     * 
     * @return The load test to be persisted on request
     * @throws java.lang.UnsupportedOperationException If the state is only
     *                                                 CREATED
     */
    public LoadTestEngine getLoadTestEngine()
        throws UnsupportedOperationException;

    /**
     * Persist the load test result once its available.
     * 
     * @return true if persistence succeeded or else false
     * @throws java.lang.UnsupportedOperationException If state is not 
     *                                                  READY_TO_START
     */
    public boolean persistTestResult()
        throws UnsupportedOperationException;

    /**
     * Returns the current state of the persistence engine
     * 
     * @return current state
     */
    public PersistenceEngine.State getState();

    /**
     * Adds Listener to observe progress of persisting test result.
     * Test result could saving could be time consuming affair in that case
     * this observer pattern will allow its clients to monitor progress and take
     * appropriate steps.
     * 
     * @param progressListener Listener that will observe
     */
    public void addProgressListener(ProgressListener progressListener);

    /**
     * Removes the progress listener if it exists and any event occurring after
     * removed will no longer be notified.
     * 
     * @param progressListener Listener to be removed
     */
    public void removeProgressListener(ProgressListener progressListener);

    /**
     * Add observer to be notified once the state of the persistence engine
     * changes
     * 
     * @param listener Observer to be added
     */
    public void addLoadTestEngineStateChangeListener(
        PersistenceEngineStateChangeListener listener);
    
    /**
     * Remove the observer if exists. No events occurring after removed will be
     * notified to this observer
     * 
     * @param listener Observer to be removed
     */
    public void removeLoadTestEngineStateChangeListener(
        PersistenceEngineStateChangeListener listener);
    
    /**
     * Returns the persistent test result engine used to read persisted test
     * results for the persistent storage in use by this engine
     * 
     * @return Persistent storage for reading
     */
    public PersistentTestResultEngine getPersistentTestResultEngine();

    public enum State {

        CREATED(1),
        INITIALIZED(2),
        READY_TO_START(3),
        STARTED(4),
        FINISHED(5);
        private int stateStep;

        State(int stateStep) {
            this.stateStep = stateStep;
        }

        public int getStateStep() {
            return stateStep;
        }
    }
}
