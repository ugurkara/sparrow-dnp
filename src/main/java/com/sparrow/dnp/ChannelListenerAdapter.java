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

import com.automatak.dnp3.ChannelListener;
import com.automatak.dnp3.enums.ChannelState;
import java.util.ArrayList;

/**
 *
 * @author KR INDUSTRIAL IT
 */
public class ChannelListenerAdapter implements ChannelListener {

    final ArrayList<ConnectionListener> listeners = new ArrayList<>();

    @Override
    public void onStateChange(ChannelState state) {

        switch (state) {
            case CLOSED:
                fireClosed();
                break;
            case OPEN:
                fireOpen();
                break;
            case OPENING:
                fireOpening();
                break;
            case SHUTDOWN:
                fireShutdown();
                break;
        }

    }

    protected void fireShutdown() {
        listeners.forEach((ConnectionListener cl) -> {
            cl.stateChanged(ConnectionState.SHUTDOWN);
        });
    }

    protected void fireOpening() {
        listeners.forEach((ConnectionListener cl) -> {
            cl.stateChanged(ConnectionState.OPENING);
        });
    }

    protected void fireClosed() {
        listeners.forEach((ConnectionListener cl) -> {
            cl.stateChanged(ConnectionState.CLOSED);
        });
    }

    protected void fireOpen() {
        listeners.forEach((ConnectionListener cl) -> {
            cl.stateChanged(ConnectionState.OPEN);
        });
    }
    
    protected void fireStarted() {
        listeners.forEach((ConnectionListener cl) -> {
            cl.stateChanged(ConnectionState.STARTED);
        });
    }
    
    protected void fireStoped() {
        listeners.forEach((ConnectionListener cl) -> {
            cl.stateChanged(ConnectionState.STOPPED);
        });
    }

}
