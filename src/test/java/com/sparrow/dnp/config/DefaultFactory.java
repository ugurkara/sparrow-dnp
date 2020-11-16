/* 
 * Copyright 2020 KR ENDÜSTRİYEL BİLİŞİM LTD. ŞTİ..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sparrow.dnp.config;

import java.io.File;

/**
 *
 *
 *
 * @author KR INDUSTRIAL IT
 *
 */
public class DefaultFactory {

    public static MasterDeviceConfig createExampleMasterDeviceConfig() {

        MasterDeviceConfig masterDeviceConfig = new MasterDeviceConfig();

        masterDeviceConfig.getLinkConfig().setMaster(Boolean.TRUE);

        masterDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.AnalogInputs());
        masterDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.AnalogOutputs());
        masterDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.DigitalInputs());
        masterDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.Counters());
        masterDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.DigitalOutputs());
        masterDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.DoubleDigitals());
        masterDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.FrozenCounters());

        for (int i = 0; i < 10; i++) {
            masterDeviceConfig.getMemoryUnits().get(0).getItems().add(new VariableConfig.AnalogInputConfig(i));
            masterDeviceConfig.getMemoryUnits().get(1).getItems().add(new VariableConfig.AnalogOutputConfig(i));
            masterDeviceConfig.getMemoryUnits().get(2).getItems().add(new VariableConfig.DigitalInputConfig(i));
            masterDeviceConfig.getMemoryUnits().get(3).getItems().add(new VariableConfig.CounterConfig(i));
            masterDeviceConfig.getMemoryUnits().get(4).getItems().add(new VariableConfig.DigitalOutputConfig(i));
            masterDeviceConfig.getMemoryUnits().get(5).getItems().add(new VariableConfig.DoubleDigitalConfig(i));
            masterDeviceConfig.getMemoryUnits().get(6).getItems().add(new VariableConfig.FrozenCounterConfig());
        }

        return masterDeviceConfig;

    }

    public static SlaveDeviceConfig createExampleSlaveDeviceConfig() {

        SlaveDeviceConfig slaveDeviceConfig = new SlaveDeviceConfig();

        slaveDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.AnalogInputs());
        slaveDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.AnalogOutputs());
        slaveDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.DigitalInputs());
        slaveDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.Counters());
        slaveDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.DigitalOutputs());
        slaveDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.DoubleDigitals());
        slaveDeviceConfig.getMemoryUnits().add(new VariableUnitConfig.FrozenCounters());

        for (int i = 0; i < 10; i++) {

            slaveDeviceConfig.getMemoryUnits().get(0).getItems().add(new VariableConfig.AnalogInputConfig(i));
            slaveDeviceConfig.getMemoryUnits().get(1).getItems().add(new VariableConfig.AnalogOutputConfig(i));
            slaveDeviceConfig.getMemoryUnits().get(2).getItems().add(new VariableConfig.DigitalInputConfig(i));
            slaveDeviceConfig.getMemoryUnits().get(3).getItems().add(new VariableConfig.CounterConfig(i));
            slaveDeviceConfig.getMemoryUnits().get(4).getItems().add(new VariableConfig.DigitalOutputConfig(i));
            slaveDeviceConfig.getMemoryUnits().get(5).getItems().add(new VariableConfig.DoubleDigitalConfig(i));
            slaveDeviceConfig.getMemoryUnits().get(6).getItems().add(new VariableConfig.FrozenCounterConfig());
        }

        return slaveDeviceConfig;

    }

    public static File parentFile() {

        File f1 = new File(System.getProperty("user.home"));
        File f2 = new File(f1, "Sparrow-DNP-Examples");
        File f3 = new File(f2, "maven-dnp-test");
        File f4 = new File(f3, "sparrow");
        File f5 = new File(f4, "com");
        f5.mkdirs();

        return f5;

    }

}
