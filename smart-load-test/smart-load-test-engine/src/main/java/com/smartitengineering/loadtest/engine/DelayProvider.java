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
 * It provides the delay configuration for creating the thread groups to execute
 * TestCase.<p />
 * It is a iterator but does not support the remove operation thus invoking it
 * would result UnsupportedOperationException. In addition it adds the size
 * operation to get the size. Please note that its size should be equal to that
 * of NextStepSizeProvider. If not the LoadTestEngine might throw an
 * IllegalArgumentException.
 *
 * @author imyousuf
 */
public interface DelayProvider extends Iterator<Integer> {
    
    /**
     * Initialize the delay provider with the properties. Please check the
     * SPI specification regarding the properties. If provider that size would
     * be 1 and delay would be 10ms.
     * @param properties Properties to be used for intializing the provider
     */
    public void init(Properties properties);
    
    /**
     * Returns the total number of steps available.
     * @return Total number of sizes available
     */
    public int size();
}
