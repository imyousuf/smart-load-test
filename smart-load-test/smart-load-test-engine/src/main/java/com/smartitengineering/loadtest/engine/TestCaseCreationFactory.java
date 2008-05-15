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

import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public interface TestCaseCreationFactory {

    /**
     * This factory method is responsible for creating TestCase. For this the
     * client will supply the properties it achieved during the test instance
     * creation. How the properties will be used depends solely on the
     * implementor
     * @param <TestCaseType> Specifying what type of TestCase this factory
     *                       returns
     * @param properties The properties that the factory may use for creating
     *                      the test case.
     * @return The test case in CREATED state
     */
    public <TestCaseType extends TestCase> TestCaseType getTestCase(
        Properties properties);
}
