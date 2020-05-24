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
import com.automatak.dnp3.OutstationStackConfig;
import com.automatak.dnp3.enums.IndexMode;
import java.beans.PropertyChangeListener;
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

    "allowUnsolicited",

    "indexMode",

    "maxControlsPerRequest",

    "maxRxFragSize",

    "maxTxFragSize",

    "selectTimeout",

    "solicitedConfirmTimeout",

    "unsolicitedConfirmTimeout"

})

public class SlaveDeviceConfig extends BaseDeviceConfig {



    private boolean allowUnsolicited = false;

    private IndexMode indexMode = IndexMode.Contiguous;

    private short maxControlsPerRequest = 16;

    private int maxRxFragSize = 2048;

    private int maxTxFragSize = 2048;

    private String selectTimeout = "PT10S";

    private String solicitedConfirmTimeout = "PT5S";

    private String unsolicitedConfirmTimeout = "PT5S";



    @Override

    protected String getDefaultName() {

        return "Outstation";

    }



    @XmlElement

    public Boolean isAllowUnsolicited() {

        return allowUnsolicited;

    }



    public void setAllowUnsolicited(Boolean newValue) {;

        Boolean oldValue = this.allowUnsolicited;

        this.allowUnsolicited = newValue;

        this.pcs.firePropertyChange("allowUnsolicited", oldValue, newValue);

    }



    @XmlElement

    public IndexMode getIndexMode() {

        return indexMode;

    }



    public IndexMode indexMode() {

        return indexMode;

    }



    public void setIndexMode(IndexMode newValue) {

        IndexMode oldValue = this.indexMode;

        this.indexMode = newValue;

        this.pcs.firePropertyChange("indexMode", oldValue, newValue);

    }



    @XmlElement

    public Short getMaxControlsPerRequest() {

        return maxControlsPerRequest;

    }



    public void setMaxControlsPerRequest(Short newValue) {

        Short oldValue = this.maxControlsPerRequest;

        this.maxControlsPerRequest = newValue;

        this.pcs.firePropertyChange("maxControlsPerRequest", oldValue, newValue);

    }



    @XmlElement

    public Integer getMaxRxFragSize() {

        return maxRxFragSize;

    }



    public void setMaxRxFragSize(Integer newValue) {

        Integer oldValue = this.maxRxFragSize;

        this.maxRxFragSize = newValue;

        this.pcs.firePropertyChange("maxRxFragSize", oldValue, newValue);

    }



    @XmlElement

    public Integer getMaxTxFragSize() {

        return maxTxFragSize;

    }



    public void setMaxTxFragSize(Integer newValue) {

        Integer oldValue = this.maxTxFragSize;

        this.maxTxFragSize = newValue;

        this.pcs.firePropertyChange("maxTxFragSize", oldValue, newValue);

    }



    @XmlElement

    public String getSelectTimeout() {

        return selectTimeout;

    }



    public Duration selectTimeoutDuration() {

        return Duration.parse(selectTimeout);

    }



    public void setSelectTimeout(String newValue) {

        String oldValue = this.selectTimeout;

        this.selectTimeout = newValue;

        this.pcs.firePropertyChange("selectTimeout", oldValue, newValue);

    }



    @XmlElement

    public String getSolicitedConfirmTimeout() {

        return solicitedConfirmTimeout;

    }



    public Duration solicitedConfirmTimeoutDuration() {

        return Duration.parse(solicitedConfirmTimeout);

    }



    public void setSolicitedConfirmTimeout(String newValue) {

        String oldValue = this.solicitedConfirmTimeout;

        this.solicitedConfirmTimeout = newValue;

        this.pcs.firePropertyChange("solicitedConfirmTimeout", oldValue, newValue);

    }



    @XmlElement

    public String getUnsolicitedConfirmTimeout() {

        return unsolicitedConfirmTimeout;

    }



    public void setUnsolicitedConfirmTimeout(String newValue) {

        String oldValue = this.unsolicitedConfirmTimeout;

        this.unsolicitedConfirmTimeout = newValue;

        this.pcs.firePropertyChange("unsolicitedConfirmTimeout", oldValue, newValue);

    }



    public Duration unsolicitedConfirmTimeoutDuration() {

        return Duration.parse(unsolicitedConfirmTimeout);

    }



//    private EventBufferConfig eventBufferConfigBuilder = new EventBufferConfig();

//

//    @XmlElement(name = "eventBuffer")

//    public EventBufferConfig getEventBufferConfig() {

//        return eventBufferConfigBuilder;

//    }



//    public void setEventBufferConfig(EventBufferConfig eventBufferConfig) {

//        this.eventBufferConfigBuilder = eventBufferConfig;

//    }



    @Override

    public void addPropertyChangeListener(PropertyChangeListener listener) {

        super.addPropertyChangeListener(listener); 

    }



    @Override

    public void removePropertyChangeListener(PropertyChangeListener listener) {

        super.removePropertyChangeListener(listener); 



    }



    public OutstationStackConfig build() {



        DatabaseConfig databaseConfig = DatabaseConfig.allValues(0);



        for (VariableUnitConfig memoryUnitConfig : getMemoryUnits()) {

            memoryUnitConfig.build(databaseConfig);



        }



        //Setup Event Buffer

        com.automatak.dnp3.EventBufferConfig eventBufferConfig = new com.automatak.dnp3.EventBufferConfig(

                getDigitalInputs().getEventBufferSize(),

                getDoubleDigitals().getEventBufferSize(),

                getAnalogInputs().getEventBufferSize(),

                getCounters().getEventBufferSize(),

                getFrozenCounters().getEventBufferSize(),

                getDigitalOutputs().getEventBufferSize(),

                getAnalogOutputs().getEventBufferSize());

        

        



        //Setup Link

        OutstationStackConfig result = new OutstationStackConfig(databaseConfig, eventBufferConfig);



        getLinkConfig().build(result.linkConfig);



        //Setup Outstation

        result.outstationConfig.allowUnsolicited = isAllowUnsolicited();

        result.outstationConfig.indexMode = indexMode();

        result.outstationConfig.maxControlsPerRequest = getMaxControlsPerRequest();

        result.outstationConfig.maxRxFragSize = getMaxRxFragSize();

        result.outstationConfig.maxTxFragSize = getMaxTxFragSize();

        result.outstationConfig.selectTimeout = selectTimeoutDuration();

        result.outstationConfig.solConfirmTimeout = solicitedConfirmTimeoutDuration();

        result.outstationConfig.unsolRetryTimeout = unsolicitedConfirmTimeoutDuration();

        return result;



    }



}

