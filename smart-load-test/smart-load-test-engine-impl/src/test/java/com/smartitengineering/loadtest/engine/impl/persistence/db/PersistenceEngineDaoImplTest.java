/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.loadtest.engine.impl.persistence.db;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author imyousuf
 */
public class PersistenceEngineDaoImplTest extends TestCase {
    
    public PersistenceEngineDaoImplTest(String testName) {
        super(testName);
    }            

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testDaoConfiguration() {
        System.setProperty("com.smartitengineering.loadtest.engine.persistence.db.props_file", "classpath:db-test-config.properties");
        ApplicationContext context = new ClassPathXmlApplicationContext("com/smartitengineering/loadtest/engine/impl/persistence/db/db-beanfactory.xml");
        context.getBean("persistenceEngine");
    }
}
