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
package com.smartitengineering.loadtest.engine.impl;

import com.smartitengineering.loadtest.engine.NextStepSizeProvider;
import java.util.Properties;

/**
 *
 * @author imyousuf
 */
public class UniformStepSizeProvider
    implements NextStepSizeProvider {

    public static final String STEP_COUNT_PROPS =
        "com.smartitengineering.loadtest.engine.uniformProvider.stepCount";
    public static final String UNIT_STEP_SIZE_PROPS =
        "com.smartitengineering.loadtest.engine.uniformProvider.stepSize";
    private int stepCount;
    private int unitStepSize;
    private int currentIteration;

    public void init(Properties properties) {
        if (properties.containsKey(STEP_COUNT_PROPS)) {
            try {
                stepCount = Integer.parseInt(properties.getProperty(
                    STEP_COUNT_PROPS));
            }
            catch (Exception ex) {
                ex.printStackTrace();
                stepCount = 1;
            }
        }
        else {
            stepCount = 1;
        }
        if (properties.containsKey(UNIT_STEP_SIZE_PROPS)) {
            try {
                unitStepSize = Integer.parseInt(properties.getProperty(
                    UNIT_STEP_SIZE_PROPS));
            }
            catch (Exception ex) {
                ex.printStackTrace();
                unitStepSize = 1;
            }
        }
        else {
            unitStepSize = 1;
        }
        currentIteration = 0;
    }

    public int size() {
        return stepCount;
    }

    public boolean hasNext() {
        return currentIteration < size();
    }

    public Integer next() {
        if (hasNext()) {
            currentIteration++;
            return unitStepSize;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void remove() {
        currentIteration++;
    }
}
