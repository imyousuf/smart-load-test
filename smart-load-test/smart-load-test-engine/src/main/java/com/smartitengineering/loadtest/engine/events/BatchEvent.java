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
package com.smartitengineering.loadtest.engine.events;

import com.smartitengineering.loadtest.engine.management.TestCaseBatchCreator;

/**
 * Represents a BatchEvent where batch creator is provided to the observer so
 * that they can handle as necessary.
 * @author imyousuf
 */
public class BatchEvent {

    private TestCaseBatchCreator batchCreator;

    /**
     * Create a batch event with the provided batch creator. Observer can get
     * the source batch creator of the event.
     *
     * @param batchCreator The batch creator to provide to the observer
     * @throws java.lang.IllegalArgumentException if batchCreator is null
     */
    public BatchEvent(TestCaseBatchCreator batchCreator)
        throws IllegalArgumentException {
        if (batchCreator == null) {
            throw new IllegalArgumentException();
        }
        this.batchCreator = batchCreator;
    }

    /**
     * Return the source batch creator of the event.
     * @return The batch creator.
     */
    public TestCaseBatchCreator getBatchCreator() {
        return batchCreator;
    }
    
}
