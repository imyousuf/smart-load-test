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

/**
 * An observer API for listening when a batch is available. Upon availability
 * the observer should take necessary action to retrieve the batch, start them
 * and monitor them.
 * @author imyousuf
 */
public interface TestCaseBatchListener {
    /**
     * Notify the observer that batch is available and provide the event to
     * retrieve the source of the event.
     * @param event The source batch creator event.
     */
    public void batchAvailable(BatchEvent event);
}
