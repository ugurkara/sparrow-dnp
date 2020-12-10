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
package com.sparrow.dnp;
import com.automatak.dnp3.Channel;
import com.automatak.dnp3.ChannelListener;
import com.automatak.dnp3.DNP3Exception;
import com.automatak.dnp3.DNP3Manager;
import com.automatak.dnp3.Header;
import com.automatak.dnp3.LogHandler;
import com.automatak.dnp3.Master;
import com.automatak.dnp3.MasterStackConfig;
import com.automatak.dnp3.Outstation;
import com.automatak.dnp3.enums.EventMode;
import com.automatak.dnp3.impl.DNP3ManagerFactory;
import com.automatak.dnp3.mock.DefaultMasterApplication;
import com.automatak.dnp3.mock.PrintingLogHandler;
import com.sparrow.dnp.config.BaseChannelConfig;
import com.sparrow.dnp.config.MasterDeviceConfig;
import com.sparrow.dnp.config.SlaveDeviceConfig;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ugurkara
 */
public abstract class BaseConection {

    private final ArrayList<OutstationDevice> outstationDataModels = new ArrayList<>();
    private final ArrayList<OutstationChangeHandler> outstations = new ArrayList<>();
    private final ArrayList<MasterDevice> masterDataModels = new ArrayList<>();
    private final ArrayList<Master> masters = new ArrayList<>();
    
    private final static Logger logger = Logger.getLogger(BaseConection.class.getName());
    
    private boolean running = false;
    
    private final ChannelListenerAdapter listeners = new ChannelListenerAdapter();
    
    private final String name;

    public String getName() {
        return name;
    }

    
    
    
    
    protected ChannelListener getChannelListener() {
        return listeners;
    }

    public void addConnectionListener(ConnectionListener cl) {
        listeners.listeners.add(cl);
    }

    public void removeConnectionListener(ConnectionListener cl) {
        listeners.listeners.remove(cl);
    }

    public void updateStatistics() {
        
        for (int i = 0; i < masters.size(); i++) {
            Master master = masters.get(i);
            masterDataModels.get(i).getStatistics().update(master.getStatistics());
        }

        for (int i = 0; i < outstations.size(); i++) {
            Outstation outstation = outstations.get(i).getOutstation();
            outstationDataModels.get(i).getStatistics().update(outstation.getStatistics());
        }
    }

    private final BaseChannelConfig config;

    public BaseConection(BaseChannelConfig config) {
        this.config = config;
        this.name=config.getName();
    }

    protected void run() throws Exception {

        DNP3Manager manager = newManasger();

        Channel channel = newChannel(manager);

        setupMasters(channel);

        setupSlaves(channel);

        block();

        stopMasters();

        stopSlaves();

        channel.shutdown();

        manager.shutdown();

        listeners.fireStoped();

    }

