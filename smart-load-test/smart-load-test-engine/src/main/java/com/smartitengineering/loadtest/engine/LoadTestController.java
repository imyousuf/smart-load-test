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
package com.smartitengineering.loadtest.engine;

import com.smartitengineering.loadtest.engine.persistence.PersistenceEngine;
import com.smartitengineering.loadtest.engine.ui.UserInterfaceEngine;
import java.util.Set;

/**
 *
 * @author imyousuf
 */
public interface LoadTestController {

    /**
     * Returns whether the load test engine is configured or not. If load test
     * engine is absent than it does not make sense to have any other engine.
     * 
     * @return true if the controller has a load test engine
     */
    public boolean isLoadTestEngineConfigured();

    /**
     * Returns whether persistent engine is configured with this controller or
     * not. Persistence engine depends on load test engine. Persistence Engine
     * configuration is only required if the test result is intended to persist
     * somewhere.
     * 
     * @return true if the controller has persistence engine configured.
     */
    public boolean isPersistenceEngineConfigured();

    /**
     * Returns whether UI Engine is configured or not. UI Engine can be
     * configured either with load test engine or persistence engine and its
     * main purpose is t display test status
     * 
     * @return true if ui engine is available in this controller
     */
    public boolean isUIEngineConfigured();

    /**
     * Return the load test engine configured with this test controller. If its
     * not present then the controller does not make any sense. It is sure to
     * return a not-null value if isLoadTestEngineConfigured is true.
     * 
     * @return Load Test Engine responsible for carrying out the tests
     * @throws java.lang.UnsupportedOperationException If
     *                   isLoadTestEngineConfigured returns false it will return
     */
    public LoadTestEngine getLoadTestEngine()
        throws UnsupportedOperationException;

    /**
     * Returns the persistence engines capable of persisitng test results
     * associated with the load test engine configured in the controller.
     * 
     * @return Returns not-null and not empty persistence engine set, which can
     *         be used for persisting test results.
     * @throws java.lang.UnsupportedOperationException If
     *                isPersistenceEngineConfigured returns false it will return
     */
    public Set<PersistenceEngine> getPersistenceEngine()
        throws UnsupportedOperationException;

    /**
     * Returns the configured user interface engine which will display progress
     * of the test and its results as they progress.
     * 
     * @return a not-null UI Engine for this controller
     * @throws java.lang.UnsupportedOperationException If
     *                         isUIEngineConfigured returns false it will return
     */
    public UserInterfaceEngine getUserInterfaceEngine()
        throws UnsupportedOperationException;
}
