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

    public TestCase getTestCase(Properties properties) {
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
