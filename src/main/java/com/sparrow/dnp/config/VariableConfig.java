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

import com.automatak.dnp3.AnalogConfig;
import com.automatak.dnp3.AnalogOutputStatusConfig;
import com.automatak.dnp3.BinaryConfig;
import com.automatak.dnp3.BinaryOutputStatusConfig;
import com.automatak.dnp3.DoubleBinaryConfig;
import com.automatak.dnp3.EventConfig;
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
import javax.xml.bind.annotation.XmlElement;

/**
 * @author ugurkara
 */
public abstract class VariableConfig<T extends EventConfig> {

    private final T eventConfig;

    private  int index=0;
    public VariableConfig(T eventConfig, int index) {
        this.eventConfig = eventConfig;
        this.index=index;
    }

    public T getEventConfig() {
        return eventConfig;
    }

    
    
    @XmlElement
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer newValue) {

        Integer oldValue = this.index;
        this.index = newValue;
        this.pcs.firePropertyChange("index", oldValue, newValue);

    }

    @XmlElement
    public PointClass getPointClass() {
        return eventConfig.clazz;
    }

    public void setPointClass(PointClass newValue) {

        PointClass oldValue = this.eventConfig.clazz;
        this.eventConfig.clazz = newValue;
        this.pcs.firePropertyChange("pointClass", oldValue, newValue);

    }

    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    /*
    Digital Input
     */
    public static class DigitalInputConfig extends VariableConfig<BinaryConfig> {

        private static int itemIndex = 0;

        public DigitalInputConfig() {
            this(itemIndex++);
        }

        public DigitalInputConfig(int index) {
            super(new BinaryConfig(),index);
        }

        @XmlElement
        public EventBinaryVariation getEventVariation() {
            return getEventConfig().eventVariation;
        }

        @XmlElement
        public StaticBinaryVariation getStaticVariation() {
            return getEventConfig().staticVariation;
        }

        public void setEventVariation(EventBinaryVariation newValue) {

            EventBinaryVariation oldValue = getEventConfig().eventVariation;
            this.getEventConfig().eventVariation = newValue;
            this.pcs.firePropertyChange("eventVariation", oldValue, newValue);

        }

        public void setStaticVariation(StaticBinaryVariation newValue) {

            StaticBinaryVariation oldValue = this.getEventConfig().staticVariation;
            this.getEventConfig().staticVariation = newValue;
            this.pcs.firePropertyChange("staticVariation", oldValue, newValue);

        }

    }

    /*
    Digital Output
     */
    public static class DigitalOutputConfig extends VariableConfig<BinaryOutputStatusConfig> {

        private static int itemIndex = 0;
        private boolean selectionRequired = false;
        private DigitalCommandConfig commandConfig = new DigitalCommandConfig();

        public DigitalOutputConfig() {
            this(itemIndex++);
        }

        public DigitalOutputConfig(int index) {
            super(new BinaryOutputStatusConfig(),index);
        }

        @XmlElement
        public EventBinaryOutputStatusVariation getEventVariation() {
            return getEventConfig().eventVariation;
        }

        @XmlElement
        public StaticBinaryOutputStatusVariation getStaticVariation() {
            return getEventConfig().staticVariation;
        }

        public void setEventVariation(EventBinaryOutputStatusVariation newValue) {

            EventBinaryOutputStatusVariation oldValue = this.getEventConfig().eventVariation;
            this.getEventConfig().eventVariation = newValue;
            this.pcs.firePropertyChange("eventVariation", oldValue, newValue);

        }

        public void setStaticVariation(StaticBinaryOutputStatusVariation newValue) {

            StaticBinaryOutputStatusVariation oldValue = this.getEventConfig().staticVariation;
            this.getEventConfig().staticVariation = newValue;
            this.pcs.firePropertyChange("staticVariation", oldValue, newValue);

        }

        @XmlElement
        public Boolean isSelectionRequired() {
            return selectionRequired;
        }

        public void setSelectionRequired(Boolean newValue) {

            Boolean oldValue = this.selectionRequired;
            this.selectionRequired = newValue;
            this.pcs.firePropertyChange("selectionRequired", oldValue, newValue);

        }

        public DigitalCommandConfig getCommandConfig() {
            return commandConfig;
        }

        @XmlElement
        public void setCommandConfig(DigitalCommandConfig commandConfig) {
            this.commandConfig = commandConfig;
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            super.addPropertyChangeListener(listener);
            this.commandConfig.addPropertyChangeListener(listener);
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {
            super.removePropertyChangeListener(listener);
            this.commandConfig.removePropertyChangeListener(listener);
        }

    }

    /*
    Analog Input
     */
    public static class AnalogInputConfig extends VariableConfig<AnalogConfig> {

        private static int itemIndex = 0;

        public AnalogInputConfig() {
            this(itemIndex++);
        }

        public AnalogInputConfig(int index) {
            super(new AnalogConfig(),index);
        }

        @XmlElement
        public EventAnalogVariation getEventVariation() {
            return getEventConfig().eventVariation;
        }

        @XmlElement
        public StaticAnalogVariation getStaticVariation() {
            return getEventConfig().staticVariation;
        }

        public void setEventVariation(EventAnalogVariation newValue) {
            EventAnalogVariation oldValue = this.getEventConfig().eventVariation;
            this.getEventConfig().eventVariation = newValue;
            this.pcs.firePropertyChange("eventVariation", oldValue, newValue);
        }

        public void setStaticVariation(StaticAnalogVariation newValue) {
            StaticAnalogVariation oldValue = this.getEventConfig().staticVariation;
            this.getEventConfig().staticVariation = newValue;
            this.pcs.firePropertyChange("staticVariation", oldValue, newValue);
        }

        @XmlElement
        public Double getDeadband() {
            return getEventConfig().deadband;
        }

        public void setDeadband(Double newValue) {
            Double oldValue = getEventConfig().deadband;
            getEventConfig().deadband = newValue;
            this.pcs.firePropertyChange("deadband", oldValue, newValue);
        }

        @Override
        public String toString() {

            StringBuilder SB = new StringBuilder();
            SB.append("[");
            SB.append(getIndex());
            SB.append("][");
            SB.append(getPointClass());
            SB.append("][");
            SB.append(getDeadband());
            SB.append("][");
            SB.append(getEventVariation());
            SB.append("][");
            SB.append(getStaticVariation());
            SB.append("]");
            return SB.toString();

        }

    }

    /*
    Analog Output
     */
    public static class AnalogOutputConfig extends VariableConfig<AnalogOutputStatusConfig> {

        private static int itemIndex = 0;

        private boolean selectionRequired = false;

        private AnalogCommandConfig commandConfig = new AnalogCommandConfig();

        public AnalogOutputConfig() {
            this(itemIndex++);
        }

        public AnalogOutputConfig(int index) {
            super(new AnalogOutputStatusConfig(),index);
        }

        @XmlElement
        public EventAnalogOutputStatusVariation getEventVariation() {
            return getEventConfig().eventVariation;
        }

        @XmlElement
        public StaticAnalogOutputStatusVariation getStaticVariation() {
            return getEventConfig().staticVariation;
        }

        public void setEventVariation(EventAnalogOutputStatusVariation newValue) {

            EventAnalogOutputStatusVariation oldValue = getEventConfig().eventVariation;
            getEventConfig().eventVariation = newValue;
            this.pcs.firePropertyChange("eventVariation", oldValue, newValue);

        }

        public void setStaticVariation(StaticAnalogOutputStatusVariation newValue) {

            StaticAnalogOutputStatusVariation oldValue = getEventConfig().staticVariation;
            getEventConfig().staticVariation = newValue;
            this.pcs.firePropertyChange("staticVariation", oldValue, newValue);

        }

        @XmlElement
        public Double getDeadband() {
            return getEventConfig().deadband;
        }

        public void setDeadband(Double newValue) {

            Double oldValue = getEventConfig().deadband;
            getEventConfig().deadband = newValue;
            this.pcs.firePropertyChange("deadband", oldValue, newValue);

        }

        @XmlElement
        public Boolean isSelectionRequired() {
            return selectionRequired;
        }

        public void setSelectionRequired(Boolean newValue) {

            Boolean oldValue = this.selectionRequired;
            this.selectionRequired = newValue;
            this.pcs.firePropertyChange("selectionRequired", oldValue, newValue);
        }

        @XmlElement
        public AnalogCommandConfig getCommandConfig() {
            return commandConfig;
        }

        public void setCommandConfig(AnalogCommandConfig commandConfig) {
            this.commandConfig = commandConfig;
        }

        @Override

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            super.addPropertyChangeListener(listener);
            this.commandConfig.addPropertyChangeListener(listener);
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {
            super.removePropertyChangeListener(listener);
            this.commandConfig.removePropertyChangeListener(listener);
        }

    }

    /*
    Counter
     */
    public static class CounterConfig extends VariableConfig<com.automatak.dnp3.CounterConfig> {

        private static int itemIndex = 0;

        public CounterConfig() {
            this(itemIndex++);
        }

        public CounterConfig(int index) {
            super(new com.automatak.dnp3.CounterConfig(),index);
        }

        @XmlElement
        public EventCounterVariation getEventVariation() {
            return getEventConfig().eventVariation;
        }

        @XmlElement
        public StaticCounterVariation getStaticVariation() {
            return getEventConfig().staticVariation;
        }

        public void setEventVariation(EventCounterVariation newValue) {

            EventCounterVariation oldValue = getEventConfig().eventVariation;
            getEventConfig().eventVariation = newValue;
            this.pcs.firePropertyChange("eventVariation", oldValue, newValue);

        }

        public void setStaticVariation(StaticCounterVariation newValue) {

            StaticCounterVariation oldValue = getEventConfig().staticVariation;
            getEventConfig().staticVariation = newValue;
            this.pcs.firePropertyChange("staticVariation", oldValue, newValue);
        }

        @XmlElement
        public Integer getDeadband() {
            return getEventConfig().deadband;
        }

        public void setDeadband(Integer newValue) {

            Integer oldValue = getEventConfig().deadband;
            getEventConfig().deadband = newValue;
            this.pcs.firePropertyChange("deadband", oldValue, newValue);

        }

    }

    /*


    Frozen Counter


     */
    public static class FrozenCounterConfig extends VariableConfig<com.automatak.dnp3.FrozenCounterConfig> {

        private static int itemIndex = 0;

        public FrozenCounterConfig() {
            this(itemIndex++);
        }

        public FrozenCounterConfig(int index) {
            super(new com.automatak.dnp3.FrozenCounterConfig(),index);
        }

        @XmlElement
        public EventFrozenCounterVariation getEventVariation() {
            return getEventConfig().eventVariation;
        }

        @XmlElement
        public StaticFrozenCounterVariation getStaticVariation() {
            return getEventConfig().staticVariation;
        }

        public void setEventVariation(EventFrozenCounterVariation newValue) {
            EventFrozenCounterVariation oldValue = getEventConfig().eventVariation;
            getEventConfig().eventVariation = newValue;
            this.pcs.firePropertyChange("eventVariation", oldValue, newValue);

        }

        public void setStaticVariation(StaticFrozenCounterVariation newValue) {

            StaticFrozenCounterVariation oldValue = getEventConfig().staticVariation;
            getEventConfig().staticVariation = newValue;
            this.pcs.firePropertyChange("staticVariation", oldValue, newValue);

        }

        @XmlElement
        public Integer getDeadband() {
            return getEventConfig().deadband;
        }

        public void setDeadband(Integer newValue) {

            Integer oldValue = getEventConfig().deadband;
            getEventConfig().deadband = newValue;
            this.pcs.firePropertyChange("deadband", oldValue, newValue);
        }

    }

    /*
    DoubleDigital
     */
    public static class DoubleDigitalConfig extends VariableConfig<DoubleBinaryConfig> {

        private static int itemIndex = 0;

        public DoubleDigitalConfig() {
            this(itemIndex++);
        }

        public DoubleDigitalConfig(int index) {
            super(new DoubleBinaryConfig(),index);
        }

        @XmlElement
        public EventDoubleBinaryVariation getEventVariation() {
            return getEventConfig().eventVariation;
        }

        @XmlElement
        public StaticDoubleBinaryVariation getStaticVariation() {
            return getEventConfig().staticVariation;
        }

        public void setEventVariation(EventDoubleBinaryVariation newValue) {
            EventDoubleBinaryVariation oldValue = getEventConfig().eventVariation;
            getEventConfig().eventVariation = newValue;
            this.pcs.firePropertyChange("eventVariation", oldValue, newValue);
        }

        public void setStaticVariation(StaticDoubleBinaryVariation newValue) {
            StaticDoubleBinaryVariation oldValue = getEventConfig().staticVariation;
            getEventConfig().staticVariation = newValue;
            this.pcs.firePropertyChange("staticVariation", oldValue, newValue);
        }
    }
}
