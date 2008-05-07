/**
 *    This module represents an engine for the load testing framework
 * 
 *    Copyright (C) 2008  Imran M Yousuf (imran@smartitengineering.com)
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License along
 *    with this program; if not, write to the Free Software Foundation, Inc.,
 *    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.smartitengineering.loadtest.engine.persistence;

import com.smartitengineering.loadtest.engine.LoadTestEngine;
import com.smartitengineering.loadtest.engine.events.ProgressListener;

/**
 *
 * @author imyousuf
 */
public interface PersistenceEngine {

    public void init(LoadTestEngine loadTestEngine);

    public boolean persistTestResult();

    public PersistenceEngine.State getState();
    
    public void addProgressListener(ProgressListener progressListener);
    
    public void removeProgressListener(ProgressListener listener);

    public enum State {

        CREATED(1),
        INITIALIZED(2),
        STARTED(3),
        FINISHED(4);
        private int stateStep;

        State(int stateStep) {
            this.stateStep = stateStep;
        }

        public int getStateStep() {
            return stateStep;
        }
    }
}
