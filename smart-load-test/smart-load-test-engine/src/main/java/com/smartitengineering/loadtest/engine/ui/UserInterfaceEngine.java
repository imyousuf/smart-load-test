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

/**
 *
 * @author imyousuf
 */
public interface UserInterfaceEngine {

    /**
     * Starts the UI Engine and it is expected that the UI Engine will guide the
     * whole load testing and its result persistence and user should not worry
     * about how the test is to be executed
     * 
     * @throws java.lang.IllegalStateException If the load test engine has been
     * started or if the UI is still not configured properly.
     */
    public void start() throws IllegalStateException;
}
