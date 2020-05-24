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



import com.automatak.dnp3.enums.FlowControl;
import com.automatak.dnp3.enums.Parity;
import com.sparrow.dnp.TcpClientConnection;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**

 *

 *

 *

 * @author ugurkara

 *

 */

@XmlRootElement(name = "dnp-serial")

@XmlType(propOrder = {"port", "baudRate", "dataBits", "parity", "stopBits", "flowControl", "channelRetry"})

public class SerialChannelConfig extends BaseChannelConfig {



    private String port = "/dev/random";

    private int baudRate = 9600;

    private int dataBits = 8;

    private Parity parity = Parity.None;

    private int stopBits = 1;

    private FlowControl flowControl = FlowControl.None;



    @XmlElement

    public int getBaudRate() {

        return baudRate;

    }



    public void setBaudRate(Integer newValue) {

        Integer oldValue = this.baudRate;

        this.baudRate = newValue;

        this.pcs.firePropertyChange("baudRate", oldValue, newValue);

    }



    @XmlElement

    public Integer getDataBits() {

        return dataBits;

    }



    public void setDataBits(Integer newValue) {

        Integer oldValue = this.dataBits;

        this.dataBits = newValue;

        this.pcs.firePropertyChange("dataBits", oldValue, newValue);

    }



    @XmlElement

    public FlowControl getFlowControl() {

        return flowControl;

    }



    public void setFlowControl(FlowControl newValue) {

        FlowControl oldValue = this.flowControl;

        this.flowControl = newValue;

        this.pcs.firePropertyChange("flowControl", oldValue, newValue);

    }



    @XmlElement

    public Parity getParity() {

        return parity;

    }



    public void setParity(Parity newValue) {

        Parity oldValue = this.parity;

        this.parity = newValue;

        this.pcs.firePropertyChange("parity", oldValue, newValue);

    }



    @XmlElement

    public Integer getStopBits() {

        return stopBits;

    }



    public void setStopBits(Integer newValue) {

        Integer oldValue = this.stopBits;

        this.stopBits = newValue;

        this.pcs.firePropertyChange("stopBits", oldValue, newValue);

    }



    @XmlElement

    public String getPort() {

        return port;

    }



    public void setPort(String newValue) {

        String oldValue = this.port;

        this.port = newValue;

        this.pcs.firePropertyChange("port", oldValue, newValue);

    }



    public static SerialChannelConfig loadFromXml(File file) throws JAXBException, FileNotFoundException {

        return loadFromXml(new FileInputStream(file));

    }



    public static SerialChannelConfig loadFromXml(String file) throws JAXBException, FileNotFoundException {

        return loadFromXml(new File(file));

    }



    public static SerialChannelConfig loadFromXml(InputStream is) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(SerialChannelConfig.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        SerialChannelConfig dnpSlaveConfig = (SerialChannelConfig) jaxbUnmarshaller.unmarshal(is);

        return dnpSlaveConfig;

    }



    public static void saveToXml(SerialChannelConfig model, OutputStream os) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(SerialChannelConfig.class);

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(model, os);

    }



    public static void saveToXml(SerialChannelConfig model, File os) throws JAXBException, FileNotFoundException {

        saveToXml(model, new FileOutputStream(os));

    }



    @Override

    public void saveToXml(OutputStream os) throws IOException {

        try {

            SerialChannelConfig.saveToXml(this, os);

        } catch (JAXBException ex) {

            throw new IOException(ex);

        }

    }



    @Override

    public void saveToXml(File os) throws IOException {

        saveToXml(new FileOutputStream(os));

    }



    private ChannelRetryConfig channelRetryConfig = new ChannelRetryConfig();



    @XmlElement(name = "reconnect")

    public ChannelRetryConfig getChannelRetry() {

        return channelRetryConfig;

    }



    public void setChannelRetry(ChannelRetryConfig channelRetryConfig) {

        this.channelRetryConfig = channelRetryConfig;

    }



    @Override

    public void addPropertyChangeListener(PropertyChangeListener listener) {

        super.addPropertyChangeListener(listener);

        this.channelRetryConfig.addPropertyChangeListener(listener);

    }



    @Override

    public void removePropertyChangeListener(PropertyChangeListener listener) {

        super.removePropertyChangeListener(listener);

        this.channelRetryConfig.removePropertyChangeListener(listener);

    }

    

    

    public static SerialChannelConfig loadFromDefault() throws JAXBException {

        InputStream is = TcpClientConnection.class.getResourceAsStream("resources/DnpSerial.xml");

        return loadFromXml(is);

    }



}

