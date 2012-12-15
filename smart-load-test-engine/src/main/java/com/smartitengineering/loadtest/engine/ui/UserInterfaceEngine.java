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
package com.smartitengineering.loadtest.engine.ui;

import com.smartitengineering.loadtest.engine.LoadTestEngine;
import com.smartitengineering.loadtest.engine.persistence.PersistenceEngine;
import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public interface UserInterfaceEngine {

    /**
     * Initialize the UI engine with the persistence engine which HAS to be in
     * initialized state or else the UI engine can not initialize. It will use
     * the load test engine of the persistence engine to execute the test suite.
     * 
     * @param persistenceEngine The engine which will provide the load test
     *                          engine and persist the results of the test. UI
     *                          engine will used the this engine's load test
     *                          engine to run and use the test and its results.
     * @param initProperties Properties to be used initialization
     * @throws IllegalStateException If persistenceEngine is not in INITIALIZED
     *                               state
     */
    public void init(PersistenceEngine persistenceEngine,
                     Properties initProperties)
        throws IllegalStateException;

    /**
     * Initialize the UI Engine with load test engine only and this signifies
     * that there is no persistence engine available for the user to save the
     * results to.
     * 
     * @param loadTestEngine Engine that will be directed and used by the UI
     * @param initProperties Properties to be used initialization
     * @throws IllegalStateException If loadTestEngine is not in INITIALIZED
     *                               state
     */
    public void init(LoadTestEngine loadTestEngine,
                     Properties initProperties)
        throws IllegalStateException;

    /**
     * Starts the UI Engine and it is expected that the UI Engine will guide the
     * whole load testing and its result persistence and user should not worry
     * about how the test is to be executed
     * 
     * @throws java.lang.IllegalStateException If the load test engine has been
     * started or if the UI is still not configured properly.
     */
    public void start()
        throws IllegalStateException;
}
