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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;



/**

 *

 *

 *

 * @author ugurkara

 *

 */

public abstract class BaseChannelConfig {



    private static int portIndex = 1;

    private String name = "Channel" + portIndex++;



    @XmlElement

    public String getName() {

        return name;

    }



    public void setName(String name) {

        String oldValue = this.name;

        this.name = name;

        this.pcs.firePropertyChange("name", oldValue, name);

    }

    

    

    

    

    

    



    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);



    public void addPropertyChangeListener(PropertyChangeListener listener) {

        pcs.addPropertyChangeListener(listener);

    }



    public void removePropertyChangeListener(PropertyChangeListener listener) {

        pcs.removePropertyChangeListener(listener);

    }

    

    

     @XmlElement(name = "slave")

    private final ArrayList<SlaveDeviceConfig> slaves = new ArrayList<>();



    public ArrayList<SlaveDeviceConfig> getSlaves() {

        return slaves;

    }



    @XmlElement(name = "master")

    private final ArrayList<MasterDeviceConfig> masters = new ArrayList<>();



    public ArrayList<MasterDeviceConfig> getMasters() {

        return masters;

    }

    

    public abstract void saveToXml(File file) throws IOException;

    

    public  abstract void saveToXml(OutputStream os) throws IOException;

    



}

