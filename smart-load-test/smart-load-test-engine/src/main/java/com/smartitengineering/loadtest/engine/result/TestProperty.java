/**
 * 
 *    This module represents an engine for the load testing framework
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
package com.smartitengineering.loadtest.engine.result;

import com.smartitengineering.domain.AbstractPersistentDTO;
import com.smartitengineering.domain.PersistentDTO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author imyousuf
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TestProperty
    extends AbstractPersistentDTO<TestProperty>
    implements PersistentDTO<TestProperty> {

    private String key;
    private String value;

    public boolean isValid() {
        if (key != null && value != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object clone() {
        TestProperty property = new TestProperty();
        super.clone(property);
        property.setKey(key);
        property.setValue(value);
        return property;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof TestProperty) {
            TestProperty resultObj = (TestProperty) obj;
            if (key != null && resultObj.key != null &&
                key.equals(resultObj.key)) {
                return true;
            }
        }
        return false;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
