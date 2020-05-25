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

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ugurkara
 *
 */
public abstract class TcpChannelConfig extends BaseChannelConfig {

    private String host = "127.0.0.1";

    private int port = 20000;

    @XmlElement()
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        String oldValue = this.host;
        this.host = host;
        this.pcs.firePropertyChange("host", oldValue, host);
    }

    @XmlElement
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        Integer oldValue = this.port;
        this.port = port;
        this.pcs.firePropertyChange("port", oldValue, port);
    }
}
