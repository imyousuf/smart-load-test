/**
 *    This module represents an engine IMPL for the load testing framework
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
package com.smartitengineering.loadtest.engine.impl.persistence;

import com.smartitengineering.loadtest.engine.LoadTestEngine;
import com.smartitengineering.loadtest.engine.events.PersistenceEngineStateChangeListener;
import com.smartitengineering.loadtest.engine.events.PersistenceEngineStateChangedEvent;
import com.smartitengineering.loadtest.engine.events.ProgressEvent;
import com.smartitengineering.loadtest.engine.events.ProgressListener;
import com.smartitengineering.loadtest.engine.persistence.PersistenceEngine;
import com.smartitengineering.loadtest.engine.persistence.PersistentTestResultEngine;
import java.util.HashSet;
import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public abstract class AbstractPersistenceEngine
    implements PersistenceEngine {

    private LoadTestEngine loadTestEngine;
    private PersistentTestResultEngine persistentTestResultEngine;
    private PersistenceEngine.State currentState;
    private HashSet<ProgressListener> progressListeners;
    private HashSet<PersistenceEngineStateChangeListener> persistenceEngineStateChangeListeners;

    protected AbstractPersistenceEngine() {
        currentState = PersistenceEngine.State.CREATED;
        progressListeners = new HashSet<ProgressListener>();
        persistenceEngineStateChangeListeners =
            new HashSet<PersistenceEngineStateChangeListener>();
    }

    public void init(LoadTestEngine loadTestEngine,
                     Properties initProperties)
        throws IllegalStateException {
        if (loadTestEngine == null || loadTestEngine.getState().getStateStep() <
            LoadTestEngine.State.INITIALIZED.getStateStep()) {
            throw new IllegalStateException(
                "Load Test Engine not initialized or is null!");
        }
        this.loadTestEngine = loadTestEngine;
        specializedInit(initProperties);
        setStatus(PersistenceEngine.State.INITIALIZED);
    }

    public LoadTestEngine getLoadTestEngine()
        throws UnsupportedOperationException {
        if (loadTestEngine == null && currentState.getStateStep() <
            PersistenceEngine.State.INITIALIZED.getStateStep()) {
            throw new UnsupportedOperationException(
                "Persistence Engine not initialized!");
        }
        return loadTestEngine;
    }

    public State getState() {
        return currentState;
    }

    public void addProgressListener(ProgressListener progressListener) {
        if (progressListener == null) {
            return;
        }
        progressListeners.add(progressListener);
    }

    public void removeProgressListener(ProgressListener progressListener) {
        if (progressListener == null) {
            return;
        }
        progressListeners.remove(progressListener);
    }

    public void addLoadTestEngineStateChangeListener(
        PersistenceEngineStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        persistenceEngineStateChangeListeners.add(listener);
    }

    public void removeLoadTestEngineStateChangeListener(
        PersistenceEngineStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        persistenceEngineStateChangeListeners.remove(listener);
    }

    public PersistentTestResultEngine getPersistentTestResultEngine() {
        if (persistentTestResultEngine == null && currentState.getStateStep() <
            PersistenceEngine.State.INITIALIZED.getStateStep()) {
            throw new UnsupportedOperationException(
                "Persistence Engine not initialized!");
        }
        return persistentTestResultEngine;
    }

    public boolean persistTestResult()
        throws UnsupportedOperationException,
               IllegalArgumentException,
               RuntimeException {
        if (!getState().equals(PersistenceEngine.State.INITIALIZED)) {
            throw new UnsupportedOperationException(
                "Persistence Engine is not ready to start!");
        }
        if (getLoadTestEngine().getState() != LoadTestEngine.State.FINISHED) {
            throw new UnsupportedOperationException(
                "Load Test Engine is not done yet!");
        }
        if (!getLoadTestEngine().getTestResult().isValid()) {
            throw new IllegalArgumentException("Test reuslt state is not valid!");
        }
        setStatus(PersistenceEngine.State.STARTED);
        boolean persistOperationResult = false;
        doPersist();
        setStatus(PersistenceEngine.State.FINISHED);
        persistOperationResult = true;
        return persistOperationResult;
    }

    public void setPersistentTestResultEngine(
        PersistentTestResultEngine persistentTestResultEngine) {
        this.persistentTestResultEngine = persistentTestResultEngine;
    }

    protected void setStatus(PersistenceEngine.State newState) {
        PersistenceEngine.State oldState = currentState;
        currentState = newState;
        fireStateChanged(oldState, newState);
    }

    protected void fireProgressEvent(Double oldValue,
                                     Double newValue) {
        final ProgressEvent progressEvent =
            new ProgressEvent(this, oldValue, newValue);

        for (ProgressListener progressListener : progressListeners) {
            progressListener.stateChanged(progressEvent);
        }
    }

    protected void fireStateChanged(PersistenceEngine.State oldState,
                                    PersistenceEngine.State newState) {
        final PersistenceEngineStateChangedEvent event =
            new PersistenceEngineStateChangedEvent(this, oldState, newState);
        for (PersistenceEngineStateChangeListener persistenceEngineStateChangeListener : persistenceEngineStateChangeListeners) {
            persistenceEngineStateChangeListener.stateChanged(event);
        }
    }

    protected abstract void specializedInit(Properties properties);

    protected abstract void doPersist()
        throws RuntimeException;
}
