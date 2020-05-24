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



import com.automatak.dnp3.enums.OperateType;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.xml.bind.annotation.XmlElement;



/**

 *

 * @author KR INDUSTRIAL IT

 */

public class AnalogCommandConfig {



    private OperateType operateType = OperateType.DirectOperate;



    private CommandDataType dataType = CommandDataType.FLOAT;



    @XmlElement

    public OperateType getOperateType() {

        return operateType;

    }



    public void setOperateType(OperateType newValue) {

        OperateType oldValue=this.operateType;

        this.operateType = newValue;

        pcs.firePropertyChange("operateType", oldValue, newValue);

    }



    @XmlElement

    public CommandDataType getDataType() {

        return dataType;

    }



    public void setDataType(CommandDataType newValue) {

        CommandDataType oldValue=this.dataType;

        this.dataType = newValue;

        pcs.firePropertyChange("dataType", oldValue, newValue);

    }



    private final PropertyChangeSupport pcs = new PropertyChangeSupport(AnalogCommandConfig.this);



    public void addPropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.addPropertyChangeListener(listener);

    }



    public void removePropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.removePropertyChangeListener(listener);

    }



}

