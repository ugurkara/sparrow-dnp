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

import com.automatak.dnp3.DatabaseConfig;

import com.automatak.dnp3.enums.EventAnalogOutputStatusVariation;
import com.automatak.dnp3.enums.EventAnalogVariation;
import com.automatak.dnp3.enums.EventBinaryOutputStatusVariation;
import com.automatak.dnp3.enums.EventBinaryVariation;
import com.automatak.dnp3.enums.EventCounterVariation;
import com.automatak.dnp3.enums.EventDoubleBinaryVariation;
import com.automatak.dnp3.enums.EventFrozenCounterVariation;
import com.automatak.dnp3.enums.PointClass;
import com.automatak.dnp3.enums.StaticAnalogOutputStatusVariation;
import com.automatak.dnp3.enums.StaticAnalogVariation;
import com.automatak.dnp3.enums.StaticBinaryOutputStatusVariation;
import com.automatak.dnp3.enums.StaticBinaryVariation;
import com.automatak.dnp3.enums.StaticCounterVariation;
import com.automatak.dnp3.enums.StaticDoubleBinaryVariation;
import com.automatak.dnp3.enums.StaticFrozenCounterVariation;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author ugurkara
 * @param <T>
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
    public abstract T newOne();
    private int eventBufferSize = 0;
    private final T defaultValue;
    public abstract void build(DatabaseConfig config);

    public VariableUnitConfig(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @XmlElement
    public Integer getEventBufferSize() {
        return eventBufferSize;
    }

    public void setEventBufferSize(Integer newValue) {
        Integer oldValue = this.eventBufferSize;
        this.eventBufferSize = newValue;
        this.pcs.firePropertyChange("eventBufferSize", oldValue, newValue);
    }

    @XmlElement
    public Integer getSize() {
        return getItems().size();
    }

    public void setSize(Integer newValue) {
        Integer oldValue = this.getSize();
        getItems().clear();
        for (int i = 0; i < newValue; i++) {
            newOne();
        }
        this.pcs.firePropertyChange("eventBufferSize", oldValue, newValue);
    }

    public PointClass getDefaultPointClass() {
        return defaultValue.getPointClass();
    }

    public void setDefaultPointClass(PointClass newValue) {
        defaultValue.setPointClass(newValue);
    }

    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public static class DigitalInputs extends VariableUnitConfig<VariableConfig.DigitalInputConfig> {

        public DigitalInputs() {
            super(new VariableConfig.DigitalInputConfig());
        }

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
                config.binary.put(t.getIndex(),t.getEventConfig());
            });
        }

        
        @Override
        public VariableConfig.DigitalInputConfig newOne() {
            var v = new VariableConfig.DigitalInputConfig();
            v.setPointClass(getDefaultPointClass());
            v.setEventVariation(getDefaultEventVariation());
            v.setStaticVariation(getDefaultStaticVariation());
            getItems().add(v);
            return v;
        }

        @XmlElement
        public EventBinaryVariation getDefaultEventVariation() {
            return super.defaultValue.getEventVariation();
        }

        public void setDefaultEventVariation(EventBinaryVariation newValue) {

            EventBinaryVariation oldValue = super.defaultValue.getEventVariation();
            super.defaultValue.setEventVariation(newValue);
            this.pcs.firePropertyChange("defaultEventVariation", oldValue, newValue);

        }

        @XmlElement
        public StaticBinaryVariation getDefaultStaticVariation() {
            return super.defaultValue.getStaticVariation();
        }

        public void setDefaultStaticVariation(StaticBinaryVariation newValue) {

            StaticBinaryVariation oldValue = super.defaultValue.getStaticVariation();
            super.defaultValue.setStaticVariation(newValue);
            this.pcs.firePropertyChange("defaultStaticVariation", oldValue, newValue);

        }

    }

    public static class DigitalOutputs extends VariableUnitConfig<VariableConfig.DigitalOutputConfig> {

        public DigitalOutputs() {
            super(new VariableConfig.DigitalOutputConfig());
        }

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
                config.boStatus.put(t.getIndex(),t.getEventConfig());
            });

        }

        @Override
        public VariableConfig.DigitalOutputConfig newOne() {
            var v = new VariableConfig.DigitalOutputConfig();
            v.setPointClass(getDefaultPointClass());
            v.setEventVariation(getDefaultEventVariation());
            v.setStaticVariation(getDefaultStaticVariation());
            getItems().add(v);
            return v;

        }

        @XmlElement
        public EventBinaryOutputStatusVariation getDefaultEventVariation() {
            return super.defaultValue.getEventVariation();
        }

        public void setDefaultEventVariation(EventBinaryOutputStatusVariation newValue) {

            EventBinaryOutputStatusVariation oldValue = super.defaultValue.getEventVariation();
            super.defaultValue.setEventVariation(newValue);
            this.pcs.firePropertyChange("defaultEventVariation", oldValue, newValue);

        }

        @XmlElement
        public StaticBinaryOutputStatusVariation getDefaultStaticVariation() {
            return super.defaultValue.getStaticVariation();
        }

        public void setDefaultStaticVariation(StaticBinaryOutputStatusVariation newValue) {
            StaticBinaryOutputStatusVariation oldValue = super.defaultValue.getStaticVariation();
            super.defaultValue.setStaticVariation(newValue);
            this.pcs.firePropertyChange("defaultStaticVariation", oldValue, newValue);
        }

    }

    public static class AnalogInputs extends VariableUnitConfig<VariableConfig.AnalogInputConfig> {

        public AnalogInputs() {
            super(new VariableConfig.AnalogInputConfig());
        }

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
                config.analog.put(t.getIndex(),t.getEventConfig());
            });

        }

        @Override

        public VariableConfig.AnalogInputConfig newOne() {

            var v = new VariableConfig.AnalogInputConfig();
            v.setPointClass(getDefaultPointClass());
            v.setEventVariation(getDefaultEventVariation());
            v.setStaticVariation(getDefaultStaticVariation());
            getItems().add(v);
            return v;

        }

        @XmlElement
        public EventAnalogVariation getDefaultEventVariation() {
            return super.defaultValue.getEventVariation();
        }

        public void setDefaultEventVariation(EventAnalogVariation newValue) {

            EventAnalogVariation oldValue = super.defaultValue.getEventVariation();
            super.defaultValue.setEventVariation(newValue);
            this.pcs.firePropertyChange("defaultEventVariation", oldValue, newValue);

        }

        @XmlElement
        public StaticAnalogVariation getDefaultStaticVariation() {
            return super.defaultValue.getStaticVariation();
        }

        public void setDefaultStaticVariation(StaticAnalogVariation newValue) {

            StaticAnalogVariation oldValue = super.defaultValue.getStaticVariation();
            super.defaultValue.setStaticVariation(newValue);
            this.pcs.firePropertyChange("defaultStaticVariation", oldValue, newValue);

        }

    }

    public static class AnalogOutputs extends VariableUnitConfig<VariableConfig.AnalogOutputConfig> {

        public AnalogOutputs() {
            super(new VariableConfig.AnalogOutputConfig());
        }

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
                config.aoStatus.put(t.getIndex(),t.getEventConfig());
            });

        }

        @Override
        public VariableConfig.AnalogOutputConfig newOne() {

            var v = new VariableConfig.AnalogOutputConfig();
            v.setPointClass(getDefaultPointClass());
            v.setEventVariation(getDefaultEventVariation());
            v.setStaticVariation(getDefaultStaticVariation());
            getItems().add(v);
            return v;
        }

        @XmlElement
        public EventAnalogOutputStatusVariation getDefaultEventVariation() {
            return super.defaultValue.getEventVariation();
        }

        public void setDefaultEventVariation(EventAnalogOutputStatusVariation newValue) {
            EventAnalogOutputStatusVariation oldValue = super.defaultValue.getEventVariation();
            super.defaultValue.setEventVariation(newValue);
            this.pcs.firePropertyChange("defaultEventVariation", oldValue, newValue);
        }

        @XmlElement
        public StaticAnalogOutputStatusVariation getDefaultStaticVariation() {
            return super.defaultValue.getStaticVariation();
        }

        public void setDefaultStaticVariation(StaticAnalogOutputStatusVariation newValue) {
            StaticAnalogOutputStatusVariation oldValue = super.defaultValue.getStaticVariation();
            super.defaultValue.setStaticVariation(newValue);
            this.pcs.firePropertyChange("defaultStaticVariation", oldValue, newValue);
        }

    }

    public static class Counters extends VariableUnitConfig<VariableConfig.CounterConfig> {

        public Counters() {
            super(new VariableConfig.CounterConfig());
        }

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
                config.counter.put(t.getIndex(),t.getEventConfig());
            });

        }

        @Override
        public VariableConfig.CounterConfig newOne() {

            var v = new VariableConfig.CounterConfig();
            v.setPointClass(getDefaultPointClass());
            v.setEventVariation(getDefaultEventVariation());
            v.setStaticVariation(getDefaultStaticVariation());
            getItems().add(v);
            return v;

        }

        @XmlElement
        public EventCounterVariation getDefaultEventVariation() {
            return super.defaultValue.getEventVariation();
        }

        public void setDefaultEventVariation(EventCounterVariation newValue) {

            EventCounterVariation oldValue = super.defaultValue.getEventVariation();
            super.defaultValue.setEventVariation(newValue);
            this.pcs.firePropertyChange("defaultEventVariation", oldValue, newValue);

        }

        @XmlElement
        public StaticCounterVariation getDefaultStaticVariation() {
            return super.defaultValue.getStaticVariation();
        }

        public void setDefaultStaticVariation(StaticCounterVariation newValue) {

            StaticCounterVariation oldValue = super.defaultValue.getStaticVariation();
            super.defaultValue.setStaticVariation(newValue);
            this.pcs.firePropertyChange("defaultStaticVariation", oldValue, newValue);

        }

    }

    public static class DoubleDigitals extends VariableUnitConfig<VariableConfig.DoubleDigitalConfig> {

        public DoubleDigitals() {
            super(new VariableConfig.DoubleDigitalConfig());
        }

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
                config.doubleBinary.put(t.getIndex(),t.getEventConfig());
            });

        }

        @Override
        public VariableConfig.DoubleDigitalConfig newOne() {
            
            var v = new VariableConfig.DoubleDigitalConfig();
            v.setPointClass(getDefaultPointClass());
            v.setEventVariation(getDefaultEventVariation());
            v.setStaticVariation(getDefaultStaticVariation());
            getItems().add(v);
            return v;

        }

        @XmlElement
        public EventDoubleBinaryVariation getDefaultEventVariation() {
            return super.defaultValue.getEventVariation();
        }

        public void setDefaultEventVariation(EventDoubleBinaryVariation newValue) {

            EventDoubleBinaryVariation oldValue = super.defaultValue.getEventVariation();
            super.defaultValue.setEventVariation(newValue);
            this.pcs.firePropertyChange("defaultEventVariation", oldValue, newValue);

        }

        @XmlElement
        public StaticDoubleBinaryVariation getDefaultStaticVariation() {
            return super.defaultValue.getStaticVariation();
        }

        public void setDefaultStaticVariation(StaticDoubleBinaryVariation newValue) {

            StaticDoubleBinaryVariation oldValue = super.defaultValue.getStaticVariation();
            super.defaultValue.setStaticVariation(newValue);
            this.pcs.firePropertyChange("defaultStaticVariation", oldValue, newValue);

        }

    }

    public static class FrozenCounters extends VariableUnitConfig<VariableConfig.FrozenCounterConfig> {

        public FrozenCounters() {
            super(new VariableConfig.FrozenCounterConfig());
        }

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
                config.frozenCounter.put(t.getIndex(),t.getEventConfig());
            });

        }

        @Override
        public VariableConfig.FrozenCounterConfig newOne() {

            var v = new VariableConfig.FrozenCounterConfig();
            v.setPointClass(getDefaultPointClass());
            v.setEventVariation(getDefaultEventVariation());
            v.setStaticVariation(getDefaultStaticVariation());
            getItems().add(v);
            return v;

        }

        @XmlElement
        public EventFrozenCounterVariation getDefaultEventVariation() {
            return super.defaultValue.getEventVariation();
        }

        public void setDefaultEventVariation(EventFrozenCounterVariation newValue) {

            EventFrozenCounterVariation oldValue = super.defaultValue.getEventVariation();
            super.defaultValue.setEventVariation(newValue);
            this.pcs.firePropertyChange("defaultEventVariation", oldValue, newValue);

        }

        @XmlElement
        public StaticFrozenCounterVariation getDefaultStaticVariation() {
            return super.defaultValue.getStaticVariation();
        }

        public void setDefaultStaticVariation(StaticFrozenCounterVariation newValue) {

            StaticFrozenCounterVariation oldValue = super.defaultValue.getStaticVariation();
            super.defaultValue.setStaticVariation(newValue);
            this.pcs.firePropertyChange("defaultStaticVariation", oldValue, newValue);

        }

    }

}
