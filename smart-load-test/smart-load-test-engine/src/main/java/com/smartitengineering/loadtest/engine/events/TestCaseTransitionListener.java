/**
 *    This module represents an engine for the load testing framework
 * 
 *    Copyright (C) 2008  Imran M Yousuf (imran@smartitengineering.com)
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License along
 *    with this program; if not, write to the Free Software Foundation, Inc.,
 *    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.smartitengineering.loadtest.engine.events;

/**
 * This listener is used to notify on a certain state attainment. So that
 * the filtering is done by the implementor rather than the client. It is suited
 * for clients wanting to perform certain task on certain states and might have
 * states its not inerested in
 * 
 * @author imyousuf
 */
public interface TestCaseTransitionListener {
    
    /**
     * This is invoked when a test case has attained initialized state
     * @param event Event representing the initialized state
     */
    public void testCaseInitialized(TestCaseStateChangedEvent event);
    
    /**
     * This is invoked when a test case has attained started state
     * @param event Event representing the started state
     */
    public void testCaseStarted(TestCaseStateChangedEvent event);
    
    /**
     * This is invoked when a test case has attained finished state
     * @param event Event representing the finished state
     */
    public void testCaseFinished(TestCaseStateChangedEvent event);
    
    /**
     * This is invoked when a test case has attained stopped state
     * @param event Event representing the stopped state
     */
    public void testCaseStopped(TestCaseStateChangedEvent event);
    
}
