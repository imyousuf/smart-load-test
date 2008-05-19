/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.loadtest.engine.impl.persistence.db;

import com.smartitengineering.loadtest.engine.impl.persistence.AbstractPersistenceEngine;
import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public class DatabasePersistenceEngineImpl
    extends AbstractPersistenceEngine {

    @Override
    protected void specializedInit(Properties properties) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean persistTestResult()
        throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
