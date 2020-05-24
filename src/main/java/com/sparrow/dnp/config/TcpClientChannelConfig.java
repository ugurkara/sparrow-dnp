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



/**

 *

 *

 *

 * @author ugurkara

 *

 */

@XmlRootElement(name = "dnp-tcp-client")
public class TcpClientChannelConfig extends TcpChannelConfig {



    public static TcpClientChannelConfig loadFromXml(File file) throws JAXBException, FileNotFoundException {
        return loadFromXml(new FileInputStream(file));
    }



    public static TcpClientChannelConfig loadFromXml(String file) throws JAXBException, FileNotFoundException {
        return loadFromXml(new File(file));
    }



    public static TcpClientChannelConfig newInstance() {

        TcpClientChannelConfig config = new TcpClientChannelConfig();
        MasterDeviceConfig masterConfig = new MasterDeviceConfig();
        config.getMasters().add(masterConfig);
        return config;

    }

    

    public static TcpClientChannelConfig loadFromDefault() throws JAXBException {

        InputStream is = TcpClientConnection.class.getResourceAsStream("DnpTcpClient.xml");
        return loadFromXml(is);

    }





    public static TcpClientChannelConfig loadFromXml(InputStream is) throws JAXBException {



        JAXBContext jaxbContext = JAXBContext.newInstance(TcpClientChannelConfig.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        TcpClientChannelConfig dnpSlaveConfig = (TcpClientChannelConfig) jaxbUnmarshaller.unmarshal(is);

        return dnpSlaveConfig;

    }



    public static void saveToXml(TcpClientChannelConfig model, OutputStream os) throws JAXBException {



        JAXBContext context = JAXBContext.newInstance(TcpClientChannelConfig.class);

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(model, os);

    }



    public static void saveToXml(TcpClientChannelConfig model, File os) throws JAXBException, FileNotFoundException {

        saveToXml(model, new FileOutputStream(os));

    }



    @Override

    public void saveToXml(OutputStream os) throws IOException {

        try {

            TcpClientChannelConfig.saveToXml(this, os);

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



    private String adapter = "0.0.0.0";



    @XmlElement()

    public String getAdapter() {

        return adapter;

    }



    public void setAdapter(String value) {

        String oldValue = this.adapter;

        this.adapter = value;

        this.pcs.firePropertyChange("adapter", oldValue, value);

    }



}

