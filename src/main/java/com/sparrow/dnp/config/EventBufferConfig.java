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



import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.xml.bind.annotation.XmlElement;



/**

 *

 *

 *

 * @author ugurkara

 *

 *

 *

 *

 *

 *

 *

 *

 *

 *

 *

 * EventBuffer Config

 *

 */

@Deprecated

public class EventBufferConfig {



    private int digitalInputsSize = 0;

    private int digitalOutputsSize = 0;

    private int analogInputsSize = 0;

    private int analogOutputsSize = 0;

    private int frozenCountersSize = 0;

    private int doubleDigitalsSize = 0;

    private int countersSize = 0;



    @XmlElement

    public Integer getAnalogInputsSize() {

        return analogInputsSize;

    }



    public void setAnalogInputsSize(Integer newValue) {

        Integer oldValue = this.analogInputsSize;

        this.analogInputsSize = newValue;

        EventBufferConfig.this.pcs.firePropertyChange("analogInputsSize", oldValue, newValue);

    }



    @XmlElement

    public Integer getAnalogOutputsSize() {

        return analogOutputsSize;

    }



    public void setAnalogOutputsSize(Integer newValue) {

        Integer oldValue = this.analogOutputsSize;

        this.analogOutputsSize = newValue;

        EventBufferConfig.this.pcs.firePropertyChange("analogOutputsSize", oldValue, newValue);

    }



    @XmlElement

    public Integer getCountersSize() {

        return countersSize;

    }



    public void setCountersSize(Integer newValue) {

        Integer oldValue = this.countersSize;

        this.countersSize = newValue;

        EventBufferConfig.this.pcs.firePropertyChange("countersSize", oldValue, newValue);

    }



    @XmlElement

    public Integer getDigitalInputsSize() {

        return digitalInputsSize;

    }



    public void setDigitalInputsSize(Integer newValue) {

        Integer oldValue = this.digitalInputsSize;

        this.digitalInputsSize = newValue;

        EventBufferConfig.this.pcs.firePropertyChange("digitalInputsSize", oldValue, newValue);

    }



    @XmlElement

    public Integer getDigitalOutputsSize() {

        return digitalOutputsSize;

    }



    public void setDigitalOutputsSize(Integer newValue) {

        Integer oldValue = this.digitalOutputsSize;

        this.digitalOutputsSize = newValue;

        EventBufferConfig.this.pcs.firePropertyChange("digitalOutputsSize", oldValue, newValue);

    }



    @XmlElement

    public Integer getDoubleDigitalsSize() {

        return doubleDigitalsSize;

    }



    public void setDoubleDigitalsSize(Integer newValue) {

        Integer oldValue = this.doubleDigitalsSize;

        this.doubleDigitalsSize = newValue;

        EventBufferConfig.this.pcs.firePropertyChange("doubleDigitalsSize", oldValue, newValue);

    }



    @XmlElement

    public Integer getFrozenCountersSize() {

        return frozenCountersSize;

    }



    public void setFrozenCountersSize(Integer newValue) {

        Integer oldValue = this.frozenCountersSize;

        this.frozenCountersSize = newValue;

        EventBufferConfig.this.pcs.firePropertyChange("frozenCountersSize", oldValue, newValue);



    }



    private final PropertyChangeSupport pcs = new PropertyChangeSupport(EventBufferConfig.this);



    public void addPropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.addPropertyChangeListener(listener);

    }



    public void removePropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.removePropertyChangeListener(listener);

    }



}

