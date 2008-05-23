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

import com.smartitengineering.dao.common.CommonDaoWithVarArgs;
import com.smartitengineering.loadtest.engine.impl.persistence.AbstractPersistenceEngine;
import com.smartitengineering.loadtest.engine.result.TestResult;
import com.smartitengineering.loadtest.engine.LoadTestEngine;
import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public class DatabasePersistenceEngineImpl
    extends AbstractPersistenceEngine {
    
    private CommonDaoWithVarArgs<TestResult> persistentEngineDao;

    @Override
    protected void specializedInit(Properties properties) {
    }

    public boolean persistTestResult()
        throws UnsupportedOperationException {
        if (getLoadTestEngine().getState() != LoadTestEngine.State.FINISHED) {
            throw new UnsupportedOperationException(
                "Load Test Engine is not done yet!");
        }
        boolean persistOperationResult = false;
        try {
            persistentEngineDao.save(getLoadTestEngine().getTestResult());
            persistOperationResult = true;
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return persistOperationResult;
    }

    public CommonDaoWithVarArgs<TestResult> getPersistentEngineDao() {
        return persistentEngineDao;
    }

    public void setPersistentEngineDao(CommonDaoWithVarArgs<TestResult> persistentEngineDao) {
        this.persistentEngineDao = persistentEngineDao;
    }

}
