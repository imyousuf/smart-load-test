/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.loadtest.engine.impl.persistence.db;

import com.smartitengineering.dao.common.CommonDaoWithVarArgs;
import com.smartitengineering.loadtest.engine.persistence.PersistentTestResultEngine;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author imyousuf
 */
public class DatabasePersistentTestResultEngineImpl
    implements PersistentTestResultEngine {

    private CommonDaoWithVarArgs<TestResult> persistentEngineDao;

    public List<TestResult> getAllResults() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
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

    public CommonDaoWithVarArgs<TestResult> getPersistentEngineDao() {
        return persistentEngineDao;
    }

    public void setPersistentEngineDao(CommonDaoWithVarArgs<TestResult> persistentEngineDao) {
        this.persistentEngineDao = persistentEngineDao;
    }
}
