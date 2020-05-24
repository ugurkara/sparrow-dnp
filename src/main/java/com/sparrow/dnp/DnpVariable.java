/* 
 * Copyright 2020 KR INDUSTRIAL IT.
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



import com.automatak.dnp3.AnalogOutputStatus;
import com.automatak.dnp3.BinaryInput;
import com.automatak.dnp3.BinaryOutputStatus;
import com.automatak.dnp3.DoubleBitBinaryInput;
import com.automatak.dnp3.Measurement;
import com.automatak.dnp3.enums.AnalogOutputStatusQuality;
import com.automatak.dnp3.enums.AnalogQuality;
import com.automatak.dnp3.enums.BinaryOutputStatusQuality;
import com.automatak.dnp3.enums.BinaryQuality;
import com.automatak.dnp3.enums.CounterQuality;
import com.automatak.dnp3.enums.DoubleBit;
import com.automatak.dnp3.enums.DoubleBitBinaryQuality;
import com.automatak.dnp3.enums.FrozenCounterQuality;
import com.sparrow.dnp.config.VariableConfig;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;



/**

 *

 *

 *

 * @author ugurkara

 *

 */

public abstract class DnpVariable<T, S extends Measurement> {



    private T value;

    private final int index;

    private long time = System.currentTimeMillis();



    public DnpVariable(int index, T value) {

        this.value = value;

        this.index = index;

    }



    public abstract Enum getQuality();



    public T getValue() {

        return value;

    }



    public void setValue(T newValue, long l) {

        T oldValue = this.value;

        this.value = newValue;

        this.time = l;

        this.pcs.firePropertyChange(new PropertyChangeEvent(this, "value", oldValue, newValue));

    }



    public void setValue(T newValue) {

        setValue(newValue, System.currentTimeMillis());

    }



    abstract void setObject(S value);



    public Long getTimeMillis() {

        return time;

    }



    //Only for test purpose. It will be removed.

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YY hh:mm:ss.ms");

    private final Date date = new Date();



    public String getTime() {

        date.setTime(getTimeMillis());

        return dateFormat.format(date);

    }



    public Integer getIndex() {

        return index;

    }



    private final PropertyChangeSupport pcs = new PropertyChangeSupport(DnpVariable.this);



