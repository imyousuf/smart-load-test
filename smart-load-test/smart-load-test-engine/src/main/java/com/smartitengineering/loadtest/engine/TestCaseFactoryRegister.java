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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author imyousuf
 */
public final class TestCaseFactoryRegister {

    private static final Map<String, TestCaseCreationFactory> registry;
    

    static {
        registry = Collections.synchronizedMap(
            new HashMap<String, TestCaseCreationFactory>());
    }

    public static boolean addFactoryToRegistry(String name,
                                               String testCaseCreationFactoryClassName)
        throws ClassNotFoundException {
        return addFactoryToRegistry(name, testCaseCreationFactoryClassName,
            Boolean.FALSE);
    }

    public static boolean addFactoryToRegistry(String name,
                                               String testCaseCreationFactoryClassName,
                                               boolean overwrite)
        throws ClassNotFoundException {
        return addFactoryToRegistry(name, (Class) Class.forName(
            testCaseCreationFactoryClassName), overwrite);
    }

    public static boolean addFactoryToRegistry(String name,
                                               Class<? extends TestCaseCreationFactory> testCaseCreationFactoryClass) {
        return addFactoryToRegistry(name, testCaseCreationFactoryClass,
            Boolean.FALSE);
    }

    public static boolean addFactoryToRegistry(String name,
                                               Class<? extends TestCaseCreationFactory> testCaseCreationFactoryClazz,
                                               boolean overwrite) {
        if (registry.containsKey(name) && !overwrite) {
            return Boolean.FALSE;
        }
        TestCaseCreationFactory factory;
        try {
            factory = testCaseCreationFactoryClazz.newInstance();
        }
        catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
        registry.put(name, factory);
        return Boolean.TRUE;
    }

    public static boolean addFactoryToRegistry(String name,
                                               TestCaseCreationFactory testCaseCreationFactory) {
        return addFactoryToRegistry(name, testCaseCreationFactory, Boolean.FALSE);
    }

    public static boolean addFactoryToRegistry(String name,
                                               TestCaseCreationFactory testCaseCreationFactory,
                                               boolean overwrite) {
        if (registry.containsKey(name) && !overwrite) {
            return Boolean.FALSE;
        }
        registry.put(name, testCaseCreationFactory);
        return Boolean.TRUE;
    }

    public static boolean hasFactory(String name) {
        return registry.containsKey(name);
    }

    public static TestCaseCreationFactory getTestCaseFactory(String name) {
        return registry.get(name);
    }
}
