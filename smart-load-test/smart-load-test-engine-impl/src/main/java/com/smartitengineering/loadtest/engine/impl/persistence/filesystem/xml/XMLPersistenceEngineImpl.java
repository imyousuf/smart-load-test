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
package com.smartitengineering.loadtest.engine.impl.persistence.filesystem.xml;

import com.smartitengineering.loadtest.engine.LoadTestEngine;
import com.smartitengineering.loadtest.engine.impl.persistence.filesystem.AbstractFileSystemPersistenceEngine;
import com.smartitengineering.loadtest.engine.result.KeyedInformation;
import com.smartitengineering.loadtest.engine.result.TestCaseInstanceResult;
import com.smartitengineering.loadtest.engine.result.TestCaseResult;
import com.smartitengineering.loadtest.engine.result.TestProperty;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

/**
 *
 * @author imyousuf
 */
public class XMLPersistenceEngineImpl
    extends AbstractFileSystemPersistenceEngine {

    @Override
    protected void specializedFileSystemInit(Properties properties) {
        setPersistentTestResultEngine(new XMLPersistentTestResultEngineImpl(
            getResultsRootDirectory()));
    }

    public boolean persistTestResult()
        throws UnsupportedOperationException {
        if (getLoadTestEngine().getState() != LoadTestEngine.State.FINISHED) {
            throw new UnsupportedOperationException(
                "Load Test Engine is not done yet!");
        }
        boolean persistOperationResult = false;
        try {
            TestResult testResult = getLoadTestEngine().getTestResult();
            JAXBContext jc = JAXBContext.newInstance(TestResult.class,
                TestCaseResult.class, TestProperty.class,
                TestCaseInstanceResult.class, KeyedInformation.class);
            Marshaller m = jc.createMarshaller();
            OutputStream os = new FileOutputStream(new File(
                getResultsRootDirectory(), new StringBuilder().append(System.
                currentTimeMillis()).append(".xml").toString()));
            m.marshal(new JAXBElement<TestResult>(new QName("testResult"),
                TestResult.class, testResult), os);
            persistOperationResult = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new UnsupportedOperationException(ex);
        }
        return persistOperationResult;
    }
}
