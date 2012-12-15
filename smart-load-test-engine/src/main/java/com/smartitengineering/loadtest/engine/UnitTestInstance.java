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
package com.smartitengineering.loadtest.engine;

import java.util.Properties;

/**
 * This class contains all the information for a test case instance. Any such
 * instance is represented by TestCaseResult in the result API of Engine. Prior
 * to configuring one should check the instances the class name required.<p />
 * The properties referred in this object should contain all necessary
 * properties for instance factory, delay provider and increment size provider.
 *
 * @author imyousuf
 */
public class UnitTestInstance {
    
    /**
     * Name of the test suite instance. A test suite in this case wraps a single
     * test case but their execution and increment is determined by other API
     * objects.
     */
    private String name;
    /**
     * Name of the class that would create the test case to be used by engine
     * to perform stress test. Please note that the class referred to in by this
     * class should be an instance of TestCaseCreationFactory
     */
    private String instanceFactoryClassName;
    /**
     * Delay for next group execution provider. This should be an instance of
     * DelayProvider.
     */
    private String delayTimeProviderClassName;
    /**
     * Increment size provider class name. This should be an instance of
     * NextStepSizeProvider.
     */
    private String incrementSizeProviderClassName;
    /**
     * Properties that would be used by all the 3 classes referred as above.
     */
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getInstanceFactoryClassName() {
        return instanceFactoryClassName;
    }

    public void setInstanceFactoryClassName(String instanceFactoryClassName) {
        this.instanceFactoryClassName = instanceFactoryClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDelayTimeProviderClassName() {
        return delayTimeProviderClassName;
    }

    public void setDelayTimeProviderClassName(String delayTimeProviderClassName) {
        this.delayTimeProviderClassName = delayTimeProviderClassName;
    }

    public String getIncrementSizeProviderClassName() {
        return incrementSizeProviderClassName;
    }

    public void setIncrementSizeProviderClassName(String incrementSizeProviderClassName) {
        this.incrementSizeProviderClassName = incrementSizeProviderClassName;
    }

}
