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

import com.smartitengineering.loadtest.engine.TestCase;
import com.smartitengineering.loadtest.engine.TestCaseCreationFactory;
import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public class DefaultTestCaseCreationFactory
    implements TestCaseCreationFactory {

    private Class<? extends TestCase> testCaseClass;

    public DefaultTestCaseCreationFactory() {
    }

    public DefaultTestCaseCreationFactory(
        Class<? extends TestCase> testCaseClass) {
        this.testCaseClass = testCaseClass;
    }

    /**
     * This is the default test creation factory which initializes test cases
     * instances either by their constructor with single arg (Properties object)
     * or no args constructor (respectively). If neither of the constructor is
     * available then it will throw a illegal arguments exception
     * 
     * @see com.smartitengineering.loadtest.engine.TestCaseCreationFactory#getTestCase(java.util.Properties) 
     * @param properties Properties object to be used by contructor if exists.
     * @return Instance of the test case to be used by engine. Will never be NULL.
     * @throws java.lang.IllegalArgumentException If the class specfied is NULL
     *                                            or it does not contain no args
     *                                            or single properties argument
     *                                            constructor.
     */
    public TestCase getTestCase(Properties properties)
        throws IllegalArgumentException {
        TestCase type = null;
        if (testCaseClass != null) {
            try {
                type = testCaseClass.getConstructor(Properties.class).
                    newInstance(properties);
            }
            catch (Exception ex) {
                try {
                    type = testCaseClass.getConstructor().newInstance();
                }
                catch (Exception innerException) {
                }
            }
        }
        if (type != null) {
            return type;
        }

        throw new IllegalArgumentException("Can not use this factory to create test case of class type: " +
            ((testCaseClass != null) ? testCaseClass.getName() : "NULL"));
    }

    public Class<? extends TestCase> getTestCaseClass() {
        return testCaseClass;
    }

    public void setTestCaseClass(Class<? extends TestCase> testCaseClass) {
        this.testCaseClass = testCaseClass;
    }
}
