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
package com.sparrow.dnp.config;



import com.automatak.dnp3.DatabaseConfig;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;


/**

 *

 *

 *

 * @author ugurkara

 *

 * @param <T>

 *

 */

@XmlSeeAlso({

    VariableUnitConfig.DigitalInputs.class,

    VariableUnitConfig.DigitalOutputs.class,

    VariableUnitConfig.AnalogInputs.class,

    VariableUnitConfig.AnalogOutputs.class,

    VariableUnitConfig.DoubleDigitals.class,

    VariableUnitConfig.Counters.class,

    VariableUnitConfig.FrozenCounters.class})



public abstract class VariableUnitConfig<T extends VariableConfig> {



    public abstract VariableType getType();



    public abstract ArrayList<T> getItems();



    public abstract void build(DatabaseConfig config);



    public abstract T newOne();



    private int eventBufferSize = 0;



    @XmlElement

    public Integer getEventBufferSize() {

        return eventBufferSize;

    }

    



    public void setEventBufferSize(Integer newValue) {

        Integer oldValue = this.eventBufferSize;

        this.eventBufferSize = newValue;

        this.pcs.firePropertyChange("eventBufferSize", oldValue, newValue);

    }

    

//    public Integer getSize() {

//        return getItems().size();

//    }

//    

//

//    public void setSize(Integer newValue) {

//        Integer oldValue = this.getSize();

//        

//        

//        this.pcs.firePropertyChange("eventBufferSize", oldValue, newValue);

//    }

    

    

    



    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);



    public void addPropertyChangeListener(PropertyChangeListener listener) {

        pcs.addPropertyChangeListener(listener);

    }



    public void removePropertyChangeListener(PropertyChangeListener listener) {

        pcs.removePropertyChangeListener(listener);

    }



    public static class DigitalInputs extends VariableUnitConfig<VariableConfig.DigitalInputConfig> {



        @XmlElement(name = "digitalInput")

        private final ArrayList<VariableConfig.DigitalInputConfig> items = new ArrayList();



        @Override

        public ArrayList<VariableConfig.DigitalInputConfig> getItems() {

            return items;

        }



        @Override

        public VariableType getType() {

            return VariableType.DigitalInput;

        }



        @Override

        public void build(DatabaseConfig config) {

            getItems().forEach((VariableConfig.DigitalInputConfig t) -> {

                config.binary.add(t.getEventConfig());

            });

        }



        @Override

        public VariableConfig.DigitalInputConfig newOne() {

            var v = new VariableConfig.DigitalInputConfig();

            getItems().add(v);

            return v;

        }



    }



    public static class DigitalOutputs extends VariableUnitConfig<VariableConfig.DigitalOutputConfig> {



        @XmlElement(name = "digitalOutput")

        private final ArrayList<VariableConfig.DigitalOutputConfig> items = new ArrayList();



        @Override

        public ArrayList<VariableConfig.DigitalOutputConfig> getItems() {

            return items;

        }



        @Override

        public VariableType getType() {

            return VariableType.DigitalOutput;

        }



        @Override

        public void build(DatabaseConfig config) {

            getItems().forEach((VariableConfig.DigitalOutputConfig t) -> {

                config.boStatus.add(t.getEventConfig());

            });

        }



        @Override

        public VariableConfig.DigitalOutputConfig newOne() {

            var v = new VariableConfig.DigitalOutputConfig();

            getItems().add(v);

            return v;

        }

    }



    public static class AnalogInputs extends VariableUnitConfig<VariableConfig.AnalogInputConfig> {



        @XmlElement(name = "analogInput")

        private final ArrayList<VariableConfig.AnalogInputConfig> items = new ArrayList();



        @Override

        public ArrayList<VariableConfig.AnalogInputConfig> getItems() {

            return items;

        }



        @Override

        public VariableType getType() {

            return VariableType.AnalogInput;

        }



        @Override

        public void build(DatabaseConfig config) {

            getItems().forEach((VariableConfig.AnalogInputConfig t) -> {

                config.analog.add(t.getEventConfig());

            });

        }



        @Override

        public VariableConfig.AnalogInputConfig newOne() {

            var v = new VariableConfig.AnalogInputConfig();

            getItems().add(v);

            return v;

        }



    }



    public static class AnalogOutputs extends VariableUnitConfig<VariableConfig.AnalogOutputConfig> {



        @XmlElement(name = "analogOutput")

        private final ArrayList<VariableConfig.AnalogOutputConfig> items = new ArrayList();



        @Override

        public ArrayList<VariableConfig.AnalogOutputConfig> getItems() {

            return items;

        }



        @Override

        public VariableType getType() {

            return VariableType.AnalogOutput;

        }



        @Override

        public void build(DatabaseConfig config) {

            getItems().forEach((VariableConfig.AnalogOutputConfig t) -> {

                config.aoStatus.add(t.getEventConfig());

            });

        }



        @Override

        public VariableConfig.AnalogOutputConfig newOne() {

            var v = new VariableConfig.AnalogOutputConfig();

            getItems().add(v);

            return v;

        }



    }



    public static class Counters extends VariableUnitConfig<VariableConfig.CounterConfig> {



        @XmlElement(name = "counter")

        private final ArrayList<VariableConfig.CounterConfig> items = new ArrayList();



        @Override

        public ArrayList<VariableConfig.CounterConfig> getItems() {

            return items;

        }



        @Override

        public VariableType getType() {

            return VariableType.Counter;

        }



        @Override

        public void build(DatabaseConfig config) {

            getItems().forEach((VariableConfig.CounterConfig t) -> {

                config.counter.add(t.getEventConfig());

            });

        }



        @Override

        public VariableConfig.CounterConfig newOne() {

            var v = new VariableConfig.CounterConfig();

            getItems().add(v);

            return v;

        }



    }



    public static class DoubleDigitals extends VariableUnitConfig<VariableConfig.DoubleDigitalConfig> {



        @XmlElement(name = "doubleDigital")

        private final ArrayList<VariableConfig.DoubleDigitalConfig> items = new ArrayList();



        @Override

        public ArrayList<VariableConfig.DoubleDigitalConfig> getItems() {

            return items;

        }



        @Override

        public VariableType getType() {

            return VariableType.DoubleDigital;

        }



        @Override

        public void build(DatabaseConfig config) {

            getItems().forEach((VariableConfig.DoubleDigitalConfig t) -> {

                config.doubleBinary.add(t.getEventConfig());

            });

        }



        @Override

        public VariableConfig.DoubleDigitalConfig newOne() {

            var v = new VariableConfig.DoubleDigitalConfig();

            getItems().add(v);

            return v;

        }



    }



    public static class FrozenCounters extends VariableUnitConfig<VariableConfig.FrozenCounterConfig> {



        @XmlElement(name = "frozenCounter")

        private final ArrayList<VariableConfig.FrozenCounterConfig> items = new ArrayList();



        @Override

        public ArrayList<VariableConfig.FrozenCounterConfig> getItems() {

            return items;

        }



        @Override

        public VariableType getType() {

            return VariableType.FrozenCounter;



        }



        @Override

        public void build(DatabaseConfig config) {



            getItems().forEach((VariableConfig.FrozenCounterConfig t) -> {

                config.frozenCounter.add(t.getEventConfig());

            });

        }



        @Override

        public VariableConfig.FrozenCounterConfig newOne() {

            var v = new VariableConfig.FrozenCounterConfig();

            getItems().add(v);

            return v;

        }



    }



}

