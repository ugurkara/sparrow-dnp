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


import com.automatak.dnp3.ApplicationIIN;
import com.automatak.dnp3.DNPTime;
import com.automatak.dnp3.OutstationApplication;
import com.automatak.dnp3.enums.AssignClassType;
import com.automatak.dnp3.enums.LinkStatus;
import com.automatak.dnp3.enums.PointClass;
import com.automatak.dnp3.enums.RestartMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.logging.Logger;

/**
 *
 * @author KR INDUSTRIAL IT
 */
public class BaseOutstationApplication implements OutstationApplication {

    private final static Logger logger = Logger.getLogger(BaseOutstationApplication.class.getName());
    private boolean supportsWriteAbsoluteTime = true;
    
    private ZoneId zoneId = ZoneId.ofOffset("", ZoneOffset.UTC);
    private LocalDateTime localDateTime = LocalDateTime.now();

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(int offset) {
        zoneId = ZoneId.ofOffset("", ZoneOffset.ofHours(offset));
    }

    public void supportsWriteAbsoluteTimeEnable() {
        supportsWriteAbsoluteTime = true;
    }

    public void enableSupportsWriteAbsoluteTime() {
        this.supportsWriteAbsoluteTime = true;
    }

    @Override
    public boolean supportsWriteAbsoluteTime() {
        return true;
    }

    @Override
    public boolean writeAbsoluteTime(long msSinceEpoch) {
        localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(msSinceEpoch), zoneId);
        logger.info("absolute LocalDateTime=" + localDateTime);
        supportsWriteAbsoluteTime = false;
        return true;
    }


    @Override
    public boolean supportsAssignClass() {
        return false;
    }

    @Override
    public void recordClassAssignment(AssignClassType type, PointClass clazz, int start, int stop) {
    }

    @Override
    public ApplicationIIN getApplicationIIN() {
        return new ApplicationIIN(supportsWriteAbsoluteTime, false, false, false);
    }

    @Override
    public RestartMode coldRestartSupport() {
        return RestartMode.UNSUPPORTED;
    }

    @Override
    public RestartMode warmRestartSupport() {
        return RestartMode.UNSUPPORTED;
    }

    @Override
    public int coldRestart() {
        return 65535;
    }

    @Override
    public int warmRestart() {
        return 65535;
    }

    @Override
    public void onStateChange(LinkStatus value) {
    }

    @Override
    public void onKeepAliveInitiated() {
    }
    
    @Override
    public void onKeepAliveFailure() {
    }
    
    @Override
    public void onKeepAliveSuccess() {
    }

    @Override
    public void onConfirmProcessed(boolean isUnsolicited, long numClass1, long numClass2, long numClass3) {
        //TODO
    }

    @Override
    public DNPTime now() {
        return new DNPTime(System.currentTimeMillis());
    }
}


