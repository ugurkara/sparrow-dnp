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



import com.automatak.dnp3.LinkLayerConfig;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**

 *

 *

 *

 * @author ugurkara

 *

 */

@XmlType(propOrder = {

    "localAddress",

    "remoteAddress",

    "master",

    "useconfirms",

    "keepAliveTimeout",

    "responseTimeout",

    "numRetry",})

public class LinkConfig {



    private int localAddress = 4;

    private int remoteAddress = 3;

    private int numRetry = 0;



    private boolean useconfirms = false;

    private boolean master = false;



    private String keepAliveTimeout = "PT1M";

    private String responseTimeout = "PT1S";



    @XmlElement

    public String getKeepAliveTimeout() {

        return keepAliveTimeout;

    }



    public void setKeepAliveTimeout(String newValue) {

        String oldValue = this.keepAliveTimeout;

        this.keepAliveTimeout = newValue;

        this.pcs.firePropertyChange("keepAliveTimeout", oldValue, newValue);

    }



    public Duration keepAliveTimeout() {

        return Duration.parse(keepAliveTimeout);

    }



    @XmlElement

    public Integer getLocalAddress() {

        return localAddress;

    }



    public void setLocalAddress(Integer newValue) {

        Integer oldValue = this.localAddress;

        this.localAddress = newValue;

        this.pcs.firePropertyChange("localAddress", oldValue, newValue);

    }



    @XmlElement

    public Integer getNumRetry() {

        return numRetry;

    }



    public void setNumRetry(Integer newValue) {

        Integer oldValue = this.numRetry;

        this.numRetry = newValue;

        this.pcs.firePropertyChange("numRetry", oldValue, newValue);

    }



    @XmlElement

    public Boolean isMaster() {

        return master;

    }



    public void setMaster(Boolean newValue) {

        Boolean oldValue = this.master;

        this.master = newValue;

        this.pcs.firePropertyChange("master", oldValue, newValue);

    }



    @XmlElement

    public Integer getRemoteAddress() {

        return remoteAddress;

    }



    public void setRemoteAddress(Integer newValue) {

        Integer oldValue = this.remoteAddress;

        this.remoteAddress = newValue;

        this.pcs.firePropertyChange("remoteAddress", oldValue, newValue);

    }



    @XmlElement

    public String getResponseTimeout() {

        return responseTimeout;

    }



    public void setResponseTimeout(String newValue) {

        String oldValue = this.responseTimeout;

        this.responseTimeout = newValue;

        this.pcs.firePropertyChange("responseTimeout", oldValue, newValue);

    }



    public Duration responseTimeout() {

        return Duration.parse(responseTimeout);

    }



    @XmlElement

    public Boolean isUseconfirms() {

        return useconfirms;

    }



    public void setUseconfirms(Boolean newValue) {

        Boolean oldValue = this.useconfirms;

        this.useconfirms = newValue;

        this.pcs.firePropertyChange("useconfirms", oldValue, newValue);

    }



    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);



    public void addPropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.addPropertyChangeListener(listener);

    }



    public void removePropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.removePropertyChangeListener(listener);

    }



    public void build(LinkLayerConfig source) {



        source.localAddr = getLocalAddress();

        source.remoteAddr = getRemoteAddress();

        source.isMaster = isMaster();

        source.keepAliveTimeout = keepAliveTimeout();

        source.numRetry = getNumRetry();

        source.responseTimeout = responseTimeout();

        source.useConfirms = isUseconfirms();



    }



}

