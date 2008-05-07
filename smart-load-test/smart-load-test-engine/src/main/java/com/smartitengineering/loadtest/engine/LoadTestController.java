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
package com.smartitengineering.loadtest.engine;

import com.smartitengineering.loadtest.engine.persistence.PersistenceEngine;
import com.smartitengineering.loadtest.engine.ui.UserInterfaceEngine;

/**
 *
 * @author imyousuf
 */
public interface LoadTestController {
    
    public boolean isLoadTestEngineConfigured();
    
    public boolean isPersistenceEngineConfigured();
    
    public boolean isUIEngineConfigured();
    
    public LoadTestEngine getLoadTestEngine();
    
    public PersistenceEngine getPersistenceEngine();
    
    public UserInterfaceEngine getUserInterfaceEngine();
    
}
