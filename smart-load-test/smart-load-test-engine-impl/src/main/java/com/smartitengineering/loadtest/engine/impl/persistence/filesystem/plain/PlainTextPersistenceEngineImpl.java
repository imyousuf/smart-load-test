/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.loadtest.engine.impl.persistence.filesystem.plain;

import com.smartitengineering.loadtest.engine.impl.persistence.filesystem.AbstractFileSystemPersistenceEngine;
import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public class PlainTextPersistenceEngineImpl
    extends AbstractFileSystemPersistenceEngine {

    @Override
    protected void specializedFileSystemInit(Properties properties) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean persistTestResult()
        throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