    public void addPropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.addPropertyChangeListener(listener);

    }



    public void removePropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.removePropertyChangeListener(listener);

    }



    /*

    

    Analog Input Variable

    

     */

    public static class AnalogInput extends DnpVariable<Double, com.automatak.dnp3.AnalogInput> {



        private AnalogQuality quality = AnalogQuality.RESTART;



        public AnalogInput(int index, Double value) {

            super(index, value);

        }



        public AnalogInput(int index) {

            super(index, 0.0);

        }



        AnalogInput(int index, com.automatak.dnp3.AnalogInput value) {

            super(index, value.value);

            super.time = value.timestamp;

            quality = AnalogQuality.fromType(value.quality);

        }



        @Override

        public AnalogQuality getQuality() {

            return quality;

        }



        @Override

        void setObject(com.automatak.dnp3.AnalogInput value) {

            setValue(value.value, value.timestamp);

            quality = AnalogQuality.fromType(value.quality);

        }



        static AnalogInput valueOf(VariableConfig.AnalogInputConfig config) {

            AnalogInput v = new AnalogInput(config.getIndex());

            return v;

        }

    }



    /*

    

    Analog Output Variable

    

     */

    public static class AnalogOutput extends DnpVariable<Double, AnalogOutputStatus> {



        private AnalogOutputStatusQuality quality = AnalogOutputStatusQuality.RESTART;



        private final boolean selectionRequired;



        private boolean selected = false;



        public AnalogOutput(int index, Double value, boolean selection) {

            super(index, value);

            this.selectionRequired = selection;

        }



        public AnalogOutput(int index, boolean selection) {

            super(index, 0.0);

            this.selectionRequired = selection;

        }



        AnalogOutput(int index, AnalogOutputStatus value) {

            super(index, value.value);

            super.time = value.timestamp;

            quality = AnalogOutputStatusQuality.fromType(value.quality);

            this.selectionRequired = false;

        }



        @Override

        public AnalogOutputStatusQuality getQuality() {

            return quality;

        }



        @Override

        void setObject(AnalogOutputStatus value) {

            setValue(value.value, value.timestamp);

            quality = AnalogOutputStatusQuality.fromType(value.quality);

        }



        static AnalogOutput valueOf(VariableConfig.AnalogOutputConfig config) {

            AnalogOutput v = new AnalogOutput(config.getIndex(), config.isSelectionRequired());

            return v;

        }



        

        /*

        Can be interface?

        */

        public boolean isSelectionRequired() {

            return selectionRequired;

        }



        public boolean isSelected() {

            return selected;

        }



        public void select() {

            this.selected = true;

        }



        public void unselect() {

            this.selected = false;

        }

        /*

        ----------------------------

        */



    }



    /*

    

    Digital Input Variable

    

     */

    public static class DigitalInput extends DnpVariable<Boolean, BinaryInput> {



        private BinaryQuality quality = BinaryQuality.RESTART;



        public DigitalInput(int index, Boolean value) {

            super(index, value);

        }



        public DigitalInput(int index) {

            super(index, false);

        }



        DigitalInput(int index, BinaryInput value) {

            super(index, value.value);

            super.time = value.timestamp;

            quality = BinaryQuality.fromType(value.quality);

        }



        @Override

        public BinaryQuality getQuality() {

            return quality;

        }



        @Override

        void setObject(BinaryInput value) {

            setValue(value.value, value.timestamp);

            quality = BinaryQuality.fromType(value.quality);

        }



        static DigitalInput valueOf(VariableConfig.DigitalInputConfig config) {

            DigitalInput v = new DigitalInput(config.getIndex());

            return v;

        }



    }



    /*

    

    Digital Output Variable

    

     */

    public static class DigitalOutput extends DnpVariable<Boolean, BinaryOutputStatus> {



        private BinaryOutputStatusQuality quality = BinaryOutputStatusQuality.RESTART;



        private final boolean selectionRequired;

        private boolean selected = false;



        public DigitalOutput(int index, Boolean value, boolean selection) {

            super(index, value);

            this.selectionRequired = selection;

        }



        public DigitalOutput(int index, boolean selection) {

            super(index, false);

            this.selectionRequired = selection;

        }



        DigitalOutput(int index, BinaryOutputStatus value) {

            super(index, value.value);

            super.time = value.timestamp;

            quality = BinaryOutputStatusQuality.fromType(value.quality);

            this.selectionRequired = false;

        }



        @Override

        public BinaryOutputStatusQuality getQuality() {

            return quality;

        }



        @Override

        void setObject(BinaryOutputStatus value) {

            setValue(value.value, value.timestamp);

            quality = BinaryOutputStatusQuality.fromType(value.quality);

        }



        static DigitalOutput valueOf(VariableConfig.DigitalOutputConfig config) {

            DigitalOutput v = new DigitalOutput(config.getIndex(), config.isSelectionRequired());

            return v;

        }



/*

        Can be interface?

        */

        public boolean isSelectionRequired() {

            return selectionRequired;

        }



        public boolean isSelected() {

            return selected;

        }



        public void select() {

            this.selected = true;

        }



        public void unselect() {

            this.selected = false;

        }

        /*

        ----------------------------

        */



    }



    /*

    

    Counter  Variable

    

     */

    public static class Counter extends DnpVariable<Long, com.automatak.dnp3.Counter> {



        private CounterQuality quality = CounterQuality.RESTART;



        public Counter(int index, Long value) {

            super(index, value);

        }



        public Counter(int index) {

            super(index, 0L);

        }



        Counter(int index, com.automatak.dnp3.Counter value) {

            super(index, value.value);

            super.time = value.timestamp;

            quality = CounterQuality.fromType(value.quality);

        }



        @Override

        public CounterQuality getQuality() {

            return quality;

        }



        @Override

        void setObject(com.automatak.dnp3.Counter value) {

            setValue(value.value, value.timestamp);

            quality = CounterQuality.fromType(value.quality);

        }



        static Counter valueOf(VariableConfig.CounterConfig config) {

            Counter v = new Counter(config.getIndex());

            return v;

        }



    }



    /*

    

    FrozenCounter Variable

    

     */

    public static class FrozenCounter extends DnpVariable<Long, com.automatak.dnp3.FrozenCounter> {



        private FrozenCounterQuality quality = FrozenCounterQuality.RESTART;



        public FrozenCounter(int index, Long value) {

            super(index, value);

        }



        public FrozenCounter(int index) {

            super(index, 0L);

        }



        FrozenCounter(int index, com.automatak.dnp3.FrozenCounter value) {

            super(index, value.value);

            super.time = value.timestamp;

            quality = FrozenCounterQuality.fromType(value.quality);

        }



        @Override

        public FrozenCounterQuality getQuality() {

            return quality;

        }



        @Override

        void setObject(com.automatak.dnp3.FrozenCounter value) {

            setValue(value.value, value.timestamp);

            quality = FrozenCounterQuality.fromType(value.quality);

        }



        static FrozenCounter valueOf(VariableConfig.FrozenCounterConfig config) {

            FrozenCounter v = new FrozenCounter(config.getIndex());

            return v;

        }

    }



    /*

    

    DoubleDigital Variable

    

     */

    public static class DoubleDigital extends DnpVariable<DoubleBit, DoubleBitBinaryInput> {



        private DoubleBitBinaryQuality quality = DoubleBitBinaryQuality.RESTART;



        public DoubleDigital(int index, DoubleBit value) {

            super(index, value);

        }



        public DoubleDigital(int index) {

            super(index, DoubleBit.DETERMINED_OFF);

        }



        DoubleDigital(int index, DoubleBitBinaryInput value) {

            super(index, value.value);

            super.time = value.timestamp;

            quality = DoubleBitBinaryQuality.fromType(value.quality);

        }



        @Override

        public DoubleBitBinaryQuality getQuality() {

            return quality;

        }



        @Override

        void setObject(DoubleBitBinaryInput value) {

            setValue(value.value, value.timestamp);

            quality = DoubleBitBinaryQuality.fromType(value.quality);

        }



        static DoubleDigital valueOf(VariableConfig.DoubleDigitalConfig config) {

            DoubleDigital v = new DoubleDigital(config.getIndex());

            return v;

        }



    }



}

