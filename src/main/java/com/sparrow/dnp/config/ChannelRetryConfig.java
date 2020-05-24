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

import com.automatak.dnp3.ChannelRetry;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author KR INDUSTRIAL IT
 */
public class ChannelRetryConfig {

    private final ChannelRetry channelRetry = ChannelRetry.getDefault();
    private Duration maxRetryDelay = channelRetry.maxRetryDelay;
    private Duration minRetryDelay = channelRetry.minRetryDelay;
    private Duration reconnectDelay = channelRetry.reconnectDelay;

    public ChannelRetryConfig() {
    }

    @XmlElement
    public String getMaxRetryDelay() {
        return maxRetryDelay.toString();
    }

    public void setMaxRetryDelay(String newValue) {
        Duration oldValue = this.maxRetryDelay;
        this.maxRetryDelay = Duration.parse(newValue);
        this.pcs.firePropertyChange("maxRetryDelay", oldValue, this.maxRetryDelay);
    }

    @XmlElement
    public String getMinRetryDelay() {
        return minRetryDelay.toString();
    }

    public void setMinRetryDelay(String newValue) {
        Duration oldValue = this.minRetryDelay;
        this.minRetryDelay = Duration.parse(newValue);
        this.pcs.firePropertyChange("minRetryDelay", oldValue, this.minRetryDelay);
    }

    @XmlElement
    public String getReconnectDelay() {
        return reconnectDelay.toString();
    }

    public void setReconnectDelay(String newValue) {
        Duration oldValue = this.reconnectDelay;
        this.reconnectDelay = Duration.parse(newValue);
        this.pcs.firePropertyChange("reconnectDelay", oldValue, this.reconnectDelay);
    }

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(ChannelRetryConfig.this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public ChannelRetry build() {
        return new ChannelRetry(minRetryDelay, maxRetryDelay, reconnectDelay);
    }
}
