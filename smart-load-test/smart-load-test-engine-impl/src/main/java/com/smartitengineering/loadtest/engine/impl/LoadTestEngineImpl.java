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
package com.smartitengineering.loadtest.engine.impl;

import com.smartitengineering.loadtest.engine.UnitTestInstance;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.Properties;
import java.util.Set;

/**
 * Default implementation of the LoadTestEngine
 *
 */
public class LoadTestEngineImpl
    extends AbstractLoadTestEngineImpl {

    @Override
    protected void rollBackToCreatedState() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void init(String testName,
                     Set<UnitTestInstance> testInstances,
                     Properties initProperties)
        throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void start()
        throws IllegalStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TestResult getTestResult()
        throws IllegalStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void initializeBeforeCreatedState() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
