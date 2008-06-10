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

import java.util.Iterator;
import java.util.Properties;

/**
 * This provider would provide the number of threads to add to the next group.
 * Its main purpose is to enable users and implementors to implement customized
 * ways to provide the number of threads. For example, normal distribution for
 * hitting a website etc. Please note that size of it should be equal to that of
 * DelayProvider or the LoadTestEngine implementor might throw an exception of
 * IllegalArgumentException.
 *
 * @author imyousuf
 */
public interface NextStepSizeProvider extends Iterator<Integer> {
    
    /**
     * Initialize the delay provider with the properties. Please check the
     * SPI specification regarding the properties. If provider that size would
     * be 1 and step size would 1 as well.
     * @param properties Properties to be used for intializing the provider
     */
    public void init(Properties properties);
    
    /**
     * Returns the total number of steps available.
     * @return Total number of sizes available
     */
    public int size();
}

