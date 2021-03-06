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







import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;


public class SaveTcpClientChannelConfigTest {

    private static Logger logger=Logger.getLogger(SaveTcpClientChannelConfigTest.class.getName());

    @Test
    public void testCreateConfigFile() throws Exception {

        TcpClientChannelConfig config = new TcpClientChannelConfig();
        config.getMasters().add(DefaultFactory.createExampleMasterDeviceConfig());
        File file = new File(DefaultFactory.parentFile(), "DnpTcpClient.xml");
        logger.log(Level.INFO, "TcpClient Config file is saving to {0}", file.getAbsolutePath());
        config.saveToXml(file);
    }







   



}



