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

import com.automatak.dnp3.enums.OperateType;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author KR INDUSTRIAL IT
 *
 *
 */
public class DigitalCommandConfig {

    private long count = 0;

    private Duration onTime = Duration.ofMillis(500);
    private Duration offTime = Duration.ofMillis(500);

//    private ControlCode controlCode = ControlCode.PULSE_ON;

    private OperateType operateType = OperateType.DirectOperate;

    @XmlElement
    public Long getCount() {
        return count;
    }

    public void setCount(Long newValue) {
        Long oldValue = this.count;
        this.count = newValue;
        pcs.firePropertyChange("count", oldValue, newValue);
    }

    @XmlElement
    public String getOnTime() {
        return onTime.toString();
    }

    public void setOnTime(String onTime) {
        onTime(Duration.parse(onTime));
    }

    @XmlElement
    public String getOffTime() {
        return offTime.toString();
    }

    public void setOffTime(String offTime) {
        offTime(Duration.parse(offTime));
    }
//
//    @XmlElement
//    public ControlCode getControlCode() {
//        return controlCode;
//    }
//
//    public void setControlCode(ControlCode newValue) {
//        ControlCode oldValue = this.controlCode;
//        this.controlCode = newValue;
//        pcs.firePropertyChange("controlCode", oldValue, newValue);
//    }

    public Duration offTime() {
        return offTime;
    }

    public void offTime(Duration newValue) {
        Duration oldValue = this.onTime;
        offTime = newValue;
        pcs.firePropertyChange("offTime", oldValue, newValue);
    }

    public Duration onTime() {
        return onTime;
    }

    public void onTime(Duration newValue) {
        Duration oldValue = this.onTime;
        onTime = newValue;
        pcs.firePropertyChange("onTime", oldValue, newValue);
    }

    @XmlElement
    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType newValue) {
        OperateType oldValue = this.operateType;
        this.operateType = newValue;
        pcs.firePropertyChange("operateType", oldValue, newValue);
    }

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(DigitalCommandConfig.this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

}
