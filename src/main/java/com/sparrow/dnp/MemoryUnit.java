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

import com.sparrow.dnp.config.VariableType;
import com.automatak.dnp3.AnalogInput;
import com.automatak.dnp3.AnalogOutputStatus;
import com.automatak.dnp3.BinaryInput;
import com.automatak.dnp3.BinaryOutputStatus;
import com.automatak.dnp3.Counter;
import com.automatak.dnp3.DNPTime;
import com.automatak.dnp3.DoubleBitBinaryInput;
import com.automatak.dnp3.Flags;
import com.automatak.dnp3.FrozenCounter;
import com.automatak.dnp3.OutstationChangeSet;
import com.automatak.dnp3.enums.AnalogOutputStatusQuality;
import com.automatak.dnp3.enums.AnalogQuality;
import com.automatak.dnp3.enums.BinaryOutputStatusQuality;
import com.automatak.dnp3.enums.BinaryQuality;
import com.automatak.dnp3.enums.CounterQuality;
import com.automatak.dnp3.enums.DoubleBitBinaryQuality;
import com.automatak.dnp3.enums.EventMode;
import com.automatak.dnp3.enums.FrozenCounterQuality;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 *
 * @author ugurkara
 *
 */
public abstract class MemoryUnit<T extends DnpVariable> {

    private final HashMap<Integer, T> items = new HashMap<>();
    private static final Logger logger = Logger.getLogger(MemoryUnit.class.getName());

    protected abstract void updateEvent(OutstationChangeSet changeSet, T t, EventMode mode);

    public abstract VariableType getMeasurement();

    public MemoryUnit() {
    }

    public HashMap<Integer, T> getItems() {
        return items;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {

        items.values().forEach((T t) -> {
            t.addPropertyChangeListener(listener);
        });

    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {

        items.values().forEach((T t) -> {
            t.removePropertyChangeListener(listener);
        });

    }

    /*
    Analog Inputs
     */
    public static class AnalogInputs extends MemoryUnit<DnpVariable.AnalogInput> {

        public AnalogInputs() {
            super();
        }

        @Override
        public VariableType getMeasurement() {
            return VariableType.AnalogInput;
        }

        @Override
        protected void updateEvent(OutstationChangeSet cs, DnpVariable.AnalogInput variable, EventMode mode) {
            AnalogInput analogInput = new AnalogInput(variable.getValue(), new Flags((byte) AnalogQuality.ONLINE.toType()), new DNPTime(variable.getTimeMillis()));
            cs.update(analogInput, variable.getIndex(), mode);

        }

    }

    /*
    Analog Outputs
     */
    public static class AnalogOutputs extends MemoryUnit<DnpVariable.AnalogOutput> {

        @Override
        public VariableType getMeasurement() {
            return VariableType.AnalogOutput;
        }

        @Override
        protected void updateEvent(OutstationChangeSet cs, DnpVariable.AnalogOutput variable, EventMode mode) {

            AnalogOutputStatus analogOutputStatus = new AnalogOutputStatus(variable.getValue(), new Flags((byte) AnalogOutputStatusQuality.ONLINE.toType()), new DNPTime(variable.getTimeMillis()));
            cs.update(analogOutputStatus, variable.getIndex(), mode);

        }

    }

    /*

    Digital Inputs
     */
    public static class DigitalInputs extends MemoryUnit<DnpVariable.DigitalInput> {

        @Override
        public VariableType getMeasurement() {
            return VariableType.DigitalInput;
        }

        @Override
        protected void updateEvent(OutstationChangeSet cs, DnpVariable.DigitalInput variable, EventMode mode) {
            BinaryInput binaryInput = new BinaryInput(variable.getValue(), new Flags((byte) BinaryQuality.ONLINE.toType()),new DNPTime( variable.getTimeMillis()));
            cs.update(binaryInput, variable.getIndex(), mode);

        }

    }

    /*

    Digital Outputs
     */
    public static class DigitalOutputs extends MemoryUnit<DnpVariable.DigitalOutput> {

        @Override
        public VariableType getMeasurement() {
            return VariableType.DigitalOutput;
        }

        @Override
        protected void updateEvent(OutstationChangeSet cs, DnpVariable.DigitalOutput variable, EventMode mode) {

            BinaryOutputStatus binaryOutputStatus = new BinaryOutputStatus(variable.getValue(), new Flags((byte) BinaryOutputStatusQuality.ONLINE.toType()), new DNPTime(variable.getTimeMillis()));
            cs.update(binaryOutputStatus, variable.getIndex(), mode);

        }

    }

    /*
    Counters
     */
    public static class Counters extends MemoryUnit<DnpVariable.Counter> {

        @Override
        public VariableType getMeasurement() {
            return VariableType.Counter;
        }

        @Override
        protected void updateEvent(OutstationChangeSet cs, DnpVariable.Counter variable, EventMode mode) {

            Counter counter = new Counter(variable.getValue(), new Flags((byte) CounterQuality.ONLINE.toType()), new DNPTime(variable.getTimeMillis()));
            cs.update(counter, variable.getIndex(), mode);

        }

    }

    /*


    Frozen Counters
     */
    public static class FrozenCounters extends MemoryUnit<DnpVariable.FrozenCounter> {

        @Override
        public VariableType getMeasurement() {
            return VariableType.FrozenCounter;
        }

        @Override
        protected void updateEvent(OutstationChangeSet cs, DnpVariable.FrozenCounter variable, EventMode mode) {

            FrozenCounter counter = new FrozenCounter(variable.getValue(), new Flags( (byte) FrozenCounterQuality.ONLINE.toType()), new DNPTime(variable.getTimeMillis()));
            //cs.update(counter, variable.getIndex(), mode);
            cs.freezeCounter(variable.getIndex(), true, mode);
            

        }

    }

    /*

    Double Digitals
     */
    public static class DoubleDigitals extends MemoryUnit<DnpVariable.DoubleDigital> {

        @Override
        public VariableType getMeasurement() {
            return VariableType.DoubleDigital;
        }

        @Override
        protected void updateEvent(OutstationChangeSet cs, DnpVariable.DoubleDigital variable, EventMode mode) {

            DoubleBitBinaryInput doubleBitBinaryInput = new DoubleBitBinaryInput(variable.getValue(), new Flags((byte) DoubleBitBinaryQuality.ONLINE.ONLINE.toType()), new DNPTime(variable.getTimeMillis()));
            cs.update(doubleBitBinaryInput, variable.getIndex(), mode);

        }

    }

}
