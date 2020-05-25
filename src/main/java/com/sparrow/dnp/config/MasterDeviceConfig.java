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

import com.automatak.dnp3.MasterStackConfig;
import com.automatak.dnp3.enums.IndexQualifierMode;
import com.automatak.dnp3.enums.TimeSyncMode;
import java.time.Duration;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;

public class MasterDeviceConfig extends BaseDeviceConfig {

    private final MasterStackConfig configDefault = new MasterStackConfig();

    private IndexQualifierMode controlQualifierMode = configDefault.master.controlQualifierMode;

    private boolean disableUnsolOnStartup = configDefault.master.disableUnsolOnStartup;
    private boolean ignoreRestartIIN = configDefault.master.ignoreRestartIIN;
    private boolean integrityOnEventOverflowIIN = configDefault.master.integrityOnEventOverflowIIN;

    private int maxRxFragSize = configDefault.master.maxRxFragSize;
    private int maxTxFragSize = configDefault.master.maxTxFragSize;

    private final ClassFieldConfig startupIntegrityClassMask = ClassFieldConfig.all();
    private final ClassFieldConfig eventScanOnEventsAvailableClassMask = new ClassFieldConfig();
    private final ClassFieldConfig unsolClassMask = ClassFieldConfig.allEvent();

    private String taskRetryPeriod = configDefault.master.taskRetryPeriod.toString();
    private String taskStartTimeout = configDefault.master.taskStartTimeout.toString();
    private String responseTimeout = configDefault.master.responseTimeout.toString();

    private TimeSyncMode timeSyncMode = configDefault.master.timeSyncMode;

    public MasterDeviceConfig() {
        getLinkConfig().setMaster(true);
    }

    @Override
    protected String getDefaultName() {
        return "Master";
    }

    @XmlElement
    public IndexQualifierMode getControlQualifierMode() {
        return controlQualifierMode;
    }

    public void setControlQualifierMode(IndexQualifierMode newValue) {
        IndexQualifierMode oldValue = this.controlQualifierMode;
        this.controlQualifierMode = newValue;
        this.pcs.firePropertyChange("controlQualifierMode", oldValue, newValue);

    }

    @XmlElement
    public ClassFieldConfig getEventScanOnEventsAvailableClassMask() {
        return eventScanOnEventsAvailableClassMask;
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
    public String getResponseTimeout() {
        return responseTimeout;
    }

    public void setResponseTimeout(String newValue) {
        String oldValue = this.responseTimeout;
        this.responseTimeout = newValue;
        this.pcs.firePropertyChange("responseTimeout", oldValue, newValue);
    }

    @XmlElement
    public ClassFieldConfig getStartupIntegrityClassMask() {
        return startupIntegrityClassMask;
    }

    @XmlElement
    public String getTaskRetryPeriod() {
        return taskRetryPeriod;
    }

    public void setTaskRetryPeriod(String newValue) {
        String oldValue = this.taskRetryPeriod;
        this.taskRetryPeriod = newValue;
        this.pcs.firePropertyChange("taskRetryPeriod", oldValue, newValue);
    }

    @XmlElement
    public String getTaskStartTimeout() {
        return taskStartTimeout;
    }

    public void setTaskStartTimeout(String newValue) {
        String oldValue = this.taskStartTimeout;
        this.taskStartTimeout = newValue;
        this.pcs.firePropertyChange("taskStartTimeout", oldValue, newValue);
    }

    @XmlElement
    public TimeSyncMode getTimeSyncMode() {
        return timeSyncMode;
    }

    public void setTimeSyncMode(TimeSyncMode newValue) {
        TimeSyncMode oldValue = this.timeSyncMode;
        this.timeSyncMode = newValue;
        this.pcs.firePropertyChange("timeSyncMode", oldValue, newValue);
    }

    @XmlElement
    public ClassFieldConfig getUnsolClassMask() {
        return unsolClassMask;
    }

    @XmlElement
    public Boolean isDisableUnsolOnStartup() {
        return disableUnsolOnStartup;
    }

    public void setDisableUnsolOnStartup(Boolean newValue) {
        Boolean oldValue = this.disableUnsolOnStartup;
        this.disableUnsolOnStartup = newValue;
        this.pcs.firePropertyChange("disableUnsolOnStartup", oldValue, newValue);
    }

    @XmlElement
    public Boolean isIgnoreRestartIIN() {
        return ignoreRestartIIN;
    }

    public void setIgnoreRestartIIN(Boolean newValue) {
        Boolean oldValue = this.ignoreRestartIIN;
        this.ignoreRestartIIN = newValue;
        this.pcs.firePropertyChange("ignoreRestartIIN", oldValue, newValue);
    }

    @XmlElement
    public Boolean isIntegrityOnEventOverflowIIN() {
        return integrityOnEventOverflowIIN;
    }

    public void setIntegrityOnEventOverflowIIN(Boolean newValue) {
        Boolean oldValue = this.integrityOnEventOverflowIIN;
        this.integrityOnEventOverflowIIN = newValue;
        this.pcs.firePropertyChange("integrityOnEventOverflowIIN", oldValue, newValue);
    }

    public Duration responseTimeout() {
        return Duration.parse(this.responseTimeout);
    }

    public Duration taskRetryPeriod() {
        return Duration.parse(this.taskRetryPeriod);
    }

    public Duration taskStartTimeout() {
        return Duration.parse(this.taskStartTimeout);
    }

    @XmlElement(name = "scan")
    private final ArrayList<ScanConfig> scanConfigs = new ArrayList<>();

    public ArrayList<ScanConfig> getScanConfigs() {
        return scanConfigs;
    }

    public MasterStackConfig build() {
        MasterStackConfig result = new MasterStackConfig();
        getLinkConfig().build(result.link);
        
        result.master.controlQualifierMode = getControlQualifierMode();
        result.master.disableUnsolOnStartup = isDisableUnsolOnStartup();
        result.master.eventScanOnEventsAvailableClassMask = getEventScanOnEventsAvailableClassMask().build();
        result.master.ignoreRestartIIN = isIgnoreRestartIIN();
        result.master.integrityOnEventOverflowIIN = isIntegrityOnEventOverflowIIN();
        result.master.maxRxFragSize = getMaxRxFragSize();
        result.master.maxTxFragSize = getMaxTxFragSize();
        result.master.responseTimeout = responseTimeout();
        result.master.startupIntegrityClassMask = getStartupIntegrityClassMask().build();
        result.master.taskRetryPeriod = taskRetryPeriod();
        result.master.taskStartTimeout = taskStartTimeout();
        result.master.timeSyncMode = getTimeSyncMode();
        result.master.unsolClassMask = getUnsolClassMask().build();

        return result;

    }

    public static class ScanConfig {

        private String duration = "PT10M";

        @XmlElement
        public String getDuration() {
            return duration;

        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public Duration duration() {
            return Duration.parse(duration);
        }

        public void duration(Duration duration) {
            this.duration = duration.toString();
        }

        @XmlElement(name = "header")
        private final ArrayList<HeaderConfig> headerConfigs = new ArrayList<>();

        public ArrayList<HeaderConfig> getHeaderConfigs() {
            return headerConfigs;

        }
    }
    public static class HeaderConfig {

    }
}
