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
 *    his program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.smartitengineering.loadtest.engine.events;

/**
 *
 * @author imyousuf
 */
public class AbstractEvent<Source> {

    /**
     * Source of the event
     */
    private Source source;
    
    /**
     * Construct the event with the given source
     * @param source Source of the event
     */
    public AbstractEvent(Source source) {
        this.source = source;
    }

    /**
     * Returns the source of the event provided during construction
     * @return Source of the event
     */
    public Source getSource() {
        return source;
    }
    
}
