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
package com.sparrow.dnp;

import com.automatak.dnp3.AnalogInput;
import com.automatak.dnp3.AnalogOutputStatus;
import com.automatak.dnp3.BinaryInput;
import com.automatak.dnp3.BinaryOutputStatus;
import com.automatak.dnp3.Counter;
import com.automatak.dnp3.DNPTime;
import com.automatak.dnp3.DoubleBitBinaryInput;
import com.automatak.dnp3.FrozenCounter;
import com.automatak.dnp3.HeaderInfo;
import com.automatak.dnp3.IndexedValue;
import com.automatak.dnp3.SOEHandler;
import com.automatak.dnp3.mock.PrintingSOEHandler;
import com.sparrow.dnp.config.MasterDeviceConfig;
import com.sparrow.dnp.config.VariableConfig;
import com.sparrow.dnp.config.VariableUnitConfig;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

/**
 * @author ugurkara
 *
 */
public class MasterDevice extends BaseDevice implements SOEHandler {

    private final HashMap<String, Object> properties = new HashMap<>();
    private final SOEHandler defaultHandler = PrintingSOEHandler.getInstance();

    public MasterDevice(String name) {
        super(name);
    }

    @Override
    public void start() {
    }

    @Override
    public void end() {
    }

    @Override
    public void processBI(HeaderInfo hi, Iterable<IndexedValue<BinaryInput>> itrbl) {
        defaultHandler.processBI(hi, itrbl);
        itrbl.forEach((IndexedValue<BinaryInput> v) -> {
            if (getDatabase().getDigitalInputs().getItems().containsKey(v.index)) {
                DnpVariable.DigitalInput oldValue = getDatabase().getDigitalInputs().getItems().get(v.index);
                oldValue.setObject(v.value);
            } else {
                getDatabase().getDigitalInputs().getItems().put(v.index, new DnpVariable.DigitalInput(v.index, v.value));
            }
        });
    }

    @Override
    public void processDBI(HeaderInfo hi, Iterable<IndexedValue<DoubleBitBinaryInput>> itrbl) {
        defaultHandler.processDBI(hi, itrbl);
        itrbl.forEach((IndexedValue<DoubleBitBinaryInput> v) -> {
            if (getDatabase().getDoubleDigitals().getItems().containsKey(v.index)) {
                DnpVariable.DoubleDigital oldValue = getDatabase().getDoubleDigitals().getItems().get(v.index);
                oldValue.setObject(v.value);
            } else {
                getDatabase().getDoubleDigitals().getItems().put(v.index, new DnpVariable.DoubleDigital(v.index, v.value));
            }
        });
    }

    @Override
    public void processAI(HeaderInfo hi, Iterable<IndexedValue<AnalogInput>> itrbl) {
        defaultHandler.processAI(hi, itrbl);
        itrbl.forEach((IndexedValue<AnalogInput> v) -> {
            if (getDatabase().getAnalogInputs().getItems().containsKey(v.index)) {
                DnpVariable.AnalogInput oldValue = getDatabase().getAnalogInputs().getItems().get(v.index);
                oldValue.setObject(v.value);
            } else {
                getDatabase().getAnalogInputs().getItems().put(v.index, new DnpVariable.AnalogInput(v.index, v.value));
            }
        });
    }

    @Override
    public void processC(HeaderInfo hi, Iterable<IndexedValue<Counter>> itrbl) {
        defaultHandler.processC(hi, itrbl);
        itrbl.forEach((IndexedValue<Counter> v) -> {
            if (getDatabase().getCounters().getItems().containsKey(v.index)) {
                DnpVariable.Counter oldValue = getDatabase().getCounters().getItems().get(v.index);
                oldValue.setObject(v.value);
            } else {
                getDatabase().getCounters().getItems().put(v.index, new DnpVariable.Counter(v.index, v.value));
            }

        });

    }

    @Override
    public void processFC(HeaderInfo hi, Iterable<IndexedValue<FrozenCounter>> itrbl) {
        
        defaultHandler.processFC(hi, itrbl);
        itrbl.forEach((IndexedValue<FrozenCounter> v) -> {
            if (getDatabase().getFrozenCounters().getItems().containsKey(v.index)) {
                DnpVariable.FrozenCounter oldValue = getDatabase().getFrozenCounters().getItems().get(v.index);
                oldValue.setObject(v.value);
            } else {
                getDatabase().getFrozenCounters().getItems().put(v.index, new DnpVariable.FrozenCounter(v.index, v.value));
            }

        });

    }

