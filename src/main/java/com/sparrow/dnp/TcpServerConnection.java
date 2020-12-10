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
import com.automatak.dnp3.DNP3Manager;
import com.automatak.dnp3.IPEndpoint;
import com.automatak.dnp3.LogMasks;
import com.automatak.dnp3.enums.ServerAcceptMode;
import com.sparrow.dnp.config.TcpServerChannelConfig;

/**
 *
 * @author ugurkara
 *
 */
public class TcpServerConnection extends BaseConection {

    private final TcpServerChannelConfig config;

    public TcpServerConnection(TcpServerChannelConfig config) {
        super(config);
        this.config = config;
    }

    @Override
    protected Channel newChannel(DNP3Manager manager) throws Exception {

        Channel channel = manager.addTCPServer(
                config.getName(),
                LogMasks.NORMAL | LogMasks.APP_COMMS,
                ServerAcceptMode.CloseExisting,
                new IPEndpoint(config.getHost(), config.getPort()),
                getChannelListener()
        );

        return channel;

    }

}
