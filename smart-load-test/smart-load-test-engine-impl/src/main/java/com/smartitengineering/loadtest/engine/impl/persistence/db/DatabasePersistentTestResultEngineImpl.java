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
import com.smartitengineering.dao.common.QueryParameter;
import com.smartitengineering.loadtest.engine.persistence.PersistentTestResultEngine;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.ArrayList;
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

    private CommonReadDao<TestResult> persistentEngineDao;
    
    private CommonWriteDao<TestResult> persistentWriteEngineDao;

    public List<TestResult> getAllResults() {
        List<TestResult> allResults;
        try {
            allResults = persistentEngineDao.getList(Collections.<QueryParameter>emptyList());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            allResults =  Collections.<TestResult>emptyList();
        }
        return new ArrayList<TestResult>(new HashSet<TestResult>(allResults));
    }

    public List<TestResult> getAllForTestName(String testName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TestResult getTestResultById(int testResultId)
        throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean deleteTestResult(TestResult testResult)
        throws IllegalArgumentException,
               UnsupportedOperationException {
        if(testResult == null) {
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

    public List<TestResult> getAllResultWithinDateRange(Date startDate,
                                                        Date endDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<TestResult> getAllResultWithinDateRange(String testName,
                                                        Date startDate,
                                                        Date endDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<TestResult> getTestResults(Map<String, ? extends Object> filters)
        throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CommonReadDao<TestResult> getPersistentEngineDao() {
        return persistentEngineDao;
    }

    public void setPersistentEngineDao(
        CommonReadDao<TestResult> persistentEngineDao) {
        this.persistentEngineDao = persistentEngineDao;
    }

    public CommonWriteDao<TestResult> getPersistentWriteEngineDao() {
        return persistentWriteEngineDao;
    }

    public void setPersistentWriteEngineDao(CommonWriteDao<TestResult> persistentWriteEngineDao) {
        this.persistentWriteEngineDao = persistentWriteEngineDao;
    }
}
