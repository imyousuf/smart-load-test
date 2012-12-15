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
package com.smartitengineering.loadtest.engine.impl.persistence.db;

import com.smartitengineering.dao.common.CommonReadDao;
import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.dao.common.queryparam.MatchMode;
import com.smartitengineering.dao.common.queryparam.QueryParameter;
import com.smartitengineering.dao.common.queryparam.QueryParameterFactory;
import com.smartitengineering.loadtest.engine.persistence.PersistentTestResultEngine;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author imyousuf
 */
public class DatabasePersistentTestResultEngineImpl
    implements PersistentTestResultEngine {

    private CommonReadDao<TestResult, Integer> persistentEngineDao;
    private CommonWriteDao<TestResult> persistentWriteEngineDao;

    public List<TestResult> getAllResults() {
        List<TestResult> allResults;
        try {
            allResults = new ArrayList<TestResult>(persistentEngineDao.getAll());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            allResults = Collections.<TestResult>emptyList();
        }
        return new ArrayList<TestResult>(new HashSet<TestResult>(allResults));
    }

    public List<TestResult> getAllForTestName(final String testName) {
        QueryParameter<String> param =
            getNameParam(testName);
        List<TestResult> searchResults;
        try {
            searchResults = persistentEngineDao.getList(param);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            searchResults = Collections.<TestResult>emptyList();
        }
        return new ArrayList<TestResult>(new HashSet<TestResult>(searchResults));
    }

    public TestResult getTestResultById(int testResultId)
        throws UnsupportedOperationException {
        return persistentEngineDao.getById(testResultId);
    }

    public List<TestResult> getAllResultWithinDateRange(Date startDate,
                                                        Date endDate) {
        QueryParameter<Date> param = getDateParam(startDate, endDate);
        List<TestResult> searchResults;
        try {
            searchResults = persistentEngineDao.getList(param);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            searchResults = Collections.<TestResult>emptyList();
        }
        return new ArrayList<TestResult>(new HashSet<TestResult>(searchResults));
    }

    public List<TestResult> getAllResultWithinDateRange(String testName,
                                                        Date startDate,
                                                        Date endDate) {
        List<TestResult> searchResults;
        try {
            searchResults = persistentEngineDao.getList(getNameParam(testName),
                getDateParam(startDate, endDate));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            searchResults = Collections.<TestResult>emptyList();
        }
        return new ArrayList<TestResult>(new HashSet<TestResult>(searchResults));
    }

    public boolean deleteTestResult(TestResult testResult)
        throws IllegalArgumentException,
               UnsupportedOperationException {
        if (testResult == null) {
            throw new IllegalArgumentException();
        }
        try {
            persistentWriteEngineDao.delete(testResult);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * It works assuming that the filter is of Entry<String, QueryParameter>.
     * Please check the specification of QueryParamater of Smart Dao to know how
     * to use it. Any value that is not QueryParamater will be ignored.
     * @param filters Map of QueryParameter keyed by String
     * @return Filtered list. Collections.emptyList if filters is null or there
     *         is any exception in the search process
     * @throws java.lang.UnsupportedOperationException Does not throw this
     *                                                 exception
     */
    public List<TestResult> getTestResults(Map<String, ? extends Object> filters)
        throws UnsupportedOperationException {
        if (filters == null) {
            return Collections.<TestResult>emptyList();
        }
        ArrayList<QueryParameter> params = new ArrayList<QueryParameter>();
        for (Map.Entry<String, ? extends Object> entry : filters.entrySet()) {
            if (entry.getValue() instanceof QueryParameter) {
                params.add((QueryParameter) entry.getValue());
            }
        }
        List<TestResult> searchResults;
        try {
            searchResults = persistentEngineDao.getList(params);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            searchResults = Collections.<TestResult>emptyList();
        }
        return new ArrayList<TestResult>(new HashSet<TestResult>(searchResults));
    }

    public CommonReadDao<TestResult, Integer> getPersistentEngineDao() {
        return persistentEngineDao;
    }

    public void setPersistentEngineDao(
        CommonReadDao<TestResult, Integer> persistentEngineDao) {
        this.persistentEngineDao = persistentEngineDao;
    }

    public CommonWriteDao<TestResult> getPersistentWriteEngineDao() {
        return persistentWriteEngineDao;
    }

    public void setPersistentWriteEngineDao(
        CommonWriteDao<TestResult> persistentWriteEngineDao) {
        this.persistentWriteEngineDao = persistentWriteEngineDao;
    }

    protected QueryParameter<Date> getDateParam(Date startDate,
                                                Date endDate) {
        Calendar calendar = Calendar.getInstance();
        if (startDate == null) {
            startDate = new Date();
            calendar.setTimeInMillis(startDate.getTime());
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            startDate = calendar.getTime();
        }
        if (endDate == null) {
            endDate = new Date();
        }
        QueryParameter<Date> param =
            QueryParameterFactory.<Date>getBetweenPropertyParam("startDateTime",
            startDate, endDate);
        return param;
    }

    protected QueryParameter<String> getNameParam(final String testName) {
        QueryParameter<String> param =
            QueryParameterFactory.<String>getStringLikePropertyParam("testName", testName == null
            ? "" : testName, MatchMode.START);
        return param;
    }
}
