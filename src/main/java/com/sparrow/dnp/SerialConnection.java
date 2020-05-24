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
package com.sparrow.dnp;



import com.automatak.dnp3.Channel;
import com.automatak.dnp3.DNP3Exception;
import com.automatak.dnp3.DNP3Manager;
import com.automatak.dnp3.LogMasks;
import com.automatak.dnp3.SerialSettings;
import com.sparrow.dnp.config.SerialChannelConfig;



/**

 *

 *

 *

 * @author ugurkara

 *

 */

public class SerialConnection extends BaseConection {



    private final SerialChannelConfig config;



    public SerialConnection(SerialChannelConfig config) {

        super(config);

        this.config = config;

    }



    @Override

    protected Channel newChannel(DNP3Manager manager) throws DNP3Exception {



        SerialSettings serialSettings = new SerialSettings(

                config.getPort(), 

                config.getBaudRate(), 

                config.getDataBits(), 

                config.getParity(), 

                config.getStopBits(), 

                config.getFlowControl());

        

        Channel channel = manager.addSerial(

                config.getName(),

                LogMasks.NORMAL | LogMasks.APP_COMMS,

                config.getChannelRetry().build(), 

                serialSettings,

                getChannelListener());



        return channel;

    }





}