    protected synchronized void block() {

        running = true;
        listeners.fireStarted();
        while (running) {
            try {
                wait();
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, "connection thread was interruppted.", ex);
            }
            logger.info("Connection bursting.");
            updateStatistics();
        }

    }

    public void start() {

        if (running) {
            return;
        }

        Thread thread = new Thread(() -> {
            try {
                run();
            } catch (Exception ex) {
                running = false;
            }
        });
        thread.start();

    }

    public synchronized void updateAll() {
        notify();
    }

    public synchronized void stop() {
        running = false;
        notify();
    }

    public boolean isRunning() {
        return running;
    }

    public ArrayList<OutstationDevice> getSlaves() {
        return outstationDataModels;

    }

    public ArrayList<MasterDevice> getMasters() {
        return masterDataModels;

    }

    protected abstract Channel newChannel(DNP3Manager manager) throws Exception;

    protected DNP3Manager newManasger() {

        return DNP3ManagerFactory.createManager(1, defaultLogHandler);

    }

    private LogHandler defaultLogHandler = PrintingLogHandler.getInstance();

    public void setDefaultLogHandler(LogHandler defaultLogHandler) {

        this.defaultLogHandler = defaultLogHandler;

    }

    public LogHandler getDefaultLogHandler() {

        return defaultLogHandler;

    }

    private void setupMasters(Channel channel) throws DNP3Exception {

        masterDataModels.clear();

        masters.clear();

        for (MasterDeviceConfig masterConfig : config.getMasters()) {

            MasterStackConfig masterStackConfig = masterConfig.build();
            MasterDevice masterDevice = MasterDevice.newDevice(masterConfig);

            Master master = channel.addMaster(
                    masterConfig.getName(),
                    masterDevice,
                    DefaultMasterApplication.getInstance(),
                    masterStackConfig);

            getMasters().add(masterDevice);
            masters.add(master);

            masterConfig.getScanConfigs().forEach((MasterDeviceConfig.ScanConfig scanConfig) -> {
                master.addPeriodicScan(scanConfig.duration(), Header.getIntegrity(),masterDevice);
            });

            master.enable();

        }

    }

    private void setupSlaves(Channel channel) throws DNP3Exception {

        outstationDataModels.clear();

        outstations.clear();

        for (SlaveDeviceConfig outstationConfig : config.getSlaves()) {
            
            

            OutstationDevice outstationDevice = OutstationDevice.newDevice(outstationConfig);
            getSlaves().add(outstationDevice);
            outstationDevice.getOutstationApplication().setZoneId(outstationConfig.getZoneOffset());
            Outstation outstation = channel.addOutstation(config.getName(),
                    outstationDevice.getDefaultCommandHandler(),
                    outstationDevice.getOutstationApplication(),
                    outstationDevice.getOutstationStackConfig());
            OutstationChangeHandler defaultChangeHandler = new OutstationChangeHandler(outstation);
            outstationDevice.getDatabase().getAnalogInputs().addPropertyChangeListener(defaultChangeHandler.getAnalogInputChangeHandler());
            //outstationDevice.getDatabase().getAnalogOutputs().addPropertyChangeListener(defaultChangeHandler.getAnalogOutputChangeHandler());
            outstationDevice.getDatabase().getCounters().addPropertyChangeListener(defaultChangeHandler.getCounterChangeHandler());
            outstationDevice.getDatabase().getDigitalInputs().addPropertyChangeListener(defaultChangeHandler.getDigitalInputChangeHandler());
            //outstationDevice.getDatabase().getDigitalOutputs().addPropertyChangeListener(defaultChangeHandler.getDigitalOutputChangeHandler());
            outstationDevice.getDatabase().getDoubleDigitals().addPropertyChangeListener(defaultChangeHandler.getDoubleDigitalChangeHandler());
            outstationDevice.getDatabase().getFrozenCounters().addPropertyChangeListener(defaultChangeHandler.getFrozenCounterChangeHandler());
            outstations.add(defaultChangeHandler);
            outstation.enable();

        }

    }

    private void stopMasters() {
        
        masters.forEach((Master t) -> {
            t.disable();
        });

        masters.clear();
        masterDataModels.clear();

    }

    private void stopSlaves() {

        for (int i = 0; i < getSlaves().size(); i++) {
            getSlaves().get(i).getDatabase().getAnalogInputs().removePropertyChangeListener(outstations.get(i).getAnalogInputChangeHandler());
            getSlaves().get(i).getDatabase().getAnalogOutputs().removePropertyChangeListener(outstations.get(i).getAnalogOutputChangeHandler());
            getSlaves().get(i).getDatabase().getCounters().removePropertyChangeListener(outstations.get(i).getCounterChangeHandler());
            getSlaves().get(i).getDatabase().getDigitalInputs().removePropertyChangeListener(outstations.get(i).getDigitalInputChangeHandler());
            getSlaves().get(i).getDatabase().getDigitalOutputs().removePropertyChangeListener(outstations.get(i).getDigitalOutputChangeHandler());
            getSlaves().get(i).getDatabase().getDoubleDigitals().removePropertyChangeListener(outstations.get(i).getDoubleDigitalChangeHandler());
            getSlaves().get(i).getDatabase().getFrozenCounters().removePropertyChangeListener(outstations.get(i).getFrozenCounterChangeHandler());
            outstations.get(i).getOutstation().shutdown();
        }

        outstations.clear();

        outstationDataModels.clear();

    }

    public void updateChangeSet(EventMode eventMode) {

        for (int i = 0; i < getSlaves().size(); i++) {
            OutstationDevice monitor = getSlaves().get(i);
            OutstationChangeHandler change = outstations.get(i);
            change.update(monitor.getDatabase(), eventMode);
        }

    }

    public void scanEventClasses() {

        for (int i = 0; i < getMasters().size(); i++) {
            Master master = masters.get(i);
            master.scan(Header.getEventClasses(),getMasters().get(i));
        }

    }

    public void scanAll() {

        for (int i = 0; i < getMasters().size(); i++) {
            Master master = masters.get(i);
            master.scan(Header.getIntegrity(),getMasters().get(i));
        }

    }

}
