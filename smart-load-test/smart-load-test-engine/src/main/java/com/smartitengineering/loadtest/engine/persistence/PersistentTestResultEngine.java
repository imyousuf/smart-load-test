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
package com.smartitengineering.loadtest.engine.persistence;

import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author imyousuf
 */
public interface PersistentTestResultEngine {
    
    /**
     * Return all the results in this persitent storage
     * 
     * @return All stored resutls
     */
    public List<TestResult> getAllResults();

    /**
     * Get all results for the specified test name. Return empty list if no
     * resutls.
     * 
     * @param testName The test name to retrieve
     * @return List of results, empty list if none
     */
    public List<TestResult> getAllForTestName(String testName);

    /**
     * Retrieve full result for the specified test result id.
     * 
     * @param testResultId ID of test result to retrieve
     * @return test result specified by id; null if no such test result
     */
    public TestResult getTestResultById(int testResultId);

    /**
     * Retrieve all test result between specified date range. All test that
     * starts greater or equal to startDate or ends lesser or equal to endDate
     * will be returned in this result.
     * 
     * @param startDate Start datetime of the search
     * @param endDate End datetime of the search
     * @return All test results within the range
     */
    public List<TestResult> getAllResultWithinDateRange(Date startDate,
                                                        Date endDate);

    /**
     * Retrieve all test result between specified date range. All test that
     * starts greater or equal to startDate or ends lesser or equal to endDate
     * and has the name testName will be returned in this result.
     * 
     * @param startDate Start datetime of the search
     * @param endDate End datetime of the search
     * @param testName Name of the test
     * @return All test results within the range and with testName. Empty list
     * if no such result.
     */
    public List<TestResult> getAllResultWithinDateRange(String testName,
                                                        Date startDate,
                                                        Date endDate);

    /**
     * This operation is SPI specific. It intends to provide a strong search
     * functionality for searching test result. Please consider SPI
     * documentation to learn more about how to use the filter.
     * 
     * @param filters Containing coditions to be used for search
     * @return The search result; empty list if none.
     */
    public List<TestResult> getTestResults(Map<String, ? extends Object> filters);
}
