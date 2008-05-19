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
package com.smartitengineering.loadtest.engine.impl.persistence.filesystem;

import com.smartitengineering.loadtest.engine.impl.persistence.AbstractPersistenceEngine;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public abstract class AbstractFileSystemPersistenceEngine
    extends AbstractPersistenceEngine {

    private String resultsRootDirectory;
    public static final String RESULTS_ROOT_DIRECTORY_PROP =
        "com.smartitengineering.loadtest.engine.persistence.fs.rootDir";

    @Override
    protected void specializedInit(Properties properties) {
        if (properties.containsKey(RESULTS_ROOT_DIRECTORY_PROP)) {
            resultsRootDirectory = properties.getProperty(
                RESULTS_ROOT_DIRECTORY_PROP);
            if (resultsRootDirectory == null || resultsRootDirectory.length() <=
                0) {
                throw new IllegalStateException("Invalid path!");
            }
            else {
                try {
                    File rootDir = new File(resultsRootDirectory);
                    if (!rootDir.exists()) {
                        rootDir.mkdirs();
                    }
                    else if (!rootDir.isDirectory()) {
                        throw new IOException(
                            "Path specified is not a directory!");
                    }
                }
                catch (Exception ex) {
                    throw new IllegalStateException(ex);
                }
            }
        }
        specializedFileSystemInit(properties);
    }

    protected abstract void specializedFileSystemInit(Properties properties);

    protected String getResultsRootDirectory() {
        return resultsRootDirectory;
    }

    protected void setResultsRootDirectory(String resultsRootDirectory) {
        this.resultsRootDirectory = resultsRootDirectory;
    }
}
