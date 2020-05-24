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




import com.sparrow.dnp.TcpServerConnection;
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
import javax.xml.bind.annotation.XmlRootElement;



/**

 *

 *

 *

 * @author ugurkara

 *

 */

@XmlRootElement(name = "dnp-tcp-server")

public class TcpServerChannelConfig extends TcpChannelConfig {



    public static TcpServerChannelConfig loadFromXml(File file) throws JAXBException, FileNotFoundException {
        return loadFromXml(new FileInputStream(file));
    }



    public static TcpServerChannelConfig loadFromXml(String file) throws JAXBException, FileNotFoundException {
        return loadFromXml(new File(file));
    }



    public static TcpServerChannelConfig loadFromXml(InputStream is) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TcpServerChannelConfig.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        TcpServerChannelConfig dnpSlaveConfig = (TcpServerChannelConfig) jaxbUnmarshaller.unmarshal(is);
        return dnpSlaveConfig;

    }



    public static void saveToXml(TcpServerChannelConfig model, OutputStream os) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(TcpServerChannelConfig.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(model, os);
    }



    public static void saveToXml(TcpServerChannelConfig model, File os) throws JAXBException, FileNotFoundException {
        saveToXml(model, new FileOutputStream(os));
    }



    @Override

    public void saveToXml(OutputStream os) throws IOException {

        try {
            TcpServerChannelConfig.saveToXml(this, os);
        } catch (JAXBException ex) {
            throw new IOException(ex);
        }

    }



    @Override

    public void saveToXml(File os) throws IOException {
        saveToXml(new FileOutputStream(os));
    }

    

    public static TcpServerChannelConfig loadFromDefault() throws JAXBException {

        InputStream is = TcpServerConnection.class.getResourceAsStream("DnpTcpServer.xml");
        return loadFromXml(is);

    }



}

