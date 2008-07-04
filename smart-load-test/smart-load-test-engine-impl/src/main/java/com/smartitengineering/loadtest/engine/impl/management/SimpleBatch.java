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
package com.smartitengineering.loadtest.engine.impl.management;

import com.smartitengineering.loadtest.engine.TestCase;
import com.smartitengineering.loadtest.engine.management.TestCaseBatchCreator;
import java.util.Map;

/**
 * Default implementation of Batch to be used by SPI batch creator
 * implementations.
 * @author imyousuf
 */
public class SimpleBatch
    implements TestCaseBatchCreator.Batch {

    private boolean batchStarted;
    private Map.Entry<ThreadGroup, Map<Thread, TestCase>> batch;
    private TestCaseBatchCreator creator;

    /**
     * Construct the simple batch with its creator and batch threads itself
     * @param creator The batch creator creating the batch threads
     * @param batch The batch threads to be wrapped by this batch.
     * @throws java.lang.IllegalArgumentException If creator or batch is null.
     */
    public SimpleBatch(final TestCaseBatchCreator creator,
                       final Map.Entry<ThreadGroup, Map<Thread, TestCase>> batch)
        throws IllegalArgumentException {
        if (batch == null || creator == null) {
            throw new IllegalArgumentException();
        }
        this.creator = creator;
        this.batch = batch;
        this.batchStarted = false;
    }

    public boolean isBatchStarted() {
        return batchStarted;
    }

    public void setBatchStarted() {
        batchStarted = true;
    }

    public Map.Entry<ThreadGroup, Map<Thread, TestCase>> getBatch() {
        return batch;
    }

    public TestCaseBatchCreator getBatchCreator() {
        return creator;
    }
}
