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
package com.smartitengineering.loadtest.engine;

import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public class UnitTestInstance {
    
    private String name;
    private String instanceFactoryClassName;
    private String stepDelayConfiguration;
    private int stepSize;
    private int stepCount;
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

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public String getStepDelayConfiguration() {
        return stepDelayConfiguration;
    }

    public void setStepDelayConfiguration(String stepDelayConfiguration) {
        this.stepDelayConfiguration = stepDelayConfiguration;
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }
    
}