    @Override
    public void processBOS(HeaderInfo hi, Iterable<IndexedValue<BinaryOutputStatus>> itrbl) {

        defaultHandler.processBOS(hi, itrbl);
        itrbl.forEach((IndexedValue<BinaryOutputStatus> v) -> {
            if (getDatabase().getDigitalOutputs().getItems().containsKey(v.index)) {
                DnpVariable.DigitalOutput oldValue = getDatabase().getDigitalOutputs().getItems().get(v.index);
                oldValue.setObject(v.value);
            } else {
                getDatabase().getDigitalOutputs().getItems().put(v.index, new DnpVariable.DigitalOutput(v.index, v.value));
            }

        });

    }

    @Override
    public void processAOS(HeaderInfo hi, Iterable<IndexedValue<AnalogOutputStatus>> itrbl) {

        defaultHandler.processAOS(hi, itrbl);
        itrbl.forEach((IndexedValue<AnalogOutputStatus> v) -> {
            if (getDatabase().getAnalogOutputs().getItems().containsKey(v.index)) {
                DnpVariable.AnalogOutput oldValue = getDatabase().getAnalogOutputs().getItems().get(v.index);
                oldValue.setObject(v.value);
            } else {
                getDatabase().getAnalogOutputs().getItems().put(v.index, new DnpVariable.AnalogOutput(v.index, v.value));
            }

        });

    }

    @Override
    public void processDNPTime(HeaderInfo hi, Iterable<DNPTime> itrbl) {

        itrbl.forEach((DNPTime dnpTime) -> {

            Object oldValue = properties.get("time");
            properties.put("time", dnpTime.msSinceEpoch);
            this.pcs.firePropertyChange(new PropertyChangeEvent(dnpTime.msSinceEpoch, "properties", oldValue, dnpTime.msSinceEpoch));

        });

    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }

    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public static MasterDevice newDevice(MasterDeviceConfig config) {

        //OutstationDevice monitor = new OutstationDevice(config.getName(), config.build());
        MasterDevice masterDevice = new MasterDevice(config.getName());
        config.getMemoryUnits().forEach((VariableUnitConfig pointUnitConfigBuilder) -> {
            switch (pointUnitConfigBuilder.getType()) {
                case AnalogInput:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.AnalogInput analogInput = DnpVariable.AnalogInput.valueOf((VariableConfig.AnalogInputConfig) t);
                        masterDevice.getDatabase().getAnalogInputs().getItems().put(analogInput.getIndex(), analogInput);
                    });

                    break;

                case AnalogOutput:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.AnalogOutput analogOutput = DnpVariable.AnalogOutput.valueOf((VariableConfig.AnalogOutputConfig) t);
                        masterDevice.getDatabase().getAnalogOutputs().getItems().put(analogOutput.getIndex(), analogOutput);
                    });

                    break;

                case Counter:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.Counter counter = DnpVariable.Counter.valueOf((VariableConfig.CounterConfig) t);
                        masterDevice.getDatabase().getCounters().getItems().put(counter.getIndex(), counter);
                    });
                    
                    break;

                case DigitalInput:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.DigitalInput digitalInput = DnpVariable.DigitalInput.valueOf((VariableConfig.DigitalInputConfig) t);
                        masterDevice.getDatabase().getDigitalInputs().getItems().put(digitalInput.getIndex(), digitalInput);
                    });

                    break;

                case DigitalOutput:

                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.DigitalOutput digitalOutput = DnpVariable.DigitalOutput.valueOf((VariableConfig.DigitalOutputConfig) t);
                        masterDevice.getDatabase().getDigitalOutputs().getItems().put(digitalOutput.getIndex(), digitalOutput);
                    });

                    break;

                case DoubleDigital:

                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.DoubleDigital doubleDigital = DnpVariable.DoubleDigital.valueOf((VariableConfig.DoubleDigitalConfig) t);
                        masterDevice.getDatabase().getDoubleDigitals().getItems().put(doubleDigital.getIndex(), doubleDigital);
                    });

                    break;

                case FrozenCounter:

                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.FrozenCounter frozenCounter = DnpVariable.FrozenCounter.valueOf((VariableConfig.FrozenCounterConfig) t);
                        masterDevice.getDatabase().getFrozenCounters().getItems().put(frozenCounter.getIndex(), frozenCounter);

                    });

                    break;

            }

        });

        return masterDevice;
    }
}