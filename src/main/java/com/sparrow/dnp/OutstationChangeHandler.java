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
import com.automatak.dnp3.DoubleBitBinaryInput;
import com.automatak.dnp3.FrozenCounter;
import com.automatak.dnp3.Outstation;
import com.automatak.dnp3.OutstationChangeSet;
import com.automatak.dnp3.enums.EventMode;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class OutstationChangeHandler {

    private final OutstationChangeSet outstationChangeSet = new OutstationChangeSet();
    private final AnalogInputChangeHandler analogInputChangeHandler;
    private final AnalogOutputChangeHandler analogOutputChangeHandler;
    private final DigitalInputChangeHandler digitalInputChangeHandler;
    private final DigitalOutputChangeHandler digitalOutputChangeHandler;
    private final CounterChangeHandler counterChangeHandler;
    private final FrozenCounterChangeHandler frozenCounterChangeHandler;
    private final DoubleDigitalChangeHandler doubleDigitalChangeHandler;
    private final Outstation outstation;

    public OutstationChangeHandler(Outstation outstation) {
        this.outstation = outstation;
        analogInputChangeHandler = new AnalogInputChangeHandler(outstation, outstationChangeSet);
        analogOutputChangeHandler = new AnalogOutputChangeHandler(outstation, outstationChangeSet);
        digitalInputChangeHandler = new DigitalInputChangeHandler(outstation, outstationChangeSet);
        digitalOutputChangeHandler = new DigitalOutputChangeHandler(outstation, outstationChangeSet);
        counterChangeHandler = new CounterChangeHandler(outstation, outstationChangeSet);
        frozenCounterChangeHandler = new FrozenCounterChangeHandler(outstation, outstationChangeSet);
        doubleDigitalChangeHandler = new DoubleDigitalChangeHandler(outstation, outstationChangeSet);
    }

    public AnalogInputChangeHandler getAnalogInputChangeHandler() {
        return analogInputChangeHandler;
    }

    public AnalogOutputChangeHandler getAnalogOutputChangeHandler() {
        return analogOutputChangeHandler;
    }

    public CounterChangeHandler getCounterChangeHandler() {
        return counterChangeHandler;
    }

    public DigitalInputChangeHandler getDigitalInputChangeHandler() {
        return digitalInputChangeHandler;
    }

    public DigitalOutputChangeHandler getDigitalOutputChangeHandler() {
        return digitalOutputChangeHandler;
    }

    public DoubleDigitalChangeHandler getDoubleDigitalChangeHandler() {
        return doubleDigitalChangeHandler;
    }

    public FrozenCounterChangeHandler getFrozenCounterChangeHandler() {
        return frozenCounterChangeHandler;
    }

    public Outstation getOutstation() {
        return outstation;
    }

    void update(Database database, EventMode eventMode) {
        database.getAnalogInputs().getItems().forEach((k, v) -> {
            analogInputChangeHandler.update(outstationChangeSet, v, eventMode);
        });

        database.getAnalogOutputs().getItems().forEach((k, v) -> {
            analogOutputChangeHandler.update(outstationChangeSet, v, eventMode);
        });

        database.getCounters().getItems().forEach((k, v) -> {
            counterChangeHandler.update(outstationChangeSet, v, eventMode);
        });

        database.getDigitalInputs().getItems().forEach((k, v) -> {
            digitalInputChangeHandler.update(outstationChangeSet, v, eventMode);
        });

        database.getDigitalOutputs().getItems().forEach((k, v) -> {
            digitalOutputChangeHandler.update(outstationChangeSet, v, eventMode);
        });

        database.getDoubleDigitals().getItems().forEach((k, v) -> {
            doubleDigitalChangeHandler.update(outstationChangeSet, v, eventMode);
        });

        database.getFrozenCounters().getItems().forEach((k, v) -> {
            frozenCounterChangeHandler.update(outstationChangeSet, v, eventMode);
        });

        outstation.apply(outstationChangeSet);

    }

    abstract class BaseChangeHandler<T extends DnpVariable> implements PropertyChangeListener {

        private final Outstation outstation;
        private final OutstationChangeSet outstationChangeSet;

        public BaseChangeHandler(Outstation outstation, OutstationChangeSet outstationChangeSet) {
            this.outstation = outstation;
            this.outstationChangeSet = outstationChangeSet;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            update(outstationChangeSet, (T) evt.getSource(), EventMode.Detect);
            outstation.apply(outstationChangeSet);
        }

        abstract void update(OutstationChangeSet changeSet, T t, EventMode mode);

    }

    class AnalogInputChangeHandler extends BaseChangeHandler<DnpVariable.AnalogInput> {

        public AnalogInputChangeHandler(Outstation outstation, OutstationChangeSet outstationChangeSet) {
            super(outstation, outstationChangeSet);
        }

        @Override
        protected void update(OutstationChangeSet changeSet, DnpVariable.AnalogInput variable, EventMode mode) {
            AnalogInput measurement = new AnalogInput(variable.getValue(), (byte) variable.getQuality().toType(), variable.getTimeMillis());
            changeSet.update(measurement, variable.getIndex(), mode);
        }

    }

    class AnalogOutputChangeHandler extends BaseChangeHandler<DnpVariable.AnalogOutput> {

        public AnalogOutputChangeHandler(Outstation outstation, OutstationChangeSet outstationChangeSet) {
            super(outstation, outstationChangeSet);
        }

        @Override
        protected void update(OutstationChangeSet changeSet, DnpVariable.AnalogOutput variable, EventMode mode) {
            AnalogOutputStatus measurement = new AnalogOutputStatus(variable.getValue(), (byte) variable.getQuality().toType(), variable.getTimeMillis());
            changeSet.update(measurement, variable.getIndex(), mode);
        }

    }

    class CounterChangeHandler extends BaseChangeHandler<DnpVariable.Counter> {

        public CounterChangeHandler(Outstation outstation, OutstationChangeSet outstationChangeSet) {
            super(outstation, outstationChangeSet);
        }

        @Override
        protected void update(OutstationChangeSet changeSet, DnpVariable.Counter variable, EventMode mode) {

            Counter measurement = new Counter(variable.getValue(), (byte) variable.getQuality().toType(), variable.getTimeMillis());
            changeSet.update(measurement, variable.getIndex(), mode);

        }

    }

    class FrozenCounterChangeHandler extends BaseChangeHandler<DnpVariable.FrozenCounter> {

        public FrozenCounterChangeHandler(Outstation outstation, OutstationChangeSet outstationChangeSet) {
            super(outstation, outstationChangeSet);
        }

        @Override
        protected void update(OutstationChangeSet changeSet, DnpVariable.FrozenCounter variable, EventMode mode) {

            FrozenCounter measurement = new FrozenCounter(variable.getValue(), (byte) variable.getQuality().toType(), variable.getTimeMillis());
            changeSet.update(measurement, variable.getIndex(), mode);

        }

    }

    class DigitalInputChangeHandler extends BaseChangeHandler<DnpVariable.DigitalInput> {

        public DigitalInputChangeHandler(Outstation outstation, OutstationChangeSet outstationChangeSet) {
            super(outstation, outstationChangeSet);
        }

        @Override
        protected void update(OutstationChangeSet changeSet, DnpVariable.DigitalInput variable, EventMode mode) {

            BinaryInput measurement = new BinaryInput(variable.getValue(), (byte) variable.getQuality().toType(), variable.getTimeMillis());
            changeSet.update(measurement, variable.getIndex(), mode);

        }

    }

    class DigitalOutputChangeHandler extends BaseChangeHandler<DnpVariable.DigitalOutput> {

        public DigitalOutputChangeHandler(Outstation outstation, OutstationChangeSet outstationChangeSet) {
            super(outstation, outstationChangeSet);
        }

        @Override
        protected void update(OutstationChangeSet changeSet, DnpVariable.DigitalOutput variable, EventMode mode) {

            BinaryOutputStatus measurement = new BinaryOutputStatus(variable.getValue(), (byte) variable.getQuality().toType(), variable.getTimeMillis());
            changeSet.update(measurement, variable.getIndex(), mode);

        }

    }

    class DoubleDigitalChangeHandler extends BaseChangeHandler<DnpVariable.DoubleDigital> {

        public DoubleDigitalChangeHandler(Outstation outstation, OutstationChangeSet outstationChangeSet) {
            super(outstation, outstationChangeSet);
        }

        @Override
        protected void update(OutstationChangeSet changeSet, DnpVariable.DoubleDigital variable, EventMode mode) {

            DoubleBitBinaryInput measurement = new DoubleBitBinaryInput(variable.getValue(), (byte) variable.getQuality().toType(), variable.getTimeMillis());
            changeSet.update(measurement, variable.getIndex(), mode);

        }
    }
}
