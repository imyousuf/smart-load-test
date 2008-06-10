/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.loadtest.engine.impl.persistence.db;

import com.smartitengineering.loadtest.engine.LoadTestEngine;
import com.smartitengineering.loadtest.engine.TestCaseThreadPolicy;
import com.smartitengineering.loadtest.engine.UnitTestInstance;
import com.smartitengineering.loadtest.engine.events.LoadTestEngineStateChangeListener;
import com.smartitengineering.loadtest.engine.events.TestCaseTransitionListener;
import com.smartitengineering.loadtest.engine.persistence.PersistenceEngine;
import com.smartitengineering.loadtest.engine.persistence.PersistentTestResultEngine;
import com.smartitengineering.loadtest.engine.result.KeyedInformation;
import com.smartitengineering.loadtest.engine.result.TestCaseInstanceResult;
import com.smartitengineering.loadtest.engine.result.TestCaseResult;
import com.smartitengineering.loadtest.engine.result.TestProperty;
import com.smartitengineering.loadtest.engine.result.TestResult;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author imyousuf
 */
public class DatabasePersistenceEngineImplTest
    extends TestCase {

    private static LoadTestEngine loadTestEngine;
    private static PersistenceEngine dbImpl;

    public DatabasePersistenceEngineImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp()
        throws Exception {
        super.setUp();
        if (loadTestEngine == null) {
            loadTestEngine = new LoadTestEngine() {

                public void init(String testName,
                                 Set<UnitTestInstance> testInstances,
                                 Properties initProperties)
                    throws IllegalArgumentException {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public State getState() {
                    return LoadTestEngine.State.FINISHED;
                }

                public void reinstanteCreatedState() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public void start()
                    throws IllegalStateException {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public Date getStartTime() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public Date getEndTime() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public long getDuration()
                    throws IllegalStateException {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public TestResult getTestResult()
                    throws IllegalStateException {
                    TestResult testResult = new TestResult();
                    testResult.setTestName("Test Name");
                    testResult.setStartDateTime(new Date());
                    testResult.setEndDateTime(new Date(
                        System.currentTimeMillis() + 10000));
                    testResult.setVersion(13);
                    HashSet<KeyedInformation> infos =
                        new HashSet<KeyedInformation>();
                    KeyedInformation info = new KeyedInformation();
                    info.setKey("Key 1");
                    info.setValue("value 1");
                    infos.add(info);
                    info = new KeyedInformation();
                    info.setKey("Key 2");
                    info.setValue("value 2");
                    infos.add(info);
                    info = new KeyedInformation();
                    info.setKey("Key 3");
                    info.setValue("value 3");
                    infos.add(info);
                    testResult.setOtherInfomations(infos);
                    HashSet<TestCaseResult> results =
                        new HashSet<TestCaseResult>();
                    TestCaseResult result = new TestCaseResult();
                    result.setInstanceFactoryClassName(
                        "instanceFactoryClassName");
                    result.setName("Test Case Name");
                    result.setOtherInfomations(infos);
                    HashSet<TestProperty> properties =
                        new HashSet<TestProperty>();
                    TestProperty property = new TestProperty();
                    property.setKey("test prop key 1");
                    property.setValue("test prop val 1");
                    properties.add(property);
                    property = new TestProperty();
                    property.setKey("test prop key 2");
                    property.setValue("test prop val 2");
                    properties.add(property);
                    property = new TestProperty();
                    property.setKey("test prop key 3");
                    property.setValue("test prop val 3");
                    properties.add(property);
                    result.setTestProperties(properties);
                    result.setStepCount(2);
                    result.setStepDelayConfiguration("hhh mm ss");
                    result.setStepSize(3);
                    HashSet<TestCaseInstanceResult> instanceResults =
                        new HashSet<TestCaseInstanceResult>();
                    TestCaseInstanceResult instanceResult =
                        new TestCaseInstanceResult();
                    instanceResult.setEndTestCaseState(
                        com.smartitengineering.loadtest.engine.TestCase.State.FINISHED);
                    instanceResult.setEndTime(new Date(
                        System.currentTimeMillis() + 10000));
                    instanceResult.setInstanceNumber(1);
                    instanceResult.setOtherInfomations(infos);
                    instanceResult.setStartTime(new Date());
                    instanceResults.add(instanceResult);
                    instanceResult = (TestCaseInstanceResult) instanceResult.
                        clone();
                    instanceResult.setInstanceNumber(2);
                    instanceResults.add(instanceResult);
                    instanceResult = (TestCaseInstanceResult) instanceResult.
                        clone();
                    instanceResult.setInstanceNumber(3);
                    instanceResults.add(instanceResult);
                    result.setTestCaseInstanceResults(instanceResults);
                    results.add(result);
                    result = (TestCaseResult) result.clone();
                    result.setName(result.getName() + " 1");
                    results.add(result);
                    result = (TestCaseResult) result.clone();
                    result.setName(result.getName() + " 2");
                    results.add(result);
                    testResult.setTestCaseRunResults(results);
                    return testResult;
                }

                public void setTestCaseThreadPolicy(TestCaseThreadPolicy policy) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public TestCaseThreadPolicy getTestCaseThreadPolicy() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public void unsetTestCaseThreadPolicy() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public void addLoadTestEngineStateChangeListener(
                    LoadTestEngineStateChangeListener listener) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public void removeLoadTestEngineStateChangeListener(
                    LoadTestEngineStateChangeListener listener) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public void addTestCaseTransitionListener(
                    TestCaseTransitionListener listener) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public void removeTestCaseTransitionListener(
                    TestCaseTransitionListener listener) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
        }
        if (dbImpl == null) {
            System.setProperty(
                "com.smartitengineering.loadtest.engine.persistence.db.props_file",
                "classpath:db-test-config.properties");
            ApplicationContext context =
                new ClassPathXmlApplicationContext(
                "com/smartitengineering/loadtest/engine/impl/persistence/db/db-beanfactory.xml");
            dbImpl = (PersistenceEngine) context.getBean("persistenceEngine");
        }
    }

    /**
     * Test of doPersist method, of class DatabasePersistenceEngineImpl.
     */
    public void testDoPersist() {
        dbImpl.init(loadTestEngine, new Properties());
        assertEquals(true, dbImpl.persistTestResult());

    }

    public void testGetAll() {
        PersistentTestResultEngine engine =
            dbImpl.getPersistentTestResultEngine();
        List<TestResult> allResults = engine.getAllResults();
        if(allResults.isEmpty()) {
            fail("All can not be null");
        }
    }

    public void testDeleteTestResult() {
        PersistentTestResultEngine engine =
            dbImpl.getPersistentTestResultEngine();
        List<TestResult> allResults = engine.getAllResults();
        for (TestResult testResult : allResults) {
            assertEquals(true, dbImpl.getPersistentTestResultEngine().deleteTestResult(testResult));
        }
    }
}
