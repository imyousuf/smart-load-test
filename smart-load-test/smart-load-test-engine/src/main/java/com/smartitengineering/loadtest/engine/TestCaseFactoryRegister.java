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
package com.smartitengineering.loadtest.engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this registry is to store test case creation factory for given
 * test name. So that Register Pattern can be implemented for this factory.
 * 
 * @author imyousuf
 */
public final class TestCaseFactoryRegister {

    public static final String DEFAULT_FACTORY = "default_factory";
    /**
     * Registry storing the test case creation factory for a given test name.
     * Please note that a same factory object/instance can reside in the
     * registry with different test name.
     */
    private static final Map<String, TestCaseCreationFactory> registry;
    

    static {
        registry = Collections.synchronizedMap(
            new HashMap<String, TestCaseCreationFactory>());
    }

    /**
     * Add a factory with the designated class name if a factory for the test
     * name does not already exist.
     * 
     * @param name Name of the test
     * @param testCaseCreationFactoryClassName Class name of the factory
     * @return true if registry is updated else false
     * @throws java.lang.ClassNotFoundException If class is not found in
     *                                          classpath
     * @throws java.lang.IllegalArgumentException If name is equal to 
     *                                            "default_factory"
     */
    public static boolean addFactoryToRegistry(String name,
                                               String testCaseCreationFactoryClassName)
        throws ClassNotFoundException,
               IllegalArgumentException {
        return addFactoryToRegistry(name, testCaseCreationFactoryClassName,
            Boolean.FALSE);
    }

    /**
     * Add a factory with the designated class name, regardless of its pre
     * existense, to the specified test name.
     * 
     * @param name Name of the test
     * @param testCaseCreationFactoryClassName Class name of the factory
     * @param overwrite notifying to update the entry for the test name if it
     *                  exists
     * @return true if registry is updated else false
     * @throws java.lang.ClassNotFoundException If class is not found in
     *                                          classpath
     * @throws java.lang.IllegalArgumentException If name is equal to 
     *                                            "default_factory"
     */
    public static boolean addFactoryToRegistry(String name,
                                               String testCaseCreationFactoryClassName,
                                               boolean overwrite)
        throws ClassNotFoundException,
               IllegalArgumentException {
        if (name == null || testCaseCreationFactoryClassName == null) {
            return Boolean.FALSE;
        }
        checkName(name);
        return addFactoryToRegistry(name, (Class) Class.forName(
            testCaseCreationFactoryClassName), overwrite);
    }

    /**
     * Add a factory with the designated class name, iff it doesn't pre
     * existense, to the specified test name.
     * 
     * @param name Name of test to register the class to
     * @param testCaseCreationFactoryClass
     * @return true if registry is updated else false
     * @throws java.lang.IllegalArgumentException If name is equal to 
     *                                            "default_factory"
     */
    public static boolean addFactoryToRegistry(String name,
                                               Class<? extends TestCaseCreationFactory> testCaseCreationFactoryClass)
        throws IllegalArgumentException {
        return addFactoryToRegistry(name, testCaseCreationFactoryClass,
            Boolean.FALSE);
    }

    /**
     * Add a factory with the designated class, regardless of its pre
     * existense, to the specified test name.
     * 
     * @param name Name of test to register the class to
     * @param testCaseCreationFactoryClazz Class to register to the test name
     * @param overwrite notifying to update the entry for the test name if it
     *                  exists
     * @return true if registry is updated else false
     * @throws java.lang.IllegalArgumentException If name is equal to 
     *                                            "default_factory"
     */
    public static boolean addFactoryToRegistry(String name,
                                               Class<? extends TestCaseCreationFactory> testCaseCreationFactoryClazz,
                                               boolean overwrite)
        throws IllegalArgumentException {
        if (name == null || testCaseCreationFactoryClazz == null) {
            return Boolean.FALSE;
        }
        checkName(name);
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

    /**
     * Add a factory with the specfied instance, iff it doesn't pre
     * existense, to the specified test name.
     * 
     * @param name Name of test to register the class to
     * @param testCaseCreationFactory The factory to associate to the test name
     * @return true if registry is updated else false
     * @throws java.lang.IllegalArgumentException If name is equal to 
     *                                            "default_factory"
     */
    public static boolean addFactoryToRegistry(String name,
                                               TestCaseCreationFactory testCaseCreationFactory)
        throws IllegalArgumentException {
        if (name != null) {
            checkName(name);
        }
        return addFactoryToRegistry(name, testCaseCreationFactory, Boolean.FALSE);
    }

    /**
     * Add a factory, regardless of its pre existense, to the specified test
     * name. It is the only method that can be used to add default creation
     * factory.
     * 
     * @param name Name of test to register the class to
     * @param testCaseCreationFactory The factory to associate to the test name
     * @param overwrite notifying to update the entry for the test name if it
     *                  exists
     * @return true if registry is updated else false
     */
    public static boolean addFactoryToRegistry(String name,
                                               TestCaseCreationFactory testCaseCreationFactory,
                                               boolean overwrite) {
        if (name == null || testCaseCreationFactory == null) {
            return Boolean.FALSE;
        }
        if (registry.containsKey(name) && !overwrite) {
            return Boolean.FALSE;
        }
        registry.put(name, testCaseCreationFactory);
        return Boolean.TRUE;
    }

    /**
     * This operation removes creation factory from registry, it should be
     * invoked once a creation factory becomes useless.
     * @param name The name of the creation factory to be removed from registry.
     * @return True if removed from registry else false
     * @throws java.lang.IllegalArgumentException If name is equal to 
     *                                            "default_factory" or is null
     */
    public static boolean removeFactory(String name)
        throws IllegalArgumentException {
        checkName(name);
        if (registry.containsKey(name)) {
            return registry.remove(name) != null;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks whether the given test name is registered in the reistry.
     * @param name Test name to search
     * @return true if exists in registry else false
     * @throws java.lang.IllegalArgumentException If name is null
     */
    public static boolean hasFactory(String name)
        throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        return registry.containsKey(name);
    }

    /**
     * Return the factory for the given test name
     * 
     * @param name Test name to retrieve
     * @return Factory for the designated test if exists or else null
     */
    public static TestCaseCreationFactory getTestCaseFactory(String name) {
        return registry.get(name);
    }

    private static void checkName(String name) {
        if (name == null || name.equals(DEFAULT_FACTORY)) {
            throw new IllegalArgumentException();
        }
    }
}
